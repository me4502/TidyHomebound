package com.me4502.tidyhomebound.util;

/**
 * Very simple polyfill of the Consumer class, due to lack of MobiVM support.
 *
 * @param <T>
 */
public interface Consumer<T> {

    void accept(T t);
}
