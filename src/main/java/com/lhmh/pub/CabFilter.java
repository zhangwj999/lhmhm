package com.lhmh.pub;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class CabFilter
 */
public class CabFilter implements Filter {
	private static final Logger logger = Logger.getLogger(CabFilter.class);
    /**
     * Default constructor. 
     */
    public CabFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = ( HttpServletRequest )request;
		String uri = req.getRequestURL().toString();
		String qu = req.getQueryString();
		System.out.println( "uri == " + uri );
//		applyController.do
		if( qu.indexOf( "upLoadJpeg" ) != -1 ){
			logger.debug( "getContentLength =  " + request.getContentLength() );
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
