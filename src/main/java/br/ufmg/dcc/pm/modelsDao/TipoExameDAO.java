package br.ufmg.dcc.pm.modelsDao;

import java.util.List;

import org.hibernate.AbstractDAO;
import org.hibernate.DataAccessLayerException;

import br.ufmg.dcc.pm.models.TipoExame;


public class TipoExameDAO extends AbstractDAO {
    public TipoExameDAO() {
        super();
    }

    /**
     * Insert a new tipoExame into the database.
     * @param tipoExame
     */
    public void create(TipoExame tipoExame) throws DataAccessLayerException {
        super.saveOrUpdate(tipoExame);
    }


    /**
     * Delete a detached tipoExame from the database.
     * @param tipoExame
     */
    public void delete(TipoExame tipoExame) throws DataAccessLayerException {
        super.delete(tipoExame);
    }

    /**
     * Find an tipoExame by its primary key.
     * @param i
     * @return
     */
    public TipoExame find(int i) throws DataAccessLayerException {
        return (TipoExame) super.find(TipoExame.class, i);
    }

    /**
     * Updates the state of a detached tipoExame.
     *
     * @param tipoExame
     */
    public void update(TipoExame tipoExame) throws DataAccessLayerException {
        super.saveOrUpdate(tipoExame);
    }

    /**
     * Saves or Updates the state of a detached tipoExame.
     *
     * @param tipoExame
     */
    public void saveOrUpdate(TipoExame tipoExame) throws DataAccessLayerException {
        super.saveOrUpdate(tipoExame);
    }

    /**
     * Finds all tipoExames in the database.
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<TipoExame> findAll() throws DataAccessLayerException{
        return (List<TipoExame>) super.findAll(TipoExame.class);
    }

}