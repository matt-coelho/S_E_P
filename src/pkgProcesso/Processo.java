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
public class Processo implements intProcesso{
    String n = "";
    int surto = 0;
    int valorSurtoOriginal = 0;
    int tChegada = 0;
    int tEspera = 0;
    int turnaround = 0;
    boolean isIOop;
    int tInicioIO = 0;
    int valorDuracaoIOOriginal = 0;
    int duracaoIO = 0;
    boolean operacaoIOAndamento;
    
    /**Define um processo requer (nome, tempo de surto, tempo de chegada, possuir ou nao IO, tempo de inicio e fim de IO
     * @param n
     * @param surto
     * @param tChegada
     * @param io
     * @param tIniIO
     * @param tDio*/
    public Processo (String n, int surto, int tChegada, boolean io, int tIniIO, int tDio) {
        this.tEspera = 0;
        this.turnaround = 0;
        this.n = n; //nome do processo ex: P1
        this.tChegada = tChegada; //tempo de chegada do processo
        this.valorSurtoOriginal = surto; //tempo de surto original do processo
        this.surto = surto;
        this.isIOop = io; //status IO
        this.tInicioIO = tIniIO; //momento de inicio do IO
        this.valorDuracaoIOOriginal = tDio; // duracao original de IO
        this.duracaoIO = tDio;
        this.operacaoIOAndamento = false;
    }
    
    /**retorna o nome do processo
     * @return ""+n*/
    @Override
    public String getN(){
        return ""+this.n;
    }
    
    /**retorna o tempo de chegada
     * @return tChegada*/
    @Override
    public int getChegada(){
        return this.tChegada; 
    }
    
    /**define o turnaround do processo
     * @param turn*/
    @Override
    public void setTurnaround(int turn){ //tempo de vida do processo
        this.turnaround = turn;
    }
    
    /**retorna o turnaround do processo
     * @return turnaround*/
    @Override
    public int getTurnaround(){ //tempo de vida do processo
        return this.turnaround;
    }
    
    /**retorna o tempo de surt
     * @return surto*/
    @Override
    public int getValorSurto(){
        return this.surto;
    }
    
    /**retorna o valor original do surto do processo
     * @return hSurto*/
    @Override
    public int getValorSurtoOriginal(){
        return this.valorSurtoOriginal;
    }
    
    /**decrementa o tempo de surto (esta sendo processado)*/
    @Override
    public void setSurto(){
        this.surto--;
    }
    
    /**incrementa o tempo de espera, esta na fila espera*/
    @Override
    public void setEspera(){ 
        this.tEspera++;
    }
    
    /**retorna o tempo de que o processo esperou
     * @return tEspera*/
    @Override
    public int getEspera(){ 
        return this.tEspera; 
    }
    //--------------------------------------------------------------------------
    /**retorna true ou false para status de I/O (este e ou nao um processo de I/O
     * @return true or false)*/
    @Override
    public boolean getIsIO(){
        return this.isIOop;
    }
    
    /**retorna o tempo do inicio do IO
     @return int
    */
    @Override
    public int momentoInicioIO(){
        return this.valorSurtoOriginal - this.surto;
    }
    
    /**retorna o tempo de inicio de I/O
     * @return tInicioIO*/
    @Override
    public int getInicioIO(){
        return this.tInicioIO;
    }
    
    /**retorna a duracao de I/O
     * @return valor de duracao original do IO*/
    @Override
    public int getDuracaoOriginalIO(){
        return this.valorDuracaoIOOriginal;
    }
    
    /**retorna o tempo executado de I/O
     * @return duracao original - tempo esperado*/
    @Override
    public int getTempoIOExecutado(){
        return this.valorDuracaoIOOriginal - this.duracaoIO;
    }
    
    /**retorna o restante de I/O
     @return duracaoIO
     */
    @Override
    public int getTempoIORestante(){
        return this.duracaoIO;
    }
    
    /**Decrementa o tempo de duracao do IO
     */
    @Override
    public void setEsperaIO(){
        this.duracaoIO--;
    }
    
    /**define se o processo está ou não no momento de IO
     * @param nStatus
     */
    @Override
    public void setEmExecucaoIO(boolean nStatus){
        this.operacaoIOAndamento = nStatus;
    }
    
    /**retorna se o processo está ou não no momento de IO
     * @return statusIORunning
     */
    @Override
    public boolean getEmExecucaoIO(){
        return this.operacaoIOAndamento;
    }
}