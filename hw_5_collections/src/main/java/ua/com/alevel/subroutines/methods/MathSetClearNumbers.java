package ua.com.alevel.subroutines.methods;

import ua.com.alevel.MathSetHelper;
import ua.com.alevel.MathSetUsabilityMain;
import ua.com.alevel.console.ConsoleSubroutine;

import java.io.BufferedReader;

public class MathSetClearNumbers extends ConsoleSubroutine {
    public final String LONG_DESCRIPTION = "Очищает множество с новыми значениями";
    public final String SHORT_DESCRIPTION = "Очистка с числами";

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
        if (MathSetUsabilityMain.mathSet == null) {
            System.out.println("Для начала вызовете один из конструкторов");
            return;
        }
        try {
            String str = bufferedReader.readLine();
            Number[] number = MathSetHelper.stringToNumberArray(str);
            System.out.println("Ваши числа:");
            for (Number numbe : number) {
                System.out.println(numbe.toString());
            }
            MathSetUsabilityMain.mathSet.clear(number);
            System.out.println("Новое множество:\n" + MathSetUsabilityMain.mathSet.toString());
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
