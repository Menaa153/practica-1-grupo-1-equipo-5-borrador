package gestorAplicacion;
import java.time.LocalDate;
import java.util.ArrayList;

package gestorAplicación.classpropuestas;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Transaccion {
	private int monto;
	private LocalDate fechaCreacion;
	protected static ArrayList<Transaccion> viajes = new ArrayList();
	protected static ArrayList<Transaccion> salud = new ArrayList();
	protected static ArrayList<Transaccion> alimentacion = new ArrayList();
	protected static ArrayList<Transaccion> transporte = new ArrayList();
	protected static ArrayList<Transaccion> educacion = new ArrayList();
	protected static ArrayList<Transaccion> hogar = new ArrayList();
	protected static ArrayList<Transaccion> entretenimiento = new ArrayList();
	protected static ArrayList<Transaccion> imprevistos = new ArrayList();
	protected static ArrayList<Transaccion> nulo = new ArrayList();
	
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
