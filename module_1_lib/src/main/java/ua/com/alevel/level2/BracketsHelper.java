package ua.com.alevel.level2;

import ua.com.alevel.level2.entity.BracketsE;

import java.util.LinkedList;

public class BracketsHelper {
    public static boolean isStringHaveValidBrackets(String str) {
        LinkedList<Character> brackets = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            if (isOpenBracket(character)) {
                brackets.add(character);
            }
            if (isCloseBracket(character)) {
                char open = brackets.getLast();
                if (BracketsE.isSameBracket(open, character)) {
                    brackets.removeLast();
                } else {
                    return false;
                }
            }
        }
        return brackets.size() == 0;
    }

    public static boolean isOpenBracket(char bracket) {
        return
                (bracket == BracketsE.Parentheses.getOpenChar()
                        || bracket == BracketsE.BoxBrackets.getOpenChar()
                        || bracket == BracketsE.Braces.getOpenChar()
                        || bracket == BracketsE.AngleBrackets.getOpenChar());
    }

    public static boolean isCloseBracket(char bracket) {
        return
                (bracket == BracketsE.Parentheses.getCloseChar()
                        || bracket == BracketsE.BoxBrackets.getCloseChar()
                        || bracket == BracketsE.Braces.getCloseChar()
                        || bracket == BracketsE.AngleBrackets.getCloseChar());
    }
}