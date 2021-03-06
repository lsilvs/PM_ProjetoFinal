package br.ufmg.dcc.pm.modelsDao;

import java.util.Date;
import java.util.List;

import org.hibernate.AbstractDAO;
import org.hibernate.DataAccessLayerException;

import br.ufmg.dcc.pm.models.Cliente;
import br.ufmg.dcc.pm.models.Exame;
import br.ufmg.dcc.pm.models.TipoExame;
import br.ufmg.dcc.pm.utils.DateUtils;


public class ExameDAO extends AbstractDAO {
    private final static String ENTITY_NAME = Exame.class.getName();


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
    
    @SuppressWarnings("unchecked")
	public List<Date> getScheduleByExame(TipoExame tipoExame, Date date) throws DataAccessLayerException{
		Date beginin = DateUtils.getBegginOfDay(date);
		Date endin = DateUtils.getEndOfDay(date);
		
        String sql = "FROM " + Exame.class.getName() + " WHERE tipoExame_id = " + tipoExame.getId() + " AND data between " + beginin.getTime() + " AND " + endin.getTime();
        List<Exame> exames = (List<Exame>) super.createQuery(sql);
        
        List<Date> datas = null;
        for (Exame exame : exames)
        	datas.add(exame.getData());
        
    	return datas;
    }

    @SuppressWarnings("unchecked")
	public List<Exame> findAllByDateAndTipoExame(Date date, TipoExame tipoExame) throws DataAccessLayerException{
		Date beginin = DateUtils.getBegginOfDay(date);
		Date endin = DateUtils.getEndOfDay(date);
		
        String sql = "FROM " + ENTITY_NAME + " WHERE aprovado = 1 AND tipoExame_id = " + tipoExame.getId() + " AND data between " + beginin.getTime() + " AND " + endin.getTime();
    	return (List<Exame>) super.createQuery(sql);
    }

    @SuppressWarnings("unchecked")
	public List<Exame> findByDateAndCliente(Date date, Cliente cliente) throws DataAccessLayerException{
		String sql = "FROM " + ENTITY_NAME + " WHERE data = " + date.getTime() + " AND cliente_id = " + cliente.getId();
		return (List<Exame>) super.createQuery(sql);
	}
    

	@SuppressWarnings("unchecked")
	public int getNumberOf(String tipo) throws DataAccessLayerException{ 
		String sql = "FROM " + ENTITY_NAME  + " WHERE tipo = '" + tipo + "'";
		List<Exame> c = (List<Exame>) super.createQuery(sql);
    	return c.size();
	}
	

}