package uiMain;
import gestorAplicacion.Ahorro;
import gestorAplicacion.Bolsillo;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {
	private String nombre;
	private Usuario usuario;
	private double saldo;
	private List<Bolsillo> bolsillos = new ArrayList<>();
	private List<Ahorro> ahorros = new ArrayList<>();
	private List<Consignacion> consignaciones = new ArrayList<>();
	private List<Retiro> retiros = new ArrayList<>();
	private List<Prestamo> prestamos = new ArrayList<>();
	private List<Meta> metas = new ArrayList<>();
	//constructor
	public Cuenta(String nombre, Usuario usuario) {

		this.nombre=nombre;

		this.usuario=usuario;

		bolsillos.add(new Bolsillo(this, Divisa.COP, "DEFAULT"));

		}
	//getters y setters

	public String getNombre() {

		return nombre;

		}

	public void setNombre(String nombre) {

		this.nombre = nombre;

		}
	public Usuario getUsuario() {

		return usuario;

		}
	public void setUsuario(Usuario usuario) {

		this.usuario = usuario;

		}
	public List<Bolsillo> getBolsillos() {

		return bolsillos;

		}

	public void setBolsillos(List<Bolsillo> bolsillos) {

		this.bolsillos = bolsillos;

		}

	public List<Colchon> getColchones() {

		return colchones;

		}

	public void setColchones(List<Colchon> colchones) {

		this.colchones = colchones;

		}

	public List<Ingreso> getIngresos() {

		return ingresos;

		}

	public void setIngresos(List<Ingreso> ingresos) {

		this.ingresos = ingresos;

		}

	public List<Salida> getSalidas() {

		return salidas;

		}

	public void setSalidas(List<Salida> salidas) {

		this.salidas = salidas;

		}

	public List<Prestamo> getPrestamos() {

		return prestamos;

		}

	public void setPrestamos(List<Prestamo> prestamos) {

		this.prestamos = prestamos;

		}

	public List<Meta> getMetas() {

		return metas;

		}

		public void setMetas(List<Meta> metas) {

		this.metas = metas;

		}

		//Se realiza un deposito en la cuenta destino del usuario y se genera un ingreso en el historial

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
