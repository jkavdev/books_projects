package br.com.jkavdev.packtpub.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import br.com.jkavdev.packtpub.springsecurity.domain.CalendarUser;

@Component
public class SpringSecurityUserContext implements UserContext {

	private final CalendarService calendarService;
	private final UserDetailsService userDetailsService;

	@Autowired
	public SpringSecurityUserContext(CalendarService calendarService, UserDetailsService userDetailsService) {
		this.calendarService = calendarService;
		this.userDetailsService = userDetailsService;
	}

	//Buscando usuario a partir do contexto do spring security
	public CalendarUser getCurrentUser() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		if (authentication == null) {
			return null;
		}

		String email = authentication.getName();

		return calendarService.findUserByEmail(email);
	}

	public void setCurrentUser(CalendarUser user) {
		throw new UnsupportedOperationException();
	}

}
