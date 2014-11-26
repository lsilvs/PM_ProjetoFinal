package br.ufmg.dcc.pm.modelsDao;

import java.util.List;

import org.hibernate.AbstractDAO;
import org.hibernate.DataAccessLayerException;

import br.ufmg.dcc.pm.models.Consulta;
import br.ufmg.dcc.pm.models.Medico;


public class ConsultaDAO extends AbstractDAO {
    public ConsultaDAO() {
        super();
    }

    /**
     * Insert a new Consulta into the database.
     * @param Consulta
     */
    public void create(Consulta consulta) throws DataAccessLayerException {
        super.saveOrUpdate(consulta);
    }


    /**
     * Delete a detached Consulta from the database.
     * @param Consulta
     */
    public void delete(Consulta consulta) throws DataAccessLayerException {
        super.delete(consulta);
    }

    /**
     * Find an Consulta by its primary key.
     * @param id
     * @return
     */
    public Consulta find(int id) throws DataAccessLayerException {
        return (Consulta) super.find(Consulta.class, id);
    }

    /**
     * Updates the state of a detached Consulta.
     *
     * @param Consulta
     */
    public void update(Consulta consulta) throws DataAccessLayerException {
        super.saveOrUpdate(consulta);
    }

    /**
     * Saves or Updates the state of a detached Consulta.
     *
     * @param Consulta
     */
    public void saveOrUpdate(Consulta consulta) throws DataAccessLayerException {
        super.saveOrUpdate(consulta);
    }

    /**
     * Finds all Consultas in the database.
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Consulta> findAll() throws DataAccessLayerException{
        return (List<Consulta>) super.findAll(Consulta.class);
    }
    
    /**
     * Finds all Consultas in the database.
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Consulta> findAllByMedico(Medico medico) throws DataAccessLayerException{
        String sql = "FROM " + Consulta.class.getName() + " WHERE medico_id = " + medico.getId();
    	return (List<Consulta>) super.createQuery(sql);
    }

}