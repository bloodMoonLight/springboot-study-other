package com.springboot.study.streams.newstreams.study;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * 用于学习Stream流的实体类
 */
public class User implements Comparable<User>{

    /**
     * 年龄
     */
    private int age;
    /**
     * 姓名
     */
    private String name;
    /**
     * 密码
     */
    private String password;

    /**
     * 性别，0未知，1男，2女
     */
    private short gendar;

    /**
     * 是否已婚
     */
    private boolean hasMarried;

    public User() {
        super();
    }

    public User(int age, String name, String password, short gendar, boolean hasMarried) {
        super();
        this.age = age;
        this.name = name;
        this.password = password;
        this.gendar = gendar;
        this.hasMarried = hasMarried;
    }


    @Override
    public int compareTo(User o2) {
        // 首先比较年龄大小，因为年龄的区分度比较高
        User o1 = this;
        if (o1.age > o2.age) {
            return 1;
        }
        if (o1.age < o2.age) {
            return -1;
        }
        // 如果年龄相同就比较性别，女的排在前面
        if (o1.gendar > o2.gendar) {
            return 1;
        }
        if (o1.gendar < o2.gendar) {
            return -1;
        }
        // 如果性别也一样就比较是否已婚
        if (o1.hasMarried == true && o2.hasMarried == false) {
            // 结婚的排在前面
            return 1;
        }
        if (o1.hasMarried == false && o2.hasMarried == true) {
            // 结婚的排在前面
            return 1;
        }
        // 最后比较姓名，因为字符串比较耗时较长
        if (o1.name.hashCode() > o2.name.hashCode()) {
            return 1;
        }
        if (o1.name.hashCode() < o2.name.hashCode()) {
            return -1;
        }
        return 0;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getGendar() {
        return gendar;
    }

    public void setGendar(short gendar) {
        this.gendar = gendar;
    }

    public boolean isHasMarried() {
        return hasMarried;
    }

    public void setHasMarried(boolean hasMarried) {
        this.hasMarried = hasMarried;
    }


    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gendar=" + gendar +
                ", hasMarried=" + hasMarried +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        User user = (User) o;
        return age == user.age &&
                gendar == user.gendar &&
                hasMarried == user.hasMarried &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password);
    }



    public List<User> createUserDatas() {

        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User(22, "王旭", "123456", (short) 1, true));
        userList.add(new User(22, "王旭", "123456", (short) 1, true));
        userList.add(new User(22, "王旭", "123456", (short) 1, true));
        userList.add(new User(21, "孙萍", "a123456", (short) 2, false));
        userList.add(new User(23, "步传宇", "b123456", (short) 1, false));
        userList.add(new User(18, "蔡明浩", "c123456", (short) 1, true));
        userList.add(new User(17, "郭林杰", "d123456", (short) 1, false));
        userList.add(new User(5, "韩凯", "e123456", (short) 1, true));
        userList.add(new User(22, "韩天琪", "f123456", (short) 2, false));
        userList.add(new User(21, "郝玮", "g123456", (short) 2, false));
        userList.add(new User(19, "胡亚强", "h123456", (short) 1, false));
        userList.add(new User(14, "季恺", "i123456", (short) 1, false));
        userList.add(new User(17, "荆帅", "j123456", (short) 1, true));
        userList.add(new User(16, "姜有琪", "k123456", (short) 1, false));
        return userList;
    }



    /**
     * 按照年龄大小进行排序，设计一个Comparator
     *
     * @return
     */
    public Comparator<User> getAgeComparator() {

        return new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.age > o2.age){
                    return 1;
                }
                if (o1.age < o2.age) {
                    return -1;
                }
                return 0;
            }
        };
    }


    public Comparator<User> getEqualComparator() {

        return new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                // 首先比较年龄大小，因为年龄的区分度比较高
                if (o1.age > o2.age) {
                    return 1;
                }
                if (o1.age < o2.age) {
                    return -1;
                }
                // 如果年龄相同就比较性别，女的排在前面
                if (o1.gendar > o2.gendar) {
                    return 1;
                }
                if (o1.gendar < o2.gendar) {
                    return -1;
                }
                // 如果性别也一样就比较是否已婚
                if (o1.hasMarried == true && o2.hasMarried == false) {
                    // 结婚的排在前面
                    return 1;
                }
                if (o1.hasMarried == false && o2.hasMarried == true) {
                    // 结婚的排在前面
                    return 1;
                }
                // 最后比较姓名，因为字符串比较耗时较长
                if (o1.name.hashCode() > o2.name.hashCode()) {
                    return 1;
                }
                if (o1.name.hashCode() < o2.name.hashCode()) {
                    return -1;
                }
                return 0;
            }
        };
    }


}
