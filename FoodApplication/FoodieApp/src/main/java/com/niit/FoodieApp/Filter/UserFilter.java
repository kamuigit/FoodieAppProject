package com.niit.FoodieApp.Filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;

        String path = httpServletRequest.getRequestURI();
        if (path.equals("/api/v1/register-customer")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String header = httpServletRequest.getHeader("Authorization");
        if(header == null || !header.startsWith("Bearer"))
        {
            throw  new ServletException("Token is Missing..");
        }
        else{
            String token= header.substring(7);
            Claims claims= Jwts.parser().setSigningKey("secretKey55").parseClaimsJws(token).getBody();
            System.out.println("Received Claims : "+claims);
            httpServletRequest.setAttribute("attr1",claims.get("email"));
            httpServletRequest.setAttribute("attr2",claims.get("role"));
            System.out.println(token);
            System.out.println("attr1=" + claims.get("email") + ", attr2=" + claims.get("role"));
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
