package ua.com.alevel.util;

import org.apache.commons.lang3.NotImplementedException;
import ua.com.alevel.persistence.entity.doctor.Category;
import ua.com.alevel.persistence.entity.doctor.Specialization;
import ua.com.alevel.persistence.entity.doctor.Types;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public final class EnumsNamesConverterUtil {

    public static String getString(Types type) {
        String name = type.getName();
        String bundle = "";
        if (type instanceof Category) {
            bundle = "category";
        } else if (type instanceof Specialization) {
            bundle = "specialization";
        } else {
            throw new NotImplementedException();
        }
        Locale locale_ru_RU = new Locale("ru", "RU");
        ResourceBundle resourceBundle = ResourceBundle.getBundle(bundle, locale_ru_RU);
        try {
            return resourceBundle.getString(name);
        } catch (MissingResourceException e) {
            return name;
        }

    }
}
