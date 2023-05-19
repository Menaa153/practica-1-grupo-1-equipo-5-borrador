package gestorAplicacion;
import java.time.LocalDate;


public abstract class Transaccion {
	private int monto;
	private LocalDate fechaCreacion;
	protected Transaccion(int monto, LocalDate fechaCreacion) {
		this.monto=monto;
		this.fechaCreacion=fechaCreacion;
	}
	//setters y getters
	public void setMonto(int monto) {
		this.monto=monto;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion=fechaCreacion;
	}
	public int getMonto() {
		return monto;
	}
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	

}
