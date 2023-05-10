package auxiliares;

public class Estado {

	private int tiempoI;
	private int tiempoF;
	private String situacion;
	private final String NOVISITADO = "BLANCO";
	private final String VISITADO = "AMARILLO";
	private final String SINADYACENTES = "NEGRO";
	
	public Estado() {
		this.tiempoI = 0;
		this.tiempoF = 0;
		this.situacion = this.NOVISITADO;
	}

	public int getTiempoI() {
		return this.tiempoI;
	}
	public void setTiempoI(int t) {
		this.tiempoI = t;
	}
	public int getTiempoF() {
		return this.tiempoF;
	}
	public void setTiempoF(int t) {
		this.tiempoF = t;
	}
	public void visitado() {
		this.situacion = this.VISITADO;
	}
	public void finalizado() {
		this.situacion = this.SINADYACENTES;
	}
	public boolean isVisitado() {
		return this.situacion == this.VISITADO;
	}
	public boolean isFinalizado() {
		return this.situacion == this.SINADYACENTES;
	}
}
