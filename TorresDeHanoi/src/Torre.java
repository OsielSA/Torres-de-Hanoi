import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Torre extends JPanel {

        private int nDiscos;
        public int posCentro;
        public Torre(int posCentro) {
            setLayout(null);
            this.nDiscos = 0;
            this.posCentro = posCentro;
        }

        public int getnDiscos() {
			return nDiscos;
		}
		public void setnDiscos(int nDiscos) {
			this.nDiscos = nDiscos;
		}

		@Override
        public void paint(Graphics g) {
        	super.paint(g);
        	
        	g.setColor(new Color(82,68,60));
            g.fillRect(128, 60, 10, 190);
        	//Base
            g.setColor(new Color(82,68,60));
            g.fillRect(20, 250, 350, 25);
            
        }

    }
