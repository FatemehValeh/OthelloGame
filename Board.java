package com.company;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Board class represents a board and strategy of the game in Othello game.
 *
 * @author Fatemh Valeh
 *
 */
public class Board {
    //main board of the game
    Disc[][] board;
    //shows which color wins the game
    private char winner = 'A';
    //height and length of the board
    private int boardSize = 8;
    //shows black discs in board
    char black = (char)9679;
    //shows white discs in board
    char white = (char) 9675;

    /**
     * create new board and first discs in it.
     */
    public Board(){
        board = new Disc[boardSize][boardSize];
        board[3][3] = new Disc(white);
        board[4][4] = new Disc(white);
        board[3][4] = new Disc(black);
        board[4][3] = new Disc(black);
    }

    /**
     * a method that gets a disc and put it in the board and flips other discs
     * @param discToPut
     */
    public void putDisc(Disc discToPut){
        int x = discToPut.getX();
        int y = discToPut.getY();
        if (this.isEmptyBlock(x,y) && this.isValidPut(discToPut)){
            board[y][x] = discToPut;
            turn(discToPut);
        }
        else
            System.out.println("can't put");
    }

    /**
     * this method checks if the block we want to put a disc is empty or not
     * @param x
     * @param y
     * @return
     */
    public boolean isEmptyBlock(int x, int y){
        if (board[y][x] == null)
            return true;
        else {
            System.out.println("This block is occupied.");
            return false;
        }
    }

    /**
     * this method checks blocks around the disc to check if there is the opposite color disc
     * @param disc disc to check around it
     * @return true if there is at least one opposite color around, false if there isn't any
     */
    public boolean isValidPut(Disc disc){
        char color = disc.getColor();
        int x = disc.getX();
        int y = disc.getY();
        boolean result = false;

        char oppColor = disc.getWhite(); // opposite color of current disc must be around the disc
        if (color == disc.getWhite())
            oppColor = disc.getBlack();

        int mx, my, flag;
        //up and left
        if (  x-1>=0 && y-1>=0 && board[y-1][x-1]!=null &&  board[y-1][x-1].getColor()==oppColor ){
            mx = x-1;
            my = y-1;
            flag = 0;
            while (mx>=0 && my>=0 && board[my][mx]!= null && board[my][mx].getColor()==oppColor){
                mx--;
                my--;
                flag++;
            }
            if ( mx>=0 && my>=0 && board[my][mx]!= null && flag>0 && board[my][mx].getColor()==color ){
                result = true;
            }

        }
        //up
        if (y-1>=0 &&  board[y-1][x]!=null && board[y-1][x].getColor()==oppColor  ){
            mx = x;
            my = y-1;
            flag = 0;
            while (my>=0 && board[my][mx] != null && board[my][mx].getColor()==oppColor){
                my--;
                flag++;
            }
            if (my>=0 && board[my][mx]!= null && flag>0 && board[my][mx].getColor()==color){
                result = true;
            }

        }
        //up and right
        if (  y-1>=0 && x+1<boardSize && board[y-1][x+1]!=null && board[y-1][x+1].getColor()==oppColor  ){
            mx = x+1;
            my = y-1;
            flag = 0;
            while (my>=0 && mx<boardSize && board[my][mx]!=null && board[my][mx].getColor()==oppColor){
                mx++;
                my--;
                flag++;
            }
            if (my>=0 && mx<boardSize && board[my][mx] != null && flag>0 && board[my][mx].getColor()==color){
                //System.out.println("u r");
                result = true;
            }

        }
        //right
        if ( x+1<boardSize && board[y][x+1]!=null && x+1<boardSize && board[y][x+1].getColor()==oppColor  ){
            mx = x+1;
            my = y;
            flag = 0;
            while (mx<boardSize && board[my][mx]!= null && board[my][mx].getColor()==oppColor){
                mx++;
                flag++;
            }
            if (mx<boardSize && board[my][mx]!=null && flag>0 && board[my][mx].getColor()==color){
                //System.out.println("r");
                result = true;
            }
        }
        //down and right
        if ( x+1<boardSize && y+1<boardSize && board[y+1][x+1]!=null && board[y+1][x+1].getColor()==oppColor ){
            mx = x+1;
            my = y+1;
            flag = 0;
            while(mx<boardSize && my<boardSize && board[my][mx]!=null && board[my][mx].getColor()==oppColor){
                mx++;
                my++;
                flag++;
            }
            if (mx<boardSize && my<boardSize && board[my][mx] != null && flag>0 && board[my][mx].getColor()==color){
                //System.out.println("d r");
                result = true;
            }
        }
        //down
        if (  y+1<boardSize && board[y+1][x]!=null &&board[y+1][x].getColor()==oppColor ){
            my=y+1;
            mx=x;
            flag = 0;
            while (my<boardSize && board[my][mx]!=null && board[my][mx].getColor()==oppColor){
                my++;
                flag++;
            }
            if (my<boardSize && board[my][mx]!=null && flag>0 && board[my][mx].getColor()==color){
                //System.out.println("d");
                result = true;
            }
        }
        //down and left
         if (x-1>=0 && y+1<boardSize && board[y+1][x-1]!=null &&  board[y+1][x-1].getColor()==oppColor  ){
            mx = x-1;
            my = y+1;
            flag = 0;
            while (my<boardSize && mx>=0 && board[my][mx]!=null && board[my][mx].getColor()==oppColor){
                mx--;
                my++;
                flag++;
            }
            if (my<boardSize && mx>=0 && board[my][mx]!=null && flag>0 && board[my][mx].getColor()==color){
                //System.out.println("d l");
                result = true;
            }
        }
        //left
        if (x-1>0 && board[y][x-1]!=null && board[y][x-1].getColor()==oppColor  ){
            //System.out.println("*");
            mx = x-1;
            my = y;
            flag = 0;
            while (mx>=0 && board[my][mx]!=null && board[my][mx].getColor()==oppColor){
                //System.out.println(flag);
                mx--;
                flag++;
            }
            if (mx>=0 && board[my][mx]!=null && flag>0 && board[my][mx].getColor()==color){
                //System.out.println("l");
                result = true;
            }
        }
        //System.out.println(result);
        return result;
    }

