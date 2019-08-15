package com.rpc.syn.code;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2018-12-20 19:27
 **/
@Configuration
@Component
public class Person {

    @Value("${height:#{null}}")
    private String height;

    @Value("${firstName:#{null}}")
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    private String name;

    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Person{" +
                "height='" + height + '\'' +
                ", firstName='" + firstName + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
