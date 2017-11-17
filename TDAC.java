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
import java.util.TreeMap;
import java.util.Map;


/**
 * Classe Tabela de Dados Agrupados por Classe
 * @author Gabriel Padovani
 */
public class TDAC {
    
    // Tab. Frequencia simples
    private Map<Integer,Integer> TFS = new TreeMap<>(); 
    // Tab. Freq. Dados agrup. classes
    private Map<Integer,classe> tdac = new TreeMap<>(); 
    private int At; // Amplitude total
    private int K; // Número de classes
    private int N; // Frequancia Total
    private int Maior; // Ocorrencia de maior valor
    private int Menor; // Ocorrencia de maior valor
    private double Ac; // Amplitude de classe
    private double LSC; // Limite superior da ultima classe
     
    /**
     * Recebe a tabela de frequencia simples sobre dados brutos
     * Uso da biblioteca Hashmap e map
     * @param tfs
     */
    public TDAC(Map<Integer,Integer> tfs){
        this.TFS = tfs;
        inicializarVariaveis();
    }
    
    public final void inicializarVariaveis(){
        this.Maior = maiorKey();
        this.Menor = menorKey();
        this.At = amplitudeTotal();
        this.N = frequenciaTotal();
        this.K = sturges();
        this.Ac = amplitudeClasse();
        this.LSC = LSC();
    }
    
    /**
     * Metodo imprime as variaveis necessarias para
     * construir a tabela de distribuicao de frequencias
     * agrupadas por classes
     * @throws java.io.IOException
     */
    public void imprime() throws IOException{
        FileWriter arq = new FileWriter("variaveis.txt");         
        BufferedWriter bw = new BufferedWriter(arq);
       
        bw.write("Variaveis:");
        bw.newLine();bw.newLine();
        bw.write("Total de observacoes(N): " + this.N);
        bw.newLine();
        bw.write("Formula de Sturges (K): " + this.K);
        bw.newLine();
        bw.write("Amplitude de classe (Ac): "+this.Ac);
        bw.newLine();
        bw.write("Amplitude total (At): " + this.At);
        bw.newLine();
        bw.write("Limite superior da ultima classe: " + this.LSC);
        bw.newLine(); 
        bw.write("Menor valor de ocorrencia: " + this.Menor);
        bw.newLine(); 
        bw.write("Maior valor de ocorrencia: " + this.Maior);
         // fecha arquivo
        bw.close();
    }
    
    /**
     * Metodo para encontrar a amplitude total do conjunto 
     * do dados brutos 
     * @return At
     */
    public int amplitudeTotal(){
        return (this.Maior - this.Menor);
    }
    
    /**
     * Metodo encontrar ocorrencia de maior valor 
     * @return maiorKey
     */
    public int maiorKey(){
        int maiorKey = 0;
        for (int key : this.TFS.keySet()) {

              if(key > maiorKey){
                  maiorKey = key;
              }
        }
        this.Maior = maiorKey;
        return maiorKey;
    }
    
    /**
     * Metodo encontrar ocorrencia de menor valor
     * @return menorKey
     */
    public int menorKey(){
        int menorKey = this.Maior;
        for (int key : this.TFS.keySet()) {

              if(key < menorKey){
                  menorKey = key;
              }
        }
        this.Menor = menorKey;
        return menorKey;
    }
    
    /**
     * Metodo para calcular numero de classes: formula de Sturges
     * k = (1 + 3,3 log10 N) 
     * @return K
     */
    public int sturges(){
       /* public int sturges(int n){
        double k = 0;
            k = (1 + (3.3*(Math.log(n))));
       // return (int) Math.round(k);*/
       
        // A atividade avaliativa determinou que k = 7
        return 7;
    }
    
    /**
     * Metodo retorna numero de casos observados (N)
     * @return N
     */
    public int frequenciaTotal(){
        int n = 0;
        for (Map.Entry<Integer, Integer> entry : this.TFS.entrySet()){
                n += entry.getValue();
        }
        return n;
    }

    /**
     * Amplitude do intervalo de classe
     * @return Ac
     */
    public double amplitudeClasse(){
        String s;
        double ac = (double)this.At/(double)this.K;
        //System.out.println(ac);
        DecimalFormat decimal = new DecimalFormat( "0.0" );
        s = decimal.format(ac);
        s = s.replace(",", ".");
        ac = Double.parseDouble(s);
        return ac;
    } 

    /**
     * Metodo determinar o limite superior da ultima classe
     * @return LSC
     */
    public double LSC(){
        double lsc;
        lsc = (double)this.Menor + ((double)this.K * (double)this.Ac);
        if(lsc <= (double)this.Maior){
            lsc = this.Maior;
        }
        return lsc;
    }
    
    /**
     * Metodo constroi a tabela de dados agrupados por classes
     * @throws java.io.IOException
    */
    public void tdac() throws IOException{
                classe c;
		double lim_inf = this.Menor;
                double lim_sup = 0;
                int frequencia;
                boolean lim = false;
		// criar classes
		for(int i = 0;i < this.K;i++){
                    if(i+1 == this.K){
                        lim_sup = lim_inf + this.Ac;
                        if(lim_sup<this.LSC){
                        lim = true;
                        lim_sup = this.LSC;
                        }
                        frequencia = somaFreq(lim_inf,lim_sup,lim);
                        c = new classe(lim_inf,lim_sup,frequencia);
                        this.tdac.put(i,c);
                        this.tdac.get(i).setLimSup(lim);
                        //lim_inf = lim_sup;
                    }else{
                        lim_sup = lim_inf + this.Ac;
                        frequencia = somaFreq(lim_inf,lim_sup,lim);
                        c = new classe(lim_inf,lim_sup,frequencia);
                        this.tdac.put(i,c);
                        lim_inf = lim_sup;
                    }
		}
            imprimeTabela mo = new imprimeTabela();
            mo.imprimeTdac(this.tdac);
    }
	
    /**
     * Metodo retorna soma da frequencia dentro
     * dos limites inferior e superior de uma classe
     * @param lim_inf
     * @param lim_sup
     * @return soma
     */
    public int somaFreq(double lim_inf, double lim_sup, boolean lim){
    int soma = 0;
    if(lim){
    for (Map.Entry<Integer, Integer> entry : this.TFS.entrySet()) {
            if((entry.getKey()>=lim_inf)&&(entry.getKey()<=lim_sup)){
                    soma += entry.getValue();
            }
    }
    }else{
    for (Map.Entry<Integer, Integer> entry : this.TFS.entrySet()) {
            if((entry.getKey()>=lim_inf)&&(entry.getKey()<lim_sup)){
                    soma += entry.getValue();
            }
    }
    }
    return soma;
    }
    
    /**
     * Metodo para encontrar a Moda (Mo) em dados brutos
     * @throws java.io.IOException
     */
    public void moda() throws IOException{
        int indice = -1, valor = Integer.MIN_VALUE; 
        for (Map.Entry<Integer, Integer> entry : this.TFS.entrySet()) {
            if (valor < entry.getValue()){
                indice = entry.getKey(); valor = entry.getValue();
            }
        }
        Map<Integer,Integer> Moda = new TreeMap<>();
         for (Map.Entry<Integer, Integer> entry : this.TFS.entrySet()) {
             if (valor == entry.getValue()){
                Moda.put(entry.getKey(),entry.getValue());
            }
        }
        
        imprimeTabela mo = new imprimeTabela();
        mo.imprimeModa(Moda,this.TFS.size());
    }
}
