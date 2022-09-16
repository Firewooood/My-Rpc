package com.wxz.rpc.transport.impl;

import com.wxz.rpc.proto.Peer;
import com.wxz.rpc.transport.TransportClient;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: WuXiangZhong
 * @Description:
 * @Date: Create in 2022/9/15
 */
public class HTTPTransportClient implements TransportClient {
    private String url;
    private HttpURLConnection connection;

    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");

            connection.connect();   // 建立连接
            IOUtils.copy(data, connection.getOutputStream());   // 将要写入的data 直接复制到当前连接的OutputStream中

            int responseCode = connection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                return connection.getInputStream();
            }
            else{
                return connection.getErrorStream();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {
        connection.disconnect();
    }
}
