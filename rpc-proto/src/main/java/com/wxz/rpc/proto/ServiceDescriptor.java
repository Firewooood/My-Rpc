package com.wxz.rpc.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author: WuXiangZhong
 * @Description: 表示服务
 * @Date: Create in 2022/9/15
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    private String clazz;
    private String method;
    private String returnType;
    private String[] parameterTypes;

    @SuppressWarnings("rawtypes")
    public static ServiceDescriptor from(Class clazz, Method method){
        ServiceDescriptor sd  = new ServiceDescriptor();
        sd.setClazz(clazz.getName());
        sd.setMethod(method.getName());
        sd.setReturnType(method.getReturnType().getName());

        Class<?>[] paramClasses = method.getParameterTypes();
        String[] paramTypes = new String[paramClasses.length];
        for(int i = 0; i < paramClasses.length; i++){
            paramTypes[i] = paramClasses[i].getName();
        }
        sd.setParameterTypes(paramTypes);
        return sd;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null | getClass() != obj.getClass()) return false;
        ServiceDescriptor that = (ServiceDescriptor) obj;
        return this.toString().equals(that.toString());
    }

    @Override
    public String toString() {
        return "clazz=" + clazz + ",method=" + method
                + ",returnType=" + returnType
                + ",args=" + Arrays.toString(parameterTypes);
    }
}
