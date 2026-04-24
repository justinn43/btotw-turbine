package btotw_turbine;

import btotw_turbine.text.Color;

public enum Status {
    OKAY(Color.GREEN),
    DANGER(Color.RED);

    private final Color color;
    private Status(Color color) {
        this.color = color;
    }
    public Color color() {
        return this.color;
    }
    
}
