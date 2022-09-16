package com.wxz.rpc.client;

import com.wxz.rpc.client.impl.RandomTransportSelectorImpl;
import com.wxz.rpc.codec.Decoder;
import com.wxz.rpc.codec.Encoder;
import com.wxz.rpc.codec.impl.JSONDecoder;
import com.wxz.rpc.codec.impl.JSONEncoder;
import com.wxz.rpc.proto.Peer;
import com.wxz.rpc.transport.TransportClient;
import com.wxz.rpc.transport.impl.HTTPTransportClient;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @Author: WuXiangZhong
 * @Description: client 配置
 * @Date: Create in 2022/9/16
 */

@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    private Class<? extends TransportSelector> selectorClass = RandomTransportSelectorImpl.class;
    private int connectCount = 1;
    private List<Peer> servers = Collections.singletonList(new Peer("127.0.0.1", 3000));
}
