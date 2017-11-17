/*
 * Trabalho final de estatistica - 2/2017
 * Decom - CEFET/MG
 *
 * Algoritmo para encontrar a Moda numa amostra de dados brutos e
 * gerar tabela de distribuicao de frequencias agrupadas por classes
 * desses dados
 */
package estatistica;

/**
 * Classe para estrutura de dados que ira compor a tabela
 * de dados agrupados por classe
 * @author Gabriel Padovani
 */
public class classe {
    private double limiteINF;
    private double limiteSUP;
    private int frequencia;
    private boolean limSup = false;

    public classe(double limiteINF, double limiteSUP, int frequencia) {
        this.limiteINF = limiteINF;
        this.limiteSUP = limiteSUP;
        this.frequencia = frequencia;
    }

    public double getLimiteINF() {
        return limiteINF;
    }

    public void setLimiteINF(double limiteINF) {
        this.limiteINF = limiteINF;
    }

    public double getLimiteSUP() {
        return limiteSUP;
    }

    public void setLimiteSUP(double limiteSUP) {
        this.limiteSUP = limiteSUP;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    public boolean isLimSup() {
        return limSup;
    }

    public void setLimSup(boolean limSup) {
        this.limSup = limSup;
    }
    
    
}
