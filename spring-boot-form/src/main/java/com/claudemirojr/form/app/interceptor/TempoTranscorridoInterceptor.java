package com.claudemirojr.form.app.interceptor;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component("tempoTranscorridoInterceptor")
public class TempoTranscorridoInterceptor implements HandlerInterceptor {
	
	
	private static final Logger logger = LoggerFactory.getLogger(TempoTranscorridoInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("TempoTranscorridoInterceptor: preHandle() entrando...");
		
		long tempoInicio = System.currentTimeMillis();
		request.setAttribute("tempoInicio", tempoInicio);
		
		Random random = new Random();
		
		Thread.sleep( random.nextInt(600) );
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long tempoFim = System.currentTimeMillis();
		long tempoInicio = (Long) request.getAttribute("tempoInicio");
		
		long tempoFinal = tempoFim - tempoInicio;
		
		
		if ( modelAndView != null ) {
			modelAndView.addObject("tempoFinal", tempoFinal);
		}
		
		
		logger.info("tempoFinal: " + tempoFinal + " milisegundos");
		
		logger.info("TempoTranscorridoInterceptor: postHandle() saindo...");
	}

}
