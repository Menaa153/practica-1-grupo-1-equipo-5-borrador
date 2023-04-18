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
		this.cedula=cedula;
	}
	//métodos

	public void nuevaConsignacion(Consignacion consignacion) {

		consignacion.getCuentaDestino().depositar(consignacion.getValorDestino());

		consignaciones.add(consignacion);

	}

	//Se realiza un retiro validando su consistencia origen del usuario y se genera una salida en el historial

	public boolean nuevoRetiro(Retiro retiro) {

		boolean salida = retiro.getCuentaOrigen().retirar(retiro.getValorOrigen());

		if(retirado){

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
