package com.bat.collection.list.list_01_arraylist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

/**
 * {@link ArrayList} API
 *
 * @author ZhengYu
 * @version 1.0 2020/9/9 14:26
 **/
public class ArrayListApi {
    public static void main(String[] args) {
        // 关于 Iterator 的思考
        thinkingInIterator();

        // 关于 Spliterator 的思考
        thinkingInSpliterator();
    }

    /**
     * 关于 Iterator 的思考
     *
     * @author ZhengYu
     * @see ArrayList.Itr
     */
    private static void thinkingInIterator() {
        List<String> list = new ArrayList<String>(5) {{
            add("str1");
            add("str2");
            add("str3");
            add("str4");
            add("str5");
        }};

        // 1. 迭代器类是ArrayList的内部类Itr，公用elementData
        Iterator<String> iterator = list.iterator();

        list = null;
        System.gc();    // 这里iterator还在引用，故gc不会回收

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * @see ArrayList.ArrayListSpliterator
     */
    private static void thinkingInSpliterator() {
        List<String> list = new ArrayList<String>(5) {{
            add("str1");
            add("str2");
            add("str3");
            add("str4");
            add("str5");
        }};

        // 1. 迭代器类是 ArrayList 的内部类 ArrayListSpliterator，其成员变量 list 指向 ArrayList 的堆内存地址
        Spliterator<String> spliterator = list.spliterator();

        list = null;
        System.gc();    // 这里只是将当前方法内的引用滞空，堆内的 ArrayList 对象还有 spliterator 的成员变量引用

        do {

        } while (spliterator.tryAdvance(System.out::println));  // tryAdvance 会修改 index 值，新的 Spliterator 还是指向之前的 ArrayList 的堆内存地址

        Spliterator<String> spliterator1 = spliterator.trySplit();
        Spliterator<String> spliterator2 = spliterator.trySplit();
    }
}
