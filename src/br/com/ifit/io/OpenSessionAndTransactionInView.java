package br.com.ifit.io;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = {"/*"})
public class OpenSessionAndTransactionInView implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            chain.doFilter(request, response);
            tx.commit();
        } catch (Exception e) {
            if (em.isOpen()) {
                tx.rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}
