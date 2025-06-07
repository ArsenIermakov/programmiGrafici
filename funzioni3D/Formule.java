
public class Formule {

    public static int xCam = 10;
    public static int yCam = 10;
    public static int zCam = 10;

    /*Funzione statica che, dato un punto in 3d, restituisce il punto convertito in 2d, secondo
      l'assonometrica isometrica. centro[] indica le cordinate del centro nella finestra, p3d è
      il punto a tre cordinate che deve essere convertito, zoom è quanto deve essere ingrandita 
      la interfaccia.
     */
    
    public static int[] conv_iso(int centro[], double p3d[], double zoom) {
        int p2d[] = new int[2];
        double angolo30 = Math.toRadians(30);
        double angolo60 = Math.toRadians(60);
        
        double x_p1 = centro[0] - (p3d[0] * Math.cos(angolo30) * zoom);
        double y_p1 = centro[1] + (p3d[0] * Math.cos(angolo60) * zoom);
        
        double x_p2 = x_p1 + (p3d[1] * Math.cos(angolo30) * zoom);
        double y_p2 = y_p1 + (p3d[1] * Math.cos(angolo60) * zoom);        
        double x_p3 = x_p2;
        double y_p3 = y_p2 - (p3d[2] * zoom);
        
        p2d[0] = (int)Math.round(x_p3);
        p2d[1] = (int)Math.round(y_p3);
    
        return p2d;
    }

    public static int[] conv_generica(int centro[], double p3d[], double zoom, double angoloX, double angoloY) {
        int p2d[] = new int[2];
        
        double radX = Math.toRadians(angoloX);
        double radY = Math.toRadians(angoloY);
        
        double x_p1 = centro[0] - (p3d[0] * Math.cos(radX) * zoom);
        double y_p1 = centro[1] + (p3d[0] * Math.sin(radX) * zoom);
        
        double x_p2 = x_p1 + (p3d[1] * Math.cos(radY) * zoom);
        double y_p2 = y_p1 + (p3d[1] * Math.sin(radY) * zoom);
        
        double x_p3 = x_p2;
        double y_p3 = y_p2 - (p3d[2] /** (Math.sin(Math.PI - (radX+radY+(radX/2)+(radY/2)))) */* zoom);
        
        p2d[0] = (int)Math.round(x_p3);
        p2d[1] = (int)Math.round(y_p3);
        
        return p2d;
    }



    public static double funzione(double x, double y){
        return (Math.pow(x, 2) + Math.pow(y, 2));
    }

    

    /*funzione statica che dato un punto 3d, lo converte nella visuale dell'assonometria 
     * cavaliera. centro[] sono le cordinate del centro della finestra, p3d è il punto 3d,
     * zoom è quanto è grande una unità sull'asse delle X nel piano cartesiano.
     */

    public static int[] conv_cav(int centro[], double p3d[], double zoom){
        int p2d[] = new int[2];
        double angolo45 = Math.toRadians(45);


        double x_p1 = centro[0] - ((p3d[0] *  zoom));
        double y_p1 = centro[1];

        double x_p2 = x_p1 + ((p3d[1] / 2) * Math.cos(angolo45) * zoom);
        double y_p2 = y_p1 + ((p3d[1] / 2) * Math.cos(angolo45) * zoom);

        double x_p3 = x_p2;
        double y_p3 = y_p2 - (p3d[2] * zoom);
        
        p2d[0] = (int)Math.round(x_p3);
        p2d[1] = (int)Math.round(y_p3);
        
        return p2d;
    }

    public static double[] rotazione_X(double p[], double angolo){
        double punto[] = new double[3];
        punto[0] = p[0];
        punto[1] = Math.cos(Math.toRadians(angolo))*p[1] - Math.sin(Math.toRadians(angolo))*p[2];
        punto[2] = Math.sin(Math.toRadians(angolo))*p[1] + Math.cos(Math.toRadians(angolo))*p[2];
        return punto;
    }

    public static double[] rotazione_Y(double p[], double angolo){
        double punto[] = new double[3];
        punto[0] = Math.cos(Math.toRadians(angolo))*p[0] + Math.sin(Math.toRadians(angolo))*p[2];
        punto[1] = p[1];
        punto[2] = -Math.sin(Math.toRadians(angolo))*p[0] + Math.cos(Math.toRadians(angolo))*p[2];
        return punto;
    }

