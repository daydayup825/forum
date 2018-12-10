package com.tensquare.user.interceptor;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: fanbopeng
 * @Date: 2018/11/21 17:49
 * @Description:
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //无论如何都放行
        //拦截器只是为了把头请求中包含的token的令牌进行解析
        System.out.println("经过拦截器");

        String header = request.getHeader("Authorization");//获取前端传过来头信息中的token
        if (!StringUtils.isEmpty(header)) {
            //如果包含有头信息就对其进行解析
            if (header.startsWith("Bearer")) {
                String token = header.substring(7);

                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if (roles != null && ("admin").equals(roles)) {

                        request.setAttribute("claims_admin", token);
                    }
                    if (roles != null && ("user").equals(roles)) {

                        request.setAttribute("claims_user", token);
                    }


                } catch (Exception e) {
                    throw new RuntimeException("令牌不正确");
                }
            }


        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