    /**
     * this method flips different discs between two same discs
     * @param disc the disc is put and other different discs flips because of this
     * @param yDir int between -1 to 1 that indicates which direction in y we wanna flip
     * @param xDir int between -1 to 1 that indicates which direction in x we wanna flip
     */
    public void turnDiscs(Disc disc, int yDir, int xDir){
        int x = disc.getX();
        int y = disc.getY();
        char color = disc.getColor();
        int currentX = x + xDir;
        int currentY = y + yDir;

        if (currentX<0 || currentY<0 || currentX==8 || currentY==8 || board[currentY][currentX]==null)
            return;
        while (board[currentY][currentX].getColor()==white || board[currentY][currentX].getColor()==black){

            if(board[currentY][currentX].getColor()==color){
                while ( !(x==currentX && y==currentY) ){
                    board[currentY][currentX].setColor(color);
                    currentY = currentY - yDir;
                    currentX -= xDir;
                }
                break;
            }
            else {
                currentX += xDir;
                currentY += yDir;
            }
            if (currentX<0 || currentY<0 || currentX==8 || currentY==8 || board[currentY][currentX]==null)
                return;
        }
    }

    /**
     * this method flips all discs around one disc according to the othello rules.
     * @param disc disc that turns other discs around itself
     */
    public void turn(Disc disc){
        //up
        turnDiscs(disc,-1,0);
        //down
        turnDiscs(disc,1,0);
        //right
        turnDiscs(disc,0,1);
        //left
        turnDiscs(disc,0,-1);
        //up and right
        turnDiscs(disc,-1,1);
        //up and left
        turnDiscs(disc,-1,-1);
        //down and right
        turnDiscs(disc, 1,1);
        //down and left
        turnDiscs(disc, 1,-1);
    }

