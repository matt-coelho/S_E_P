/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgPoliticas;

import pkgProcesso.Processo;
/**
 *
 * @author Leonardo Neves
 * @author Matheus Coelho
 * @author Rômulo Lima
 */
public class Escalonamentos implements intEscalonamentos{
    /**executa o metodo fifo
     * @param todos
     * @param surtoTotal
     * @return String respostas[2]
     */
    @Override
    public String[] fifo(Processo [] todos, int surtoTotal){
        return ff(todos, surtoTotal);
    }
    //
    /**executa o metodo round robin
     * @param quantum
     * @param todos
     * @param surtoTotal
     * @return String respostas[2]
     */
    @Override
    public String[] roundRobin(int quantum, Processo [] todos, int surtoTotal){
        return rr(quantum, todos, surtoTotal);
    }
    //
    /**executa o metodo smalest job first
     @param  todos
     @param surtoTotal
     * @return String respostas[2]*/
    @Override
    public String[] smalestJobFirst(Processo [] todos, int surtoTotal){
        return sjf(todos, surtoTotal);
    }
    //
    /**executa o metodo smalest remaining time first
     * @param todos
     * @param surtoTotal
     * @return string respostas[2]*/
    @Override
    public String [] smalestRemainingTimeFirst(Processo [] todos, int surtoTotal){
        return srtf(todos, surtoTotal);
    }
    //--------------------------------------------------------------------------
    private String [] ff( Processo [] todos, int surtoTotal){
        FIFO pFifo = new FIFO();
        String [] rFifo = pFifo.FIFO(todos, surtoTotal);
        return rFifo;
    }
    //
    private String [] rr(int q, Processo [] todos, int surtoTotal){
        RR pRR = new RR();
        String [] rRR = pRR.RR(q, todos, surtoTotal);
        return rRR;
    }
    //
    private String [] sjf( Processo [] todos, int surtoTotal){
        SJF pSJF = new SJF();
        String [] rSJF = pSJF.SJF(todos, surtoTotal);
        return rSJF;
    }
    //
    private String[] srtf( Processo [] todos, int surtoTotal){
        SRTF pSRTF = new SRTF();
        String [] rSRTF = pSRTF.SRTF(todos, surtoTotal);
        return rSRTF;
    }
}