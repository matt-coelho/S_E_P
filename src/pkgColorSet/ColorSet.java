/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgColorSet;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Matheus Coelho
 */
public class ColorSet implements intColorSet {

    private LinkedList<Color> cor = new LinkedList<Color>();

    //
    public ColorSet() { //cores pre definidas
        cor.add(Color.BLACK);
        cor.add(Color.BLUE);
        cor.add(Color.CYAN);
        cor.add(Color.GREEN);
        cor.add(Color.MAGENTA);
        cor.add(Color.ORANGE);
        cor.add(Color.PINK);
        cor.add(Color.RED);
        cor.add(Color.YELLOW);
        cor.add(Color.DARK_GRAY);
    }

    /*
     * retorna uma cor aleatoria
     */
    @Override
    public Cor getRandomColor() {

        Cor randCor = new Cor();
        do {
            Random rand = new Random();
            randCor.setBlue(rand.nextInt(256));
            randCor.setGreen(rand.nextInt(256));
            randCor.setRed(rand.nextInt(256));
            //enquanto os numeros aleatorios forem iguais, ou seja branco ou tom de cinza, será gerada outra cor, valor RGB
        } while (randCor.getBlue() == randCor.getGreen()
                || randCor.getGreen() == randCor.getRed()
                || randCor.getRed() == randCor.getBlue());
        return randCor;
    }

    //
    /*
    * retorna uma cor pre definida
     */
    @Override
    public Color getAColor(int x) {
        return cor.get(x);
    }
}
/*
Links referencia
guj.com.br/t/grafico-de-linhas-jfreechart-resolvido/75879
pt.stackoverflow.com/questions/60859/jfreechart-problemas-ao-customizar-gr%C3%A1fico
caelum.com.br/apostila-java-testes-xml-design-patterns/graficos-com-jfreechart/#7-7-nossos-indicadores-e-o-design-pattern-strategy
*/