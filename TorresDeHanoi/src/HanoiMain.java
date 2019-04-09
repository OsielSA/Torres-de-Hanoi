import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class HanoiMain {
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		HanoiVista vista = new HanoiVista();
		HanoiModelo modelo = new HanoiModelo();
		HanoiControlador controlador = new HanoiControlador(vista, modelo);
	}
	
}