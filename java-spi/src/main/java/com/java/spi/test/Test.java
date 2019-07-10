package com.java.spi.test;

import com.java.spi.test.entity.Dog;

/**
 * Created by zhangyaping on 2019/7/9.
 */
public class Test {

    public static void main(String[] args) {
        SerializerService serializerService = new SerializerService();
        ObjectSerializer objectSerializer = serializerService.getObjectSerializer();
        System.out.println(objectSerializer.getSchemeName());
        try {
            byte[] arrays = objectSerializer.serialize(new Dog("Hello"));
            Dog dog = objectSerializer.deSerialize(arrays, Dog.class);
            System.out.println(dog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
