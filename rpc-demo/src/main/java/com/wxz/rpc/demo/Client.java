package com.wxz.rpc.demo;

import com.wxz.rpc.client.RpcClient;

/**
 * @Author: WuXiangZhong
 * @Description:
 * @Date: Create in 2022/9/16
 */
public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();

        CalcService service = client.getProxy(CalcService.class);

        int r1 = service.add(1,2);
        int r2 = service.minus(20,9);

        System.out.println(r1);
        System.out.println(r2);
    }
}
