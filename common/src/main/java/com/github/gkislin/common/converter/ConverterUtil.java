package com.github.gkislin.common.converter;

import com.github.gkislin.common.ExceptionType;
import com.github.gkislin.common.LoggerWrapper;
import com.github.gkislin.common.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: gkislin
 * Date: 16.09.13
 */
public class ConverterUtil {
    private static final LoggerWrapper LOGGER = LoggerWrapper.get(ConverterUtil.class);

    public static <S, T> List<T> convert(List<S> list, Converter<S, T> converter, ExceptionType type) {
        if (Util.isNotEmpty(list)) {
            List<T> result = new ArrayList<>(list.size());
            try {
                for (S attach : list) {
                    result.add(converter.convert(attach));
                }
            } catch (Exception e) {
                throw LOGGER.getStateException("Ошибка преобразования", type, e);
            }
            return result;
        } else {
            return Collections.emptyList();
        }
    }
}
