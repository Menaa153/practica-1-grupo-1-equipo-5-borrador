package gestorAplicación.classpropuestas;
import java.time.LocalDate;
import java.ArrayList;

public abstract class Transaccion {
	private int monto;
	private LocalDate fechaCreacion;
	static private List<Transaccion> viajes = new ArrayList<>();
	static private List<Transaccion> salud = new ArrayList<>();
	static private List<Transaccion> alimentacion = new ArrayList<>();
	static private List<Transaccion> transporte = new ArrayList<>();
	static private List<Transaccion> educacion = new ArrayList<>();
	static private List<Transaccion> hogar = new ArrayList<>();
	static private List<Transaccion> entretenimiento = new ArrayList<>();
	static private List<Transaccion> imprevistos = new ArrayList<>();
	static private List<Transaccion> nulo = new ArrayList<>();
	
	protected Transaccion(int monto, LocalDate fechaCreacion) {
		this.monto=monto;
		this.fechaCreacion=fechaCreacion;
	}
	//setters y getters
	public void setMonto(int monto) {
		this.monto=monto;
	}
	public void setCategoria(String categoria) {
		this.categoria=categoria;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion=fechaCreacion;
	}
	public int getMonto() {
		return monto;
	}
	public String getCategoria() {
		return categoria;
	}

}
