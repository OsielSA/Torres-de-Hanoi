import java.util.Vector;

public class HanoiModelo {
	private Vector<Movimiento> movimientos;
	public HanoiModelo(){
		movimientos = new Vector();
	}
	public int mov=0;
	public Vector<Movimiento> getMovimiento() {
        return movimientos;
    }
	public void BorrarMovimientos(){
        movimientos.removeAllElements();
    }
	public void iniciaSimulacion(int numeroDiscos, int torreInicial, int torreCentral, int torreFinal) {
        this.movimientos.removeAllElements();
        hanoi(numeroDiscos, torreInicial, torreCentral, torreFinal);
    }
	
	public void hanoi(int numeroDiscos, int torreInicial, int torreCentral, int torreFinal) {
        if (numeroDiscos == 1) {
            movimientos.add(new Movimiento(1, torreInicial, torreFinal));
            return;
        }
        hanoi(numeroDiscos - 1, torreInicial, torreFinal, torreCentral);
        movimientos.add(new Movimiento(numeroDiscos, torreInicial, torreFinal));
        hanoi(numeroDiscos - 1, torreCentral, torreInicial, torreFinal);
    }
}
