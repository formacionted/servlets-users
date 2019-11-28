package com.telefonica.jee.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.telefonica.jee.domain.Usuario;
import com.telefonica.jee.util.AppConstants;

/**
 * Servlet Filter implementation class SessionCheckFilter
 */
@WebFilter(filterName = "SessionCheckFilter", urlPatterns = { "/user/*", "/todos/*" })
public class SessionCheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SessionCheckFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		Usuario user = (Usuario) session.getAttribute(AppConstants.SESSION_USER);

		if (user != null) {
			chain.doFilter(request, response);
		} else {
			request.getRequestDispatcher(AppConstants.LOGIN_PAGE).forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
