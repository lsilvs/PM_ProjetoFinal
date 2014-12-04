package br.ufmg.dcc.pm.modelsDao;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.hibernate.AbstractDAO;
import org.hibernate.DataAccessLayerException;

import br.ufmg.dcc.pm.models.Exame;
import br.ufmg.dcc.pm.models.Medico;
import br.ufmg.dcc.pm.models.TipoExame;


public class ExameDAO extends AbstractDAO {
    public ExameDAO() {
        super();
    }

    /**
     * Insert a new exame into the database.
     * @param exame
     */
    public void create(Exame exame) throws DataAccessLayerException {
        super.saveOrUpdate(exame);
    }


    /**
     * Delete a detached exame from the database.
     * @param exame
     */
    public void delete(Exame exame) throws DataAccessLayerException {
        super.delete(exame);
    }

    /**
     * Find an exame by its primary key.
     * @param i
     * @return
     */
    public Exame find(int i) throws DataAccessLayerException {
        return (Exame) super.find(Exame.class, i);
    }

    /**
     * Updates the state of a detached exame.
     *
     * @param exame
     */
    public void update(Exame exame) throws DataAccessLayerException {
        super.saveOrUpdate(exame);
    }

    /**
     * Saves or Updates the state of a detached exame.
     *
     * @param exame
     */
    public void saveOrUpdate(Exame exame) throws DataAccessLayerException {
        super.saveOrUpdate(exame);
    }

    /**
     * Finds all exames in the database.
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Exame> findAll() throws DataAccessLayerException{
        return (List<Exame>) super.findAll(Exame.class);
    }

	@SuppressWarnings({ "unchecked", "null" })
	public List<Date> getScheduleByExame(TipoExame tipo) {
		String sql = "FROM atendimento WHERE DTYPE = 'EXAME' and tipoExame_id = "+tipo.getId();
		List<Exame> exames = (List<Exame>) super.createQuery(sql);
		List<Date> datas = null;
		for (Exame exame : exames)
			datas.add(exame.getData());
		return datas;
	}
	
//	public Boolean dataRegistrada(TipoExame tipo, Date newData) throws DataAccessLayerException {
//		String sql = "FROM " + TipoExame.class.getName() + " WHERE DTYPE = 'EXAME' and tipoExame_id = "+tipo.getId()+" and data = '"+newData+"'";
//		List<Exame> all = (List<Exame>) super.createQuery("SELECT * FROM atendimento );
//		List<Exame> exames = (List<Exame>) super.createQuery(sql);
//		return  all != null;
//    }

}