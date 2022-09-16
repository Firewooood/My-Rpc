package com.wxz.rpc.codec;

/**
 * @Author: WuXiangZhong
 * @Description: 将byte数组转换为 对象
 * @Date: Create in 2022/9/15
 */
public interface Decoder {
    <T> T decode(byte[] bytes, Class<T> clazz);
}
