package br.ufmg.dcc.pm.modelsDao;

import java.util.List;

import org.hibernate.AbstractDAO;
import org.hibernate.DataAccessLayerException;

import br.ufmg.dcc.pm.models.Medico;


public class MedicoDAO extends AbstractDAO {
    public MedicoDAO() {
        super();
    }

    /**
     * Insert a new Medico into the database.
     * @param Medico
     */
    public void create(Medico medico) throws DataAccessLayerException {
        super.saveOrUpdate(medico);
    }


    /**
     * Delete a detached Medico from the database.
     * @param Medico
     */
    public void delete(Medico medico) throws DataAccessLayerException {
        super.delete(medico);
    }

    /**
     * Find an Medico by its primary key.
     * @param id
     * @return
     */
    public Medico find(Long id) throws DataAccessLayerException {
        return (Medico) super.find(Medico.class, id);
    }

    /**
     * Updates the state of a detached Medico.
     *
     * @param Medico
     */
    public void update(Medico medico) throws DataAccessLayerException {
        super.saveOrUpdate(medico);
    }

    /**
     * Saves or Updates the state of a detached Medico.
     *
     * @param Medico
     */
    public void saveOrUpdate(Medico medico) throws DataAccessLayerException {
        super.saveOrUpdate(medico);
    }

    /**
     * Finds all Medicos in the database.
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Medico> findAll() throws DataAccessLayerException{
        return (List<Medico>) super.findAll(Medico.class);
    }

}