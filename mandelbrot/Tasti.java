import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tasti implements KeyListener{ 
    public Pannello2 panel;

    public Tasti(Pannello2 panel){
        this.panel = panel;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("Tasto premuto: " + keyCode); // Stampa il codice del tasto
        //System.out.print("interazioniMax: " + panel.interazioniMax);
        if (keyCode == KeyEvent.VK_A) {
            panel.spostamentoAsseX(0); // Azione per il tasto 'A'
            panel.repaint(); 
        }
        if (keyCode == KeyEvent.VK_D) {
            panel.spostamentoAsseX(-1); // Azione per il tasto 'D'
            panel.repaint(); 
        }

        if (keyCode == KeyEvent.VK_W) {
            panel.spostamentoAsseY(0); // Azione per il tasto 'A'
            panel.repaint(); 
        }
        if (keyCode == KeyEvent.VK_S) {
            panel.spostamentoAsseY(-1); // Azione per il tasto 'A'
            panel.repaint(); 
        }

        if (keyCode == KeyEvent.VK_U) {
            panel.zoom(0); // Azione per il tasto 'A'
            panel.repaint(); 
        }
        if (keyCode == KeyEvent.VK_J) {
            panel.zoom(-1); // Azione per il tasto 'A'
            panel.repaint(); 
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
       // System.out.println("Tasto rilasciato: " + e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Carattere digitato: " + e.getKeyChar());
    }
}
