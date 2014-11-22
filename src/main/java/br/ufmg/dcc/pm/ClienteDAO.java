package br.ufmg.dcc.pm;

import java.util.List;

import org.hibernate.AbstractDAO;
import org.hibernate.DataAccessLayerException;


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
    public Cliente find(Long id) throws DataAccessLayerException {
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

}