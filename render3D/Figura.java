import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

class Figura extends JPanel {
    private int centro[];
    public ArrayList<double[][]> cubo;
    private BufferedImage buffer;

    public Figura(int x, int y){
        centro = new int[2];
        centro[0] = x;
        centro[1] = y;
        cubo = Formule.letturaFile();
        System.out.print("poligoni: " + cubo.size());
        ruotaFig_X(90);
        buffer = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);


        /*cubo = new Triangolo[12];
        cubo[0]= new Triangolo(new double[]{0, 0, 0}, new double[]{0, 1, 0}, new double[]{1, 1, 0});
        cubo[1]= new Triangolo(new double[]{0, 0, 0}, new double[]{1, 0, 0}, new double[]{1, 1, 0});
        cubo[2]= new Triangolo(new double[]{0, 0, 0}, new double[]{1, 0, 0}, new double[]{1, 0, 1});
        cubo[3]= new Triangolo(new double[]{0, 0, 0}, new double[]{0, 1, 1}, new double[]{1, 0, 1});
        cubo[4]= new Triangolo(new double[]{0, 0, 0}, new double[]{0, 1, 0}, new double[]{0, 1, 1});
        cubo[5]= new Triangolo(new double[]{0, 0, 0}, new double[]{0, 0, 1}, new double[]{0, 1, 1});
        cubo[6]= new Triangolo(new double[]{1, 0, 0}, new double[]{1, 1, 0}, new double[]{1, 1, 1});
        cubo[7]= new Triangolo(new double[]{1, 0, 0}, new double[]{1, 0, 1}, new double[]{1, 1, 1});
        cubo[8]= new Triangolo(new double[]{0, 1, 0}, new double[]{1, 1, 0}, new double[]{1, 1, 1});
        cubo[9]= new Triangolo(new double[]{0, 1, 0}, new double[]{1, 0, 1}, new double[]{1, 1, 1});
        cubo[10]= new Triangolo(new double[]{0, 0, 1}, new double[]{1, 0, 1}, new double[]{1, 1, 1});
        cubo[11]= new Triangolo(new double[]{0, 0, 1}, new double[]{0, 1, 1}, new double[]{1, 1, 1});
        cubo = new Triangolo[1];
        cubo[0]= new Triangolo(new double[]{-1, 2, 1}, new double[]{-1, 0, -1}, new double[]{-1, 0, 1});
        /*cubo[1]= new Triangolo(new double[]{-1, 2, -1}, new double[]{1, 0, -1}, new double[]{-1, 0, -1});
        cubo[2]= new Triangolo(new double[]{1, 2, -1}, new double[]{1, 0, 1}, new double[]{1, 0, -1});*/
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //disegnaAssi_iso(g);
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
        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK); // Colore del bordo 

       
        Color startColor = Color.BLUE;
        Color endColor = Color.YELLOW;
        double d[]= new double[3];
        int zoom = 10;

        for (int i=0; i<cubo.size(); i++) {
            double[][] a = cubo.get(i);
            int[] xPoints = new int[a.length];
            int[] yPoints = new int[a.length];
            for(int j=0; j<a.length; j++){
                xPoints[j] = Formule.conv_iso(centro, a[j], zoom)[0];
                yPoints[j] = Formule.conv_iso(centro, a[j], zoom)[1];
            }

            g2d.setColor(Color.BLACK); // Colore del bordo 
            g2d.drawPolygon(xPoints, yPoints, xPoints.length);
           // g2d.setColor(Color.RED); g2d.fillOval(p1[0] - 2, p1[1] - 2, 4, 4); g2d.fillOval(p2[0] - 2, p2[1] - 2, 4, 4); g2d.fillOval(p3[0] - 2, p3[1] - 2, 4, 4);
        }

        





        /*
        /*g2d.setColor(Color.LIGHT_GRAY); // Colore di riempimento 
        g2d.fillPolygon(xPoints, yPoints, 3); 
        
        d[0] = 1;
        d[1] = 1;
        d[2] = 0;

        double cubo[][] = generaPunti();


        //rotazione del quadrato
        for(int i=0; i<4; i++){
            cubo[i] = Formule.rotazione_Z(cubo[i], 45);
            cubo[i] = Formule.rotazione_X(cubo[i], 45);
            cubo[i] = Formule.rotazione_Y(cubo[i], 0);
            
        }


        //disegno del quadrato
        for(int i=0; i<4; i++){
            double p1[] = new double[3];
            p1[0] = cubo[i][0];
            p1[1] = cubo[i][1];
            p1[2] = cubo[i][2];
            int pConv1[] = Formule.conv_iso(centro, p1, zoom);

            double p2[] = new double[3];
            if(i<3){
                p2[0] = cubo[i+1][0];
                p2[1] = cubo[i+1][1];
                p2[2] = cubo[i+1][2];
            }else{
                p2[0] = cubo[0][0];
                p2[1] = cubo[0][1];
                p2[2] = cubo[0][2];
            }
            int pConv2[] = Formule.conv_iso(centro, p2, zoom);

            g2d.drawLine(pConv1[0], pConv1[1], pConv2[0], pConv2[1]);*/


        }

        public void ruotaFig_X(double angolo){
            for(double[][] c : cubo){ //divisione di ogni riga della lista dei triangoli. Dopo di accede alla prima posizione di ogni stringa, che indica la cordinata del triangolo
                for(int i=0; i<c.length; i++){     
                    c[i] = Formule.rotazione_X(c[i], angolo);         
                }
            }
        }

        public void ruotaFig_Y(double angolo){
            for(double[][] c : cubo){ //divisione di ogni riga della lista dei triangoli. Dopo di accede alla prima posizione di ogni stringa, che indica la cordinata del triangolo
                for(int i=0; i<c.length; i++){     
                    c[i] = Formule.rotazione_Y(c[i], angolo);         
                }
            }
        }

        public void ruotaFig_Z(double angolo){
            for(double[][] c : cubo){ //divisione di ogni riga della lista dei triangoli. Dopo di accede alla prima posizione di ogni stringa, che indica la cordinata del triangolo
                for(int i=0; i<c.length; i++){     
                    c[i] = Formule.rotazione_Z(c[i], angolo);         
                }
            }
        }
}
    

