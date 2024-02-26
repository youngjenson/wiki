package com.jens.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import com.jens.dto.LoginUser;
import com.jens.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @Author:Jens
 * @Date: 2024/2/13  23:35
 * @Version 1.0
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            //放行
            filterChain.doFilter(request,response);
            return;
        }
        //解析token
        String id = JwtUtil.getJSONObject(token).get("id").toString();
        //从redis中获取用户信息
        String redisKey = "login:"+id;
        LoginUser loginUser = (LoginUser) redisTemplate.opsForValue().get(redisKey);
        if(ObjectUtil.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }
        //注入SecurityContextHolder
        // 获取权限信息封装到Authentication
        Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request,response);
    }
}
