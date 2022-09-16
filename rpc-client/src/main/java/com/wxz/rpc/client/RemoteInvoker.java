package com.wxz.rpc.client;

import com.wxz.rpc.codec.Decoder;
import com.wxz.rpc.codec.Encoder;
import com.wxz.rpc.proto.Request;
import com.wxz.rpc.proto.Response;
import com.wxz.rpc.proto.ServiceDescriptor;
import com.wxz.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: WuXiangZhong
 * @Description: 调用远程服务的代理类
 * @Date: Create in 2022/9/16
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;     // 选择一个远程网络连接

    public RemoteInvoker(Class clazz, Encoder encoder,
                         Decoder decoder, TransportSelector selector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 1. 构建一个request请求
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz,method));
        request.setParameters(args);

        // 2. 获得远程调用的响应
        Response response = invokeRemote(request);
        if(response == null || response.getCode() != 0){
            throw new IllegalStateException("fail to invoke remote: " + response);
        }
        return response.getData();
    }

    private Response invokeRemote(Request request){
        Response response = null;
        TransportClient client = null;

        //
        try {
            client = selector.select();
            byte[] outBytes = encoder.encode(request);
            InputStream receive = client.write(new ByteArrayInputStream(outBytes));
            byte[] inBytes = IOUtils.readFully(receive, receive.available());
            response = decoder.decode(inBytes,Response.class);
        } catch (IOException e) {
            log.warn(e.getMessage(),e);
            response = new Response();
            response.setCode(1);    // code = 1 代表调用远程失败
            response.setMessage("RpcClient got error: " + e.getClass() + " " + e.getMessage());
        } finally {
            if(client != null){
                selector.release(client);
            }
        }

        return response;
    }

}
