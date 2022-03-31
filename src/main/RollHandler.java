package main;

import java.util.HashMap;
import java.util.Map;

public class RollHandler {
    private final Map<String, Modifier> modifierMap = new HashMap<>();

    public RollHandler() {

    }

    public void addModifier( String name, Modifier modifier ) {
        this.modifierMap.put( name, modifier );
    }

    public Modifier getModifier( String name ) {
        return this.modifierMap.get( name );
    }

    public void removeModifier( String name ) {
        this.modifierMap.remove( name );
    }

    public Map<String, Modifier> getModifierMap() {
        return this.modifierMap;
    }
}
