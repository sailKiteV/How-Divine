package main;

import java.util.HashMap;
import java.util.Map;

public class Modifier {
    private final Map<String, RollRange> rangeMap = new HashMap<>();

    public Modifier() {

    }

    public void addRange( String name, int rangeMin, int rangeMax ) {
        this.rangeMap.put( name, new RollRange( rangeMin, rangeMax ));
    }

    public void removeRange( String name ) {
        this.rangeMap.remove( name );
    }

    public RollRange getRange( String name ) {
        return this.rangeMap.get( name );
    }
}
