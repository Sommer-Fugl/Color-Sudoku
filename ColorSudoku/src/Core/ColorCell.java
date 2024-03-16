package Core;

public enum ColorCell {
    YELLOW, RED, GREEN, BLUE, GRAY, CYAN, WHITE, BLACK, Purple;

    public static ColorCell getColor(int colorCode) {
        for (ColorCell c : ColorCell.values()) {
            if (c.ordinal() == colorCode) {
                return c;
            }
        }
        return null;
    }

    public static String colorHandler(int colorOrdinal){
        return switch (colorOrdinal) {
            case (0) -> "\u001B[43m";
            case (1) -> "\u001B[41m";
            case (2) -> "\u001B[42m";
            case (3) -> "\u001B[44m";
            case (4) -> "\u001B[100m";
            case (5) -> "\u001B[46m";
            case (6) -> "\u001B[107m";
            case (7) -> "\u001B[40m";
            case (8) -> "\u001B[105m";
            default -> null;
        };
    }
}