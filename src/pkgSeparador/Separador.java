/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgSeparador;

/**
 *
 * @author Leonardo Neves
 * @author Matheus Coelho
 * @author Rômulo Lima
 */
public class Separador implements intSeparador{
    /**gera uma string de - + para separar um texto
     * @return separador*/
    @Override
    public String separa(){
        return sep();
    }
    //
    private String sep(){
        String separador = "\n";
        for(int x = 0; x < 37; x++){
            separador += "-  -  ";
        }
        return separador;
    }
}