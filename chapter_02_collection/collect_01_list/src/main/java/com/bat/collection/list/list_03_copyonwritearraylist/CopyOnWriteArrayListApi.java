package com.bat.collection.list.list_03_copyonwritearraylist;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * {@link CopyOnWriteArrayList} API
 *
 * @author ZhengYu
 * @version 1.0 2020/9/9 14:26
 **/
public class CopyOnWriteArrayListApi {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        copyOnWriteArrayList.add("str1");
        copyOnWriteArrayList.add("str2");

        copyOnWriteArrayList.get(10);

        copyOnWriteArrayList.remove(1);

        for (String s : copyOnWriteArrayList) {
            System.out.println(s);
        }
    }
}
