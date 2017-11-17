/*
 * Trabalho final de estatistica - 2/2017
 * Decom - CEFET/MG
 *
 * Algoritmo para encontrar a Moda numa amostra de dados brutos e
 * gerar tabela de distribuicao de frequencias agrupadas por classes
 * desses dados
 */
package estatistica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.TreeMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Gabriel Padovani
 */
public class Estatistica {
    
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String line;
        int num=0;
        Map<Integer,Integer> mapDados = new TreeMap<>();
        
       /**
       *  Metodo ler arquivo entrada
       */
        BufferedReader arquivo;
        
        if(args.length > 0){
            arquivo = new BufferedReader(new FileReader(args[0]));
        }else{
            System.out.print("Endereco do diretorio: ");
            arquivo = new BufferedReader(new FileReader(in.nextLine()));
        }
        
        /**
         * Metodo leitura de linha por linha do arquivo
         * Mapa de frequencias de valores dos dados brutos gerado
         */
        while((line = arquivo.readLine()) != null){
            num = Integer.parseInt(line);
            //verifica se esse valor está no mapa
            Integer freq = mapDados.get(num); 	
            if (freq != null) { //se palavra existe, atualiza a frequencia
                    mapDados.put(num, freq+1);
            }
            else { // se palavra não existe, 
                    //insiro um novo valor e atribui frequencia = 1.
                    mapDados.put(num,1);
            }
        }
        // fecha arquivo
        arquivo.close();
        
        // Instancia objeto TDAC
        TDAC tdac = new TDAC(mapDados);
        
        /**
        * Imprime a moda dos dados brutos
        */
            tdac.moda();
        /**
        * Imprime Tabela Frequencia simples
        */
            imprimeTabela tfs = new imprimeTabela();
            tfs.imprimeTFS(mapDados);
        /**
         * Imprime Tabela Frequencia agrupada por classes
         */
            tdac.tdac();
       /**
        * Imprime variaveis necessarias para a TDAC
        */  
            tdac.imprime();
    }
}
