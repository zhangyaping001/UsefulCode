package com.useful.builer.pattern;

/**
 * Created by zhangyaping on 18/8/5.
 * 构造器模式 可通过链式方法避免过多的set方法出现在业务代码中
 */
public class Dog {

    private int height;

    private int weight;

    private String name;

    private String Type;

    public Dog(int height, int weight, String name, String type) {
        this.height = height;
        this.weight = weight;
        this.name = name;
        Type = type;
    }

    /**
     * 必要的属性在创建Builder时传入, 不必要的使用链式方法传入
     *
     * 在调用一系列属性设置的方法之后始终只有一个Builder对象
     * 在调用build()方法后将Builder的属性作为
     * @param name
     * @return
     */
    public static Builder newBuilder(String name){
        return new Builder(name);
    }

    public static class Builder {

        private int height;

        private int weight;

        private String name;

        private String Type;

        public Builder(String name){
            this.name = name;
        }

        public Builder height(int height){
            this.height = height;
            return this;
        }

        public Builder weight(int weight){
            this.weight = weight;
            return this;
        }

        public Builder Type(String Type){
            this.Type = Type;
            return this;
        }

        public Dog build(){
            return new Dog(height,weight,name,Type);
        }

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "height=" + height +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}
