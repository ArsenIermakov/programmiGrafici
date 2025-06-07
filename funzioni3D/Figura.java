import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

class Figura extends JPanel {
    private int centro[];
    //private ArrayList<double[][]> cubo;
    private BufferedImage buffer;
    private int intervallo = 3;
    private double zoom;

    public Figura(int x, int y){
        centro = new int[2];
        centro[0] = x;
        centro[1] = y;
        zoom = x/intervallo;
        //cubo = Formule.letturaFile();
        //System.out.print("poligoni: " + cubo.size());
        /*for(double[][] tr : cubo){ //stampa lista delle cordinate dei triangoli
            for(int i=0; i<tr.length; i++){
                System.out.print("[");
                for(int j=0; j<tr[i].length; j++){
                    System.out.print(tr[i][j] + " ");
                }
                System.out.print("]");
            }
            System.out.println();
            
        }*/
        buffer = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        disegnaAssi_iso(g);
        //disegnaAssi_cav(g);
        disegnaFigura(g);
        g.drawImage(buffer, 0, 0, this); 

    }


    public void disegnaAssi_iso(Graphics g){
        double p[] = new double[3];
        g.drawLine(centro[0], centro[1], centro[0], 0);
        p[0] = 100;
        p[1] = 0;
        p[2] = 0;
        int p2[] = Formule.conv_iso(centro, p, 10);
        g.drawLine(centro[0], centro[1], p2[0], p2[1]);

        p[0] = 0;
        p[1] = 100;
        p[2] = 0;
        p2 = Formule.conv_iso(centro, p, 10);
        g.drawLine(centro[0], centro[1], p2[0], p2[1]);
    }

    public void disegnaAssi_cav(Graphics g){
        double p[] = new double[3];
        g.drawLine(centro[0], centro[1], centro[0], 0);

        p[0] = 100;
        p[1] = 0;
        p[2] = 0;
        int p2[] = Formule.conv_cav(centro, p, 10);
        g.drawLine(centro[0], centro[1], p2[0], p2[1]);

        p[0] = 0;
        p[1] = 100;
        p[2] = 0;
        p2 = Formule.conv_cav(centro, p, 10);
        g.drawLine(centro[0], centro[1], p2[0], p2[1]);
    }

    public void disegnaFigura(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK); // Colore del bordo 

       
        Color startColor = Color.BLUE;
        Color endColor = Color.YELLOW;
        //Color c = new Color(155, 23, 43);
        int r = 0;
        //int g = 0;
        int b = 0;
        
        //int[] p2d = Formule.conv_iso(centro, d, zoom);
        //g2d.setColor(Color.BLACK); // Colore del bordo 
        //g2d.drawLine(p2d[0], p2d[1], p2d[0]+2, p2d[1]+2);
        double minZ = 0; // Sostituiscilo con il valore minimo della tua funzione
        double maxZ = 2;  // Sostituiscilo con il valore massimo della tua funzione


        for(double i=-2.5; i<intervallo-1; i=i+0.001){ //x
            for(double j=-2.5; j<intervallo-1; j=j+0.001){ //y
                double[] p = new double[3];
                p[0] = i;
                p[1] = j;
                p[2] = Formule.funzione(i, j);
                if(p[2] <= 2){
                    int[] p2d = Formule.conv_iso(centro, p, zoom);
                    double normalizedZ = Math.max(0, Math.min((p[2] - minZ) / (maxZ - minZ), 1));
                    r = (int) (normalizedZ * 255);
                    b = 255 - r;

                    Color c = new Color(r, 0, b);

                    g2d.setColor(c);
                    g2d.drawLine(p2d[0], p2d[1], p2d[0], p2d[1]);
                }
                
                
                
                
            }
        }

    
            
            
           // g2d.setColor(Color.RED); g2d.fillOval(p1[0] - 2, p1[1] - 2, 4, 4); g2d.fillOval(p2[0] - 2, p2[1] - 2, 4, 4); g2d.fillOval(p3[0] - 2, p3[1] - 2, 4, 4);
        
    }
}
    
