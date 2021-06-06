package com.longder.trace.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆成功后的处理
 */
public class FormLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    Logger logger = LoggerFactory.getLogger(FormLoginSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.debug("登陆成功！");
        RequestCache requestCache = new HttpSessionRequestCache();
        String url = null;
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest != null){
            url = savedRequest.getRedirectUrl();
            response.sendRedirect(url);
        }
        if(url == null){
            getRedirectStrategy().sendRedirect(request,response,getDefaultTargetUrl());
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
