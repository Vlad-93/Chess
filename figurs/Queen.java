package figurs;

import board.Board;
import board.Board.Field;
import board.Color;
import board.File;

import static board.Color.white;

public final class Queen extends Figure {

    @Override
    public boolean validMove(Field field, boolean move) {

        int x = this.getField().getFile().getValue();
        int x2 = field.getFile().getValue();
        int y = this.getField().getRange();
        int y2 = field.getRange();

        if (x == x2) {                                  //одна вертикаль
            for (Field f : Board.getFileLine(field))
                if (f.getFigure() != null &&
                        f.getRange() > Math.min(y, y2) &&
                        f.getRange() < Math.max(y, y2))
                    return false;

            return field.getFigure() == null || field.getFigure().getColor() != this.getColor();
        }

        if (y == y2) {                                  //одна горизонталь
            for (Field f : Board.getRangeLine(field))
                if (f.getFigure() != null &&
                        f.getFile().getValue() > Math.min(x, x2) &&
                        f.getFile().getValue() < Math.max(x, x2))
                    return false;

            return field.getFigure() == null || field.getFigure().getColor() != this.getColor();
        }

        if (Math.abs(x - x2) == Math.abs(y - y2) )      //одна диагональ
            while (x != x2) {
                x = (x < x2)? x+1 : x-1;
                y = (y < y2)? y+1 : y-1;

                if (x == x2)
                    return field.getFigure() == null || field.getFigure().getColor() != this.getColor();

                if (Board.getField(File.getFile(x), y).getFigure() != null)
                    return false;
            }

        return false;
    }

    public Queen(Color color, Field field) {
        super(color, field);
    }

    //Ферзь белых:
    public static final Queen queenD1 = new Queen(white, Board.d1);
    public static final Queen[] queensWhite = new Queen[] {queenD1, null, null, null, null, null, null, null, null};
    //Ферзь чёрных:
    public static final Queen queenD8 = new Queen(Color.black, Board.d8);
    public static final Queen[] queensBlack = new Queen[] {queenD8, null, null, null, null, null, null, null, null};

    @Override
    public String toString() {
        return this.getColor() + "queen";
    }
    @Override
    public char toChar() {
        if (this.getColor() == white)
            return '♕';
        return '♛';
    }

    public static void init() {}
}
