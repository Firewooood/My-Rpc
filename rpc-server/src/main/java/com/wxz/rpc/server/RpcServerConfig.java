package com.wxz.rpc.server;

import com.wxz.rpc.codec.Decoder;
import com.wxz.rpc.codec.Encoder;
import com.wxz.rpc.codec.impl.JSONDecoder;
import com.wxz.rpc.codec.impl.JSONEncoder;
import com.wxz.rpc.transport.TransportServer;
import com.wxz.rpc.transport.impl.HTTPTransportServer;
import lombok.Data;

/**
 * @Author: WuXiangZhong
 * @Description: server配置
 * @Date: Create in 2022/9/15
 */

@Data
public class RpcServerConfig {
    // <? extends TransportServer> 上界通配符，指所有TransportServer类及其派生子类
    // JDK中，普通的Class.newInstance()方法的定义返回Object，要将该返回类型强制转换为另一种类型;
    // 但是使用泛型的Class<T>，Class.newInstance()方法具有一个特定的返回类型;
    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 3000;
}
