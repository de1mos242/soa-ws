package com.github.gkislin.common.util;

import com.github.gkislin.common.LoggerWrapper;
import com.github.gkislin.common.Presentable;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: gkislin
 * Date: 23.01.14
 */
public class Util {
    private static final LoggerWrapper LOGGER = LoggerWrapper.get(Util.class);

    // Pattern for search ${key}
    // Keys must begin with letter ([a-zA-Z]) and contains any world cheracter (\w) or '.'
    static final Pattern REPLACE_PATTERN = Pattern.compile("\\x24\\x7B([a-zA-Z][\\w\\x2E].*?)\\x7D");

    static final Pattern WINDOWS_SLASH = Pattern.compile("\\", Pattern.LITERAL);

    @SuppressWarnings("unchecked")
    public static <K, T> Map<K, T> asMap(Object... parameters) {
        Map<K, T> result = new HashMap<K, T>();
        for (int i = 0; i < parameters.length; i = i + 2) {
            result.put((K) parameters[i], (T) parameters[i + 1]);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <E extends Enum, T> Map<E, T> asEnumMap(Class<E> clazz, Object... parameters) {
        Map<E, T> result = new EnumMap(clazz);
        for (int i = 0; i < parameters.length; i = i + 2) {
            result.put((E) parameters[i], (T) parameters[i + 1]);
        }
        return result;
    }

    public static String resolveReplacement(String str) {
        // resolve properties first
        str = resolveReplacement(str, (Map) System.getProperties());
        str = resolveReplacement(str, System.getenv());
        checkReplacement(str);
        return replaceWindowsSlash(str);
    }


    /**
     * Check for unresolved environment
     *
     * @param str
     * @return origin if all substitutions resolved
     */
    public static void checkReplacement(String str) {
        Matcher matcher = REPLACE_PATTERN.matcher(str);
        if (matcher.find()) {
            throw LOGGER.getIllegalArgumentException("Environment variable '" + matcher.group(1) + "' is not defined");
        }
    }

    // replace in str ${key} to value
    public static String resolveReplacement(String str, final Map<String, String> replacements) {
        return resolveReplacement(str, REPLACE_PATTERN, new Presentable<Matcher>() {
            @Override
            public String toString(Matcher matcher) {
                return replacements.get(matcher.group(1));
            }
        });
    }

    public static String resolveReplacement(String str, Pattern pattern, Presentable<Matcher> presenter) {
        Matcher matcher = pattern.matcher(str);
        boolean result = matcher.find();
        if (result) {
            StringBuffer sb = new StringBuffer();
            do {
                String value = presenter.toString(matcher);
                if (value != null) {
                    matcher.appendReplacement(sb, value);
                }
                result = matcher.find();
            } while (result);
            matcher.appendTail(sb);
            return sb.toString();
        }
        return str;
    }

    public static String replaceWindowsSlash(String path) {
        return WINDOWS_SLASH.matcher(path).replaceAll("/");
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
