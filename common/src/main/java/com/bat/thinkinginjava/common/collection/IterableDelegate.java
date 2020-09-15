package com.bat.thinkinginjava.common.collection;

import java.util.Iterator;
import java.util.Spliterator;

/**
 * {@link Iterable} API 执行
 *
 * @author ZhengYu
 * @version 1.0 2020/9/9 14:31
 **/
public class IterableDelegate {

    public static <T> void iterableApiCall(Iterable<T> iterable) {
        // Iterable API

        System.out.println("################## Iterator#iterator ##################");
        Iterator<T> iterator = iterable.iterator();
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        iterator.remove();
        iterator.forEachRemaining(System.out::println);

        System.out.println("################## Iterable#forEach ##################");
        iterable.forEach(System.out::println);

        System.out.println("################## Iterable#spliterator ##################");
        Spliterator<T> spliterator = iterable.spliterator();
        spliterator.tryAdvance(System.out::println);
        spliterator.trySplit();
        System.out.println(spliterator.estimateSize());
        System.out.println(spliterator.characteristics());
    }
}
