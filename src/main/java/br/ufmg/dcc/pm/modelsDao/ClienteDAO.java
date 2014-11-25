package br.ufmg.dcc.pm.modelsDao;

import java.util.List;

import org.hibernate.AbstractDAO;
import org.hibernate.DataAccessLayerException;
import org.hibernate.HibernateFactory;
import org.hibernate.criterion.Restrictions;

import br.ufmg.dcc.pm.models.Cliente;


public class ClienteDAO extends AbstractDAO {
    public ClienteDAO() {
        super();
    }

    /**
     * Insert a new cliente into the database.
     * @param cliente
     */
    public void create(Cliente cliente) throws DataAccessLayerException {
        super.saveOrUpdate(cliente);
    }


    /**
     * Delete a detached cliente from the database.
     * @param cliente
     */
    public void delete(Cliente cliente) throws DataAccessLayerException {
        super.delete(cliente);
    }

    /**
     * Find an cliente by its primary key.
     * @param id
     * @return
     */
    public Cliente find(int id) throws DataAccessLayerException {
        return (Cliente) super.find(Cliente.class, id);
    }

    /**
     * Updates the state of a detached cliente.
     *
     * @param cliente
     */
    public void update(Cliente cliente) throws DataAccessLayerException {
        super.saveOrUpdate(cliente);
    }

    /**
     * Saves or Updates the state of a detached cliente.
     *
     * @param cliente
     */
    public void saveOrUpdate(Cliente cliente) throws DataAccessLayerException {
        super.saveOrUpdate(cliente);
    }

    /**
     * Finds all clientes in the database.
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Cliente> findAll() throws DataAccessLayerException{
        return (List<Cliente>) super.findAll(Cliente.class);
    }
    
    public boolean jaCadastrado(String numCpf) throws DataAccessLayerException{
    	HibernateFactory.getSessionFactory().openSession().createCriteria( Cliente.class ).
    	           add( Restrictions.eq("numCPF", numCpf) ).
    	           uniqueResult();
    	return true; 
    	
    	
    }

}