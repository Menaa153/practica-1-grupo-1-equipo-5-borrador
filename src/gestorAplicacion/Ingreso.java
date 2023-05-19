package gestorAplicacion;
import java.util.ArrayList;
import java.time.LocalDate;

import gestorAplicacion.confirmacion.Alerta;

public class Ingreso extends Transaccion{
	private Cuenta cuentaOrigen;
	private Cuenta cuentaDestino;
	private Categoria categoria;

	public Ingreso(int monto, LocalDate fechaCreacion, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
		this (monto, fechaCreacion, cuentaOrigen, cuentaDestino, Categoria.Nulo);
	}

	public Ingreso (int monto, LocalDate fechaCreacion, Cuenta cuentaDestino, Categoria categoria){
		this (monto, fechaCreacion, null, cuentaDestino, categoria);

	}

	public Ingreso(int monto, LocalDate fechaCreacion,Cuenta cuentaDestino){
		this (monto, fechaCreacion, null, cuentaDestino, Categoria.Nulo);
	}

	public Ingreso (int monto, LocalDate fechaCreacion, Categoria categoria){
		this (monto, fechaCreacion, null, null, categoria);

	}

	public Ingreso (int monto, LocalDate fechaCreacion, Cuenta cuentaOrigen, Cuenta cuentaDestino, Categoria categoria) {
		super(monto, fechaCreacion);
		this.cuentaOrigen=cuentaOrigen;
		this.cuentaDestino=cuentaDestino;
		this.categoria=categoria;

		categoria.setSaldo(categoria.getSaldo()+monto);

		if (categoria!=Categoria.Nulo){
		  if (categoria.getSaldo()>=categoria.getPresupuesto()){
			System.out.println(Alerta.Excede(categoria));
		  }
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
