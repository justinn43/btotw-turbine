package btotw_turbine.text;

/**
 * Ansi styles
 */
public enum Decoration {
    BOLD(1),
    FAINT(2),
    ITALIC(3),
    UNDERLINE(4),
    CROSSED_OUT(9);

    private final int code;

    private Decoration(int code) {
        this.code = code;
    }

    public int code() {
        return this.code;
    }
}
