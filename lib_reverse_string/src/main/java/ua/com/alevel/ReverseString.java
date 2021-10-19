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

    public static String reverse(String src, String dest) {
        int index = src.indexOf(dest);
        if (index == -1) {
            return src;
        }
        String reverse = reverse(dest);
        return src.replace(dest, reverse);
    }

    public static String reverse(String src, int firstIndex, int lastIndex) {
        return reverse(src, firstIndex, lastIndex, false);
    }

    public static String reverse(String src, int firstIndex, int lastIndex, boolean byWords) {
        String dest = src.substring(firstIndex, lastIndex);
        String reverseDest = reverse(dest, byWords);
        StringBuilder buf = new StringBuilder(src);

        return buf.replace(firstIndex, lastIndex, reverseDest).toString();
    }
}
