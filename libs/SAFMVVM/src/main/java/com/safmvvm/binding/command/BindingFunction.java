package com.safmvvm.binding.command;

public interface BindingFunction<T, R> {

    R apply(T t);
}