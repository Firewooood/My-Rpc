package com.wxz.rpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @Author: WuXiangZhong
 * @Description: 表示一个具体的服务
 * @Date: Create in 2022/9/15
 */

@Data
@AllArgsConstructor
public class ServiceInstance {
    private Object target;  // 提供服务的对象
    private Method method;  // 对象提供的方法
}
