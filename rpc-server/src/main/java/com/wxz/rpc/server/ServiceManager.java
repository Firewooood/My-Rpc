package com.wxz.rpc.server;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.wxz.rpc.common.ReflectionUtils;
import com.wxz.rpc.proto.Request;
import com.wxz.rpc.proto.ServiceDescriptor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: WuXiangZhong
 * @Description: 管理rpc暴露的服务
 * @Date: Create in 2022/9/15
 */

@Slf4j
public class ServiceManager {
    //          key 服务描述  value 具体实现
    private Map<ServiceDescriptor, ServiceInstance>services;

    public ServiceManager(){
        this.services = new ConcurrentHashMap<>();  //
    }

    // 将接口的方法 和 bean 绑定起来，形成一个ServiceInstance,并将其注册到services map中
    public <T> void register(Class<T> interfaceClass, T bean){
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);    // 获取请求的接口方法中的public 方法

        for(Method method:methods){
            ServiceInstance instance = new ServiceInstance(bean, method);
            ServiceDescriptor sd = ServiceDescriptor.from(interfaceClass, method);
            services.put(sd,instance);
            log.info("register service: {} {}", sd.getClazz(), sd.getMethod());
        }
    }

    // 在services  map中寻找已注册的方法
    public ServiceInstance lookup(Request request){
        ServiceDescriptor sd = request.getService();
        return services.get(sd);    // services在get ServiceDescriptor 是根据该类的equals方法来判断的，需重写equals方法
    }
}
