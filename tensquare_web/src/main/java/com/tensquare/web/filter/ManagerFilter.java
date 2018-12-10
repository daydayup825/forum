package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.RequestContent;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: fanbopeng
 * @Date: 2018/11/23 11:47
 * @Description:
 */
@Component
public class ManagerFilter extends ZuulFilter {
    /**
     *
     * 功能描述:
     *          表示在请求前或者后执行
     * @param:
     * @return:
     * @auther: fanbopeng
     * @date: 2018/11/23 11:48
     */
    @Override
    public String filterType() {
            //先得到request上下文
        RequestContext requestContext =RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String header = request.getHeader("Authorization");
        if (StringUtils.isNotEmpty(header)){
            requestContext.addZuulRequestHeader("Authorization", header);

        }

        return "pre";
    }

    /**
     *
     * 功能描述:
     *      多个过滤器的执行顺序
     * @param:
     * @return:
     * @auther: fanbopeng
     * @date: 2018/11/23 11:48
     */
    @Override
    public int filterOrder() {
        return 0;
    }
        /**
         *
         * 功能描述: 
         *
         * @param:  当前过滤器是否开启true开启
         * @return: 
         * @auther: fanbopeng
         * @date: 2018/11/23 11:49
         */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     *
     * 功能描述:
     *      过滤器内执行的顺序  return 任何object的值都表示继续执行
     *      setsendzullResponse(false)表示不再继续执行
     *
     * @param:
     *
     * @return:
     * @auther: fanbopeng
     * @date: 2018/11/23 11:49
     */

    @Override
    public Object run() throws ZuulException {

        System.out.println("经过后台过滤器了");
        return null;
    }
}
