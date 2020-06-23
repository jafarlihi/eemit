package com.github.jafarlihi.eemit;

@FunctionalInterface
public interface Callback<T> {
    void call(String channel, T object);
}
