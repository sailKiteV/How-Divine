package main;

import java.util.HashMap;
import java.util.Map;

public class ProbabilityCalculator {

    public ProbabilityCalculator() {

    }

    public double rollAtLeast( int target, RollHandler rollHandler ) {
        double result = 0;
        Map<Double, Integer> avgHitRanges = new HashMap<>();
        Map<Double, Integer> fireRanges = new HashMap<>();
        Map<Double, Integer> coldRanges = new HashMap<>();
        Map<Double, Integer> lightRanges = new HashMap<>();

        populateMap( fireRanges, rollHandler, "fire" );
        populateMap( coldRanges, rollHandler, "cold" );
        populateMap( lightRanges, rollHandler, "light" );

        double sum;
        int tempInt = 0;
        for( Double fireKey : fireRanges.keySet() ) {
            for( Double coldKey : coldRanges.keySet() ){
                for( Double lightKey: lightRanges.keySet() ) {
                    sum = fireKey + coldKey + lightKey;
                    tempInt = fireRanges.get( fireKey )
                            * coldRanges.get( coldKey )
                            * lightRanges.get( lightKey );
                    avgHitRanges.put( sum, tempInt );
                }
            }
        }

        tempInt = 0;
        for( Double key : avgHitRanges.keySet() ) {
            if( key < target ) {
                result += avgHitRanges.get( key );
            }
            tempInt += avgHitRanges.get( key );
        }

        return 1 - result / tempInt;
    }

    private void populateMap( Map<Double, Integer> map, RollHandler handler, String modifier ) {
        int min1 = handler.getModifier( modifier ).getRange( "lower" ).getMinimum();
        int min2 = handler.getModifier( modifier ).getRange( "lower" ).getMaximum();
        int max1 = handler.getModifier( modifier ).getRange( "upper" ).getMinimum();
        int max2 = handler.getModifier( modifier ).getRange( "upper" ).getMaximum();

        int tempInt = 0;
        double tempDouble = 0;
        for ( int a = max1; a < max2 + 1; a++ ) {
            for ( int b = min1; b < min2 + 1; b++ ) {
                tempDouble = (double)(a+b)/2;
                tempInt = map.containsKey( tempDouble )
                        ? map.get( tempDouble ) + 1
                        : 1;
                map.put( tempDouble, tempInt );
            }
        }
    }
}
