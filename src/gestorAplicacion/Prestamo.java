package gestorAplicacion;

public class Prestamo {
    double montoPrestado;
    double totalPagado;

    public Prestamo(double montoPrestado, double totalPagado) {
        this.montoPrestado = montoPrestado;
        this.totalPagado = totalPagado;
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
