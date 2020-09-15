package com.bat.collection.list.list_02_linkedlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * {@link LinkedList} API
 *
 * @author ZhengYu
 * @version 1.0 2020/9/12 17:38
 **/
public class LinkedListApi {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("111");

        ListIterator<String> stringListIterator = list.listIterator();

        System.out.println(stringListIterator.hasPrevious());

        System.out.println(stringListIterator.previous());
        System.out.println(stringListIterator.previous());
    }
}
