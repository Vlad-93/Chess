package figurs;

import board.Board;
import board.Board.Field;
import board.Color;
import board.File;

import static board.Color.black;
import static board.Color.white;

public final class Bishop extends Figure {

    @Override
    public boolean validMove(Field field, boolean move) {

        int x = this.getField().getFile().getValue();
        int x2 = field.getFile().getValue();
        int y = this.getField().getRange();
        int y2 = field.getRange();

        if (Math.abs(x - x2) == Math.abs(y - y2) )          //одна диагональ
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

    public Bishop(Color color, Field field) {
        super(color, field);
    }

    //Слоны белых:
    public static final Bishop bishopC1 = new Bishop(white, Board.c1);
    public static final Bishop bishopF1 = new Bishop(white, Board.f1);
    public static final Bishop[] bishopsWhite = new Bishop[] {bishopC1, bishopF1, null, null, null, null, null, null, null, null};
    //Слоны чёрных:
    public static final Bishop bishopC8 = new Bishop(black, Board.c8);
    public static final Bishop bishopF8 = new Bishop(black, Board.f8);
    public static final Bishop[] bishopsBlack = new Bishop[] {bishopC8, bishopF8, null, null, null, null, null, null, null, null};

    @Override
    public String toString() {
        return this.getColor() + "bishop";
    }
    @Override
    public char toChar() {
        if (this.getColor() == white)
            return '♗';
        return '♝';
    }

    public static void init() {}
}
