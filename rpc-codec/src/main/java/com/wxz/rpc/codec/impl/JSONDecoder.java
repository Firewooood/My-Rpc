package com.wxz.rpc.codec.impl;

import com.alibaba.fastjson.JSON;
import com.wxz.rpc.codec.Decoder;

/**
 * @Author: WuXiangZhong
 * @Description: 实现json的反序列化
 * @Date: Create in 2022/9/15
 */
public class JSONDecoder implements Decoder {
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
