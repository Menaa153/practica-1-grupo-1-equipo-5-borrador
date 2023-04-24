package gestorAplicacion;
import java.util.ArrayList;

public abstract class Cuenta {
	private String nombre;
	private Usuario usuario;
	private int saldo;
	Transaccion[] transacciones;
	
	//constructor
	protected Cuenta(String nombre, Usuario usuario) {

		this.nombre=nombre;

		this.usuario=usuario;
		
		this.saldo=0;
		}
	protected Cuenta (Usuario usuario) {
		this (null, usuario);}
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
	public void setSaldo(int saldo) {
		this.saldo=saldo;}
	
	public int getSaldo() {
		return saldo;
	}

	public void depositar(int cantidad) {
        saldo = saldo + cantidad;
    }

    public boolean retirar(int cantidad) {
    	if(cantidad<=saldo) {
    		saldo = saldo - cantidad;
    		return true;
    	}else {
    		return false;
    	}
    }
		


}
