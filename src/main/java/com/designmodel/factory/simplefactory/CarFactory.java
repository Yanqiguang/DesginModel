package com.designmodel.factory.simplefactory;

/**
 * @program: DesignModel
 * @description: 工厂类
 * @author: lester.yan
 * @create: 2018-12-03 10:47
 **/

public class CarFactory {

    public static final String Benz = "Benz";

    public static final String BMW = "BMW";

    public static final String Audi = "Audi";


    public static ICar driveCar(String type) {
        if (Benz.equals(type)) {
            return new BenzCAr();
        }
        if (BMW.equals(type)) {
            return new BMWCar();
        }
        if (Audi.equals(type)) {
            return new AudiCar();
        }
        return null;
    }

    public static void main(String[] args) {
        ICar car = CarFactory.driveCar("Benz");
        System.out.println(car.getCAr());
    }
}
