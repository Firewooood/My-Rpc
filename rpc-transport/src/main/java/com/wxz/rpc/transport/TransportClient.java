package com.wxz.rpc.transport;

import com.wxz.rpc.proto.Peer;

import java.io.InputStream;

/**
 * @Author: WuXiangZhong
 * @Description: 1. 创建连接
 *               2. 发送数据，并且等待响应
 *               3. 关闭连接
 * @Date: Create in 2022/9/15
 */
public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();
}
