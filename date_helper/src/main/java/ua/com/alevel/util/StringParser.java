package ua.com.alevel.util;

import ua.com.alevel.enums.DateComponent;
import ua.com.alevel.enums.MonthsEnum;
import ua.com.alevel.map.MapEntry;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParser {

    private static final Pattern yy = Pattern.compile("yy");
    private static final Pattern yyyy = Pattern.compile("yyyy");
    private static final Pattern MM = Pattern.compile("MM");
    private static final Pattern MMM = Pattern.compile("MMM");
    private static final Pattern dd = Pattern.compile("dd");
    private static final Pattern hh = Pattern.compile("hh");
    private static final Pattern mm = Pattern.compile("mm");
    private static final Pattern ss = Pattern.compile("ss");
    private static final Pattern SSS = Pattern.compile("SSS");

    public static Map<DateComponent, Long> parseDate(String s) {
        Map<DateComponent, Long> map = DateSetUtil.getMapFromValues(0, 1, 1, 0, 0, 0, 0);
        String[] parts = s.split(" ");
        if (parts.length > 2 || parts.length < 1) {
            throw new RuntimeException("Wrong format. Try to use format string");
        }
        if (parts[0].contains(":") || (parts.length == 2 && parts[1].contains("/"))) {
            throw new RuntimeException("Wrong format. Try to use format string");
        }
        String[] date = parts[0].split("/");
        if (date.length > 3 || date.length < 1) {
            throw new RuntimeException("Wrong format. Try to use format string");
        }
        date = ArrayHelper.invert(date, String.class);
        switch (date.length) {
            case 3:
                if (!Objects.equals(date[2], "")) {
                    map.put(DateComponent.DAY, Long.parseLong(date[2]));
                }
            case 2:
                if (!Objects.equals(date[1], "")) {
                    map.put(DateComponent.MONTH, Long.parseLong(date[1]));
                }
            case 1:
                if (!Objects.equals(date[0], "")) {
                    map.put(DateComponent.YEAR, Long.parseLong(date[0]));
                }
        }
        if (parts.length == 2) {
            String[] time = parts[1].split(":");
            if (time.length < 1 || time.length > 4) {
                throw new RuntimeException("Wrong format. Try to use format string");
            }
            switch (time.length) {
                case 4:
                    if (!Objects.equals(time[3], "")) {
                        map.put(DateComponent.MILLISECOND, Long.parseLong(time[3].substring(0, 3)));
                    }
                case 3:
                    if (!Objects.equals(time[2], "")) {
                        map.put(DateComponent.SECOND, Long.parseLong(time[2]));
                    }
                case 2:
                    if (!Objects.equals(time[1], "")) {
                        map.put(DateComponent.MINUTE, Long.parseLong(time[1]));
                    }
                case 1:
                    if (!Objects.equals(time[0], "")) {
                        map.put(DateComponent.HOUR, Long.parseLong(time[0]));
                    }
            }
        }
        return map;
    }

    public static Map<DateComponent, Long> parseDate(String s, String format) {
        Map<DateComponent, Long> map = DateSetUtil.getMapFromValues(0, 1, 1, 0, 0, 0, 0);
        boolean isStringMonth = false;
        String replaced = format
                .replace(dd.pattern(), "([0-9]{0,2})")
                .replace(hh.pattern(), "([0-9]{0,2})")
                .replace(mm.pattern(), "([0-9]{0,2})")
                .replace(ss.pattern(), "([0-9]{0,2})")
                .replace(SSS.pattern(), "([0-9]{0,3})")
                .replace("/", "\\/");
        if (replaced.contains(yyyy.pattern())) {
            replaced = replaced.replace(yyyy.pattern(), "([0-9]{4})");
        } else if (replaced.contains(yy.pattern())) {
            replaced = replaced.replace(yy.pattern(), "([0-9]{2})");
        }
        if (replaced.contains(MMM.pattern())) {
            replaced = replaced.replace(MMM.pattern(), "([A-z].+)");
        } else if (replaced.contains(MM.pattern())) {
            replaced = replaced.replace(MM.pattern(), "([0-9]{1,2})");
        }
        Pattern pattern = Pattern.compile(replaced);
        Map<Integer, DateComponent> order = new TreeMap<>();
        Matcher matcher = yy.matcher(format);
        if (matcher.find()) {
            order.put(matcher.start(), DateComponent.YEAR);
        }
        matcher = yyyy.matcher(format);
        if (matcher.find()) {
            order.put(matcher.start(), DateComponent.YEAR);
        }
        matcher = MM.matcher(format);
        if (matcher.find()) {
            order.put(matcher.start(), DateComponent.MONTH);
        }
        matcher = MMM.matcher(format);
        if (matcher.find()) {
            isStringMonth = true;
            order.put(matcher.start(), DateComponent.MONTH);
        }
        matcher = dd.matcher(format);
        if (matcher.find()) {
            order.put(matcher.start(), DateComponent.DAY);
        }
        matcher = hh.matcher(format);
        if (matcher.find()) {
            order.put(matcher.start(), DateComponent.HOUR);
        }
        matcher = mm.matcher(format);
        if (matcher.find()) {
            order.put(matcher.start(), DateComponent.MINUTE);
        }
        matcher = ss.matcher(format);
        if (matcher.find()) {
            order.put(matcher.start(), DateComponent.SECOND);
        }
        matcher = SSS.matcher(format);
        if (matcher.find()) {
            order.put(matcher.start(), DateComponent.MILLISECOND);
        }
        Matcher main = pattern.matcher(s);
        Map<Integer, DateComponent> orderNorm = new TreeMap<>();
        int counter = 1;
        for (Integer name : order.keySet()) {
            DateComponent url = order.get(name);
            orderNorm.put(counter, url);
            counter++;
        }
        main.find();
        DateComponent url ;
        for (Integer name : orderNorm.keySet()) {
            try {
                url = orderNorm.get(name);
                String var = main.group(name);
                if (isStringMonth && url == DateComponent.MONTH) {
                    long month = MonthsEnum.getOrderByName(var);
                    map.put(url, month);
                    continue;
                }
                map.put(url, Long.parseLong(var));
            } catch (Exception ignored) {
            }
        }
        return map;
    }

    public static String toFormatString(Map<DateComponent, Long> map, String format) {
        String replaced = format
                .replace(dd.pattern(), map.get(DateComponent.DAY).toString())
                .replace(hh.pattern(), map.get(DateComponent.HOUR).toString())
                .replace(mm.pattern(), map.get(DateComponent.MINUTE).toString())
                .replace(ss.pattern(), map.get(DateComponent.SECOND).toString())
                .replace(SSS.pattern(), map.get(DateComponent.MILLISECOND).toString());
        if (replaced.contains(yyyy.pattern())) {
            replaced = replaced.replace(yyyy.pattern(), map.get(DateComponent.YEAR).toString());
        } else if (replaced.contains(yy.pattern())) {
            replaced = replaced.replace(yy.pattern(), map.get(DateComponent.YEAR).toString());
        }
        if (replaced.contains(MMM.pattern())) {
            replaced = replaced.replace(MMM.pattern(), MonthsEnum.getMonthByOrder(map.get(DateComponent.MONTH)).name());
        } else if (replaced.contains(MM.pattern())) {
            replaced = replaced.replace(MM.pattern(), map.get(DateComponent.MONTH).toString());
        }
        return replaced;
    }
}
