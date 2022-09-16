package com.wxz.rpc.transport;

import org.eclipse.jetty.http.HttpParser;

/**
 * @Author: WuXiangZhong
 * @Description: 1. 启动、监听
 *               2. 接受请求，处理，响应
 *               3. 关闭
 * @Date: Create in 2022/9/15
 */
public interface TransportServer {
    void init(int port, RequestHandler handler);

    void start();

    void stop();
}
