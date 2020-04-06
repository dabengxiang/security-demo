package com.onion.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gyc
 * @date 2020/4/5
 */

public class CustomerAuthenticationEntryPoint implements AuthenticationEntryPoint {


    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String requestURI = httpServletRequest.getRequestURI();
        if(isAjaxRequest(httpServletRequest)){
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getMessage());
        }
       else{
            redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"http://127.0.0.1:7070/login");
        }
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("X-Requested-With");
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }

}
