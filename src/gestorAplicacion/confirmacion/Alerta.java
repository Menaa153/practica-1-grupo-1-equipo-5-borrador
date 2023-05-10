package gestorAplicacion.confirmacion;

public abstract class Alerta {

    public static String Incorrecto(double opcMin, double opcMax){
        return "PORFAVOR INGRESE UN DATO VALIDO,\n" +
        "RECUERDE USAR ',' EN CASO DE TENER DECIMALES \n" +
        "y que el valor este entre el "+ opcMin+" y el "+ opcMax;
    }


}
