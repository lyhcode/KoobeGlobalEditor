package com.koobe.editor.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author cloude
 * @since 2014-1-29
 */
public class GWTCacheControlFilter implements Filter {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestURI = httpRequest.getRequestURI();
		
		if (requestURI.contains(".nocache.")) {

			HttpServletResponse httpResponse = (HttpServletResponse) response;

			httpResponse.setHeader("Expires", "Sat, 1 January 2000 12:00:00 GMT");
			httpResponse.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
			httpResponse.addHeader("Cache-Control", "post-check=0, pre-check=0");
			httpResponse.setHeader("Pragma", "no-cache");
		}

		filterChain.doFilter(request, response);
	}
}
