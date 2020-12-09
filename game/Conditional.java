package game;

import board.Board;
import board.Board.Field;
import figurs.*;

import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

import static figurs.Figure.figuresBlack;
import static figurs.Figure.figuresWhite;
import static figurs.King.kingE1;
import static figurs.King.kingE8;
import static game.GameResult.*;

public class Conditional {
    public static String result;
    public static String condition;

    private static int numMoveNoPlay = 0;
    private static final char[] startPosition = {'♜','♞','♝','♛','♚','♝','♞','♜',           // 0 - нет фигуры,
                                                 '♟','♟','♟','♟','♟','♟','♟','♟',           // 1 - пешка,
                                                  ' ', ' ', ' ', ' ', ' ', ' ',' ', ' ',           // 2 - конь,
                                                  ' ', ' ', ' ', ' ', ' ', ' ',' ', ' ',           // 3 - слон,
                                                  ' ', ' ', ' ', ' ', ' ', ' ',' ', ' ',           // 4 - ладья,
                                                  ' ', ' ', ' ', ' ', ' ', ' ',' ', ' ',           // 5 - ферзь,
                                                 '♙','♙','♙','♙','♙','♙','♙','♙',           // 6 - король
                                                 '♖','♘','♗','♕','♔','♗','♘','♖'};          // '-' - фигура чёрных

    private static HashMap<char[], Integer> positions = new HashMap<>();
    private static HashMap<Integer, char[]> gamePositions = new HashMap<>();

    // Мат или пат:
    public static void checkMate() {
        if (Game.isMoveWhite()) {                                   // Ход белых
            for (Figure figure : figuresWhite)
                for (Field field : Board.fields)
                    if (!figure.moveOnCheck(field) && figure.validMove(field, false))
                        return;

            if (kingE1.isCheck() ) {
                Game.setResult(blackWin);
                result = "Black win!";
                condition = "Checkmate";
            }
            else {
                Game.setResult(draw);
                result = "Draw";
                condition = "Stalemate";
            }
        }
        else {                                                      // Ход чёрных
            for (Figure figure : figuresBlack)
                for (Field field : Board.fields)
                    if (!figure.moveOnCheck(field) && figure.validMove(field, false))
                        return;

            if (kingE8.isCheck() ) {
                Game.setResult(whiteWin);
                result = "White win!";
                condition = "Checkmate";
            }
            else {
                Game.setResult(draw);
                result = "Draw";
                condition = "Stalemate";
            }
        }

    }

    // 3 раза повторилась позиция:
    public static void position3times() {

        if (Game.numMoves == 1)
            positions.put(startPosition, 1);

        char[] position = new char[64];

        for (int i = 0; i < 64; i++)
            if (Board.fields[i].getFigure() == null)
                position[i] = ' ';
            else
                position[i] = Board.fields[i].getFigure().toChar();

        gamePositions.put(Game.numMoves, position);                 // сохраняем позицию для каждого хода

        int numIteration = 0;

        L:for (char[] chars: positions.keySet() ) {
            numIteration++;
            for (int i = 0; i < 64; i++)
                if (chars[i] != position[i]) {
                    if (numIteration == positions.size())       // если последняя итерация
                        positions.put(position, 1);
                    continue L;
                }
            positions.put(chars, positions.get(chars) + 1);
            break;
        }

        if (positions.containsValue(3)) {
            Game.setResult(draw);
            result = "Draw";
            condition = "Threefold repetition";
        }
    }

    // 50 ходов:
    public static void moves50(Figure figure, boolean capture) {

        if (figure instanceof Pawn || capture)
            numMoveNoPlay = 0;
        else
            numMoveNoPlay++;

        if (numMoveNoPlay == 100) {
            Game.setResult(draw);
            result = "Draw";
            condition = "Fifty-move rule";
        }
    }

    // Недостаток фигур:
    public static void noFigures() {
        List<Figure> figures = new LinkedList<>();
        figures.addAll(figuresWhite);
        figures.addAll(figuresBlack);

        if (figures.size() == 2) {
            Game.setResult(draw);
            result = "Draw";
            condition = "Insufficient material";
        }

        if (figures.size() == 3)
            for (Figure figure: figures)
                if (figure instanceof Bishop ||
                        figure instanceof Knight) {
                    Game.setResult(draw);
                    result = "Draw";
                    condition = "Insufficient material";
                }
    }

}
