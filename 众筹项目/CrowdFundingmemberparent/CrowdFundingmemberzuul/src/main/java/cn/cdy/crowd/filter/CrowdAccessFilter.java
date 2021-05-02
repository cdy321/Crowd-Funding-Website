package cn.cdy.crowd.filter;

import cn.cdy.crowd.util.AccessPassResource;
import cn.cdy.crowd.util.CrowdConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CrowdAccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String servletPath = request.getServletPath();
        boolean containsResult = AccessPassResource.PASS_RES_SET.contains(servletPath);
        if(containsResult) {
          // 5.如果当前请求是可以直接放行的特定功能请求则返回 false 放行
            return false;
        }
        return !AccessPassResource.judgeCurrentServletPathWetherStaticResource(servletPath);
    }

    @Override
    public Object run() throws ZuulException {
        // 1.获取当前请求对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        // 2.获取当前 Session 对象
        HttpSession session = request.getSession();
        // 3.尝试从 Session 对象中获取已登录的用户
        Object loginmember = session.getAttribute(CrowdConstant.ATTR_LOGIN_MEMBER);
        // 4.判断 loginMember 是否为空
        if(loginmember == null) {
          // 5.从 requestContext 对象中获取 Response 对象
            HttpServletResponse response = requestContext.getResponse();
        // 6.将提示消息存入 Session 域
            session.setAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_ACCESS_FORBIDDEN);
        // 7.重定向到 auth-consumer 工程中的登录页面
            try {
                response.sendRedirect("/auth/member/to/login/page.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
