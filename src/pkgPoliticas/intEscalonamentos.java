/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgPoliticas;

import pkgProcesso.Processo;

/**
 *
 * @author Leonardo Neves
 * @author Matheus Coelho
 * @author Rômulo Lima
 */
public interface intEscalonamentos {
    public String[] fifo(Processo [] todos, int surtoTotal);
    public String[] roundRobin(int quantum, Processo [] todos, int surtoTotal);
    public String[] smalestJobFirst(Processo [] todos, int surtoTotal);
    public String [] smalestRemainingTimeFirst(Processo [] todos, int surtoTotal);
}
