package gestorAplicación.classpropuestas;
import java.util.ArrayList;

public class Usuario {
	//atributos
	private int cedula;
	private String nombre;
	private String correo;
	
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
		
		}
	
	//getters y setters
	public String getNombre() {
		return nombre;
	}
	
	public String getCorreo() {
		return correo;
	}
	public int getCedula() {
		return cedula;
	}
	public ArrayList <Bolsillo> getBolsillos() {
        	return bolsillos;
    }
	public ArrayList <Ahorro> getAhorros() {
        	return ahorros;
    }
    	public ArrayList <Ingreso> getIngresos() {
        	return ingresos;
    }
    	public ArrayList <Retiro> getRetiros() {
        	return retiros;
    }
    	public ArrayList <Prestamo> getPrestamos() {
        	return prestamos;
    }
   	public ArrayList <Meta> getMetas() {
        	return metas;
    }
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	
	public void setCorreo(String correo) {
		this.correo=correo;
	}
	public void setCedula(int cedula) {
		this.cedula=cedula;}
	
	public void setBolsillos(ArrayList<Bolsillo> bolsillos) {
	    this.bolsillos = bolsillos;}
	
	public void setAhorros(ArrayList<Ahorro> ahorros) {
        this.ahorros = ahorros;
    }
	public void setIngresos(ArrayList<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }

	public void setRetiros(ArrayList<Retiro> retiros) {
	    this.retiros = retiros;
	    }
	
	public void setPrestamos(ArrayList<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
	
	public void setMetas(ArrayList<Meta> metas) {
        this.metas = metas;
    }

	//métodos

	public void nuevoIngreso(Ingreso ingreso) {

		ingreso.getCuentaDestino().depositar(ingreso.getMonto());

		ingresos.add(ingreso);

	}

	//Se realiza un retiro validando su consistencia origen del usuario y se genera una salida en el historial

	public boolean nuevoRetiro(Retiro retiro) {

		boolean salida = retiro.getCuentaOrigen().retirar(retiro.getMonto());

		if(salida){

			retiros.add(retiro);

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

//	public void nuevoPrestamo(PrestamoLargoPlazo prestamo, Bolsillo bolsillo) {

		//prestamos.add(prestamo);

		//bolsillo.depositar(prestamo.getDivisa().ConvertToDivisa(prestamo.getValorInicial(), bolsillo.getDivisa())[0]);

	
	//
//sobrecarga de método nuevoprestamo
	//public void nuevoPrestamo(PrestamoFugaz prestamo, Ahorro ahorro) {

		//prestamos.add(prestamo);

		//ahorro.depositar(prestamo.getDivisa().ConvertToDivisa(prestamo.getValorInicial(), bolsillo.getDivisa())[0]);

//}

	//Se realiza una separacion del dinero del usuario por divisas guardada en bolsillos, colchones y metas

	public int getDineroCuenta() {

		int total = 0;

		ArrayList<Cuenta> contables = new ArrayList();

		contables.addAll(getBolsillos());

		contables.addAll(getAhorros());

		for (Cuenta i : contables) {

			total+=i.getSaldo();
		}
		return total;
	}
		


	
	

}
