/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgProcesso;

/**
 *
 * @author Leonardo Neves
 * @author Matheus Coelho
 * @author Rômulo Lima
 */
public interface intProcesso {
    public String getN();
    public int getChegada();
    public void setTurnaround(int turn);
    public int getTurnaround();
    public int getValorSurto();
    public int getValorSurtoOriginal();
    public void setSurto();
    public void setEspera();
    public int getEspera();
    public boolean getIsIO();
    public int getInicioIO();
    public int getDuracaoOriginalIO();
    public int getTempoIOExecutado();
    public int getTempoIORestante();
    public void setEsperaIO();
    public void setEmExecucaoIO(boolean nStatus);
    public boolean getEmExecucaoIO();
    public int momentoInicioIO();
}
