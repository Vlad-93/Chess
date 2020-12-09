package figurs;

import board.Board;
import board.Board.Field;
import board.Color;

import static board.Color.black;
import static board.Color.white;

public final class Rook extends Figure {

    private boolean startPosition = true;

    public boolean isStartPosition() {
        return startPosition;
    }

    public void setStartPosition(boolean startPosition) {
        this.startPosition = startPosition;
    }

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

        return false;
    }

    public Rook(Color color, Field field) {
        super(color, field);
    }

    //Белые ладьи:
    public static final Rook rookA1 = new Rook(white, Board.a1);
    public static final Rook rookH1 = new Rook(white, Board.h1);
    public static final Rook[] rooksWhite = new Rook[] {rookA1, rookH1, null, null, null, null, null, null, null, null};
    //Чёрные ладьи:
    public static final Rook rookA8 = new Rook(black, Board.a8);
    public static final Rook rookH8 = new Rook(black, Board.h8);
    public static final Rook[] rooksBlack = new Rook[] {rookA8, rookH8, null, null, null, null, null, null, null, null};

    @Override
    public String toString() {
        return this.getColor() + "rook";
    }
    @Override
    public char toChar() {
        if (this.getColor() == white)
            return '♖';
        return '♜';
    }

    public static void init() {}
}
