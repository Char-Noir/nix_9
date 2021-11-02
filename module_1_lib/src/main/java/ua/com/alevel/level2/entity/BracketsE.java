package ua.com.alevel.level2.entity;

public enum BracketsE {
    Parentheses('(', ')'),
    BoxBrackets('[', ']'),
    Braces('{', '}'),
    AngleBrackets('<', '>');
    private final char openBracket;
    private final char closeBracket;

    BracketsE(char openBracket, char closeBracket) {
        this.openBracket = openBracket;
        this.closeBracket = closeBracket;
    }

    public static boolean isSameBracket(char openBracket, char closeBracket) {
        return switch (openBracket) {
            case '{' -> (closeBracket == '}');
            case '(' -> (closeBracket == ')');
            case '[' -> (closeBracket == ']');
            case '<' -> (closeBracket == '>');
            default -> false;
        };
    }

    public char getOpenChar() {
        return this.openBracket;
    }

    public char getCloseChar() {
        return this.closeBracket;
    }
}
