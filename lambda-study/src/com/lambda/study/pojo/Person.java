package com.lambda.study.pojo;

public class Person {

    private String username;
    private Integer age;
    private String eamil;

    public Person() {
    }

    public Person(String username, Integer age, String eamil) {
        this.username = username;
        this.age = age;
        this.eamil = eamil;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEamil() {
        return eamil;
    }

    public void setEamil(String eamil) {
        this.eamil = eamil;
    }

    @Override
    public String toString() {
        return "Person{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", eamil='" + eamil + '\'' +
                '}';
    }
}
