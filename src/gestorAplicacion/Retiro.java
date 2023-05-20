package gestorAplicacion;
import java.time.LocalDate;

public class Retiro extends Transaccion{
	private Cuenta cuentaOrigen;
	private Cuenta cuentaDestino;
	private Categoria categoria;

	public Retiro(int monto, LocalDate fechaCreacion, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
		this (monto, fechaCreacion, cuentaOrigen, cuentaDestino, Categoria.Nulo);
	}

	public Retiro (int monto, LocalDate fechaCreacion, Cuenta cuentaDestino, Categoria categoria){
		this (monto, fechaCreacion, null, cuentaDestino, categoria);

	}

	public Retiro(int monto, LocalDate fechaCreacion,Cuenta cuentaDestino){
		this (monto, fechaCreacion, null, cuentaDestino, Categoria.Nulo);
	}

	public Retiro (int monto, LocalDate fechaCreacion, Categoria categoria){
		this (monto, fechaCreacion, null, null, categoria);

	}

	public Retiro (int monto, LocalDate fechaCreacion, Cuenta cuentaOrigen, Cuenta cuentaDestino, Categoria categoria) {
		super(monto, fechaCreacion);
		this.cuentaOrigen=cuentaOrigen;
		this.cuentaDestino=cuentaDestino;
		this.categoria=categoria;

		categoria.setSaldo(categoria.getSaldo()-monto);
		
	 
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


