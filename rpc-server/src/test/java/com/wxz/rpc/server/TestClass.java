package com.wxz.rpc.server;


/**
 * @Author: WuXiangZhong
 * @Description:
 * @Date: Create in 2022/9/15
 */
public class TestClass implements TestInterface {

    @Override
    public void hello() {
        System.out.println("hello world");
    }
}
