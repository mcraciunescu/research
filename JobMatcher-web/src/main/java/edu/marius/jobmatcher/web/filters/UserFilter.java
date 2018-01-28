/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.jobmatcher.web.filters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marius
 */
@WebFilter(filterName = "UserFilter", urlPatterns = {"/user/*"})
public class UserFilter implements Filter {

    public UserFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession ses = req.getSession(false);

            if (isValidUser(ses)) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(req.getContextPath());
            }

        } catch (IOException | ServletException ex) {
            Logger.getLogger(UserFilter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void destroy() {

    }

    private boolean isValidUser(HttpSession session) {
        if (session == null) {
            return false;
        }
        return session.getAttribute("user") != null;
    }
}
