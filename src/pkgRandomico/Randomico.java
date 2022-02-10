/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgRandomico;

import java.util.Random;

/**
 *
 * @author Leonardo Neves
 * @author Matheus Coelho
 * @author Rômulo Lima
 */
public class Randomico implements intRandomico{
    /**retorna um valor inteiro pseudo-aletorio
     * @return valorRandomico*/
    @Override
    public int r(){
        return randomico();
    }
    //
    private int randomico(){
        Random R = new Random();
        return R.nextInt(61); //maior valor sera 60
    }
    /**retorna um valor inteiro pseudo-aleatorio para quantum
     * @return numero randomico inteiro*/
    @Override
    public int rquantum(){
        return randomico_quantum();
    }
    //
    private int randomico_quantum(){
        Random R = new Random();
        return R.nextInt(31); //maior valor sera 20
    }
    //
    /**retorna um valor booleno para status de IO, true se primo, false se na
     * @return true se primo, false se nao primo*/
    @Override
    public boolean rIOOp(){
        return rIO();
    }
    //
    private boolean rIO(){//retorna verdadeiro ou falso se primo
        Random R = new Random();
        int count = 0 ,n = R.nextInt(61);
        for(int x = 1; x < n; x++){
            if(n % x == 0){
                count++;
            }
        }
        if(count == 1){
            return true;
        }else{
            return false;
        }
    }
    //
    /**retorna uma valor menor que o indicado
     * @param num
     * @return */
    @Override
    public int nIn(int num){
        return n(num);
    }
    //
    private int n(int num){
        Random R = new Random();
        return R.nextInt(num);
    }
}