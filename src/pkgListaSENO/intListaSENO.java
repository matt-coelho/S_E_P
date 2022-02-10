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
public interface intListaSENO {
    public void insertFirst(Processo novo);
    public void insertLast(Processo novo);
    public void clear();
    public void removeFirstOccurrence(Processo num);
    public void removeAllOccurrences(Processo num);
    public Processo getFirstProcess();
    public Processo getLastProcess();
    public void removeFirst();
    public Processo get(int c);
    public void setThisProcess(Processo p);
    public void set(int c, Processo p);
    public boolean isEmpty();
    public int size();
    public Processo [] toArray();
    public void add(Processo novo);
    public int indexOf(Processo p);
}