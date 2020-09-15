package com.bat.thinkinginjava.common.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试实体类
 *
 * @author ZhengYu
 * @version 1.0 2020/9/9 16:04
 **/
public class User {

    private Long id;

    public User(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static List<User> buildUserArrayList() {
        return new ArrayList<User>(1) {{
            add(new User(1L));
            add(new User(2L));
            add(new User(3L));
        }};
    }
}
