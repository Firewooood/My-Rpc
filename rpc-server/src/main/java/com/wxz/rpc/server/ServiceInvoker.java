package com.wxz.rpc.server;

import com.wxz.rpc.common.ReflectionUtils;
import com.wxz.rpc.proto.Request;

/**
 * @Author: WuXiangZhong
 * @Description: 调用具体服务
 * @Date: Create in 2022/9/15
 */
public class ServiceInvoker {
    // 使用反射类执行具体服务中的方法， request请求中有方法参数
    public Object invoke(ServiceInstance service, Request request){
        return ReflectionUtils.invoke(service.getTarget(),service.getMethod(),request.getParameters());
    }
}
