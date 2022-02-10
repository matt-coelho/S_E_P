/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgSort;

import pkgProcesso.Processo;
import pkgListaSENO.ListaSENO;

/**
 *
 * @author Leonardo Neves
 * @author Matheus Coelho
 * @author Rômulo Lima
 */
public class ProcessosSort implements BubbleSortProcessos{
    
    /**retorna um vetor ordenado por ordem de chegada
     * @param todos
     * @return */
    @Override
    public Processo [] chegadaVet(Processo [] todos){
        return bubbleSortChegadaVet(todos);
    }
    
    //metodo bolha foi utilizado por ser simples e estável.
    private Processo [] bubbleSortChegadaVet(Processo [] todos){
        Processo aux;
        for(int item1 = 0; item1 < todos.length; item1++){
            for(int item2 = 0; item2 < (todos.length - 1); item2++){
                if(todos[item2].getChegada() > todos[item2 + 1].getChegada()){
                    aux = todos[item2];
                    todos[item2] = todos[item2 + 1];
                    todos[item2 + 1] = aux;
                }
            }
        }
        return todos;
    }
    
    /**retorna uma lista encadeada com a lista de espera ordenada por ordem crescente de surto
     * @param espera
     * @return */
    @Override
    public ListaSENO menorSurtoVet(Processo [] espera){ //recebe a lista de espera para ordenar por surto
        return SJF_Vet(espera);
    }
    
    //metodo bolha foi utilizado por ser simples e estável.
    private ListaSENO SJF_Vet(Processo [] espera){ //recebe a lista de prontos para ordenar por surto
        int tamanho = 0;
        //
        for(int x = 0; x< espera.length; x++){
            if(espera[x] != null){
                tamanho++;
            }
        }
        //
        Processo e [] = new Processo[tamanho];
        for(int x = 0; x < e.length; x++){
            e[x] = espera[x];
        }
        //
        espera = e;
        //-----------------------------------
        Processo aux;
        ListaSENO resp = new ListaSENO(); //retorna uma lista encadeada pois espera em Escalonamentos e uma lista encadeada
        for(int item1 = 0; item1 < espera.length; item1++){
            for(int item2 = 0; item2 < (espera.length - 1); item2++){
                if(espera[item2].getChegada() > espera[item2 + 1].getChegada()){
                    aux = espera[item2];
                    espera[item2] = espera[item2 + 1];
                    espera[item2 + 1] = aux;
                }
            }
        }
        for(int item = 0; item < espera.length; item++){ //adiciona o vetor ja ordenado a lista encadeada
            resp.insertLast(espera[item]);
        }
        return resp;
    }
    
    //--------------------------------------------------------------------------
    
    /**retorna uma ListaSENO ordenada por ordem de chegada
     * @param prontos
     * @return */
    @Override
    public ListaSENO chegadaListaSENO(ListaSENO prontos){
        return chegadaLista_SENO(prontos);
    }
    
    //metodo bolha foi utilizado por ser simples e estável.
    private ListaSENO chegadaLista_SENO(ListaSENO prontos){
        Processo aux;
        for (int x = 0; x < prontos.size() - 1; x++) //i - x
            for (int v = 1; v < prontos.size() - x; v++) //j - v
                if (prontos.get(v).getChegada() < prontos.get(v - 1).getChegada()){ //faz a comparacao entre o tempo de chegada de dois processos
                    aux = prontos.get(v);
                    prontos.set(v, prontos.get(v - 1));
                    prontos.set(v - 1, aux);
                  }
        return prontos;
    }
    
    @Override
    /**retorna uma ListaSENO*/
    public ListaSENO menorSurtoListaSENO(ListaSENO prontos){
        return menorSurto_ListaSENO(prontos);
    }
    
    //metodo bolha foi utilizado por ser simples e estável.
    private ListaSENO menorSurto_ListaSENO(ListaSENO prontos){
        Processo aux;
        for (int x = 0; x < prontos.size() - 1; x++) //i - x
            for (int v = 1; v < prontos.size() - x; v++) //j - v
                if (prontos.get(v).getValorSurto() < prontos.get(v - 1).getValorSurto()){ //faz a comparacao entre o tempo de chegada de dois processos
                    aux = prontos.get(v);
                    prontos.set(v, prontos.get(v - 1));
                    prontos.set(v - 1, aux);
                  }
        return prontos;
    }
}