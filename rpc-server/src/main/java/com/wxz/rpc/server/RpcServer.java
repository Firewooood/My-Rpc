package com.wxz.rpc.server;

import com.wxz.rpc.codec.Decoder;
import com.wxz.rpc.codec.Encoder;
import com.wxz.rpc.common.ReflectionUtils;
import com.wxz.rpc.proto.Request;
import com.wxz.rpc.proto.Response;
import com.wxz.rpc.transport.RequestHandler;
import com.wxz.rpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * @Author: WuXiangZhong
 * @Description:
 * @Date: Create in 2022/9/15
 */

@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;  // 服务管理 (注册，寻找)
    private ServiceInvoker serviceInvoker;  // 服务调用

    // 处理网络要求的 handler
    private RequestHandler handler = (receive, os) -> {
        Response resp = new Response();
        try {
            byte[] bytes = IOUtils.readFully(receive, receive.available()); // 从输入流中读入
            Request request = decoder.decode(bytes, Request.class);  // 将byte数组转换为Request对象
            log.info("get request: {}", request);

            ServiceInstance instance = serviceManager.lookup(request);  // 获取请求对应的具体服务
            Object res = serviceInvoker.invoke(instance, request);
            resp.setData(res);

        } catch (IOException e) {
            log.warn(e.getMessage(),e);
            resp.setCode(1);
            resp.setMessage("RpcServer got error:" + e.getClass().getName() + " " + e.getMessage());
        } finally {
            // 将响应转为byte数组
            byte[] bytes = encoder.encode(resp);
            try {
                os.write(bytes); // 将响应写入输出流
                log.info("response client");
            } catch (IOException e) {
                log.warn(e.getMessage(),e);
            }
        }
    };

    public RpcServer(RpcServerConfig config){
        this.config = config;

        // net
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);

        // codec
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());

        // service
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass, T bean){
        this.serviceManager.register(interfaceClass,bean);
    }

    public void start(){
        this.net.start();
    }

    public void stop(){
        this.net.stop();
    }
}
