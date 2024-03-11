package Core;

public enum ColorCell {
    YELLOW, RED, GREEN, BLUE, GRAY, CYAN, BLACK, MAGENTA, Purple;

    public static ColorCell getColor(int colorCode) {
        for (ColorCell c : ColorCell.values()) {
            if (c.ordinal() == colorCode) {
                return c;
            }
        }
        return null;
    }
}