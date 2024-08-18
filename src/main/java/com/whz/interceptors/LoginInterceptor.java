package com.whz.interceptors;

import com.whz.pojo.Result;
import com.whz.utils.JwtUtil;
import com.whz.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @author whz
 * @description 拦截器 用于统一进行token验证
 * @since 2024/8/13 22:51
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @description 拦截器
     *
     * @param
     * @return true-验证通过； false-验证失败
     * @date
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 令牌验证
        String token = request.getHeader("Authorization");
        // 验证token
        try {
             // 从redis中获取相同的token
             // ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
             // String redisToken = operations.get(token);
             // if (redisToken == null) {
             //     // token已失效
             //     throw new RuntimeException();
             //}

            Map<String, Object> claims = JwtUtil.parseToken(token);

            // 将业务数据存储到ThreadLocal中
            ThreadLocalUtil.set(claims);

            return true;
        } catch(Exception e) {
            response.setStatus(401);    // 设置http响应状态码为401
            return false;
        }
    }

    /**
     * @description 用完数据后的清理
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     * @date
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清空ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}
