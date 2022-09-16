package com.wxz.rpc.codec.impl;

import com.alibaba.fastjson.JSON;
import com.wxz.rpc.codec.Encoder;

/**
 * @Author: WuXiangZhong
 * @Description: 基于json的序列化实现
 * @Date: Create in 2022/9/15
 */
public class JSONEncoder implements Encoder {
    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
