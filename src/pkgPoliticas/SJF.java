/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgPoliticas;

import pkgGrafico.MostraGrafico;
import pkgListaSENO.ListaSENO;
import pkgProcesso.Processo;
import pkgResultado.Resultado;
import pkgSort.ProcessosSort;

/**
 *
 * @author Leonardo Neves
 * @author Matheus Coelho
 * @author Rômulo Lima
 */
public class SJF {

    /**
     * SJF - Smalest Job First
     *
     * @param todos
     * @param surtoTotal
     * @return
     */
    public String[] SJF(Processo[] todos, int surtoTotal) {
        return SmalestJobFirst(todos, surtoTotal);
    }

    //
    private String[] SmalestJobFirst(Processo[] todos, int surtoTotal) {
        MostraGrafico graficoSJF = new MostraGrafico();
        ListaSENO prontos = new ListaSENO();
        String respostas[] = new String[2];
        String temposProcessos = "", nomeU = "";
        Processo P, p; //em processamento (P), em pronto (p)
        int Unt = 0;//unidade de tempo
        //
        ProcessosSort sort = new ProcessosSort(); //objeto para ordenacao, 9 linhas abaixo
        //
        for (int item = 0; item < todos.length; item++) { //adiciona a prontos todos os processos que chegam no tempo Unt, zero
            if (todos[item].getChegada() == 0) {
                prontos.insertLast(todos[item]);
            }
        }
        //
        prontos = sort.menorSurtoListaSENO(prontos);
        graficoSJF.geraGrafico(Unt, surtoTotal, false, prontos.getFirstProcess().getN());
        //
        while (prontos.isEmpty() != true) {
            prontos = sort.menorSurtoListaSENO(prontos);
            P = prontos.getFirstProcess();
            prontos.removeFirst();
            temposProcessos += "\n\nEm processamento (SJF) - " + P.getN() + "\ntempo: " + Unt;
            if (P.getIsIO() == true) {//processamento I/O
                for (int item = 0; item < P.getValorSurtoOriginal() + P.getDuracaoOriginalIO(); item++) { //processamento
                    if ((P.getValorSurtoOriginal() - P.getValorSurto()) >= P.getInicioIO() && (P.getTempoIOExecutado() - P.getDuracaoOriginalIO() != 0)) { //se o IO comeca no momento de surto do processo e sua duracao nao terminou
                        P.setEsperaIO();
                        P.setEspera();
                        temposProcessos += " tempo *" + Unt + "*,"; //*tempo* == IO
                        
                        graficoSJF.geraGrafico(Unt, surtoTotal, false, P.getN());
                        Unt++;
                    } else {
                        P.setSurto();
                        temposProcessos += " tempo " + Unt + ","; //tempo != IO
                        
                        //
                        graficoSJF.geraGrafico(Unt, surtoTotal, false, P.getN());
                        surtoTotal--;
                        Unt++; //incremento unidade de tempo
                    }
                    //
                    for (int item1 = 0; item1 < prontos.size(); item1++) {//incremento a espera de todos que estao em espera, apartir de 1 pois o primeiro e o que esta sendo processado
                        p = prontos.get(item1);
                        p.setEspera();
                        prontos.set(item1, p);
                    }
                    //
                    if (Unt > 0) {
                        for (int item1 = 0; item1 < todos.length; item1++) { //adiciona a prontos todos os processos que chegam no tempo Unt
                            if (todos[item1].getChegada() == Unt) {
                                prontos.insertLast(todos[item1]);
                            }
                        }
                    }
                }
            } else {//processamento padrao
                for (int item = 0; item < P.getValorSurtoOriginal(); item++) { //processamento
                    P.setSurto(); //reduz o tempo de surto restante do processo
                    //
                    for (int item1 = 0; item1 < prontos.size(); item1++) {//incremento a espera de todos que estao em espera, apartir de 1 pois o primeiro e o que esta sendo processado
                        p = prontos.get(item1);
                        p.setEspera();
                        prontos.set(item1, p);
                    }
                    //
                    
                    temposProcessos += " tempo " + Unt + ",";
                    graficoSJF.geraGrafico(Unt, surtoTotal, false, P.getN());
                    surtoTotal--;
                    Unt++; //incremento unidade de tempo
                    //
                    if (Unt > 0) {
                        for (int item1 = 0; item1 < todos.length; item1++) { //adiciona a prontos todos os processos que chegam no tempo Unt
                            if (todos[item1].getChegada() == Unt) {
                                prontos.insertLast(todos[item1]);
                            }
                        }
                    }
                }
            }

            //
            for (int item1 = 0; item1 < todos.length; item1++) {//recolo o processo processado atualizado no vetor original
                if (todos[item1].getN().equals(P.getN())) { //verifico se o processo tem o mesmo nome que o que foi processado para atualizacao
                    P.setTurnaround(P.getEspera() + P.getValorSurtoOriginal()); //defino o turnaround do processo
                    todos[item1] = P;
                }
            }
            //
            //surtoTotal -= P.getholdSurto();
            graficoSJF.geraGrafico(Unt, surtoTotal, false, P.getN());
            //
            nomeU = P.getN();
        }//fim while(prontos.isEmpty() != true)
        //
        prontos.clear(); //garante a prontos vazia para os outros escalonamentos
        //
        String resposta = "\n\tSJF";
        //
        Resultado r = new Resultado();
        resposta += r.listaResultado(todos, Unt);
        //
        respostas[0] = resposta;
        respostas[1] = temposProcessos;
        //
        graficoSJF.geraGrafico(Unt, surtoTotal, true, nomeU);
        //
        return respostas;
    }
}
