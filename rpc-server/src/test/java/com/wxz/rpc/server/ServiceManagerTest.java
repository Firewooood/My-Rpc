package com.wxz.rpc.server;

import com.wxz.rpc.common.ReflectionUtils;
import com.wxz.rpc.proto.Request;
import com.wxz.rpc.proto.ServiceDescriptor;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @Author: WuXiangZhong
 * @Description:
 * @Date: Create in 2022/9/15
 */
public class ServiceManagerTest {
    private ServiceManager serviceManager;

    @Before
    public void init(){
        serviceManager = new ServiceManager();
        testRegister();
    }

    @Test
    public void testRegister() {
        TestInterface bean = new TestClass();
        serviceManager.register(TestInterface.class, bean);
    }

    @Test
    public void testLookup() {
        Method method = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor sd = ServiceDescriptor.from(TestInterface.class, method);
        Request request = new Request();
        request.setService(sd);
        ServiceInstance instance = serviceManager.lookup(request);
        Assert.assertNotNull(instance);
    }
}