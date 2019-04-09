import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

public class HanoiVista extends JFrame implements ActionListener{
	private Color colores[] = {
			new Color(27, 12, 87),
			new Color(19, 138, 9),
			new Color(238, 241, 25),
			new Color(241, 120, 25),
			new Color(166, 25, 241),
			new Color(241, 25, 25),
			new Color(78, 161, 234),
			new Color(154, 154, 154)};
	
	private JLabel menuDiscos;
	public JButton iniciar;
	public JSlider sliderDiscos;
	private JSeparator separador;
	private Torre torres[];
	private JPanel glass;
	private Disco discos[];
	public Timer t;
	private Vector<Movimiento> movimientos;
	public HanoiVista(){
		super("Torres de Hanoi");
		HazInterfaz();
	}
	private void HazInterfaz(){
		setSize(1000,350);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		----------------------------------
		glass = (JPanel) getGlassPane();
        glass.setLayout(null);
        glass.setVisible(true);
//		----------------------------------
		//Menú de opciones
		separador = new JSeparator();
		separador.setOrientation(SwingConstants.VERTICAL);
		separador.setBounds(800, 10, 10, 300);
		add(separador);

		menuDiscos = new JLabel("Número de discos");
		menuDiscos.setBounds(850, 60, 200, 100);
		add(menuDiscos);
		
		sliderDiscos = new JSlider(3,8,3);
		sliderDiscos.setMajorTickSpacing(1);
		sliderDiscos.setMinorTickSpacing(1);
		sliderDiscos.setPaintTicks(true);
		sliderDiscos.setPaintLabels(true);
		sliderDiscos.setBounds(811, 120, 175, 50);
		add(sliderDiscos);
		
		iniciar = new JButton("Iniciar");
		iniciar.setBounds(850,180,100,35);
		iniciar.setEnabled(false);
		add(iniciar);
		//Torres
		AñadeTorres();
		
		t = new Timer(1,this);
		
		setVisible(true);
	}
	
	private void AñadeTorres() {
		torres = new Torre[3];
		//aumento de 45
		torres[0] = new Torre(85);
		torres[0].setBounds(10, 1, 250, 300);
		add(torres[0]);
		torres[1] = new Torre(335);
		torres[1].setBounds(260, 1, 250, 300);
		add(torres[1]);
		torres[2] = new Torre(585);
		torres[2].setBounds(510, 1, 250, 300);
		add(torres[2]);
	}
	
	public void AñadeDiscos(int nDiscos) {
		discos = null;
		if(glass.getComponentCount()!=0)
			glass.removeAll();
		
		menuDiscos.setText("Número de discos: "+nDiscos);
		discos = new Disco[nDiscos];
		Disco d1 = new Disco(85, 231, 120, 20);
		discos[0] = d1;
		discos[0].setBounds(85, 231, 120, 20);
		discos[0].setBackground(colores[0]);
		glass.add(discos[0]);
		int posX, posY, width, heigth;
		for(int i=1;i<nDiscos;i++) {
			
			posX = discos[i-1].getX()+5;
			posY = discos[i-1].getY()-20;
			width = discos[i-1].getWidth() - 10;
			heigth = 20;
			
			discos[i] = new Disco(posX, posY, width, heigth);
			discos[i].setBounds(posX, posY, width, heigth);
			discos[i].setBackground(colores[i]);
			glass.add(discos[i]);
		}
		Invertir();
//		glass.update(glass.getGraphics());
		SwingUtilities.updateComponentTreeUI(glass);
		torres[0].setnDiscos(nDiscos);
		iniciar.setEnabled(true);
	}
	private void Invertir() {
		Disco aux;
	    for (int i = 0; i <= (discos.length-1)/2; i++) {
	        aux = discos [i];
	        discos [i] = discos [(discos.length-1)-i];
	        discos [(discos.length-1)-i] = aux;
	    }
	}
	public boolean estaArriba = false;
	public boolean SubirD(Movimiento m) {
		if(estaArriba)
			return true;
		int i = m.getDisco()-1;
		int k = m.getTorreInicio();
		int limY=10;
		
		discos[i].setBounds(discos[i].getX(),discos[i].getY()-1,discos[i].getWidth(),discos[i].getHeight());
		
		if(discos[i].getY() <= limY) {
			torres[k].setnDiscos(torres[k].getnDiscos()-1);
			estaArriba = true;
			return true;
		} 
		return false;
	}
	
	public boolean BajarD(Movimiento m) {
		int i = m.getDisco()-1;
		int k = m.getTorreDestino();
		int limY = 231 -(torres[k].getnDiscos()*20);
		discos[i].setBounds(discos[i].getX(),discos[i].getY()+1,discos[i].getWidth(),discos[i].getHeight());
		
		if(discos[i].getY() >= limY) {
			torres[k].setnDiscos(torres[k].getnDiscos()+1);
			return true;
		} 
		return false;
	}
	
	public boolean isMoving = false;
	public boolean MueveD(Movimiento m) {
		if(isMoving)
			return true;
		int i = m.getDisco()-1;
		int k = m.getTorreDestino();
		int espacio = (5 * (discos.length - m.getDisco()));
		//Validar si llegó
		if(discos[i].getX()== torres[k].posCentro+espacio)
			return true;
		//Va a la derecha
		if(m.getTorreInicio()>k) {
			discos[i].setBounds(discos[i].getX()-1,discos[i].getY(),discos[i].getWidth(),discos[i].getHeight());
			return false;
		}
		//Va a la izquierda
		if(m.getTorreInicio()<k) {
			discos[i].setBounds(discos[i].getX()+1,discos[i].getY(),discos[i].getWidth(),discos[i].getHeight());
			return false;
		}
		return false;
	}
	
	public boolean ejecutarMovimientos(Movimiento m ) {
		if(!SubirD(m)) {
//			System.out.println("Ya llegó arriba");
			return false;
		}
		if(!MueveD(m)) {
//			System.out.println("Movio");
			return false;
		}
		if(!BajarD(m)) {
//			System.out.println("Bajó");
			
			return false;
		}
		estaArriba = false;
		isMoving = false;
		return true;
	}
	public void setMovimientos(Vector<Movimiento> movimientos) {
		this.movimientos = movimientos;
		index = 0;
		torres[1].setnDiscos(0);
		torres[2].setnDiscos(0);
	}
	private int index;
	Movimiento m;
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==t) {
			m = movimientos.get(index);
			if(ejecutarMovimientos(m))
				index++;
		}
		if((index+1) > movimientos.size()) {
			JOptionPane.showMessageDialog(null,"Juego Terminado");
			t.stop();
			sliderDiscos.setEnabled(true);
        }
	}
	
	
	
}
