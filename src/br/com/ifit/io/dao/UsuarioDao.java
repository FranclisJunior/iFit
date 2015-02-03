package br.com.ifit.io.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.ifit.exception.DAOException;
import br.com.ifit.model.Usuario;

public class UsuarioDao extends DaoGenericImpl<Usuario> implements IUsuarioDao, Serializable {

   
    public Usuario getPorLogin(String login) throws DAOException {    	
        Criteria criteria = this.getCriteria();
        Usuario resultado = null;
        try {
            criteria.add(Restrictions.eq("cpf", login).ignoreCase());
            resultado = (Usuario) criteria.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.READ_ERROR), e.getCause());
        }
        return resultado;
    }
    
    public boolean existeUsuarioPorCPF(String cpf) throws DAOException {
        Criteria criteria = this.getCriteria();
        Usuario resultado = null;
        try {
            criteria.add(Restrictions.eq("cpf", cpf).ignoreCase());
            resultado = (Usuario) criteria.uniqueResult();
            if (resultado != null) {
            	return true;
            } else {
            	return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.READ_ERROR), e.getCause());
        }
    }
    
    public Usuario getPorCpf(String cpf) throws DAOException {
    	System.out.println("chegou DAO");
        Criteria criteria = this.getCriteria();
        Usuario resultado = null;
        try {
            criteria.add(Restrictions.eq("cpf", cpf).ignoreCase());
            resultado = (Usuario) criteria.uniqueResult();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.READ_ERROR), e.getCause());
        }
        return resultado;
    }
    
    @Override
    public List<Usuario> buscar(String busca, String tipo) throws DAOException {
        Criteria criteria = this.getCriteria();
        List<Usuario> resultado = null;
        try {
            criteria.add(Restrictions.eq("tipo", tipo));
            criteria.add(Restrictions.like("nome", "%" + busca + "%").ignoreCase());
            resultado = (List<Usuario>) criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.READ_ERROR), e.getCause());
        }
        System.out.println("result" + resultado.size());
        return resultado;
    }
   

}