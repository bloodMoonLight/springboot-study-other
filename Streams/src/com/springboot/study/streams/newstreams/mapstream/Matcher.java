package com.springboot.study.streams.newstreams.mapstream;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface Matcher extends BiPredicate<Stream<Integer> , Predicate<Integer>> {
}
