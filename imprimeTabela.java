/*
 * Trabalho final de estatistica - 2/2017
 * Decom - CEFET/MG
 *
 * Algoritmo para encontrar a Moda numa amostra de dados brutos e
 * gerar tabela de distribuicao de frequencias agrupadas por classes
 * desses dados
 */
package estatistica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;

/**
 *
 * @author Gabriel Padovani
 */
public class imprimeTabela {
    
    /**
    * Metodo imprime a tabela de freqüências simples 
    * (não agrupadas em classes)
    * @param tfs
    * @throws java.io.IOException
    */
    public void imprimeTFS(Map<Integer,Integer> tfs) throws IOException{
        FileWriter arq = new FileWriter("tfs.txt");         
        BufferedWriter bw = new BufferedWriter(arq);
        int k = 70;
       
        bw.write("\tTabela de frequencia simples sobre dados brutos");
        bw.newLine();
        bw.write("\t" + Linha(k));
        bw.newLine();
        bw.write("\t\tvalor\t|\tfrequencia");
        bw.newLine();
        bw.write("\t" + Linha(k));
        bw.newLine();
        int total=0;
            for (Map.Entry<Integer, Integer> entry : tfs.entrySet()) {
                
                total += entry.getValue();

                bw.write("\t\t" + entry.getKey() + "\t|\t" 
                        + entry.getValue());
                bw.newLine();
            }
        //System.out.println(total);
        bw.write("\t" + Linha(k));
        bw.newLine();
        bw.write("\t\tTotal\t|\t" + total);
        bw.newLine(); 
        bw.write("\t" + Linha(k));
      
         // fecha arquivo
        bw.close();
    }
    
    /**
     * Metodo imprime Moda
     * @param indice
     * @param valor
     * @param ctrl
     * @throws java.io.IOException
     */
    public void imprimeModa(Map<Integer,Integer> moda,int tamanho) 
            throws IOException{
        FileWriter arq = new FileWriter("moda.txt");         
        BufferedWriter bw = new BufferedWriter(arq);
        int k = 70;
       
         bw.write("\t\t\tMODA");
         bw.newLine();
         bw.write("\t" + Linha(k));
         bw.newLine();
        if(tamanho == moda.size()){
            bw.write("\tNao ocorre a moda na amostra.");
            bw.newLine();
            bw.write("\tAmostra possui ocorrencias com");
            bw.newLine();
            bw.write("\tmesmo numero de frequencias.");
            bw.newLine();
            bw.write("\t" + Linha(k));
            bw.newLine();
            
        }else{
            bw.write("\t\tvalor\t|\tfrequencia");
            bw.newLine();
            bw.write("\t" + Linha(k));
            bw.newLine();
            for (Map.Entry<Integer, Integer> entry : moda.entrySet()) {
            bw.write("\t\t" + entry.getKey() + 
                    "\t|\t" +entry.getValue());
            bw.newLine();
            }
            bw.write("\t" + Linha(k));  
        }
        // fecha arquivo
            bw.close();
    }
    
    /**
     * Metodo imprime a tabela de distribuicao de frequencia 
     * agrupada por classes
     * @param tdac
     * @throws java.io.IOException
     */
    public void imprimeTdac(Map<Integer,classe> tdac) throws IOException{
        FileWriter arq = new FileWriter("tdac.txt");         
        BufferedWriter bw = new BufferedWriter(arq);
        int k = 80;
       
        bw.write("\t\tTabela de distribuicao de frequencia"
                + " agrupada por classes");
        bw.newLine();
        bw.write("\t" + Linha(k));
        bw.newLine();
        bw.write("\t\tFaixa de valores\t\t|\t Frequencia");
        bw.newLine();
        bw.write("\t" + Linha(k));
        bw.newLine();
        int total=0;
        for (Map.Entry<Integer, classe> entry : tdac.entrySet()) {

            total += entry.getValue().getFrequencia();

            bw.write("\t\t " + limClasse(entry.getValue().getLimiteINF())
                    + "\t"); 
            if(entry.getValue().isLimSup()){
                bw.write(" |--| ");
            }else{
                bw.write(" |-- ");
            }
            bw.write("\t" + limClasse(entry.getValue().getLimiteSUP()) + 
                    " \t\t|\t" + entry.getValue().getFrequencia());
            bw.newLine();
        }
        //System.out.println(total);
        bw.write("\t" + Linha(k));
        bw.newLine();
        bw.write("\t\t\t\t\tTotal\t|\t" + total);
        bw.newLine(); 
        bw.write("\t" + Linha(k));
      
         // fecha arquivo
        bw.close();
    }
    
     /**
     * Arredonda limite do intervalo de classe
     * @return Ic
     */
    public double limClasse(double Ic){
        String s;
        DecimalFormat decimal = new DecimalFormat( "0.0" );
        s = decimal.format(Ic);
        s = s.replace(",", ".");
        Ic = Double.parseDouble(s);
        return Ic;
    } 
    
    /**
     * Procedimento para complementar a tabela
     * @return linha
     */
    public String Linha(int k){
        String linha ="";
        for(int i = 0;i<=k;i++){
            linha+= "-";
        }        
        return linha;
    }
}
