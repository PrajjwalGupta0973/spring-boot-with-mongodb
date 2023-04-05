package com.prajjwal.interceptor;
//
//import java.security.Principal;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.MDC;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//@Component
//public class CompanyInterceptor implements HandlerInterceptor {
//
//	
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object Handler) throws Exception {
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//		String username= null;
//		if(principal instanceof Principal) {
//			username= ((Principal) principal).getName();
//		}
//		else
//		{
//			username = principal.toString();
//		}
//		MDC.put("userId", username);
//		return true;
//	}
//	
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView mv) throws Exception{
//		
//	}
//	
//	@Override
//	public void afterCompletion
//    (HttpServletRequest request, HttpServletResponse response, Object 
//    handler, Exception exception) throws Exception{
//		MDC.clear();
//	}
//	
//	
//}
