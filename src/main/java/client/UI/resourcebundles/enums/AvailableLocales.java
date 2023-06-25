package client.UI.resourcebundles.enums;

import client.UI.controllers.MainFormController;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public enum AvailableLocales {
    ENGLISH(new Locale("en", "US"), "english", ZoneId.of("US/Central")),
    RUSSIAN(new Locale("ru", "RU"), "russian", ZoneId.of("Europe/Moscow"));


    private Locale locale;

    private DateTimeFormatter dateTimeFormatter;

    private final String bundleObjectName;

    private final ZoneId zoneId;

    AvailableLocales(Locale locale, String bundleObjectName, ZoneId zoneId){
        this.locale = locale;
        this.bundleObjectName = bundleObjectName;
        this.zoneId = zoneId;
        this.dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).localizedBy(locale);
    }

    public Locale getLocale(){
        return locale;
    }

    public ZoneId getZoneID(){
        return zoneId;
    }

    @Override
    public String toString() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("client.UI.resourcebundles.availablelocalesbundles.AvailableLocalesRB", MainFormController.getCurrentLocale().get().getLocale());
        return resourceBundle.getString(bundleObjectName);
    }

    public DateTimeFormatter getDateTimeFormatter(){
        return dateTimeFormatter;
    }
}
