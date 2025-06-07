import javax.swing.*;

public class Interfaccia extends Thread{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Line Drawing Example");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Figura panel = new Figura((int)(800/2), (int)(800/2));
       
        /*panel.x0=(getSize().height-1)/2;
        panel.y0= (getSize().width-1)/2;*/
        frame.add(panel);
        frame.setLocationRelativeTo(null); //finestra al centro dello schermo
        frame.setResizable(false); //finestra non ridimensionabile
        frame.setVisible(true);
        int fps = 30;
        double angolo = 0.01;
        panel.ruotaFig_X(-90);
        while (true) { 
            panel.ruotaFig_Z(angolo);
            try {
                panel.repaint();
                Thread.sleep((int)(1/fps));
            } catch (Exception e) {
            }
        }
    }
}


