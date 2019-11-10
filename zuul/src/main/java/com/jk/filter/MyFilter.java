package com.jk.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class MyFilter extends ZuulFilter {
    /*
        filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
					pre：路由之前 ,先执行过滤器再执行后台方法
					routing：路由之时, 执行完后台方法在执行过滤器
					post： 路由之后, 执行完后台方法在执行过滤器
					error：发送错误调用，如果请求正常没有错误，则不需要执行业务逻辑判断
    */
    @Override
    public String filterType() {
        System.out.println("过滤器类型filterType");
        return "pre";
    }

    //过滤的顺序, 值越小优先级越高，最小值默认为0
    @Override
    public int filterOrder() {
        System.out.println("过滤器执行顺序方法filterOrder");
        return 0;
    }

    //这里可以写逻辑判断，是否要过滤，true,永远过滤。
    // 如果返回值为true，则进入run方法进行业务处理
    // false直接进入后台
    @Override
    public boolean shouldFilter() {
        System.out.println("shouldFilter方法");
        return true;
    }

    //过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
    @Override
    public Object run() throws ZuulException {
        System.out.println("业务逻辑处理方法run");

        // 获取request对象
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        // 获取当前用户请求的地址
        String url = request.getRequestURL().toString();
        System.out.println(url);

        // 请求的方法
        String method = request.getMethod();
        System.out.println(method);

        // 获取session对象
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        user = new Object();
        System.out.println(user);

        // 获取地址栏传入的秘钥作为验证，验证此请求是否为正常请求
        String secret_key = request.getParameter("secret_key");
        System.out.println(secret_key);

        if(user == null){
            try {
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(401);
                HttpServletResponse response = context.getResponse();
                //让浏览器用utf8来解析返回的数据
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                //servlet用UTF-8转码，而不是用默认的ISO8859
                response.setCharacterEncoding("utf-8");
                response.getWriter().write("请登录，再使用本系统。");
                return "";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(secret_key == null || !"1904b".equals(secret_key)){
            try {
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(401);
                HttpServletResponse response = context.getResponse();
                //让浏览器用utf8来解析返回的数据
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                //servlet用UTF-8转码，而不是用默认的ISO8859
                response.setCharacterEncoding("utf-8");
                response.getWriter().write("不好意思，您不是VIP，请充值");
                return "";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
