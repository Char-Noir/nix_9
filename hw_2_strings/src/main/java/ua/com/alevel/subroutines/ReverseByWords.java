package ua.com.alevel.subroutines;

        import ua.com.alevel.ReverseString;
        import ua.com.alevel.console.ConsoleSubroutine;

        import java.io.BufferedReader;

public class ReverseByWords extends ConsoleSubroutine {

    public final String LONG_DESCRIPTION = "Возвращает строку, которая является обратной входной по словам. Ex: \"Hello world\" -> \"olleH dlrow\"";
    public final String SHORT_DESCRIPTION = "Просто реверсирует строку по словам.";

    @Override
    public String getShortDescription() {
        return SHORT_DESCRIPTION;
    }

    @Override
    public String getLongDescription() {
        return LONG_DESCRIPTION;
    }

    @Override
    public void run(BufferedReader br) {
        String normal = "";
        String reversed = "";

        try {
            normal = br.readLine();
            reversed = ReverseString.reverse(normal,true);
        } catch (Exception e) {
            System.err.println(ERROR_MESSAGE);
            e.getStackTrace();
        }

        System.out.println(new StringBuilder("Результат задания: ").append(normal).append(" -> ").append(reversed).append("\n"));
    }
}
