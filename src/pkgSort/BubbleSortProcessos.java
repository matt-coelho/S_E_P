/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgSort;

import pkgListaSENO.ListaSENO;
import pkgProcesso.Processo;

/**
 *
 * @author Leonardo Neves
 * @author Matheus Coelho
 * @author Rômulo Lima
 */
public interface BubbleSortProcessos {
    public Processo [] chegadaVet(Processo [] todos);
    public ListaSENO menorSurtoVet(Processo [] espera);
    public ListaSENO chegadaListaSENO(ListaSENO prontos);
    public ListaSENO menorSurtoListaSENO(ListaSENO prontos);
}
