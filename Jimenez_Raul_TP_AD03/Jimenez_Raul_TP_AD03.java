package Jimenez_Raul_TP_AD03;

import empresaz.util.HibernateUtil;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author RadW 
 */
public class Jimenez_Raul_TP_AD03 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**  
        * Desactivar mensajes de Hibernate en consola
        */
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        
        /* 2.a)Para cada departamento, el salario máximo y mínimo 
        (se muestra el código, nombre y localidad del departamento y 
        los salarios máximo y mínimo). */
        deptMaxMin();
        
        
        /* 2.b)Para cada departamento, total de empleados por oficio 
        (se muestra el código del departamento, oficio y total de empleados) */
        deptEmpOfi();
        
    }

    private static void deptMaxMin() {
        Session sesion;
        //obtiene una sesion
        SessionFactory sf = HibernateUtil.getSessionFactory();
        //abre la sesion
        sesion = sf.openSession();
        
            
            Query listaDep = sesion.createQuery(""
                    + "select departamentos.deptNo ,  departamentos.dnombre ,"
                    + " departamentos.loc,  Max(salario), Min(salario) from Empleados"
                    + " group by departamentos.deptNo ");
            Iterator<?> iterDep = listaDep.iterate(); //objeto Iterador sobre la consulta Deparpamento
            //Iterator<?> iterPro = listaEmp.iterate();//objeto iterador sobre la consulta Proyecto
            System.out.println("====== Salarios Máximo y Mínimo Por Departamento =========");
            while(iterDep.hasNext()){ //mientras haya filas de la consulsta sobre Departamento
                //extraer array de objetos
                Object[] foo = (Object[]) iterDep.next();
                //asigna cada parte correspondiente tipo de objeto
                System.out.println("====== INFO DEP "+foo[1] +"==========");
                
                System.out.println("Código: " + foo[0] );
                System.out.println("Nombre: +" + foo[1] );
                System.out.println("Localidad: +" + foo[2] );
                System.out.println("Salario Máximo: " + foo[3] );
                System.out.println("Salario Mínimo: " + foo[4] );                    

               
            } 
        //sf.close();
        sesion.close();
        System.out.println("\nFIN DE LA CONSULTA \n\n " );  
    
    
    }

    private static void deptEmpOfi() {
        
        Session sesion;
        //obtiene una sesion
        SessionFactory sf = HibernateUtil.getSessionFactory();
        //abre la sesion
        sesion = sf.openSession();
        
            
            Query listaDep = sesion.createQuery(""
                    + " select departamentos.deptNo , oficio , COUNT(empNo) "
                    + "from Empleados group by oficio ");
            Iterator<?> iterDep = listaDep.iterate(); //objeto Iterador sobre la consulta 
            
            System.out.println("====== Número de Empleados por Oficio y Departamento =========");
            while(iterDep.hasNext()){ //mientras haya filas de la consulsta sobre Departamento
                //extraer array de objetos
                Object[] foo = (Object[]) iterDep.next();
                //asigna cada parte correspondiente tipo de objeto
                System.out.println("====== INFO OFICIO " + foo[1] +"==========");
                                
                System.out.println("Código Dept: " + foo[0] );
                
                System.out.println("Número empleados: +" + foo[2]  );
                
            }
        sesion.close();
        System.out.println("\nFIN DE LA CONSULTA\n\n " );
    }
    
}
