package com.wxz.rpc.proto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: WuXiangZhong
 * @Description: 表示网络传输的一个端点
 * @Date: Create in 2022/9/15
 */

@Data
@AllArgsConstructor
public class Peer {
    private String host;
    private int port;
}
