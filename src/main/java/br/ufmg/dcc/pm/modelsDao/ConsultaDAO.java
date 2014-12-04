package br.ufmg.dcc.pm.modelsDao;

import java.util.Date;
import java.util.List;

import org.hibernate.AbstractDAO;
import org.hibernate.DataAccessLayerException;

import br.ufmg.dcc.pm.models.Cliente;
import br.ufmg.dcc.pm.models.Consulta;
import br.ufmg.dcc.pm.models.Medico;
import br.ufmg.dcc.pm.utils.DateUtils;


public class ConsultaDAO extends AbstractDAO {
	
	private String ENTITY_NAME = Consulta.class.getName();
	
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
    
    @SuppressWarnings("unchecked")
	public List<Consulta> findAllByMedico(Medico medico) throws DataAccessLayerException{
        String sql = "FROM " + ENTITY_NAME + " WHERE medico_id = " + medico.getId();
    	return (List<Consulta>) super.createQuery(sql);
    }
     
	@SuppressWarnings("unchecked")
	public List<Consulta> findAllByDateAndMedico(Date date, Medico medico) throws DataAccessLayerException{
		Date beginin = DateUtils.getBegginOfDay(date);
		Date endin = DateUtils.getEndOfDay(date);
		
        String sql = "FROM " + ENTITY_NAME + " WHERE aprovado = 1 AND medico_id = " + medico.getId() + " AND data between " + beginin.getTime() + " AND " + endin.getTime();
    	return (List<Consulta>) super.createQuery(sql);
    }
	
	@SuppressWarnings("unchecked")
	public int getNumberOf(String tipo) throws DataAccessLayerException{ 
		String sql = "FROM Consulta WHERE tipo = '" + tipo + "'";
		List<Consulta> c = (List<Consulta>) super.createQuery(sql);
    	return c.size();
	}
	
	@SuppressWarnings("unchecked")
	public List<Consulta> findByDateAndCliente(Date date, Cliente cliente) throws DataAccessLayerException{
		String sql = "FROM " + ENTITY_NAME + " WHERE data = " + date.getTime() + " AND cliente_id = " + cliente.getId();
		return (List<Consulta>) super.createQuery(sql);
	}

}