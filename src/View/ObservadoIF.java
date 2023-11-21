package View;

public interface ObservadoIF {

	//Adiciona um observador
	public void add(ObservadorIF o );

	//Remove um observador
	public void remove(ObservadorIF o );

	//Retorna o objeto observado
	public Object get();

}
