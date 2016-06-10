package desafios;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.processing.Filer;
 
/**
 *
 * @author augustomeira
 */
public class DesenhaPontos {
 
    int[][][] figura;
    static final File dir =
            new File("");
 
    public void setPreto(int i, int j) {
        figura[i][j][0] = 0;
        figura[i][j][1] = 0;
        figura[i][j][2] = 0;
    }
    
    public void setRed(int i, int j) {
        figura[i][j][0] = 255;
        figura[i][j][1] = 0;
        figura[i][j][2] = 0;
    }

    public void setC1(int i, int j) {
        figura[i][j][0] = 255;
        figura[i][j][1] = 4;
        figura[i][j][2] = 179;
    }
    
    public void setC2(int i, int j) {
        figura[i][j][0] = 230;
        figura[i][j][1] = 4;
        figura[i][j][2] = 255;
    }
    
    public void setC3(int i, int j) {
        figura[i][j][0] = 179;
        figura[i][j][1] = 4;
        figura[i][j][2] = 255;
    }
    
    public void setC4(int i, int j) {
        figura[i][j][0] = 79;
        figura[i][j][1] = 4;
        figura[i][j][2] = 255;
    }
    
    public void setC5(int i, int j) {
        figura[i][j][0] = 4;
        figura[i][j][1] = 205;
        figura[i][j][2] = 255;
    }
    
    public void setC6(int i, int j) {
        figura[i][j][0] = 4;
        figura[i][j][1] = 255;
        figura[i][j][2] = 163;
    }
    
    public void setC7(int i, int j) {
        figura[i][j][0] = 4;
        figura[i][j][1] = 205;
        figura[i][j][2] = 255;
    }
    
    public void setC8(int i, int j) {
        figura[i][j][0] = 4;
        figura[i][j][1] = 255;
        figura[i][j][2] = 63;
    }
    
    public void setC9(int i, int j) {
        figura[i][j][0] = 4;
        figura[i][j][1] = 255;
        figura[i][j][2] = 4;
    }
    
    public void setC10(int i, int j) {
        figura[i][j][0] = 4;
        figura[i][j][1] = 205;
        figura[i][j][2] = 255;
    }
    
    public void setC11(int i, int j) {
        figura[i][j][0] = 196;
        figura[i][j][1] = 255;
        figura[i][j][2] = 4;
    }
    
    public void setC12(int i, int j) {
        figura[i][j][0] = 238;
        figura[i][j][1] = 255;
        figura[i][j][2] = 4;
    }
    
    public void setC13(int i, int j) {
        figura[i][j][0] = 4;
        figura[i][j][1] = 205;
        figura[i][j][2] = 255;
    }
    
    public void setC14(int i, int j) {
        figura[i][j][0] = 255;
        figura[i][j][1] = 196;
        figura[i][j][2] = 4;
    }
    
    public void setC15(int i, int j) {
        figura[i][j][0] = 255;
        figura[i][j][1] = 146;
        figura[i][j][2] = 4;
    }
    
    public void setC16(int i, int j) {
        figura[i][j][0] = 164;
        figura[i][j][1] = 122;
        figura[i][j][2] = 67;
    }
    
    public void setC17(int i, int j) {
        figura[i][j][0] = 96;
        figura[i][j][1] = 156;
        figura[i][j][2] = 77;
    }
    
    public void setC18(int i, int j) {
        figura[i][j][0] = 174;
        figura[i][j][1] = 175;
        figura[i][j][2] = 208;
    }
    
