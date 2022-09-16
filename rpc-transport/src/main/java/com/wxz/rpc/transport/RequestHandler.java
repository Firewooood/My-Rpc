package com.wxz.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Author: WuXiangZhong
 * @Description: 处理网络请求的handler
 * @Date: Create in 2022/9/15
 */
public interface RequestHandler {
    void onRequest(InputStream receive, OutputStream os);
}
