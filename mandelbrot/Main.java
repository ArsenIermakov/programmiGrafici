import javax.swing.*;

public class Main extends Thread {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Trasformazione Complessa z^2");
        Pannello2 panel = new Pannello2();
        frame.add(panel);
        frame.setSize(panel.larghezza + 16, panel.larghezza +39);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Tasti t = new Tasti(panel);
        frame.addKeyListener(t);
    }
}
