package com.wxz.rpc.utils;

import com.wxz.rpc.common.ReflectionUtils;
import junit.framework.TestCase;
import org.junit.Assert;

import java.lang.reflect.Method;

/**
 * @Author: WuXiangZhong
 * @Description:
 * @Date: Create in 2022/9/15
 */
public class ReflectionUtilsTest extends TestCase {

    public void testNewInstance() {
        TestClass testClass = ReflectionUtils.newInstance(TestClass.class);
        Assert.assertNotNull(testClass); //断言， 若不符合预期，则抛出异常
    }

    public void testGetPublicMethods() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        Assert.assertEquals(1,methods.length);  // TestClass中的public 方法只有一个

        String name = methods[0].getName();
        Assert.assertEquals("b",name);  //TestClass 中的public方法名为b
    }

    public void testInvoke() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        Method b = methods[0];

        TestClass t = new TestClass();
        Object invoke = ReflectionUtils.invoke(t, b);
        Assert.assertEquals("b",invoke);
    }
}