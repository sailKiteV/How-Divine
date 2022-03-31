package main;

import java.util.HashMap;
import java.util.Map;

public class ProbabilityCalculator {

    public ProbabilityCalculator() {

    }

    public double rollAtLeast( int target, RollHandler rollHandler ) {
        double result = 0;
        Map<Integer, Integer> ranges = new HashMap<>();

        int fireMin = rollHandler.getModifier( "fire" ).getRange( "range" ).getMinimum();
        int fireMax = rollHandler.getModifier( "fire" ).getRange( "range" ).getMaximum();
        int coldMin = rollHandler.getModifier( "cold" ).getRange( "range" ).getMinimum();
        int coldMax = rollHandler.getModifier( "cold" ).getRange( "range" ).getMaximum();
        int lightMin = rollHandler.getModifier( "light" ).getRange( "range" ).getMinimum();
        int lightMax = rollHandler.getModifier( "light" ).getRange( "range" ).getMaximum();

        int temp = 0;
        for ( int i = fireMin; i < fireMax + 1; i++ ) {
            for ( int j = coldMin; j < coldMax + 1; j++ ) {
                for ( int k = lightMin; k < lightMax + 1; k++ ) {
                    temp = ranges.containsKey( i+j+k )
                            ? ranges.get( i+j+k ) + 1
                            : 1;
                    ranges.put( i+j+k, temp );
                }
            }
        }

        temp = 0;
        for( Integer key : ranges.keySet() ) {
            if( key < target ) {
                result += ranges.get( key );
            }
            temp += ranges.get( key );
        }

        return 1 - result / temp;
    }
}
