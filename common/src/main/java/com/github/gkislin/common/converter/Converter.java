package com.github.gkislin.common.converter;

/**
 * User: gkislin
 * Date: 16.09.13
 */
public interface Converter<S,T> {
    T convert(S source) throws Exception;
}
