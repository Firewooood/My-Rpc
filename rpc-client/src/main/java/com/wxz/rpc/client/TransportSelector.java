package com.wxz.rpc.client;

import com.wxz.rpc.proto.Peer;
import com.wxz.rpc.transport.TransportClient;

import java.util.List;

/**
 * @Author: WuXiangZhong
 * @Description: 选择哪个server进行连接
 * @Date: Create in 2022/9/16
 */
public interface TransportSelector {
    /**
     *
     * @param peers 可连接的server端点信息
     * @param count client可与server建立多少个连接
     * @param clazz client实现class
     */
    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz);

    /**
     * 选择一个transport与server做交互
     * @return 网络client
     */
    TransportClient select();

    // 释放用完的client
    void release(TransportClient client);

    void close();
}
