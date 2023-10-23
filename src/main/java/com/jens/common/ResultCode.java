package com.jens.common;

import com.baomidou.mybatisplus.extension.api.IErrorCode;

/**
 * 枚举了一些常用API操作码
 * Created by macro on 2019/4/19.
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或session已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    UNKNOWN(99999, "未知异常，请稍后再试！");
    private long code;
    private String msg;

    private ResultCode(long code, String message) {
        this.code = code;
        this.msg = message;
    }

    public long getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
