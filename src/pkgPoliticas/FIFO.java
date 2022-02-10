/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgPoliticas;

import pkgListaSENO.ListaSENO;
import pkgProcesso.Processo;
import pkgGrafico.MostraGrafico;
import pkgResultado.Resultado;
import pkgSeparador.Separador;

/**
 *
 * @author Leonardo Neves
 * @author Matheus Coelho
 * @author Rômulo Lima
 */
public class FIFO {

    /**
     * FIFO - First In First Out
     *
     * @param todos
     * @param surtoTotal
     * @return String respostas[2]
     */
    public String[] FIFO(Processo[] todos, int surtoTotal) {
        return FirstInFirstOut(todos, surtoTotal);
    }
    //
    private String[] FirstInFirstOut(Processo[] todos, int surtoTotal) {
        MostraGrafico graficoFIFO = new MostraGrafico();
        ListaSENO prontos = new ListaSENO();
        Separador separador = new Separador();
        String respostas[] = new String[2];
        String temposProcessos = "", nomeU = "";
        Processo P, p; //em processamento (P), em pronto (p)
        
        int Unt = 0;//unidade de tempo
        //
        for (int item = 0; item < todos.length; item++) { //adiciona a prontos todos os processos que chegam no tempo Unt, zero
            if (todos[item].getChegada() == 0) {
                prontos.add(todos[item]);
            }
        }
        //
        graficoFIFO.geraGrafico(Unt, surtoTotal, false, prontos.getFirstProcess().getN()); //envio os dados iniciais para gerar o grafico
        //
        while (prontos.isEmpty() != true) {
            P = prontos.getFirstProcess(); //seguro o primeiro processo de prontos em P
            prontos.removeFirst(); //removo o processo que sera processado de prontos
            temposProcessos += separador.separa();
            temposProcessos += "\n\nEm processamento (FIFO) - " + P.getN() + "\ttempo: " + Unt;
            //
            int tProcessamento = P.getValorSurtoOriginal(); //define o surto de processamento
            if (P.getIsIO() == true) { //se o processo possuir IO
                tProcessamento = P.getDuracaoOriginalIO() + P.getValorSurtoOriginal(); //seu tempo de operacao sera a duracao do IO mais seu surto
            }
            for (int item = 0; item < tProcessamento; item++) {//execuçao do processo
                //
                if (P.getIsIO() == true) {
                    if ((P.getValorSurtoOriginal() - P.getValorSurto()) >= P.getInicioIO() && (P.getTempoIOExecutado() - P.getDuracaoOriginalIO() != 0)) { //se o IO comeca no momento de surto do processo e sua duracao nao terminou
                        P.setEsperaIO();
                        P.setEspera();
                        
                        graficoFIFO.geraGrafico(Unt, surtoTotal, false, P.getN());
                        temposProcessos += " tempo *" + Unt + "*,"; //*tempo* == IO
                    } else {
                        P.setSurto();
                        graficoFIFO.geraGrafico(Unt, surtoTotal, false, P.getN());

                        surtoTotal--;
                        temposProcessos += " tempo " + Unt + ","; //tempo != IO
                    }
                } else {
                    P.setSurto();
                    graficoFIFO.geraGrafico(Unt, surtoTotal, false, P.getN());
                    surtoTotal--;
                    temposProcessos += " tempo " + Unt + ","; //tempo != IO
                }
                //
                for (int item1 = 0; item1 < prontos.size(); item1++) { //incremento a espera de todos que estao em espera
                    p = prontos.get(item1);
                    p.setEspera();
                    prontos.set(item1, p);
                }
                //
                Unt++; //incremento de unidade de tempo
                //
                if (Unt > 0) {
                    for (int item1 = 0; item1 < todos.length; item1++) { //adiciona a prontos todos os processos que chegam no tempo Unt
                        if (todos[item1].getChegada() == Unt) {
                            prontos.add(todos[item1]);
                        }
                    }
                }
                nomeU = P.getN();
            }//fim execucao do processo
            //
            for (int item1 = 0; item1 < todos.length; item1++) {//recolo o processo processado atualizado no vetor original
                if (todos[item1].getN().equals(P.getN())) { //verifico se o processo tem o mesmo nome que o que foi processado para atualizacao
                    P.setTurnaround(P.getEspera() + P.getValorSurtoOriginal()); //defino o turnaround do processo
                    todos[item1] = P;
                }
            }
            //
            //surtoTotal -= P.getholdSurto();
            graficoFIFO.geraGrafico(Unt, surtoTotal, false, P.getN());
        }//fim while(prontos.isEmpty() != true)
        //
        prontos.clear(); //garante que a fila estará vazia para os proximos escalonamentos
        //
        String resultado = "\n\tFIFO";
        //
        Resultado r = new Resultado();
        resultado += r.listaResultado(todos, Unt);
        //
        respostas[0] = resultado;
        respostas[1] = temposProcessos;
        //
        graficoFIFO.geraGrafico(Unt, surtoTotal, true, nomeU);
        //
        return respostas;
    }
}
