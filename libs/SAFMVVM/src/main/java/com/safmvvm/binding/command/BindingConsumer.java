package com.safmvvm.binding.command;

/**
 * 一个参数的动作，比如点击事件的 v 参数
 */
public interface BindingConsumer<T> {
    void call(T t);
}