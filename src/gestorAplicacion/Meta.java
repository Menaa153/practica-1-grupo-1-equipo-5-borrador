package gestorAplicacion;

import java.time.LocalDate;

public class Meta {
	private Usuario usuario;
    private String nombre;
    private boolean cumplida;
    private LocalDate fechaCumplimiento;
    private LocalDate fechaInicio;
    private double objetivo;
    private double saldo = 0;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isCumplida() {
        return cumplida;
    }

    public void setCumplida(boolean cumplida) {
        this.cumplida = cumplida;
    }


    public double getObjetivo() {
        return objetivo;
    }

    public boolean[] setObjetivo(double objetivo) {
    	boolean[] bol = new boolean[2];
        if (!this.cumplida) {
            this.objetivo = objetivo;
            bol[1] = this.metaCumplida();
            bol[0] = true;
        } else {
        	bol[0] = false;
        	bol[1] = false;
        }
        return bol;
    }

    public boolean metaCumplida() {
    	return this.saldo>= this.objetivo;
        
    }
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaCumplimiento() {
        return fechaCumplimiento;
    }

    public void setFechaCumplimiento(LocalDate fechaCumplimiento) {
        this.fechaCumplimiento = fechaCumplimiento;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Override
    public Movimiento abonar(double monto, Cuenta origen) {
        if (!this.cumplida) {
            Salida salida = new Salida(monto, LocalDate.now(), origen, null);
            boolean retirado = this.usuario.nuevaSalida(salida);
            if(!retirado) {
            	return null;
            }
            this.saldo += monto2[0];
            return salida;
        }
        return null;
    }

	@Override
	public Movimiento terminar(Cuenta cuenta) {
		double[] nuevoSaldo = this.getDivisa().ConvertToDivisa(this.saldo, cuenta.getDivisa());
        this.saldo = 0;
        this.cumplida = true;
        this.fechaCumplimiento = LocalDate.now();
        Ingreso ingreso = new Ingreso(nuevoSaldo[0], this.saldo, LocalDate.now(), true, null, null, cuenta, this.divisa, cuenta.getDivisa());
        usuario.nuevoIngreso(ingreso);
		return ingreso;
	}
}
