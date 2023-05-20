package gestorAplicacion;

import java.time.LocalDate;
import java.io.Serializable;

public class Meta implements Serializable{

    
    private static final long serialVersionUID = 659116063038663746L;

	private Usuario usuario;
    private String nombre;
    private boolean cumplida;
    private LocalDate fechaCumplimiento;
    private LocalDate fechaInicio;
    private double objetivo;
    private int saldo = 0;
    
    //constructor
    public Meta(Usuario usuario, String nombre, LocalDate fechaInicio, double objetivo) {
        this.nombre = nombre;
        this.cumplida = false;
        this.fechaCumplimiento = null;
        this.fechaInicio = fechaInicio;
        this.objetivo = objetivo;
        this.usuario = usuario;
        this.saldo = 0;
    }
    
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

    public void setSaldo(int saldo) {
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

    
    public Transaccion abonar(int monto, Cuenta origen) {
        if (!this.cumplida) {
            Retiro retiro = new Retiro(monto, LocalDate.now(), origen, (Cuenta)null);
            boolean retirado = this.usuario.nuevoRetiro(retiro);
            if(!retirado) {
            	return null;
            }
            this.saldo += monto;
            return retiro;
        }
        return null;
    }

	
	public Transaccion terminar(Cuenta cuenta) {
		int nuevoSaldo = this.saldo;
        this.saldo = 0;
        this.cumplida = true;
        this.fechaCumplimiento = LocalDate.now();
        Ingreso ingreso = new Ingreso(nuevoSaldo, LocalDate.now(), null, cuenta);
        usuario.nuevoIngreso(ingreso);
		return ingreso;
	}
}
