/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgListaSENO;

import pkgProcesso.Processo;
/**
 *
 * @author Leonardo Neves
 * @author Matheus Coelho
 * @author Rômulo Lima
 */
public class ListaSENO implements intListaSENO{
    private Lista anterior;
    private Lista inicio;
    private Lista aux;
    private Lista fim;

    public ListaSENO() {
        inicio = null;
        fim = null;
    }
    //
    /**insere o processo dado no n.inicio da lista de processos insertFirst(Processo
     * @param novo)*/
    @Override
    public void insertFirst(Processo novo){
        Lista n = new Lista();
        n.p = novo;
        if(inicio == null){
            inicio = n;
            fim = n;
            n.proximo = null;
        }else{
            n.proximo = inicio;
            inicio = n;
        }
    }
    //
    /**insere o processo dado no final da lista de processo, equivale ao add insertLast(Processo
     * @param novo)*/
    @Override
    public void insertLast(Processo novo){
        Lista n = new Lista();
        n.p = novo;
        if(inicio == null){
            inicio = n;
            fim = n;
            n.proximo = null;
        }else{
            fim.proximo = n;
            fim = n;
            n.proximo = null;
        }
    }
    //
    /**apaga toda a lista de processos*/
    @Override
    public void clear(){
        inicio = null;
    }
    //
    /**remove a primeira ocorrencia de um dado processo na lista removeFirstOccurrence(Processo
     * @param num)*/
    @Override
    public void removeFirstOccurrence(Processo num){
        aux = inicio;
        anterior = null;
        while( aux != null){
            if(aux.p == num){
                if(aux == inicio){
                    inicio = aux.proximo;
                    aux = inicio;
                    break;
                }else if(aux == fim){
                    anterior.proximo = null;
                    fim = anterior;
                    aux = null;
                    break;
                }else{
                    anterior.proximo = aux.proximo;
                    aux = aux.proximo;
                    break;
                }
            }else{
                anterior = aux;
                aux = aux.proximo;
            }
        }
    }
    //
    /**remove todas as ocorrencias de um dado processo removeAllOccurrences(Processo
     * @param num)*/
    @Override
    public void removeAllOccurrences(Processo num){
        aux = inicio;
        anterior = null;
        while( aux != null){
            if(aux.p == num){
                if(aux == inicio){
                    inicio = aux.proximo;
                    aux = inicio;
                }else if(aux == fim){
                    anterior.proximo = null;
                    fim = anterior;
                    aux = null;
                }else{
                    anterior.proximo = aux.proximo;
                    aux = aux.proximo;
                }
            }else{
                anterior = aux;
                aux = aux.proximo;
            }
        }
    }
    //
    /**retona o primeiro processo
     * @return inicio.p*/
    @Override
    public Processo getFirstProcess(){
        if(inicio == null){
            System.err.println("Lista vazia, nao ha dados para responder");
            return null;
        }else{
            return inicio.p;
        }
    }
    //
    /**retorna o ultimo processo
     * @return anterior.p*/
    @Override
    public Processo getLastProcess(){
        aux = inicio;
        while (aux != null){
            anterior = aux;
            aux = aux.proximo;
        }
        return anterior.p;
    }
    //
    /**remove primeiro processo da lista*/
    @Override
    public void removeFirst(){
        aux = inicio;
        inicio = aux.proximo;
    }
    //
    /**retorna o processo no indice especificado get(int)
     * @param c
     * @return aux.p*/
    @Override
    public Processo get(int c){
        int count = 0;
        aux = inicio;
        if(inicio != null){
            while(count < c){
            aux = aux.proximo;
            count++;
            }
        }else{
            System.err.println("Lista vazia para retornar");
        }
        return aux.p;
    }
    //
    /**substitui um processo com o mesmo nome do processo em parametro
     * @param p*/
    @Override
    public void setThisProcess(Processo p){
        aux = inicio;
        while(aux.proximo != null){
            if(aux.p.getN().equals(p.getN())){ //se o nome do processo em aux é igual ao do processo passado no parametro ele é substituido com o novo, passado do parametro
                aux.p = p;
            }else{
                aux = aux.proximo;
            }
        }
    }
    //
    /**redefine um processo alocado na lista set(index, Processo)
     * @param c
     * @param p*/
    @Override
    public void set(int c, Processo p){
        int count = 0;
        aux = inicio;
        while(count < c){
            aux = aux.proximo;
            count++;
        }
        aux.p = p;
    }
    //
    /**retorna true ou false, lista vazia ou nao
     * @return true or false*/
    @Override
    public boolean isEmpty(){
        if(inicio == null){
            return true;
        }else{
            return false;
        }
    }
    //
    /**retorna o tamanho da lista
     * @return count*/
    @Override
    public int size(){
        int count = 1; //a partir de um pois a posicao zero conta como uma
        aux = inicio;
        if(aux == null){ //se a lista esta vazia retorna tamanho zero
            return 0;
        }else{
            while(aux.proximo != null){ //do contrario retorna count
                aux = aux.proximo;
                count++;
            }
        }
        return count;
    }
    //
    /**retorna a lista de processos em um array de processos
     * @return array da lista*/
    @Override
    public Processo [] toArray(){
        int count = 1;
        //
        aux = inicio;
        //
        while(aux.proximo != null){ //as vezes erro de referencia nula aqui
            aux = aux.proximo;
            count++;
        }
        //---conta quantos processos existem na lista encadeada
        Processo lista [] = new Processo[count]; //declaracao combinada do vetor que sera retornado do tamanho de count
        //
        aux = inicio;
        //
        for(int x = 0; x < count; x++){
            lista[x] = aux.p;
            aux = aux.proximo;
        }
        //coloca cada processo numa posicao do vetor
        return lista;
    }
    //
    /**insere o processo dado no final da lista de processo, equivale ao insertLast(Processo)
     * @param novo*/
    @Override
    public void add(Processo novo){
        insertLast(novo);
    }
    //
    /**retorna um inteiro, o indice de um processo espercificado
     * @param p
     * @return count*/
    @Override
    public int indexOf(Processo p){
        int count = 0;
        aux = inicio;
        while(aux.p != p){
            aux = aux.proximo;
            count++;
        }
        return count;
    }
}