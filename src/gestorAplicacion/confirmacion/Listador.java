package gestorAplicacion.confirmacion;

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;

//import gestorAplicacion.economia.Garantia;
//import gestorAplicacion.Prestamo;
import gestorAplicacion.Bolsillo;
import gestorAplicacion.Ahorro;
import gestorAplicacion.Meta;
import gestorAplicacion.Usuario;
import gestorAplicacion.Categoria;

public class Listador {
    //Se listan las garantias del sistema
    //public static void listarGarantias() {
    //    int j = 1;
    //    System.out.println("--------------------------------------------------------------------------");
    //    for (Garantia i : Garantia.values()) {
    //        System.out.println(j + ". " + i);
    //        j++;
    //    }
    //    System.out.println("--------------------------------------------------------------------------");
    //}
    
    //Se listan los bolsillos del usuario que pasa como referencia
    public static boolean listarBolsillos() {
        int j = 1;
    	System.out.println("--------------------------------------------------------------------------");
        for (Categoria i : Categoria.values()) {
            System.out.println(j + ". " + i);
            j++;
        }
    	System.out.println("--------------------------------------------------------------------------");
        return true;
    }
    
  //Se listan los Ahorros del usuario que se seleccionó en el login()
    public static boolean listarAhorros(Usuario usuario) {
        System.out.println("---------------------------------------------------------");
        if (!usuario.getAhorros().isEmpty()) {
            int j = 1;
            for (Ahorro i : usuario.getAhorros()) {
                System.out.println(j + ". " + i.getNombre() + "\t\tDisponible: " + String.format("%.2f",i.getSaldo()) + "\t\tFecha de retiro: " + i.getFechaRetiro() );
                j++;
                System.out.println("--------------------------------------------------------------------------");
            }
            return true;
        } else {
            System.out.println("EL USUARIO NO POSEE COLCHONES...\n");
            System.out.println("--------------------------------------------------------------------------");
            return false;
        }
    }
    
  //Se listan las metas del usuario que se seleccionó en el login()
    public static boolean listarMetas(Usuario usuario) {
        System.out.println("---------------------------------------------------------");
        if (!usuario.getMetas().isEmpty()) {
            int j = 1;
            for (Meta i : usuario.getMetas()) {
                System.out.println(j + ". " + i.getNombre() + "\t\tcumplido: "+(i.isCumplida()?"Si":"No")+"\t\tDisponible: " + String.format("%.2f",i.getSaldo())  + " cantidad objetivo: " + String.format("%.2f",i.getObjetivo()));
                j++;
                System.out.println("--------------------------------------------------------------------------");
            }
            return true;
        } else {
            System.out.println("EL USUARIO NO POSEE METAS...\n");
            System.out.println("--------------------------------------------------------------------------");
            return false;
        }
    }
    
  //Se listan los prestamos del usuario que se seleccionó en el login()
  //  public static boolean listarPrestamos(Usuario usuario) {
  //      System.out.println("---------------------------------------------------------");
  //      if (!usuario.getPrestamos().isEmpty()) {
  //          int j = 1;
  //          for (Prestamo i : usuario.getPrestamos()) {
  //              System.out.println(j + ". " + i.getClass().getSimpleName() + "\t\tcumplido: "+(i.isCumplida()?"Si":"No")+"\t\tValor: " + String.format("%.2f",i.getValorInicial()) + " cantidad Pagada: " + String.format("%.2f",i.getValorPagado()));
  //              j++;
  //              System.out.println("--------------------------------------------------------------------------");
  //          }
  //          return true;
  //      } else {
  //          System.out.println("EL USUARIO NO POSEE PRESTAMOS...\n");
  //          System.out.println("--------------------------------------------------------------------------");
  //          return false;
  //      }
  //  }
    
 
    
    //Retorna la cantidad de dias entre dos fechas, inicio: inicio el credito, fin: la fecha de pago (LocalDate.now() para obtener fecha del sistema)
    public static long diasEntreFechas(LocalDate inicio, LocalDate fin){
        return Math.abs(DAYS.between(inicio, fin));
    }
    //Retorna la cantidad de meses entre dos fechas, inicio: inicio el credito, fin: la fecha de pago (LocalDate.now() para obtener fecha del sistema)
    public static long mesesEntreFechas(LocalDate inicio, LocalDate fin){
        return Math.abs(MONTHS.between(inicio, fin));
    }
}
