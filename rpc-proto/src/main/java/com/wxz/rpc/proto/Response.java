package com.wxz.rpc.proto;

import lombok.Data;

/**
 * @Author: WuXiangZhong
 * @Description: 表示RPC的响应
 * @Date: Create in 2022/9/15
 */

@Data
public class Response {
    private int code = 0;   // 0-成功，非0 - 失败
    private String message = "ok";     // 具体原因
    private Object data;
}
