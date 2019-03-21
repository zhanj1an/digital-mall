package com.zhan.filter;

import com.zhan.service.impl.DigitalMallSsoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "consoleFilter", urlPatterns = "/*")
@Component
public class DigitalMallConsoleFilter implements Filter {

    private DigitalMallSsoServiceImpl digitalMallSsoService;

    @Autowired
    public DigitalMallConsoleFilter(DigitalMallSsoServiceImpl digitalMallSsoService) {
        this.digitalMallSsoService = digitalMallSsoService;
    }

    @Override
    public void init(FilterConfig filterConfig){
        System.out.println("console filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        digitalMallSsoService.loginVerification(servletRequest, servletResponse, filterChain);
    }

    @Override
    public void destroy() {
        System.out.println("console filter destroy");
    }
}
