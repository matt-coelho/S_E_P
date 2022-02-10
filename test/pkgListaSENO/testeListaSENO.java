/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgListaSENO;
import java.util.LinkedList;
import java.util.Scanner;
import pkgListaSENO.ListaSENO;
import pkgProcesso.Processo;
/**
 *
 * @author DELL
 */
/**pacote de teste para pacote pkgListaSENO*/
public class testeListaSENO {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        ListaSENO lista = new ListaSENO();
        LinkedList p = new LinkedList();
        Processo vet [] = new Processo[3];
        
        for(int x = 0; x < 3; x++){
            System.out.println("");
            String nome = "n"+x;
            System.out.println("surto");
            int tS = in.nextInt();
            System.out.println("chegada");
            int tCheg = in.nextInt();
            vet[x] = new Processo(nome, tS, tCheg, false, -1, -1);
            //lista.add(new Processo(nome, tS, tCheg)); //adicao de processos direta
        }
        
        for(int x = 0; x < 3; x++){ //adiciono processos a lista indiretamente
            lista.add(vet[x]);
        }
        
        System.out.println("////////////////////////////////////////////////////Impressao usando a lista.get");
        for(int x = 0; x < 3; x++){
        System.out.println("processo, P"+lista.get(x).getN()+"; surto "+lista.get(x).getValorSurto()+"; chegada "+lista.get(x).getChegada()+"; Hsurto"+lista.get(x).getValorSurtoOriginal());
        lista.get(x).setSurto();
        System.out.println("processo decrementado 1 surto, P"+lista.get(x).getN()+"; surto "+lista.get(x).getValorSurto()+"; chegada "+lista.get(x).getChegada()+"; Hsurto"+lista.get(x).getValorSurtoOriginal());
        }
        
        System.out.println("////////////////////////////////////////////////////Impressao usando o vetor de processos criado a partir da lista.toArray");
        
        vet = lista.toArray();
        for(int x = 0; x < 3; x++){// LISTA SENO
        System.out.println("processo, P"+vet[x].getN()+"; surto "+vet[x].getValorSurto()+"; chegada"+vet[x].getChegada()+"; Hsurto"+vet[x].getValorSurtoOriginal());
        vet[x].setSurto();
        System.out.println("processo decrementado 1 surto, P"+vet[x].getN()+"; surto"+vet[x].getValorSurto()+"; chegada"+vet[x].getChegada()+"; Hsurto"+vet[x].getValorSurtoOriginal());
        }
        
        System.out.println("////////////////////////////////////////////////////primeiro e ultimos processos da lista");
        System.out.println("\n");
        
        System.out.println("1° "+lista.getFirstProcess().getN()+"; "+lista.getFirstProcess().getChegada()+"; "+lista.getFirstProcess().getValorSurto());
        System.out.println("last "+lista.getLastProcess().getN()+"; "+lista.getLastProcess().getChegada()+"; "+lista.getLastProcess().getValorSurto());
        
        System.out.println("antes de limpar - esta limpa? "+lista.isEmpty());
        lista.clear();
        System.out.println("depois de limpar - esta limpa? "+lista.isEmpty());
        System.out.println("FIM");
    }
}
