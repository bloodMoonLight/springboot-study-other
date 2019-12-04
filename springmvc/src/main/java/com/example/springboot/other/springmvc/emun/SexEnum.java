package com.example.springboot.other.springmvc.emun;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum SexEnum {

    MALE(1,"男"),
    FEMALE(2,"女");
    /** 标记数据库存的值是id */
    @EnumValue
    private int id;
    private String name;

    SexEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 循环取出该枚举类中的数据
     * @param id
     * @return
     */
    public static SexEnum getEnumById(int id){
        for(SexEnum sexEnum : SexEnum.values()){
            if(sexEnum.getId() == id){
                return sexEnum;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
