package com.wxz.rpc.transport.impl;

import com.wxz.rpc.transport.RequestHandler;
import com.wxz.rpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Author: WuXiangZhong
 * @Description:
 * @Date: Create in 2022/9/15
 */

@Slf4j
public class HTTPTransportServer implements TransportServer {
    private RequestHandler handler;
    private Server server;

    @Override
    public void init(int port, RequestHandler handler) {
        this.handler = handler;
        this.server = new Server(port); // 创建server

        // servlet接受请求
        ServletContextHandler ctx = new ServletContextHandler();    // 创建一个contextHandler
        server.setHandler(ctx);

        ServletHolder holder = new ServletHolder(new RequestServlet());
        ctx.addServlet(holder,"/*");
    }

    @Override
    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }

    class RequestServlet extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
            log.info("client connected");

            InputStream is = req.getInputStream();
            OutputStream os = resp.getOutputStream();

            if(handler != null){
                handler.onRequest(is,os);
            }
            os.flush(); // 刷新输出流并且将输出字节写出
        }
    }
}
