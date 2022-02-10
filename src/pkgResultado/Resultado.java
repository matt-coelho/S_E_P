/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgResultado;

import java.text.NumberFormat;
import java.util.Locale;
import pkgProcesso.Processo;

/**
 *
 * @author Matheus Coelho
 */
public class Resultado {
    private double tMe;
    private double tEm;
    private double tEt;
    
    public String listaResultado(Processo[] todos, int Unt){
        String resultado = "";
        for (int iteracao = 0; iteracao < todos.length; iteracao++) {//mostra os dados de cada processo
            resultado += "\n Processo: " + todos[iteracao].getN() + " espera: " + todos[iteracao].getEspera() + "\n Turnaround: " + todos[iteracao].getTurnaround() + " Surto: " + todos[iteracao].getValorSurtoOriginal() + "\n";
        }
        //
        for (int iteracao = 0; iteracao < todos.length; iteracao++) {//calcular tempos
            tEt += todos[iteracao].getEspera();
        }
        //
        tMe = Unt / todos.length; //calcula o tempo medio de execucao
        tEm = tEt / todos.length; //calcula o tempo de espera medio
        //define o numero de casas da variavel
        Locale.setDefault(Locale.US); //define local da JVM (pode variar, virgula e ponto)
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(2);
        //format.setMinimumFractionDigits(2);
        //format.setMaximumIntegerDigits(2);
        //format.setRoundingMode(RoundingMode.HALF_UP);
        tMe = Double.valueOf(format.format(tMe).replaceAll(",", ""));
        tEm = Double.valueOf(format.format(tEm).replaceAll(",", ""));
        //
        int tSurtoTotal = 0;
        int tIOtotal = 0;
        for(int x = 0; x < todos.length; x++){
            tSurtoTotal += todos[x].getValorSurtoOriginal(); //surto de todos os processos
            if(todos[x].getIsIO() == true){
                tIOtotal += todos[x].getDuracaoOriginalIO();
            }
        }
        
        resultado += "\n Tempo de ESPERA : " + tEm
                  + "\n Tempo de ESPERA TOTAL (espera de todos os processos): " + (int) tEt
                  + "\n Tempo de EXECUÇÂO MÉDIO: " + tMe
                  + "\n Tempo de EXECUÇÂO TOTAL (surto de todos os processos e tempo de I/O): " + Unt
                  + "\n Tempo de SURTO TOTAL (surto de todos os processos): " + tSurtoTotal
                  + "\n Tempo de I/O TOTAL (I/O de todos os processos): " + tIOtotal
                  + "\n";
        return resultado;
    }
}