    /**
     * prints game board in console
     */
    public void showBoard(){
        System.out.println("   A  B  C  D  E  F  G  H");
        //System.out.println("   - - - - - - - -");
        for (int i=0; i<boardSize; i++){
            System.out.print(i+1 + "|");
            for (int j=0; j<boardSize; j++){

                if (board[i][j] == null){
                    System.out.print("[ ]");
                }
                else
                    System.out.print("[" + board[i][j] + "]");
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

    /**
     * convert a char to an int in order of alphabet and according to the table
     * @param c char user entered
     * @return int of column in table
     */
    public int charToInt(char c){
        int s = -1;
        switch (c){
            case 'A' :
                s = 0;
                break;
            case 'B':
                s = 1;
                break;
            case 'C':
                s = 2;
                break;
            case 'D' :
                s = 3;
                break;
            case 'E' :
                s = 4;
                break;
            case 'F':
                s = 5;
                break;
            case 'G':
                s = 6;
                break;
            case 'H':
                s = 7;
                break;
        }
        return s;
    }

    /**
     * checks if table is full or not
     * @return true if table is full, false if it's not
     */
    public boolean isTableFull(){
        boolean res = true;
        int flag = 0;
        for (int i=0; i<boardSize; i++){
            for (int j=0; j<boardSize; j++){
                if (board[i][j]==null){
                    flag++;
                    break;
                }
            }
        }
        if (flag>0){
            res = false;
        }
        return res;
    }

    /**
     * this method checks if one can play according to Othello rules
     * @param player color of player
     * @return true if there is possible move for player, false if it's not
     */
   public boolean canMove(char player){
        ArrayList<Disc> res = new ArrayList<Disc>();

        for (int i=0; i<boardSize; i++){
            for (int j=0; j<boardSize; j++){
                Disc tmp = new Disc(player, j, i);
                if (isValidPut(tmp)){
                    res.add(tmp);
                }
            }
        }
        boolean result = res.size() > 0;
        if (!result){
            System.out.println("Can't move anymore!");
        }
        return result;
    }

    /**
     * calculates discs in board and indicates who the winner is
     * @return total score of winner, who has more discs
     */
    public int winnerScore(){
        int res = 0;
        int blackWin, whiteWin;
        blackWin=whiteWin=0;
        for (int i=0; i<boardSize; i++){
            for (int j=0; j<boardSize; j++){

                if(board[i][j] != null) {

                    if (board[i][j].getColor() == black) {
                        blackWin++;
                    } else if (board[i][j].getColor() == white) {
                        whiteWin++;
                    }
                }

            }
        }
        setWinner(whiteWin,blackWin);
        if (whiteWin>blackWin){
            res = whiteWin;
        }
        else if (blackWin>whiteWin){
            res = blackWin;
        }

        return res;
    }

    /**
     * set the winner field, who won the game
     * @param whiteWin scores of white player
     * @param blackWin scores of black player
     */
    public void setWinner(int whiteWin, int blackWin){
        if (whiteWin>blackWin){
            winner = white;
        }
        else if (blackWin>whiteWin){
            winner = black;
        }
    }

    /**
     * prints the result of the game.
     */
    public void winner(){
        int result = winnerScore();
        if (winner != 'A'){
            System.out.println("'" + winner + "' wins with " + result + " points :)");
        }
        if (winner == 'A'){
            System.out.println("Draw " + result + "-" + result);
        }
    }

    /**
     * main method of the game. decide single player or multi player, switch turn, put discs, flip discs and tell the winner.
     */
    public void play(){


        Scanner sc = new Scanner(System.in);
        System.out.println("Chose which one you wanna play:");
        System.out.println("1) Single player. ");
        System.out.println("2) Multiple player. ");
        int choice = sc.nextInt();
        sc.nextLine();
        if (choice==1){
            System.out.println("You play '" + black + "' and computer plays '" + white + "'.");
        }
        this.showBoard();

        int x,y;
        char player = black;
        while ( !isTableFull() && canMove(player) ) {
            System.out.println("It is '" + player + "' turn:");

            if (choice==1 && player==white){
                Random rand = new Random();
                x = rand.nextInt(8); // single player is random so it's not intelligent.
                y = rand.nextInt(8);
                //System.out.println("x= " + x + "y= " + y);
            }
            else {
                String line = sc.nextLine();
                StringTokenizer st = new StringTokenizer(line, " ");
                int n = Integer.parseInt(st.nextToken());
                char c = st.nextToken().charAt(0);
                int m = charToInt(c);
                x = m;
                y = n - 1;
                if (x < 0 || y < 0 || x >= boardSize || y >= boardSize) {
                    System.out.println("invalid input!");
                    break;
                }
            }

            Disc disc1 = new Disc(player, x, y);
            this.putDisc(disc1);
            this.showBoard();

            if (player == black){
                player = white;
            }
            else {
                player = black;
            }
        }
        winner();

    }
}
