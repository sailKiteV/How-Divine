package main;

public class RollRange {
    private final int minimum, maximum;

    public RollRange( int minimum, int maximum ) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public int getMinimum() {
        return minimum;
    }

    public int getMaximum() {
        return maximum;
    }
}
