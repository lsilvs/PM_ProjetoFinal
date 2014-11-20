package br.ufmg.dcc.pm;

import java.util.List;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class App {
    private static SessionFactory sessionFactory = null; 
    private static ServiceRegistry serviceRegistry = null; 
    
    private static SessionFactory configureSessionFactory() throws HibernateException { 
        Configuration configuration = new Configuration(); 
        configuration.configure(); 
        
        Properties properties = configuration.getProperties();
        
        serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();         
        sessionFactory = configuration.buildSessionFactory(serviceRegistry); 
        
        return sessionFactory; 
    }
    
    public static void main(String[] args) {
        configureSessionFactory();
        
        Session session = null;
        Transaction tx=null;
        
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            
            Cliente myCliente1 = new Cliente("Lucia Santos Miranda", "lucimi@email.com");
            Cliente myCliente2 = new Cliente("Carlos Antunes Goncalves", "caango@email.com");
            
            session.saveOrUpdate(myCliente1);
            session.saveOrUpdate(myCliente2);
            
            session.flush();
            tx.commit();
            
            List<Cliente> ClienteList = session.createQuery("from Cliente").list();
            
            for (Cliente Cliente : ClienteList) {
                System.out.println("Id: " + Cliente.getId() + " | Name:" + Cliente.getName() + " | Email:" + Cliente.getEmail());
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally{
            if(session != null) {
                session.close();
            }
        }
    }
}