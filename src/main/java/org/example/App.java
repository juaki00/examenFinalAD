package org.example;

import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


/**
 * Hello world!
 *
 */
public class App{

    private static final EntityManagerFactory emf;

    static{
        emf = Persistence.createEntityManagerFactory("data.odb");
    }
    public static void main( String[] args )
    {
        ClienteDAO dao = new ClienteDAO();

        Cliente cliente1 = new Cliente(1L,"Joaquin",1L,"activo");
        dao.insertarCliente( cliente1 );
        Cliente cliente2 = new Cliente(2L,"Adrian",5L,"inactivo");
        dao.insertarCliente( cliente2 );
        Cliente cliente3 = new Cliente(3L,"Paco",10L,"activo");
        dao.insertarCliente( cliente3 );
        Cliente cliente4 = new Cliente(4L,"Santiago",15L,"inactivo");
        dao.insertarCliente( cliente4 );
        Cliente cliente5 = new Cliente(5L,"Victor",21L,"activo");
        dao.insertarCliente( cliente5 );

        System.out.println( "Estadisticas:" );
        dao.estadisticas();
        System.out.println( "Mejores clientes:" );
        dao.listarMejoresClientes( 10L );
        System.out.println( "Get cliente numero 3:" );
        dao.getCliente( 3L );
    }

}
