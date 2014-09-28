package uk.org.fyodor.generators;

import uk.org.fyodor.generators.characters.CharacterFilter;
import uk.org.fyodor.generators.characters.CharacterSetFilter;
import uk.org.fyodor.generators.characters.CharacterSetGenerator;
import uk.org.fyodor.generators.characters.CharacterSetRange;
import uk.org.fyodor.range.Range;

import java.util.Arrays;

import static uk.org.fyodor.random.RandomValuesProvider.randomValues;

class StringGenerator implements Generator<String> {

    private Range<Integer> range;
    private char[] charSet;

    StringGenerator(Integer length) {
        this(length, new CharacterSetGenerator(CharacterSetFilter.AllExceptDoubleQuotes, CharacterSetRange.defaultLatinBasic));
    }

    StringGenerator(Integer length, CharacterFilter filter) {
        this(length, new CharacterSetGenerator(filter, CharacterSetRange.defaultLatinBasic));
    }

    StringGenerator(Integer length, Range<Integer> range) {
        this(length, new CharacterSetGenerator(CharacterSetFilter.AllExceptDoubleQuotes, range));
    }

    StringGenerator(Integer length, CharacterSetGenerator characterSetGenerator) {
        this(length, characterSetGenerator.getCharset());
    }

    StringGenerator(Integer length, char[] charset) {
        this(Range.fixed(length), charset);
    }

    StringGenerator(Range<Integer> range) {
        this(range, new CharacterSetGenerator(CharacterSetFilter.AllExceptDoubleQuotes, CharacterSetRange.defaultLatinBasic));
    }

    StringGenerator(Range<Integer> range, CharacterFilter filter) {
        this(range, new CharacterSetGenerator(filter, CharacterSetRange.defaultLatinBasic));
    }

    StringGenerator(Range<Integer> stringRange, Range<Integer> charsetRange) {
        this(stringRange, new CharacterSetGenerator(CharacterSetFilter.AllExceptDoubleQuotes, charsetRange));
    }

    StringGenerator(Range<Integer> range, CharacterSetGenerator characterSetGenerator) {
        this(range, characterSetGenerator.getCharset());
    }

    StringGenerator(Integer length, String charset) {
        this(Range.fixed(length), charset.toCharArray());
    }

    StringGenerator(Range<Integer> range, char[] charset) {
        this.range = range;
        this.charSet = charset;
    }

    @Override
    public String next() {
        Integer length = RDG.integer(range).next();
        char[] ret = new char[length];
        for (int i = 0; i < length; i++) {
            ret[i] = charSet[randomValues().randomInteger(charSet.length - 1)];
        }
        return String.valueOf(ret);
    }

    public char[] getCharSet() {
        return Arrays.copyOf(charSet, charSet.length);
    }
}
