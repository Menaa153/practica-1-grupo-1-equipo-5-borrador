package uiMain;

public class Usuario {
	//atributos
	private String nombre;
	private String cel;
	private String correo;
	private Cuenta cuenta;
	private int cedula;
	private List<Bolsillo> bolsillos = new ArrayList<>();
	private List<Ahorro> ahorros = new ArrayList<>();
	private List<Consignacion> consignaciones = new ArrayList<>();
	private List<Retiro> retiros = new ArrayList<>();
	private List<Prestamo> prestamos = new ArrayList<>();
	private List<Meta> metas = new ArrayList<>();
	
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
	public List<Bolsillo> getBolsillos() {
        	return bolsillos;
    }
	public List<Ahorro> getAhorros() {
        	return ahorros;
    }
    	public List<Consignacion> getConsignaciones() {
        	return consignaciones;
    }
    	public List<Retiro> getRetiros() {
        	return retiros;
    }
    	public List<Prestamo> getPrestamos() {
        	return prestamos;
    }
    	public List<Meta> getMetas() {
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
	    this.bolsillos = bolsillos;
	}
	
	public void setAhorros(List<Ahorro> ahorros) {
        	this.ahorros = ahorros;
    }
	public void setConsignaciones(List<Consignacion> consignaciones) {
        	this.consignaciones = consignaciones;
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

	public void nuevaConsignacion(Consignacion consignacion) {

		consignacion.getCuentaDestino().depositar(consignacion.getValorDestino());

		consignaciones.add(consignacion);

	}

	//Se realiza un retiro validando su consistencia origen del usuario y se genera una salida en el historial

	public boolean nuevoRetiro(Retiro retiro) {

		boolean salida = retiro.getCuentaOrigen().retirar(retiro.getValorOrigen());

		if(salida){

	retiros.add(salida);

	}

	return salida;

	}

	public void nuevoBolsillo(Bolsillo bolsillo) {

		bolsillos.add(bolsillo);

	}

	public void nuevoColchon(Colchon colchon) {

	colchones.add(colchon);

	}

	public void nuevaMeta(Meta meta) {

	metas.add(meta);

	}

	public void nuevoPrestamo(PrestamoLargoPlazo prestamo, Bolsillo bolsillo) {

	prestamos.add(prestamo);

	bolsillo.depositar(prestamo.getDivisa().ConvertToDivisa(prestamo.getValorInicial(), bolsillo.getDivisa())[0]);

	}

	public void nuevoPrestamo(PrestamoFugaz prestamo, Bolsillo bolsillo) {

	prestamos.add(prestamo);

	bolsillo.depositar(prestamo.getDivisa().ConvertToDivisa(prestamo.getValorInicial(), bolsillo.getDivisa())[0]);

	}

	//Se realiza una separacion del dinero del usuario por divisas guardada en bolsillos, colchones y metas

	public double[] getDineroTotal() {

	double[] total = new double[Divisa.values().length];

	List<Contable> contables = new ArrayList<>();

	contables.addAll(getBolsillos());

	contables.addAll(getColchones());

	contables.addAll(getMetas());

	for (Contable i : contables) {

	for (int j = 0; j < Divisa.values().length; j++) {

	if (i.getDivisa().equals(Divisa.values()[j])) {

	total[j] += i.getSaldo();

	break;/**

	*

	*/

	}

	}

	}

	return total;

	}

	}
	
	
}

}

	
	

}
