package com.wxz.rpc.demo;

/**
 * @Author: WuXiangZhong
 * @Description:
 * @Date: Create in 2022/9/16
 */
public class CalcServiceImpl implements CalcService{

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }
}
