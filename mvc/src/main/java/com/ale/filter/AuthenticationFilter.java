package com.ale.filter;

import com.ale.common.ResponseResult;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!authSuccess()) {
            log.info("认证失败");
            returnJson(response, new ResponseResult<String>());
            return;
        }
        filterChain.doFilter(request, response);
    }

    public Boolean authSuccess() {
        // todo 认证逻辑
        return Boolean.TRUE;
    }


    public static void returnJson(HttpServletResponse response, ResponseResult<String> result) {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(result.getCode());
        String json = JSON.toJSONString(result);
        try (PrintWriter writer = response.getWriter()) {
            writer.print(json);
        } catch (IOException e) {
            log.error("response error", e);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestURI = request.getRequestURI();
        log.info("requestURI: {}", requestURI);
        return Boolean.TRUE.equals(request.getAttribute("SHOULD_NOT_FILTER"));
    }

}
