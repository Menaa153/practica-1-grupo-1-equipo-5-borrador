package gestorAplicacion.economia;

import gestorAplicacion.Garantia;
import gestorAplicacion.Prestamo;
import gestorAplicacion.Usuario;

import java.time.LocalDate;

public class PrestamoPropulsor extends Prestamo {

    private static final long serialVersionUID = 1L;

    private String[] referencia;

    private Garantia garantia;

    public PrestamoPropulsor(Usuario usuario, double valorIncial, int tiempo, LocalDate fechaInicio,
                             String[] referencia, Garantia garantia) {
        super(usuario, valorIncial, tiempo, fechaInicio, fechaInicio.plusMonths(tiempo), Garantia.values()[opcGarantia]);
        setReferencia(referencia);
        setGarantia(garantia);
        setTEA(valorIncial, garantia);
    }

    public PrestamoPropulsor(Usuario usuario, double valorIncial, int tiempo, LocalDate fechaInicio, Divisa divisa,
                             String[] referencia) {
        super(usuario, valorIncial, tiempo, fechaInicio, fechaInicio.plusMonths(tiempo), divisa);
        setReferencia(referencia);
        setGarantia(null);
        setTEA(valorIncial);
    }

    public String[] getReferencia() {
        return referencia;
    }

    public void setReferencia(String[] referencia) {
        this.referencia = referencia;
    }

    @Override
    protected void setTEA(double monto) {
        this.setTEA(monto, null);
    }

    @Override
    protected void setTEA(double monto, Garantia garantia) {
        double acumulado = 0;
        if (monto < 5000000) {
            acumulado += super.baseTEAAlto - 30;
        } else {
            acumulado += super.baseTEABajo - 30;
        }
        if (garantia != null) {
            switch (garantia) {
                case Vivienda -> acumulado += 6;
                case Lote -> acumulado += 9;
                case Carro -> acumulado += 16;
                case Moto -> acumulado += 22;
            }
        } else {
            acumulado += 30;
        }

        super.TEA = acumulado;

    }

    public double getTEA() {
        return TEA;
    }

    public Garantia getGarantia() {
        return garantia;
    }

    public void setGarantia(Garantia garantia) {
        this.garantia = garantia;
    }

}
