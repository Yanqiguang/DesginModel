package com.designmodel.factory.factory;

import com.designmodel.factory.simplefactory.BMWCar;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2018-12-03 11:42
 **/

public class ChinaCarFactory extends  CountryCarFactory{


    @Override
    public String generateCar() {
        return new BMWCar().getCAr();
    }
}
