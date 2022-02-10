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
import pkgSeparador.Separador;

/**
 *
 * @author Leonardo Neves
 * @author Matheus Coelho
 * @author Rômulo Lima
 */
public class RR {

    /**
     * RR - Round Robin
     *
     * @param quantum
     * @param todos
     * @param surtoTotal
     * @return
     */
    public String[] RR(int quantum, Processo[] todos, int surtoTotal) {
        return roundRobin(quantum, todos, surtoTotal);
    }

    //
    private String[] roundRobin(int quantum, Processo[] todos, int surtoTotal) {
        MostraGrafico graficoRR = new MostraGrafico();
        ListaSENO prontos = new ListaSENO(); //lista de prontos
        Separador separador = new Separador();
        String respostas[] = new String[2];
        String temposProcessos = "", nomeU = "";
        Processo P, p; //em processamento (P), em pronto (p)
        int Unt = 0, diferenca; //unidade de tempo, diferenca de surto restante e quantum
        //
        for (int item = 0; item < todos.length; item++) { //adiciona a prontos todos os processos que chegam no tempo Unt, zero
            if (todos[item].getChegada() == 0) {
                prontos.add(todos[item]);
            }
        }
        //
        graficoRR.geraGrafico(Unt, surtoTotal, false, prontos.getFirstProcess().getN());
        //
        while (prontos.isEmpty() != true) {
            P = prontos.getFirstProcess();
            prontos.removeFirst(); //removo de prontos o processo que entra em processamento
            temposProcessos += separador.separa();
            temposProcessos += "\n\nEm processamento (RR) - " + P.getN() + "\ttempo: " + Unt;
            graficoRR.geraGrafico(Unt, surtoTotal, false, P.getN());
            if (P.getEmExecucaoIO() == true) {

                if (P.getTempoIORestante() <= quantum) { //se a diferenca entre o tempo de surto restante e menor ou igual ao tempo de quantum (menor ou igual para economiar linha e for)
                    diferenca = P.getTempoIORestante();
                    //
                    for (int x = 0; x < diferenca; x++) { // unidade de processamento
                        //
                        for (int item = 0; item < prontos.size(); item++) { //Incrementa o tempo de prontos
                            p = prontos.get(item);
                            p.setEspera();
                            prontos.set(item, p);
                        }
                        //
                        Unt++; //incremento de unidade de tempo
                        temposProcessos += " tempo *" + Unt + "*,";
                        //
                        if (Unt > 0) {
                            for (int item1 = 0; item1 < todos.length; item1++) { //adiciona a prontos todos os processos que chegam no tempo Unt
                                if (todos[item1].getChegada() == Unt) {
                                    prontos.add(todos[item1]);
                                }
                            }
                        }
                        //
                        P.setEsperaIO(); //decrementa em uma unidade de tempo a duracao do IO
                        P.setEspera();
                        graficoRR.geraGrafico(Unt, surtoTotal, false, P.getN());
                        nomeU = P.getN();
                    }//fim do processamento do processo
                    //
                    for (int item1 = 0; item1 < todos.length; item1++) {//recolo o processo processado atualizado no vetor original
                        if (todos[item1].getN().equals(P.getN())) { //verifico se o processo tem o mesmo nome que o que foi processado para atualizacao
                            P.setTurnaround(P.getEspera() + P.getValorSurtoOriginal()); //defino o turnaround do processo
                            todos[item1] = P;
                        }
                    }
                    //
                    //surtoTotal -= diferenca;
                    //graficoRR.geraGrafico(Unt, surtoTotal, false, P.getN());
                    //
                    P.setEmExecucaoIO(false);
                    if (P.getValorSurto() > 0) {//se o processo apos o io possuir um surto de processamento ele deve ser recolocado em prontos
                        prontos.add(P);
                    }
                    //
                } else { // se nao ha diferenca
                    for (int x = 0; x < quantum; x++) {
                        //
                        for (int item = 0; item < prontos.size(); item++) { //Incrementa o tempo de prontos, a partir de 1 pois o processo 1 esta processando
                            p = prontos.get(item);
                            p.setEspera();
                            prontos.set(item, p);
                        }
                        //
                        Unt++; //incremento de unidade de tempo
                        temposProcessos += " tempo *" + Unt + "*,";
                        //
                        if (Unt > 0) {
                            for (int item1 = 0; item1 < todos.length; item1++) { //adiciona a prontos todos os processos que chegam no tempo Unt
                                if (todos[item1].getChegada() == Unt) {
                                    prontos.insertLast(todos[item1]);
                                }
                            }
                        }
                        P.setEsperaIO(); //decrementa uma unidade o tempo de duracao de IO
                        P.setEspera();
                        graficoRR.geraGrafico(Unt, surtoTotal, false, P.getN());
                        nomeU = P.getN();
                    }//fim processamento quantum
                    //
                    //surtoTotal -= quantum;
                    //graficoRR.geraGrafico(Unt, surtoTotal, false, P.getN());
                    prontos.add(P);
                }
            } else { //se nao for processo de IO ele será executado normalmente como RR
                if (P.getValorSurto() <= quantum) { //se a diferenca entre o tempo de surto restante e menor ou igual ao tempo de quantum (menor ou igual para economiar linha e for)
                    diferenca = P.getValorSurto();
                    //
                    for (int x = 0; x < diferenca; x++) { // unidade de processamento
                        //
                        for (int item = 0; item < prontos.size(); item++) { //Incrementa o tempo de prontos
                            p = prontos.get(item);
                            p.setEspera();
                            prontos.set(item, p);
                        }
                        //
                        Unt++; //incremento de unidade de tempo
                        temposProcessos += " tempo " + Unt + ",";
                        //
                        if (Unt > 0) {
                            for (int item1 = 0; item1 < todos.length; item1++) { //adiciona a prontos todos os processos que chegam no tempo Unt
                                if (todos[item1].getChegada() == Unt) {
                                    prontos.add(todos[item1]);
                                }
                            }
                        }
                        //
                        if (P.getValorSurtoOriginal() - P.getValorSurto() == P.getInicioIO()) {//se IO comeca neste momento
                            P.setEmExecucaoIO(true);
                        }
                        //
                        P.setSurto(); //decrementa em uma unidade de tempo o surto do processo
                        surtoTotal--;
                        graficoRR.geraGrafico(Unt, surtoTotal, false, P.getN());
                        nomeU = P.getN();
                        //
                        if(P.getEmExecucaoIO() == true){
                            prontos.insertFirst(P);
                            break;
                        }
                    }//fim do processamento do processo
                    //
                    for (int item1 = 0; item1 < todos.length; item1++) {//recolo o processo processado atualizado no vetor original
                        if (todos[item1].getN().equals(P.getN())) { //verifico se o processo tem o mesmo nome que o que foi processado para atualizacao
                            P.setTurnaround(P.getEspera() + P.getValorSurtoOriginal()); //defino o turnaround do processo
                            todos[item1] = P;
                        }
                    }
                    //
                    //surtoTotal -= diferenca;
                    //graficoRR.geraGrafico(Unt, surtoTotal, false, P.getN());
                } else { // se nao ha diferenca
                    for (int x = 0; x < quantum; x++) {
                        //
                        for (int item = 0; item < prontos.size(); item++) { //Incrementa o tempo de prontos
                            p = prontos.get(item);
                            p.setEspera();
                            prontos.set(item, p);
                        }
                        //
                        Unt++; //incremento de unidade de tempo
                        temposProcessos += " tempo " + Unt + ",";
                        //
                        if (Unt > 0) {
                            for (int item1 = 0; item1 < todos.length; item1++) { //adiciona a prontos todos os processos que chegam no tempo Unt
                                if (todos[item1].getChegada() == Unt) {
                                    prontos.insertLast(todos[item1]);
                                }
                            }
                        }
                        //
                        P.setSurto(); //decrementa 1 do tempo de surto restante
                        surtoTotal--;
                        graficoRR.geraGrafico(Unt, surtoTotal, false, P.getN());
                        nomeU = P.getN();
                        //
                        if (P.getValorSurtoOriginal() - P.getValorSurto() == P.getInicioIO()) {//se IO comeca neste momento
                            P.setEmExecucaoIO(true);
                        }
                        //
                        if(P.getEmExecucaoIO() == true){
                            prontos.insertFirst(P);
                            break;
                        }
                    }//fim processamento quantum
                    //
                    //surtoTotal -= quantum;
                    //graficoRR.geraGrafico(Unt, surtoTotal, false, P.getN());
                    //
                    prontos.add(P);
                }
            }
        }//fim while(prontos.isEmpty() != true)
        //
        prontos.clear();
        //
        String resposta = "\n\tRound Robin";
        //
        Resultado r = new Resultado();
        resposta += r.listaResultado(todos, Unt);
        //
        respostas[0] = resposta;
        respostas[1] = temposProcessos;
        //
        graficoRR.geraGrafico(Unt, surtoTotal, true, nomeU);
        //
        return respostas;
    }
}