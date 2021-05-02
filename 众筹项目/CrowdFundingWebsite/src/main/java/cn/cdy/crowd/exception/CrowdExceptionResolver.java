package cn.cdy.crowd.exception;

import cn.cdy.crowd.util.CrowdConstant;
import cn.cdy.crowd.util.CrowdUtil;
import cn.cdy.crowd.util.ResultEntity;
import com.google.gson.Gson;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CrowdExceptionResolver {

    private ModelAndView commonResolveException(Exception exception,
                                                HttpServletRequest request,
                                                HttpServletResponse response,
                                                String viewName)throws Exception{
        ModelAndView mv = new ModelAndView();
        boolean res = CrowdUtil.getrequestType(request);
        if(res){
            ResultEntity<Object> resultEntity = ResultEntity.fail(exception.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(resultEntity);
            response.getWriter().write(json);
            return null;
        }
        mv.addObject(CrowdConstant.ATTR_NAME_EXCEPTION,exception);
        mv.setViewName(viewName);
        return mv;
    }
    //登录异常
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView loginFailedResolveException(LoginFailedException exception,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response)throws Exception{
        String viewName = "admin-login";
        return commonResolveException(exception,request,response,viewName);
    }
    //新增唯一约束异常
    @ExceptionHandler(value = LoginAcctAlreadyInUseException.class)
    public ModelAndView loginacctalreadInuseResolveException(LoginAcctAlreadyInUseException exception,
                                                             HttpServletRequest request,
                                                             HttpServletResponse response)throws Exception{
        String viewName = "admin-add";
        return commonResolveException(exception,request,response,viewName);
    }
    //更新唯一约束异常
    @ExceptionHandler(value = LoginAcctAlreadyInUseForUpdateException.class)
    public ModelAndView loginacctalreadInuseForUpdateResolveException(LoginAcctAlreadyInUseForUpdateException exception,
                                                             HttpServletRequest request,
                                                             HttpServletResponse response)throws Exception{
        String viewName = "admin-update";
        return commonResolveException(exception,request,response,viewName);
    }
    //禁止访问异常
    @ExceptionHandler(value = Exception.class)
    public ModelAndView ResolveAccessDeniedException(Exception exception,
                                       HttpServletRequest request,
                                       HttpServletResponse response)throws Exception{
        String viewName = "system-error";
        return commonResolveException(exception,request,response,viewName);
    }
}
