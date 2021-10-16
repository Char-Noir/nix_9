package ua.com.alevel;

public class ReverseString {
    public static String reverse(String src) {
        StringBuilder sb = new StringBuilder();
        for (int i = src.length() - 1; i >= 0; i--) {
            sb.append(src.charAt(i));
        }
        return sb.toString();
    }

    public static String reverse(String src, boolean byWords) {

        if (byWords) {
            String[] strings = src.split(" ");
            StringBuilder stringBuilder = new StringBuilder();

            for (String line :
                    strings) {
                stringBuilder.append(reverse(line)).append(" ");
            }

            return stringBuilder.toString();
        } else {
            return reverse(src);
        }
    }
}
