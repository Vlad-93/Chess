package game;

import board.Board.Field;
import figurs.Figure;

import static figurs.King.kingE1;
import static figurs.King.kingE8;

import figurs.Pawn;
import player.Player;

import java.util.LinkedList;

import static game.GameResult.*;

public class Game {

    static GameResult result = noResult;
    static int numMoves = 0;            // Кол-во сделанных ходов в партии
    static boolean moveWhite = true;
    static Figure figure = null;
    static LinkedList<String> list = new LinkedList();
    public static String moveWrite;
    private Player playerWhite;
    private Player playerBlack;

    public static void chooseFigure(Figure f) {
        figure = f;
    }

    public static void move(Figure figure, Field field, int num) {
        int figursNumber = Figure.figuresBlack.size() + Figure.figuresWhite.size();
        moveWrite = figure.toChar() + " " + figure.getField() + " - " + field;

        figure.move(field);
        if (num != 0)
            Pawn.promotion((Pawn) figure, field, num);

        numMoves++;
        moveWhite = !moveWhite;

        Conditional.checkMate();

        if (kingE1.isCheck() || kingE8.isCheck())
            if (result != noResult)
                moveWrite += " x";
            else
                moveWrite += " +";

        //System.out.println(moveWrite);
        list.add(moveWrite);

        if (result != noResult)
            return;

        Conditional.position3times();
        Conditional.moves50(figure, figursNumber != Figure.figuresBlack.size() + Figure.figuresWhite.size());
        Conditional.noFigures();
    }

    public Game(Player playerWhite, Player playerBlack) {
        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;
    }

    public static GameResult getResult() {
        return result;
    }

    public static void setResult(GameResult result) {
        Game.result = result;
    }

    public static boolean isMoveWhite() {
        return moveWhite;
    }

    public static Figure getFigure() {
        return figure;
    }
}
