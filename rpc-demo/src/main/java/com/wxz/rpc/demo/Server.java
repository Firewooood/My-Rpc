package com.wxz.rpc.demo;

import com.wxz.rpc.server.RpcServer;
import com.wxz.rpc.server.RpcServerConfig;

/**
 * @Author: WuXiangZhong
 * @Description:
 * @Date: Create in 2022/9/16
 */
public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer(new RpcServerConfig());
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}
