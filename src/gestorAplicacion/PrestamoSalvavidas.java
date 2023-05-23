package gestorAplicacion;

import java.time.LocalDate;

public class PrestamoSalvavidas extends Prestamo {

    private static final long serialVersionUID = -3388280595710146580L;

    public PrestamoSalvavidas(Usuario usuario, double valorInicial, LocalDate fechaInicio) {
        super(usuario,valorInicial, 6, fechaInicio, fechaInicio.plusMonths(6), Garantia.values()[opcGarantia]);
        setTEA(valorInicial);
    }

    @Override
    protected void setTEA(double monto) {
        if (monto < 1000000) {
            super.TEA = super.baseTEAAlto;
        } else {
            super.TEA = super.baseTEABajo;
        }
    }

    @Override
    protected void setTEA(double monto, Garantia garantia) {
        this.setTEA(monto);
    }

    public double getTEA() {
        return TEA;
    }
}
