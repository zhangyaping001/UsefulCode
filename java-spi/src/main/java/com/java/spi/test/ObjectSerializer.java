package com.java.spi.test;

/**
 * Created by zhangyaping on 2019/7/9.
 */
public interface ObjectSerializer {

    byte[] serialize(Object obj) throws Exception;

    <T> T deSerialize(byte[] param, Class<T> clazz) throws Exception;

    String getSchemeName();

}
