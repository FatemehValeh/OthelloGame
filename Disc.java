package com.company;

/**
 * Disc class indicates a disc in board in Othello game.
 * @author Fatemeh Valeh
 */
public class Disc {
    //the color of the disc can be white or black using unicode
    private char color;
    // x position of the disc
    private int x;
    // y position of the disc
    private int y;
    int w = 9675;
    char white = (char) w;
    int b = 9679;
    public char black = (char) b;

    /**
     * create a new disc with a color and position in board game
     * @param color color of disc, white or black
     * @param x x position in board
     * @param y y position in board
     */
    public Disc(char color, int x, int y){
        this.color = color;
        this.x = x;
        this.y = y;
    }

    /**
     * create a disc with just color
     * @param color color of the disc
     */
    public Disc(char color){
        this.color = color;
    }

    /**
     * @return x position of disc
     */
    public int getX() {
        return x;
    }

    /**
     * @return y position of disc
     */
    public int getY() {
        return y;
    }

    /**
     * show the color of disc
     */
    public void display(){
        System.out.println(color);
    }

    /**
     * @return color of disc
     */
    public char getColor() {
        return color;
    }

    /**
     * set color of disc
     * @param color color field
     */
    public void setColor(char color) {
        this.color = color;
    }

    /**
     * @return black character
     */
    public char getBlack() {
        return black;
    }

    /**
     * @return withe character
     */
    public char getWhite() {
        return white;
    }

    @Override
    public String toString() {
        return
                "" + color ;
    }
}
