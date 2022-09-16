package com.wxz.rpc.client;

import com.wxz.rpc.codec.Decoder;
import com.wxz.rpc.codec.Encoder;
import com.wxz.rpc.common.ReflectionUtils;

import java.lang.reflect.Proxy;

/**
 * @Author: WuXiangZhong
 * @Description:
 * @Date: Create in 2022/9/16
 */
public class RpcClient {
    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }
    public RpcClient(RpcClientConfig config) {
        this.config = config;
        this.encoder = ReflectionUtils.newInstance(this.config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(this.config.getDecoderClass());
        this.selector = ReflectionUtils.newInstance(this.config.getSelectorClass());

        this.selector.init(this.config.getServers(), this.config.getConnectCount(), this.config.getTransportClass());
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> clazz) {
        // 动态代理
        // 该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{clazz}, new RemoteInvoker(clazz, encoder, decoder, selector));
    }
}
