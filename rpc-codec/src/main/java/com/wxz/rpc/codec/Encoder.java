package com.wxz.rpc.codec;

/**
 * @Author: WuXiangZhong
 * @Description: 将对象转化为 byte数组
 * @Date: Create in 2022/9/15
 */
public interface Encoder {
    byte[] encode(Object obj);
}
