package br.ufmg.dcc.pm.modelsDao;

import java.util.List;

import org.hibernate.AbstractDAO;
import org.hibernate.DataAccessLayerException;

import br.ufmg.dcc.pm.models.Especialidade;


public class EspecialidadeDAO extends AbstractDAO {
    public EspecialidadeDAO() {
        super();
    }

    /**
     * Insert a new especialidade into the database.
     * @param especialidade
     */
    public void create(Especialidade especialidade) throws DataAccessLayerException {
        super.saveOrUpdate(especialidade);
    }


    /**
     * Delete a detached especialidade from the database.
     * @param especialidade
     */
    public void delete(Especialidade especialidade) throws DataAccessLayerException {
        super.delete(especialidade);
    }

    /**
     * Find an especialidade by its primary key.
     * @param i
     * @return
     */
    public Especialidade find(int i) throws DataAccessLayerException {
        return (Especialidade) super.find(Especialidade.class, i);
    }

    /**
     * Updates the state of a detached especialidade.
     *
     * @param especialidade
     */
    public void update(Especialidade especialidade) throws DataAccessLayerException {
        super.saveOrUpdate(especialidade);
    }

    /**
     * Saves or Updates the state of a detached especialidade.
     *
     * @param especialidade
     */
    public void saveOrUpdate(Especialidade especialidade) throws DataAccessLayerException {
        super.saveOrUpdate(especialidade);
    }

    /**
     * Finds all especialidades in the database.
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Especialidade> findAll() throws DataAccessLayerException{
        return (List<Especialidade>) super.findAll(Especialidade.class);
    }

}