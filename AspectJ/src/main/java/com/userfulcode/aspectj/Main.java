package com.userfulcode.aspectj;

/**
 * Created by zhangyaping on 2019/9/1.
 */
public class Main {


    public static void main(String[] args) {
        Account account = new Account();
        account.withdraw(5);

        System.out.println(account.toString());
    }
}
