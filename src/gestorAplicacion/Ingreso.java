package gestorAplicacion;
import java.util.ArrayList;
import java.time.LocalDate;

public class Ingreso extends Transaccion{
	private Cuenta cuentaOrigen;
	private Cuenta cuentaDestino;
	private Categoria categoria;

	public Ingreso(int monto, LocalDate fechaCreacion, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
		this (monto, fechaCreacion, cuentaOrigen, cuentaDestino, Categoria.Nulo);
	}
	public Ingreso (int monto, LocalDate fechaCreacion, Cuenta cuentaOrigen, Cuenta cuentaDestino, Categoria categoria) {
		super(monto, fechaCreacion);
		this.cuentaOrigen=cuentaOrigen;
		this.cuentaDestino=cuentaDestino;
		this.categoria=categoria;
		
	if (categoria==Categoria.Viajes) {
		Transaccion.viajes.add(this);
	}
	if (categoria==Categoria.Salud) {
		Transaccion.salud.add(this);
	}
	if (categoria==Categoria.Alimentacion) {
		Transaccion.alimentacion.add(this);
	}
	if (categoria==Categoria.Transporte) {
		Transaccion.transporte.add(this);
	}
	if (categoria==Categoria.Educacion) {
		Transaccion.educacion.add(this);
	}
	if (categoria==Categoria.Hogar) {
		Transaccion.hogar.add(this);
	}
	if (categoria==Categoria.Entretenimiento) {
		Transaccion.entretenimiento.add(this);
	}
	if (categoria==Categoria.Imprevistos) {
		Transaccion.imprevistos.add(this);
	}
	if (categoria==Categoria.Nulo) {
		Transaccion.nulo.add(this);
	}
	
	}
	//getters y setters
	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.cuentaOrigen=cuentaOrigen;
	}
	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.cuentaDestino=cuentaDestino;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria=categoria;
	}
	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}
	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}
	public Categoria getCategoria() {
		return categoria;
	}
}
