package br.com.jkavdev.casadocodigo.jpaeficaz.filter;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.jkavdev.casadocodigo.jpaeficaz.utils.JpaUtils;

//Criando um filter para o controle da transacao
//Caso ocorra algum erro ele realiza o rollback e fecha o manager

public class ConnectionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		EntityManager manager = JpaUtils.getEntityManager();
		try {
			manager.getTransaction().begin();
			chain.doFilter(request, response);
			manager.getTransaction().commit();
		} catch (Exception e) {
			if (manager.isOpen()) {
				manager.getTransaction().rollback();
			}
		} finally {
			JpaUtils.closeEntityManager();
		}
	}

	@Override
	public void destroy() {

	}

}
