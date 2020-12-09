package figurs;

import board.Board;
import board.Board.Field;
import board.Color;

import static board.Color.black;
import static board.Color.white;

public final class Knight extends Figure {

    @Override
    public boolean validMove(Field field, boolean move) {

        int x = this.getField().getFile().getValue();
        int x2 = field.getFile().getValue();
        int y = this.getField().getRange();
        int y2 = field.getRange();

        return (Math.abs(x - x2) == 1 && Math.abs(y - y2) == 2 ||
                Math.abs(x - x2) == 2 && Math.abs(y - y2) == 1) &&
               (field.getFigure() == null || field.getFigure().getColor() != this.getColor() );
    }

    public Knight(Color color, Field field) {
        super(color, field);
    }

    //Кони белых:
    public static final Knight knightB1 = new Knight(white, Board.b1);
    public static final Knight knightG1 = new Knight(white, Board.g1);
    public static final Knight[] knightsWhite = new Knight[] {knightB1, knightG1, null, null, null, null, null, null, null, null};
    //Кони чёрных:
    public static final Knight knightB8 = new Knight(black, Board.b8);
    public static final Knight knightG8 = new Knight(black, Board.g8);
    public static final Knight[] knightsBlack = new Knight[] {knightB8, knightG8, null, null, null, null, null, null, null, null};

    @Override
    public String toString() {
        return this.getColor() + "knight";
    }
    @Override
    public char toChar() {
        if (this.getColor() == white)
            return '♘';
        return '♞';
    }

    public static void init() {}
}
