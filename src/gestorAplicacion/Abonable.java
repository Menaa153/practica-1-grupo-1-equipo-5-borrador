package gestorAplicacion;

public interface Abonable <T> {
	T abonar(double monto, Cuenta origen);
    T abonar(double monto, Categoria origen);
    Transaccion terminar(Cuenta cuenta);
    Transaccion terminar(Categoria categoria);
}
