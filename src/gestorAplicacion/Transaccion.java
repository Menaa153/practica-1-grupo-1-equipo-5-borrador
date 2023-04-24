package gestorAplicación;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Transaccion {
	private int monto;
	private LocalDate fechaCreacion;
	static private ArrayList<Transaccion> viajes = new ArrayList<>();
	static private ArrayList<Transaccion> salud = new ArrayList<>();
	static private ArrayList<Transaccion> alimentacion = new ArrayList<>();
	static private ArrayList<Transaccion> transporte = new ArrayList<>();
	static private ArrayList<Transaccion> educacion = new ArrayList<>();
	static private ArrayList<Transaccion> hogar = new ArrayList<>();
	static private ArrayList<Transaccion> entretenimiento = new ArrayList<>();
	static private ArrayList<Transaccion> imprevistos = new ArrayList<>();
	static private ArrayList<Transaccion> nulo = new ArrayList<>();
	
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
	

}
