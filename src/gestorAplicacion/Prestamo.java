package gestorAplicacion;

import java.time.LocalDate;

public class Prestamo implements Abonable{
    static double tasa = 0.18;
    double montoPrestado;
    double totalPagado;
    boolean pagado;
    private final LocalDate fechaInicio;
    private final LocalDate fechaFinal;
    Garantia garantia;

    public Prestamo(Usuario usuario, double montoPrestado, int periodos, String[] referencia) {
        this.montoPrestado = montoPrestado;
        this.totalPagado = 0;
        this.fechaFinal = LocalDate.now().plusMonths(periodos);
        this.fechaInicio = LocalDate.now();
    }

    public Prestamo(Usuario usuario, double montoPrestado, int periodos, String[] referencia, Garantia garantia) {
        this(usuario, montoPrestado, periodos, referencia);
        this.garantia = garantia;
    }

    /**
     * calculates the monthly payments that a user
     * can afford to pay for a loan based on their
     * monthly income and housing expenses.
     * It returns an array of two doubles: the first
     *  element is the number of payment periods, and
     *  the second element is the amount that the user
     *  can afford to pay each period.
     * 
     */
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

    /**
     * Calculates the number of payment periods
     * required to pay off a loan based on the
     * amount that the user can afford to pay
     * each period (`cantidadQuePodraPagar`)
     * and the total amount of the loan (`posibleCantidadPrestamo`).
     * It uses a mathematical formula to calculate
     * the number of periods.
     * The result is the number of payment periods required
     * to pay off the loan.
     * 
     */
    private static double calcularPeriodos(double cantidadQuePodraPagar, double posibleCantidadPrestamo) {
        return Math.log( Math.abs(((Prestamo.tasa*posibleCantidadPrestamo) / cantidadQuePodraPagar) - 1)) / Math.log(Math.abs(1+Prestamo.tasa));
    }

    public static double getTasa() {
        return tasa;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public Garantia getGarantia() {
        return garantia;
    }

    public void setGarantia(Garantia garantia) {
        this.garantia = garantia;
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

    @Override
    public Object abonar(double monto, Cuenta origen) {
        return null;
    }

    @Override
    public Object abonar(double monto, Categoria origen) {
        return null;
    }

    @Override
    public Transaccion terminar(Cuenta cuenta) {
        return null;
    }

    @Override
    public Transaccion terminar(Categoria categoria) {
        return null;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }
}
