package com.jens.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Jens
 * @Date: 2024/2/9  15:47
 * @Version 1.0
 */
@Component
public class JwtUtil {
    /**
     * 盐值很重要，不能泄漏，且每个项目都应该不一样，可以放到配置文件中
     */
    private static final String KEY = "yangjincheng";

    /**
     * 创建token
     *
     * @param id
     * @return
     */
    public static String createToken(Long id) {
        DateTime now = DateTime.now();
        DateTime expTime = now.offsetNew(DateField.HOUR, 24);
        Map<String, Object> payload = new HashMap<>();
        // 签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        // 过期时间
        payload.put(JWTPayload.EXPIRES_AT, expTime);
        // 生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        // 内容
        payload.put("id", id);
        String token = JWTUtil.createToken(payload, KEY.getBytes());
        return token;
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    public static JSONObject validate(String token) {
        JWT jwt = JWTUtil.parseToken(token).setKey(KEY.getBytes());
        // validate包含了verify
        return jwt.getPayloads();
    }

    public static JSONObject getJSONObject(String token) {
        JWT jwt = JWTUtil.parseToken(token).setKey(KEY.getBytes());
        JSONObject payloads = jwt.getPayloads();
        payloads.remove(JWTPayload.ISSUED_AT);
        payloads.remove(JWTPayload.EXPIRES_AT);
        payloads.remove(JWTPayload.NOT_BEFORE);
        return payloads;
    }

    public static void main(String[] args) {
        JWT jwt = JWTUtil.parseToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE3MDc4MzgyNTIsIm5hbWUiOiJhZG1pbiIsImlkIjoxLCJleHAiOjE3MDc5MjQ2NTIsImlhdCI6MTcwNzgzODI1Mn0.XU3DRqudVnYuEf3AbKQSQHZG7RsUZC2bUNSRjW1hKbc").setKey(KEY.getBytes());
        // validate包含了verify
        String id = jwt.getPayloads().get("id").toString();
        System.out.println(id);
    }
}
