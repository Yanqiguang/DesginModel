package com.designmodel.factory.factory;

import com.designmodel.factory.simplefactory.BenzCAr;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2018-12-03 11:45
 *
 *
 **/


public class CanadaCarFactory extends CountryCarFactory {
    @Override
    public String generateCar() {
        return new BenzCAr().getCAr();
    }
}
