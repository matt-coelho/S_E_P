/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgGrafico;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.LinkedList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import pkgColorSet.ColorSet;

/**
 *
 * @author Leonardo Neves
 * @author Matheus Coelho
 * @author Rômulo Lima
 */
public class MostraGrafico implements intMostrarGraficos {

    public static String politica;
    public static String titulo;

    private DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    //
    private LinkedList surtos = new LinkedList();
    private LinkedList tempos = new LinkedList();
    //
    private LinkedList nomeProcesso = new LinkedList();

    //--------------------------------------------------------------------------
    /**
     * Gera grafico
     *
     * @param time
     * @param surtoTotal
     * @param mostrar
     * @param nome
     */
    @Override
    public void geraGrafico(int time, int surtoTotal, boolean mostrar, String nome) {
        tempos.add(Integer.toString(time));
        surtos.add(surtoTotal);
        nomeProcesso.add(nome);
        //
        if (mostrar == true) {
            show();
        }
    }

    //
    private void show() {
        String p = "";
        int nProcessos = 0;
        for (int x = 0; x < nomeProcesso.size(); x++) {
            if (!nomeProcesso.get(x).equals(p)) {//conto quantos processos foram processados
                p = nomeProcesso.get(x).toString();
                nProcessos++;
            }
        }
        //
        for (int x = 0; x < surtos.size(); x++) {
            dataset.addValue(Integer.parseInt(surtos.get(x).toString()), nomeProcesso.get(x).toString(), tempos.get(x).toString());//ponto y, nome da serie(nome do processo) onde cada serie diferente é reconhecida por valores a partide de zero, ponto x
        }
        //crio o dataset de pontos do grafico
        JFreeChart chart = ChartFactory.createLineChart(politica, "Tempo", "Surto Total", dataset,PlotOrientation.VERTICAL, true, true, false);
        org.jfree.chart.renderer.category.CategoryItemRenderer render = chart.getCategoryPlot().getRenderer();
        //
        for (int x = 0; x < nProcessos; x++) {//defino as cores que cada processo do dataset tera no grafico
            if (x >= 10) {
                ColorSet ncolor = new ColorSet();
                render.setSeriesPaint(x, new Color(ncolor.getRandomColor().getBlue(), ncolor.getRandomColor().getGreen(), ncolor.getRandomColor().getRed()));//valor da serie, cor
            } else {
                ColorSet ncolor = new ColorSet();
                render.setSeriesPaint(x, ncolor.getAColor(x));
            }
        }
        //
        plot(chart);
    }
    //
    private void plot(JFreeChart chart){
        org.jfree.chart.ChartFrame frame = new org.jfree.chart.ChartFrame("Gráfico tempo de execução total - " + titulo, chart);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/pkgIcone/chip-icon.png")));
        frame.setSize(720, 680);
        frame.setResizable(false); //define o redimencionamento da janela do grafico fixo
        frame.setLocationRelativeTo(null); //define o grafico no centro da tela
        frame.setVisible(true);
    }
}
