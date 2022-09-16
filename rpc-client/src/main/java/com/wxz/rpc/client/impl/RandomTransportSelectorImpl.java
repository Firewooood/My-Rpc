package com.wxz.rpc.client.impl;

import com.wxz.rpc.client.TransportSelector;
import com.wxz.rpc.common.ReflectionUtils;
import com.wxz.rpc.proto.Peer;
import com.wxz.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: WuXiangZhong
 * @Description:
 * @Date: Create in 2022/9/16
 */

@Slf4j
public class RandomTransportSelectorImpl implements TransportSelector {
    private List<TransportClient> clients;  // 已连接的client

    public RandomTransportSelectorImpl(){
        this.clients = new ArrayList<>();
    }

    @Override
    public synchronized void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count,1);  // 连接数最少为1
        for(Peer peer : peers){
            for(int i = 0; i < count; i++){
                TransportClient client = ReflectionUtils.newInstance(clazz);
                client.connect(peer);   // 用户端和一server端点创建连接
                clients.add(client);    // 已连接的clients列表新增
            }
            log.info("connect server: {}", peer);
        }
    }

    @Override
    public synchronized TransportClient select() {
        int i = new Random().nextInt(clients.size());   // 随机选择一个做交互
        return clients.remove(i);   // 正在做交互的client 无法被再次选择
    }

    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);
    }

    @Override
    public synchronized void close() {
        // 释放所有连接的client， 并清空clients列表
        for(TransportClient client:clients){
            client.close();
        }
        clients.clear();
    }
}
