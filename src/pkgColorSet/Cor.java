/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgColorSet;

/**
 *
 * @author Matheus Coelho
 */
public class Cor {

    private int blue;
    private int green;
    private int red;

    /**retorna valor entre 0 e 255
     * @return int*/
    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
    
    /**retorna valor entre 0 e 255
     * @return int*/
    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }
    
    /**retorna valor entre 0 e 255
     * @return int*/
    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }
}