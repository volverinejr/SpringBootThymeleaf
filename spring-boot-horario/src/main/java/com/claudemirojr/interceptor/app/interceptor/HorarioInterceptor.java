package com.claudemirojr.interceptor.app.interceptor;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("horarioInterceptor")
public class HorarioInterceptor implements HandlerInterceptor {

	@Value("${config.horario.abertura}")
	private Integer abertura;

	@Value("${config.horario.fechamento}")
	private Integer fechamento;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Calendar calendar = Calendar.getInstance();
		int hora = calendar.get(Calendar.HOUR_OF_DAY);

		if (hora >= abertura && hora <= fechamento) {
			StringBuilder mensagem = new StringBuilder("Bem vindo ao horário de atendimento aos clientes");
			mensagem.append(", atendemos " + abertura + "hrs às " + fechamento + "hrs");
			mensagem.append(". Obrigado por sua visita");

			request.setAttribute("mensagem", mensagem.toString());

			return true;
		}
		
		
		response.sendRedirect(request.getContextPath().concat("/fechado"));
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		String mensagem = (String) request.getAttribute("mensagem");

		if (modelAndView != null) {
			modelAndView.addObject("horario", mensagem);
		}

	}

}
