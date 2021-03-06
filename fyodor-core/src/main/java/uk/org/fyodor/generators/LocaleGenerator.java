package uk.org.fyodor.generators;

import java.util.Locale;

public class LocaleGenerator implements Generator<Locale> {

    private final Generator<Locale> localeGenerator = RDG.value(Locale.getAvailableLocales());

    public Locale next() {
        return localeGenerator.next();
    }
}
