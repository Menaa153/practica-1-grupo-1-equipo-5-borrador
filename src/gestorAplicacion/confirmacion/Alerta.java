package gestorAplicacion.confirmacion;
import gestorAplicacion.Categoria;

public class Alerta {

    public static String Incorrecto(double opcMin, double opcMax){
        return "PORFAVOR INGRESE UN DATO VALIDO,\n" +
        "RECUERDE USAR ',' EN CASO DE TENER DECIMALES \n" +
        "y que el valor este entre el "+ opcMin+" y el "+ opcMax;
    }

    public static String NoBolsillo(){
        return "El usuario no cuenta con bolsillos";
    }

    public static String NoPrestamo(){
        return "El usuario no cuenta con prestamos";
    }

    public static String NoAhorro(){
        return "El usuario no cuenta con ahorros";
    }

    public static String NoMeta(){
        return "El usuario no cuenta con metas";
    }

    public static String Excede(Categoria categoria){
        return "Advertencia!!, has destinado todo el presupuesto para "+ categoria.name();
    }

    public static String Insuficiente(){
        return "No tiene saldo suficiente para realizar este retiro";
    }


}
