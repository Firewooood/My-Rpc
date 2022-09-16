package com.wxz.rpc.proto;

import lombok.Data;

/**
 * @Author: WuXiangZhong
 * @Description: 表示RPC的一个请求
 * @Date: Create in 2022/9/15
 */
@Data
public class Request {
    private ServiceDescriptor service;
    private Object[] parameters;
}
