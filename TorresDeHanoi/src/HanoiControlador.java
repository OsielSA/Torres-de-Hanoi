import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
public class HanoiControlador implements ActionListener, ChangeListener  {
	private HanoiVista vista;
	private HanoiModelo modelo;
	private JSlider slider;
//	private Timer t;
	public int nDiscos;
	public HanoiControlador(HanoiVista vista,HanoiModelo modelo){
		this.modelo = modelo;
		this.vista = vista;
		HazEscuchas();
//		t = new Timer(1,this);
	}
	private void HazEscuchas() {
		slider = vista.sliderDiscos;
		slider.addChangeListener(this);
		vista.iniciar.addActionListener(this);
	}
	public void Iniciar() {
		modelo.iniciaSimulacion(nDiscos, 0, 1, 2);
		vista.setMovimientos(modelo.getMovimiento());
		vista.t.start();
	}
	Movimiento m;
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==vista.iniciar){
			JButton btn = (JButton)evt.getSource();
			System.out.println("llegó");
			vista.iniciar.setEnabled(false);
			vista.sliderDiscos.setEnabled(false);
			Iniciar();
		}
	}
	@Override
	public void stateChanged(ChangeEvent evt) {
		modelo.BorrarMovimientos();
		nDiscos = vista.sliderDiscos.getValue();
		vista.AñadeDiscos(nDiscos);
		
	}
}
