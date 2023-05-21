package gestorAplicacion;

public class Prestamo {
    static double tasa = 0.18;
    double montoPrestado;
    double totalPagado;

    public Prestamo(double montoPrestado, double totalPagado) {
        this.montoPrestado = montoPrestado;
        this.totalPagado = totalPagado;
    }

    public static double[] calcularCuotas(double posibleCantidadPrestamo, double ingresoMensual, double gastoVivienda) {
        double[] infoCuotas = new double[2];
        infoCuotas[0] = 0; // Periodos
        infoCuotas[1] = 0; // cantidadQuePodraPagar
        double cantidadQuePodraPagar = ingresoMensual * 0.3;

        if (gastoVivienda >= ingresoMensual) {
            return infoCuotas;
        }
        double porcentajeGastoVivienda = gastoVivienda / ingresoMensual;
        if (porcentajeGastoVivienda > 0.7) {
            return infoCuotas;
        }
        if (porcentajeGastoVivienda > 0.5) {
            cantidadQuePodraPagar = ingresoMensual * 0.1;
            infoCuotas[0] = calcularPeriodos(cantidadQuePodraPagar, posibleCantidadPrestamo);
            infoCuotas[1] = cantidadQuePodraPagar;
            return infoCuotas;
        }
        infoCuotas[0] = calcularPeriodos(cantidadQuePodraPagar, posibleCantidadPrestamo);
        infoCuotas[1] = cantidadQuePodraPagar;
        return  infoCuotas;
    }

    private static double calcularPeriodos(double cantidadQuePodraPagar, double posibleCantidadPrestamo) {
        return Math.log( Math.abs(((Prestamo.tasa*posibleCantidadPrestamo) / cantidadQuePodraPagar) - 1)) / Math.log(Math.abs(1+Prestamo.tasa));
    }

    public double getMontoPrestado() {
        return montoPrestado;
    }

    public void setMontoPrestado(double montoPrestado) {
        this.montoPrestado = montoPrestado;
    }

    public double getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(double totalPagado) {
        this.totalPagado = totalPagado;
    }
}
