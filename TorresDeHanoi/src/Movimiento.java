
public class Movimiento {
private int disco, torreInicio, torreDestino;
	
	public Movimiento(int disco, int torreInicio, int torreDestino) {
		this.disco = disco;
		this.torreInicio = torreInicio;
		this.torreDestino = torreDestino;
	}

	public int getDisco() {
		return disco;
	}
	
	public int getTorreInicio() {
		return torreInicio;
	}

	public int getTorreDestino() {
		return torreDestino;
	}

}
