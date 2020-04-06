package com.onion.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gyc
 * @date 2020/4/6
 */

public class CustomerAccessDeniedHandler implements AccessDeniedHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    public static boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("X-Requested-With");
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        String requestURI = httpServletRequest.getRequestURI();
        if(isAjaxRequest(httpServletRequest)){
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN,e.getMessage());
        }
        else{
            redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"http://127.0.0.1:7070/notAccess");
        }
    }
}
