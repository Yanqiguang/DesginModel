package com.designmodel.factory.factory;

import com.designmodel.factory.simplefactory.AudiCar;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2018-12-03 11:46
 *
 **/

public class AmericanCarFactory extends  CountryCarFactory {

    @Override
    public String generateCar() {
        return new AudiCar().getCAr();
    }
}
