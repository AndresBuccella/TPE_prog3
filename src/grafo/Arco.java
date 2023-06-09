package grafo;


/*
 * La clase arco representa un arco del grafo. Contiene un vertice origen, un vertice destino y una etiqueta.
 * Nota: Para poder exponer los arcos fuera del grafo y que nadie los modifique se hizo esta clase inmutable
 * (Inmutable: una vez creado el arco no es posible cambiarle los valores).
 */
public class Arco<T> {

	private Integer verticeOrigen;
	private Integer verticeDestino;
	private T etiqueta;

	public Arco(int verticeOrigen, int verticeDestino, T etiqueta) {
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.etiqueta = etiqueta;
	}
	
	public int getVerticeOrigen() {
		return verticeOrigen;
	}
	
	public int getVerticeDestino() {
		return verticeDestino;
	}

	public T getEtiqueta() {
		return etiqueta;
	}
	
	public String toString() {
		return "| " + this.getVerticeOrigen() + " <-> " + this.getVerticeDestino() + " |";
	}

	public boolean equals(Object o) {
		try {
			Arco<T> arco = (Arco<T>) o;
			return(this.getVerticeOrigen() == arco.getVerticeOrigen() && 
					this.getVerticeDestino() == arco.getVerticeDestino());			
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public int hashCode() {
		return ((this.verticeOrigen == null) ? 0 : this.verticeOrigen.hashCode()) + 
				((this.verticeDestino == null) ? 0 : this.verticeDestino.hashCode());
	}
}
