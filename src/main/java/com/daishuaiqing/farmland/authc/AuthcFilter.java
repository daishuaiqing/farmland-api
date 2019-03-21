package com.daishuaiqing.farmland.authc;

import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthcFilter extends HttpServlet implements Filter {
//    @Autowired
//    private UserInfoUtils userInfoUtils;
//    @Autowired
//    private RulePermissionService rulePermissionService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //System.out.println("==>Filter启动");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 将请求转换成HttpServletRequest 请求
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader("token");
        if(checkAuthc(token,request)){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = response.getWriter();
            JSONObject res = new JSONObject();
            res.put("status",2);
            res.put("msg","无权限");
            res.put("data","");
            out.append(res.toString());
            out.flush();
            out.close();
        }
    }

    private boolean checkAuthc(String token, HttpServletRequest request) {
        if(token==null){
            return false;
        }else{
            /**
             * 获取请求路径
             */
            String requestURI = request.getRequestURI();
            /**
             * 获取用户信息
             */
            /*AdminInfo adminInfo = userInfoUtils.getAdminInfo(token);
            if(adminInfo.getCategory()==1&&adminInfo.getOwner()==1){//如果是企业账号，并且是自营，则拥有全部权限
                return true;
            }*/
            /**
             * 获得权限表
             */
            //List<RulePermission> permissions = rulePermissionService.findByAdminId(adminInfo.getAdminId());
            /**
             * 判断是否有该权限
             */
            boolean flag = false;
            /*for(RulePermission permission: permissions){
                if(permission.getUrl().equals(requestURI)){
                    flag = true;
                }
            }*/
            return flag;
        }
    }


}
