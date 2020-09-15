package com.bat.collection.map;

import java.util.HashMap;
import java.util.stream.IntStream;

/**
 * {@link HashMap} API
 *
 * @author ZhengYu
 * @version 1.0 2020/9/15 11:19
 **/
public class HashMapApi {
    public static void main(String[] args) {
        HashMap<Integer, Integer> hashMap = new HashMap<>(1);
        IntStream.range(0, 10).forEach(index -> hashMap.put(index, index));
        System.out.println(hashMap.size());
    }
}
