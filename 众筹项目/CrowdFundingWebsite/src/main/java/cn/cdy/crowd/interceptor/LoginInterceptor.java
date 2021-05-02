package cn.cdy.crowd.interceptor;

import cn.cdy.crowd.entity.TAdmin;
import cn.cdy.crowd.exception.AccessForbiddenException;
import cn.cdy.crowd.util.CrowdConstant;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//拦截器
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        TAdmin admin = (TAdmin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
        if(admin == null){
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDDEN);
        }
        return true;
    }
}
