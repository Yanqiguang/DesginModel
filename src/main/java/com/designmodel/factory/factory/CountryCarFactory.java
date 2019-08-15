package com.designmodel.factory.factory;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2018-12-03 11:42
 **/

public abstract class CountryCarFactory {

    public abstract String generateCar();


    public static void main(String[] args) {
        CountryCarFactory china=new ChinaCarFactory();
        String carName=china.generateCar();
        System.out.println(carName);

    }
}
