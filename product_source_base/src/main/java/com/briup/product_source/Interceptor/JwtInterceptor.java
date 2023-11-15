package com.briup.product_source.Interceptor;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.briup.product_source.exception.ServiceException;
import com.briup.product_source.result.ResultCode;
import com.briup.product_source.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Hlmove
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 当请求方式预检请求时，不需要进行token验证
        String method = request.getMethod();
        if("OPTIONS".equals(method)){
            return true;//通过拦截
        }

        // 1.获取请求头信息token  查看是否携带令牌
        String token = request.getHeader("token");
        System.out.println("token = " + token);

        // 2.对token字符串进行验证
        if(token == null) {  //直接浏览器访问，没有token
            throw new ServiceException(ResultCode.USER_NOT_LOGIN);
        }
        // 当提交了token
        try {
            JwtUtil.checkSign(token);
        }catch (Exception ex) {
            // 扩展：可以根据不同的异常类型 提供用户不同的错误内容
            if(ex instanceof TokenExpiredException) {
                throw new ServiceException(ResultCode.TOKEN_TIMEOUT);
            }
            // 添加多个类型的异常判断
            throw new ServiceException(ResultCode.TOKEN_VALIDATE_ERROR);
        }

        return true;
    }
}