    public void setC19(int i, int j) {
        figura[i][j][0] = 206;
        figura[i][j][1] = 156;
        figura[i][j][2] = 208;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void setBranco(int i, int j) {
        figura[i][j][0] = 255;
        figura[i][j][1] = 255;
        figura[i][j][2] = 255;
    }
 
    public void imprimePontos() {
        try {
            int xmax = Integer.MIN_VALUE;
            int ymax = Integer.MIN_VALUE;
            int xmin = Integer.MAX_VALUE;
            int ymin = Integer.MAX_VALUE;
 
            //primeira vez encontra os limites xmax, xmin, ymax, ymin
            //segunda vez imprime o arquivo ppm
 
            for (int vez = 0; vez < 2; vez++) {
                int dx = 0;
                int dy = 0;
                BufferedWriter out = null;
                if (vez == 1) {
                    dx = xmax - xmin + 1;
                    dy = ymax - ymin + 1;
                    //cada posicao da matrix eh uma coordenada x,y com tres
                    //dimensoes [x][y][0] [x][y][1] [x][y][2] para R-G-B
                    figura = new int[dx][dy][3];
                    out = new BufferedWriter(new FileWriter(new File( "_aux.ppm")));
                    //cabecalho da figura ppm
                    //http://en.wikipedia.org/wiki/Netpbm_format#PPM_example
                    out.write("P3\n");
                    out.write(dy + " " + dx + "\n");
                    out.write("255\n");
 
                    //inicializa matriz quadriculada
                    for (int i = 0; i < dx; i++) {
                        for (int j = 0; j < dy; j++) {
                            if (i % 100 == 9 || j % 100 == 9) {
                                setPreto(i, j);
                            } else {
                                setBranco(i, j);
                            }
 
                        }
                    }
 
                }
 
 
                //coordenadas.
                BufferedReader b =
                        new BufferedReader(
                        new FileReader(
                        new File("coordenadas.txt")));
                //leitura
                String linha = b.readLine();
                while (linha != null && linha.length() > 0) {
                    String[] lista = linha.split(";");
 
                    double a1 = Double.parseDouble(lista[3]);
                    double a2 = Double.parseDouble(lista[4]);
 
                    String estado = lista[2];
                    
                    //converte em inteiro com um fator de escala 30.
                    int x = (int) (a1 * 30);
                    int y = (int) (a2 * 30);
 
                     
                    if (vez == 0) {
                        //procura min  e max
                        if (x < xmin) {
                            xmin = x;
                        }
                        if (x > xmax) {
                            xmax = x;
                        }
                        if (y < ymin) {
                            ymin = y;
                        }
                        if (y > ymax) {
                            ymax = y;
                        }
                    } else {
                        //imprime latitude e longitude
                    	if(estado.equals("SC"))
                    		setRed(x - xmin, y - ymin);
                    	else if(estado.equals("SP"))
                    		setC1(x - xmin, y - ymin);
                    	else if(estado.equals("MT"))
                    		setC2(x - xmin, y - ymin);
                    	else if(estado.equals("PR"))
                    		setC3(x - xmin, y - ymin);
                    	else if(estado.equals("MA"))
                    		setC4(x - xmin, y - ymin);
                    	else if(estado.equals("TO"))
                    		setC5(x - xmin, y - ymin);
                    	else if(estado.equals("BA"))
                    		setC6(x - xmin, y - ymin);
                    	else if(estado.equals("RO"))
                    		setC7(x - xmin, y - ymin);
                    	else if(estado.equals("AC"))
                    		setC8(x - xmin, y - ymin);
                    	else if(estado.equals("RR"))
                    		setC9(x - xmin, y - ymin);
                    	else if(estado.equals("RN"))
                    		setC10(x - xmin, y - ymin);
                    	else if(estado.equals("PB"))
                    		setC11(x - xmin, y - ymin);
                    	else if(estado.equals("PE"))
                    		setC12(x - xmin, y - ymin);
                    	else if(estado.equals("AL"))
                    		setC13(x - xmin, y - ymin);
                    	else if(estado.equals("SE"))
                    		setC14(x - xmin, y - ymin);
                    	else if(estado.equals("ES"))
                    		setC15(x - xmin, y - ymin);
                    	else if(estado.equals("RJ"))
                    		setC16(x - xmin, y - ymin);
                    	else if(estado.equals("RS"))
                    		setC17(x - xmin, y - ymin);
                    	else if(estado.equals("AM"))
                    		setC18(x - xmin, y - ymin);
                    	else if(estado.equals("DF"))
                    		setC19(x - xmin, y - ymin);
                    	else if(estado.equals("PI"))
                    		setRed(x - xmin, y - ymin);
                    	else if(estado.equals("AP"))
                    		setRed(x - xmin, y - ymin);
                    	else
                    		setPreto(x - xmin, y - ymin);
                    }
                    assert (ymin <= ymax);
 
                    linha = b.readLine();
                }
                if (vez == 1) {
                    //imprime o arquivo.
                    for (int i = dx - 1; i >= 0; i--) {
                        for (int j = 0; j < dy; j++) {
                            out.write(figura[i][j][0] + " " +
                                    figura[i][j][1] + " " +
                                    figura[i][j][2] + " ");
                        }
                        out.write("\n");
                    }
 
                    out.write("\n");
                    out.close();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DesenhaPontos.class.getName()).log(Level.SEVERE, null, ex);
        }
 
 
    }
 
    //leitura de pontos. Exemplo.
    public void le(){
        File dir =
            new File("");
        BufferedReader b = null;
        try{
            b = new BufferedReader(
                    new FileReader(
                    new File("coordenadas.txt")));
            String linha = b.readLine();
            while (linha != null && linha.length() > 0) {
                String[] lista = linha.split(";");
                for(int i=0;i<lista.length;i++){
                    System.out.println(lista[i]+" ");
                }
                double lati = Double.parseDouble(lista[3]);
                double longi = Double.parseDouble(lista[4]);
 
                linha = b.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }       
    }
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DesenhaPontos m = new DesenhaPontos();
        m.le();
        m.imprimePontos();
    }
}