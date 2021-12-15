package ua.com.alevel.subroutines;

        import ua.com.alevel.ExceptionsMain;
        import ua.com.alevel.MoralDate;
        import ua.com.alevel.console.ConsoleSubroutine;
        import ua.com.alevel.enums.DateFormatEnum;
        import ua.com.alevel.impl.MoralDateImpl;
        import ua.com.alevel.util.ConsoleDateUtil;

        import java.io.BufferedReader;
        import java.io.IOException;

public class DateSubTime extends ConsoleSubroutine {

    public final String LONG_DESCRIPTION = "Позволяет отнять от даты указанную единицу времени";
    public final String SHORT_DESCRIPTION = "Отнять";

    public final String EXPECTED_INPUT = "";

    @Override
    public String getShortDescription() {
        return SHORT_DESCRIPTION;
    }

    @Override
    public String getLongDescription() {
        return LONG_DESCRIPTION;
    }

    @Override
    public String getExpectedInput() {
        return EXPECTED_INPUT;
    }

    @Override
    public void run(BufferedReader bufferedReader) {
        try {
            System.out.println("Введите дату!");
            System.out.println("Формат:");
            System.out.println(ExceptionsMain.inputFormat);
            String date1 = bufferedReader.readLine();
            MoralDate m1;
            m1 = new MoralDateImpl(date1, ExceptionsMain.inputFormat);
            System.out.println(m1.toString(ExceptionsMain.outputFormat));
            DateFormatEnum comp = ConsoleDateUtil.readDateFormat(bufferedReader);
            System.out.println("Сколько вы хотите добавить");
            long time = Long.parseLong(bufferedReader.readLine());
            System.out.println(time);
            m1.sub(comp, time, true);
            System.out.println("Новая дата" + m1.toString(ExceptionsMain.outputFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
