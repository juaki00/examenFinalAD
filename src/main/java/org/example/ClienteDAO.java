package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private static final EntityManagerFactory emf;

    static{
        emf = Persistence.createEntityManagerFactory( "data.odb");
    }

    public void insertarCliente(Cliente c){
        //EntityManager
        EntityManager em = emf.createEntityManager();

        try {
            //begin transaction
            em.getTransaction( ).begin( );
            //persist + commit
            em.persist( c );
            em.getTransaction().commit();

        }
        finally {
            //close entity manager
            em.close();
        }
    }

    public void getCliente(Long id){
        //EntityManager
        EntityManager em = emf.createEntityManager();

        try {
            //begin transaction
            em.getTransaction( ).begin( );
            TypedQuery<Cliente> query = em.createQuery( "select c from Cliente c", Cliente.class);
            List<Cliente> resultados = query.getResultList();
            Cliente resultado = new Cliente(  );
            for(Cliente c: resultados){
                if(c.getId().equals( id )){
                    resultado = c;
                }
            }
            System.out.println( resultado );
        }
        finally {
            //close entity manager
            em.close();
        }
    }

    public void listarMejoresClientes(Long cantidad){
        //EntityManager
        EntityManager em = emf.createEntityManager();

        try {
            //begin transaction
            em.getTransaction( ).begin( );
            TypedQuery<Cliente> query = em.createQuery( "select c from Cliente c", Cliente.class);
            List<Cliente> todosClientes = query.getResultList();
            List<Cliente> mejores = new ArrayList<>(  );
            for(Cliente c: todosClientes){
                if(c.getTotalVentas() > cantidad && c.getEstado().equals( "activo" ) ){
                    mejores.add( c );
                }
            }
            for(Cliente c: mejores){
                System.out.println( c );
            }
        }
        finally {
            //close entity manager
            em.close();
        }
    }

    public void estadisticas(){
        //EntityManager
        EntityManager em = emf.createEntityManager();
        Long total = 0L;
        Double promedio = 0.0;
        Double clientesActivos = 0.0;
        Long cantidad = 0L;

        try {
            //begin transaction
            em.getTransaction( ).begin( );
            TypedQuery<Cliente> query = em.createQuery( "select c from Cliente c", Cliente.class);
            List<Cliente> todosClientes = query.getResultList();
            for(Cliente c: todosClientes){
                total += c.getTotalVentas();
                if(c.getEstado().equals( "activo" )){
                    clientesActivos ++;
                    promedio += c.getTotalVentas();
                }
                else{
                    if(c.getTotalVentas()>0){
                        cantidad++;
                    }
                }
                System.out.println( c );
            }
            promedio = promedio / clientesActivos;
            System.out.println( "Total: " + total );
            System.out.println( "Promedio: " + promedio );
            System.out.println( "Cantidad: " + cantidad );
        }
        finally {
            //close entity manager
            em.close();
        }
    }
}
