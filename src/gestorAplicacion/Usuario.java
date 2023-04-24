package gestorAplicacion;
import java.util.ArrayList;

public class Usuario {
	//atributos
	private int cedula;
	private String nombre;
	private String correo;
	private Cuenta cuenta;
	
	private ArrayList<Bolsillo> bolsillos = new ArrayList();
	private ArrayList<Ahorro> ahorros = new ArrayList();
	private ArrayList<Ingreso> ingresos = new ArrayList();
	private ArrayList<Retiro> retiros = new ArrayList();
	private ArrayList<Prestamo> prestamos = new ArrayList();
	private ArrayList<Meta> metas = new ArrayList();
	
	public Usuario(int cedula, String nombre, String correo) {

		this.cedula=cedula;

		this.nombre=nombre;
		
		this.correo=correo;
		
		new Cuenta("DEFAULT", this);
		}
	
	//getters y setters
	public String getNombre() {
		return nombre;
	}
	public String getCel() {
		return cel;
	}
	public String getCorreo() {
		return correo;
	}
	public int getCedula() {
		return cedula;
	}
	public List <Bolsillo> getBolsillos() {
        return bolsillos;
    }
	public List <Ahorro> getAhorros() {
        return ahorros;
    }
    public List <Ingresos> getIngresos() {
        return ingresos;
    }
    public List <Retiro> getRetiros() {
        return retiros;
    }
    public List <Prestamo> getPrestamos() {
        return prestamos;
    }
    public List <Meta> getMetas() {
        return metas;
    }
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	public void setCel(String cel) {
		this.cel=cel;
	}
	public void setCorreo(String correo) {
		this.correo=correo;
	}
	public int setCedula(int cedula) {
		this.cedula=cedula;}
	
	public void setBolsillos(List<Bolsillo> bolsillos) {
	    this.bolsillos = bolsillos;}
	
	public void setAhorros(List<Ahorro> ahorros) {
        this.ahorros = ahorros;
    }
	public void setIngresos(List<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }

	public void setRetiros(List<Retiro> retiros) {
	    this.retiros = retiros;
	    }
	
	public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
	
	public void setMetas(List<Meta> metas) {
        this.metas = metas;
    }

	//métodos

	public void nuevoIngreso(Consignacion consignacion) {

		consignacion.getCuentaDestino().depositar(consignacion.getValorDestino());

		ingresos.add(consignacion);

	}

	//Se realiza un retiro validando su consistencia origen del usuario y se genera una salida en el historial

	public boolean nuevaSalida(Retiro retiro) {

		boolean salida = retiro.getCuentaOrigen().retirar(retiro.getValorOrigen());

		if(salida){

			retiros.add(salida);

	}
		return salida;}

	public void nuevoBolsillo(Bolsillo bolsillo) {

		bolsillos.add(bolsillo);

	}

	public void nuevoAhorro(Ahorro ahorro) {

		ahorros.add(ahorro);

	}

	public void nuevaMeta(Meta meta) {

		metas.add(meta);

	}

	public void nuevoPrestamo(PrestamoLargoPlazo prestamo, Bolsillo bolsillo) {

		prestamos.add(prestamo);

	bolsillo.depositar(prestamo.getDivisa().ConvertToDivisa(prestamo.getValorInicial(), bolsillo.getDivisa())[0]);

	}
//sobrecarga de método nuevoprestamo
	public void nuevoPrestamo(PrestamoFugaz prestamo, Ahorro ahorro) {

		prestamos.add(prestamo);

		ahorro.depositar(prestamo.getDivisa().ConvertToDivisa(prestamo.getValorInicial(), bolsillo.getDivisa())[0]);

	}

	//Se realiza una separacion del dinero del usuario por divisas guardada en bolsillos, colchones y metas

	public int getDineroCuenta() {

		int total = 0;

		List<Contable> contables = new ArrayList<>();

		contables.addAll(getBolsillos());

		contables.addAll(getAhorros());

		for (Contable i : contables) {

			total+=i.getSaldo();
		}
		return total;
	}
		


	
	

}
