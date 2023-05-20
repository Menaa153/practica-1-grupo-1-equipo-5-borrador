package gestorAplicacion;
import java.time.LocalDate;
import java.io.Serializable;


public abstract class Transaccion implements Serializable{

	
	private static final long serialVersionUID = 3102227367414792060L;


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
