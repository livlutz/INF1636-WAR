package View;

public interface ObservadorIF {

		public default void notifica(ObservadoIF o) {
			o.get();
		}
}
