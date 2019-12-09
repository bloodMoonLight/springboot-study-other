package com.lambda.study.zidingyiFunction;
@FunctionalInterface
public interface TriFunctional<T,U,V,R> {
    R applay(T t,U u,V v);

}
