package gestorAplicacion;

public enum Categoria {
	Viajes(0, 0),
	Salud(0,0),
	Alimentacion(0,0),
	Transporte(0,0),
	Educacion(0,0),
	Hogar(0,0),
	Entretenimiento(0,0),
	Imprevistos(0,0),
	Nulo(0,0);

	private double saldo;
    private double presupuesto;

	Categoria (double saldo, double presupuesto) {
        this.saldo = saldo;
        this.presupuesto = presupuesto;
    }

	public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

	public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
	}


	//for (Categoria categoria: Categoria.values()){
	//	Usuario.setBolsillos(Usuario.getBolsillos().add(categoria))

	//}


}
