import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

//import java.awt.image.BufferedImage;

public class Pannello2 extends JPanel{
    public static int larghezzaUnita = 200;
    public static int numeroUnita = 4;
    public static int larghezza = larghezzaUnita*numeroUnita;
    public static int centro = larghezza/2;

    public static double xMin = -2;
    public static double xMax = 2;
    public static double yMin = -2;
    public static double yMax = 2;

    public static int interazioniMax = 200;
    public static double distanzaDivergenza = 2;

    public static double scala;
    public static int spostamentoPixel;

    public static int zoom;

    public Pannello2(){
        xMin = -2;
        xMax = 2;
        yMin = -2;
        yMax = 2;
        interazioniMax = 25;
        distanzaDivergenza = 2;
        scala = (xMax - xMin) / (double)larghezza;
        spostamentoPixel = 3;
        zoom = 5;
    }
    


@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    BufferedImage buffer = new BufferedImage(larghezza, larghezza, BufferedImage.TYPE_INT_RGB);

    for (int x = 0; x < larghezza; x++) {
        for (int y = 0; y < larghezza; y++) {
            double[] p = {conversionePixelNumeroComplesso_X(x), conversionePixelNumeroComplesso_Y(y)};
            int iterazioni = calcolaIterazioni(p);

            // Gradazione simile alla tua versione OpenGL
            float colore = iterazioni == interazioniMax ? 0.0f : iterazioni / (float) interazioniMax;
            int tono = (int) (colore * 255);
            int rgb = new Color(tono, tono, tono).getRGB();

            buffer.setRGB(x, y, iterazioni < interazioniMax ? rgb : 0x000000); // Nero ai bordi
        }
    }

    g2d.drawImage(buffer, 0, 0, null);
    g2d.setColor(Color.RED);
    g2d.drawLine(centro-1, centro-1, centro+1, centro+1);
    g2d.drawLine(centro+1, centro-1, centro-1, centro+1);
}


    public static int calcolaIterazioni(double[] n) {
        double[] z = {0, 0}; // Inizia con z = 0
        int iterazioni = 0;
        while (((z[0] * z[0] + z[1] * z[1]) < distanzaDivergenza * distanzaDivergenza) && (iterazioni < interazioniMax)) {
            double zReale = z[0] * z[0] - z[1] * z[1] + n[0];
            double zImmaginaria = 2 * z[0] * z[1] + n[1];
            z[0] = zReale;
            z[1] = zImmaginaria;
            iterazioni++;
        }
        return iterazioni;
    }
    

    public static double conversionePixelNumeroComplesso_X(int x_pixel) {
        // Larghezza totale in pixel del pannello
        //double scala = (double) scala / larghezza; // Rapporto tra intervallo complesso e larghezza pannello
        return xMin + (x_pixel * scala); // Converte il pixel in una coordinata sul piano complesso
    }

    public static double conversionePixelNumeroComplesso_Y(int y_pixel) {
        // Altezza totale in pixel del pannello
       // double scala = (double) (yMax - yMin) / larghezza; // Rapporto tra intervallo complesso e altezza pannello
        return yMax - (y_pixel * scala); // Nota: inverti perché l'asse Y è capovolto nei pixel
    }

    public static void spostamentoAsseX(int i){
        System.out.println("Dentro lo spostamento ");
        double s = scala*(double)spostamentoPixel;
        if(i==0){
            xMin -= s;
            xMax -= s;
        }else{
            xMin += s;
            xMax += s;
        }
        System.out.println("scala" + scala);
        System.out.println("spostamentoPixel: "+ spostamentoPixel);
        System.out.println("s: "+ s);
        System.out.println("xMin: " + xMin);
        System.out.println("xMax: " + xMax);
    }

    public static void spostamentoAsseY(int i){
        System.out.println("Dentro lo spostamento ");
        double s = scala*(double)spostamentoPixel;
        if(i==0){ //zoom
            yMin -= s;
            yMax -= s;
        }else{
            yMin += s;
            yMax += s;
        }
        System.out.println("scala" + scala);
        System.out.println("spostamentoPixel: "+ spostamentoPixel);
        System.out.println("s: "+ s);
        System.out.println("yMin: " + yMin);
        System.out.println("yMax: " + yMax);
    }

    public static void zoom(int i){
        System.out.println("Dentro lo zoom ");
        double s = scala*(double)zoom;
        if(i==0){ //zoom+
            yMin += s;
            yMax -= s;
            xMin += s;
            xMax -= s;
        }else{
            yMin -= s;
            yMax += s;
            xMin -= s;
            xMax += s;
        }
        System.out.println("zoom" + zoom);
        System.out.println("spostamentoPixel: "+ spostamentoPixel);
        System.out.println("s: "+ s);
        System.out.println("xMin: " + xMin);
        System.out.println("xMax: " + xMax);
        System.out.println("yMin: " + yMin);
        System.out.println("yMax: " + yMax);
        scala = (xMax - xMin) / (double)larghezza;
        //interazioniMax += 10;
    }





    
}
