package aos.format.date;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeReader {

    private final Map<String, Long> units = new HashMap<String, Long>();

    private static final String UNIT_PATTERN = "\\w+";
    private static final Pattern ITEM_PATTERN = Pattern.compile("(\\d+)\\s*(" + UNIT_PATTERN + ")");

    public static void main(final String[] args) {
        final TimeReader timeReader = new TimeReader().addUnit("h", 3600000l).addUnit("m", 60000l).addUnit("s", 1000l);

        System.out.println(timeReader.parse("3h, 2m   25  s"));
    }

    /**
     * Add a new time unit.
     * 
     * @param unit
     *            the unit, e.g. "s"
     * @param value
     *            the unit's modifier value (multiplier from milliseconds, e.g.
     *            1000)
     * @return self reference for chaining
     */
    public TimeReader addUnit(final String unit, final long value) {
        if (value < 0 || !unit.matches(UNIT_PATTERN)) {
            throw new IllegalArgumentException();
        }
        units.put(unit, Long.valueOf(value));
        return this;
    }

    /**
     * Parse a string using the defined units.
     * 
     * @return the resulting number of milliseconds
     */
    public long parse(final String input) {
        long value = 0l;
        final Matcher matcher = ITEM_PATTERN.matcher(input);
        while (matcher.find()) {
            final long modifier = Long.parseLong(matcher.group(1));
            final String unit = matcher.group(2);
            if (!units.containsKey(unit)) {
                throw new IllegalArgumentException("Unrecognized token: " + unit);
            }
            value += units.get(unit).longValue() * modifier;
        }
        return value;
    }

}
