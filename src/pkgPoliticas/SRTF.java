/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgPoliticas;

import pkgListaSENO.ListaSENO;
import pkgGrafico.MostraGrafico;
import pkgProcesso.Processo;
import pkgResultado.Resultado;
import pkgSort.ProcessosSort;

/**
 *
 * @author Leonardo Neves
 * @author Matheus Coelho
 * @author Rômulo Lima
 */
public class SRTF {
    
    /**SRTF - Smalest Remaining Time First
     * @param todos
     * @param surtoTotal
     * @return */
    public String [] SRTF(Processo [] todos, int surtoTotal){
        return SmalestRemainingTimeFirst(todos, surtoTotal);
    }
    //
    private String [] SmalestRemainingTimeFirst(Processo [] todos, int surtoTotal){
        MostraGrafico graficoSRTF = new MostraGrafico();
        ProcessosSort sort = new ProcessosSort();
        ListaSENO prontos = new ListaSENO();
        String respostas [] = new String [2];
        String temposProcessos = "", nomeU = "";
        Processo P, p; //em processamento (P), em pronto (p)
        int Unt = 0, surtoRestante;//unidade de tempo, tempo de processamento sem preempcao(tpssp = 0,), surto restante de um processo
        boolean temProcessoMenor = false;
        //
        for(int item = 0; item < todos.length; item++){ //adiciona a prontos todos os processos que chegam no tempo Unt, zero
            if(todos[item].getChegada() == Unt){
                prontos.insertLast(todos[item]);
            }
        }
        //
        prontos = sort.menorSurtoListaSENO(prontos);
        graficoSRTF.geraGrafico(Unt, surtoTotal, false, prontos.getFirstProcess().getN());
        //
        while(prontos.isEmpty() != true){
            prontos = sort.menorSurtoListaSENO(prontos);
            P = prontos.getFirstProcess();
            prontos.removeFirst();
            temposProcessos += "\n\nEm processamento (SRTF) - "+P.getN()+"\ttempo: "+Unt;
            surtoRestante = P.getValorSurto();
            for(int x = 0; x < surtoRestante; x++){
                //tpssp++;//tempo de processamento sem preempção
                if(P.getIsIO() == true){//processamento com io
                    if((P.getValorSurtoOriginal() - P.getValorSurto()) >= P.getInicioIO() && (P.getTempoIOExecutado() - P.getDuracaoOriginalIO() != 0) ){ //se o IO comeca no momento de surto do processo e sua duracao nao terminou
                        P.setEsperaIO(); 
                        P.setEspera();
                        temposProcessos += " tempo *"+Unt+"*,"; //*tempo* == IO
                        graficoSRTF.geraGrafico(Unt, surtoTotal, false, P.getN());
                        surtoRestante++;// incremento o tempo de surto restante quando  executando IO pois este não faz parte do tempo de surto
                    }else{
                        P.setSurto();
                        temposProcessos += " tempo "+Unt+","; //tempo != IO
                        graficoSRTF.geraGrafico(Unt, surtoTotal, false, P.getN());
                        surtoTotal--;
                    }
                }else{//processamento sem io
                    P.setSurto();
                    temposProcessos += " tempo "+Unt+","; //tempo != IO
                    graficoSRTF.geraGrafico(Unt, surtoTotal, false, P.getN());
                    surtoTotal--;
                }
                //
                for(int y = 0; y < prontos.size(); y++){//incremento o tempo de espera dos processos em prontos
                    p = prontos.get(y);
                    p.setEspera();
                    prontos.set(y, p);
                }
                //
                if(Unt > 0){
                    for(int k = 0; k < todos.length; k++){
                        if(todos[k].getChegada() == Unt){
                            prontos.add(todos[k]);
                            if( todos[k].getValorSurtoOriginal() < P.getValorSurto() ){
                                temProcessoMenor = true;
                            }
                        }
                    }
                    prontos = sort.menorSurtoListaSENO(prontos);
                }
                //
                Unt++;
                //
                for(int item1 = 0; item1 < todos.length; item1++){//recolo o processo processado atualizado no vetor original
                    if(todos[item1].getN().equals(P.getN())){ //verifico se o processo tem o mesmo nome que o que foi processado para atualizacao
                        P.setTurnaround(P.getEspera() + P.getValorSurtoOriginal()); //defino o turnaround do processo
                        todos[item1] = P;
                    }
                }
                //
                if(temProcessoMenor == true){
                    temProcessoMenor = false;
                    if(P.getValorSurto() > 0){
                        prontos.add(P);
                    }
                    break;
                }//fim if possui processo menor que o que estava em processamento
            }//fim processamento
            //
            //surtoTotal -= tpssp;
            //tpssp = 0;
            nomeU = P.getN();
            graficoSRTF.geraGrafico(Unt, surtoTotal, false, P.getN());
        }//fim while(prontos.isEmpty() != true)
        //
        prontos.clear(); //garante a prontos vazia para os outros escalonamentos
        //
        String resposta = "\n\tSRTF";
        //
        Resultado r = new Resultado();
        resposta += r.listaResultado(todos, Unt);
        //
        respostas[0] = resposta;
        respostas[1] = temposProcessos;
        //
        graficoSRTF.geraGrafico(Unt, surtoTotal, true, nomeU);
        //
        return respostas;
    }
}