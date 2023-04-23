package gestorAplicacion;

public class Bolsillo extends Cuenta {
	private Categoria categoria;
	
	//constructor)
	public Bolsillo (Usuario usuario, Categoria Categoria) {
		super(usuario);
		this.categoria=categoria;
		
	}
	

}