    public static double[] rotazione_Z(double p[], double angolo){
        double punto[] = new double[3];
        punto[0] = Math.cos(Math.toRadians(angolo))*p[0] - Math.sin(Math.toRadians(angolo))*p[1];
        punto[1] = Math.sin(Math.toRadians(angolo))*p[0] + Math.cos(Math.toRadians(angolo))*p[1];
        punto[2] = p[2];
        return punto;
    }


    /*funzione che va a leggere l'oggetto dal file .obj. Rewstituisce una lista di matrice double, che contiene per ogni elemento le cordinate di ogni triangolo.
    pos[0] = {
        [1,1,1]
        [2,2,2]
        [3,3,3]
    }
    */
    /*public static ArrayList<double[][]> letturaFile() {
        ArrayList<double[]> vertici = new ArrayList<double[]>(); //lista di vettori double che contiene i vertici della figura ottenuti nel file;
        ArrayList<String> triangoli = new ArrayList<String>(); //lista di stringhe che contiene i triangoli della figura;
                                                                //0/0/0 1/1/1 2/2/2
        try {
            BufferedReader br = new BufferedReader(new FileReader("scimmia.obj")); //apertura file .obj;
            String riga;       

            while((riga=br.readLine()) != null){ //leggi fino alla fine del file;
                String[] testo = riga.split(" "); //dividi la riga in righe divise da spazi;
                switch(testo[0]){ //swhitch case sul primo carattere della riga letta
                    case "v":{ //vertice;
                        vertici.add(new double[]{
                            Double.parseDouble(testo[1]), //prima cordinata
                            Double.parseDouble(testo[2]), //seconda cordinata
                            Double.parseDouble(testo[3]) //terza cordinata
                        });
                        //System.out.println("vertice: " + testo[1] + " " +  testo[2] + " " + testo[3] + "£");
                        break;
                    }
                    case "f":{ //face = triangolo;
                        String app = "";
                        for(int i=1; i<testo.length; i++){
                            if(i==1){
                                app = testo[i];
                            }else{
                                app = app + " " + testo[i];
                            }   
                            
                        }
                        triangoli.add(app); //concatena 0/0/0 1/1/1 2/2/2
                        break;
                    }
                }
            }
        } catch (Exception e) {
        }

        ArrayList<double[][]> trian = new ArrayList<double[][]>(); //lista di matrice dei triangoli
                                                                // [1,1,1][2,2,2][3,3,3] primo triangolo
                                                                // [1,1,1][2,2,2][3,3,3] secondo triangolo ... 

        for(String tr : triangoli){ //divisione di ogni riga della lista dei triangoli. Dopo di accede alla prima posizione di ogni stringa, che indica la cordinata del triangolo
            String[] testo = tr.split(" "); //dopo di che, si va a cercare la cordinata della lista dei vertici, e la si inserisce nella matrice;
            int lungTesto = testo.length;
            double[][] tab = new double[lungTesto][3];
            for(int i=0; i<lungTesto; i++){
                String[] num = testo[i].split("/"); 
                int posizione = Integer.parseInt(num[0]);
                //System.out.print("carattere: " + posizione);
                tab[i] = vertici.get(posizione-1); 
            }
            //System.out.println();
            trian.add(tab);
        }
        return trian;
        

        /*for(double[][] tr : trian){ //stampa lista delle cordinate dei triangoli
            for(int i=0; i<tr.length; i++){
                System.out.print("[");
                for(int j=0; j<tr[i].length; j++){
                    System.out.print(tr[i][j] + " ");
                }
                System.out.print("]");
            }
            System.out.println();
        }

    }*/

    /*public static double distanzaDuePunti(double p1[], double p2[]){
        double d = Math.sqrt(Math.pow((p1[0] - p2[0]), 2) + Math.pow((p1[1] - p2[1]), 2) + Math.pow((p1[2] - p2[2]), 2));
        return d;
    }

    public static int[] conv_prosp(int centro[], double p3d[], double zoom){
        int p2d[] = new int[2];
        double f = 
        p2d[0] = Math.round(())
    }*/


}
