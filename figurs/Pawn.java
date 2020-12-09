package figurs;

import board.Board;
import board.Board.Field;
import board.Color;
import game.Game;

import static board.Color.black;
import static board.Color.white;
import static figurs.King.kingE1;
import static figurs.King.kingE8;

public final class Pawn extends Figure {

    private static Pawn inPass;
    private static boolean takeInPass;

    public static Pawn getInPass() {
        return inPass;
    }

    public static void setInPass(Pawn inPass) {
        Pawn.inPass = inPass;
    }

    public static boolean isTakeInPass() {
        return takeInPass;
    }

    public static void setTakeInPass(boolean takeInPass) {
        Pawn.takeInPass = takeInPass;
    }

    @Override
    public boolean validMove(Field field, boolean move) {

        int x = this.getField().getFile().getValue();
        int x2 = field.getFile().getValue();
        int y = this.getField().getRange();
        int y2 = field.getRange();

        if (this.getColor() == white) {                                             //ход (белая пешка):
            if (x == x2 && field.getFigure() == null)                               //одна вертикаль и нет фигуры на поле перемещения
               if (y + 1 == y2) {                                                   //следующая горизонталь
                   if (move)
                        inPass = null;
                    return true;
               }
               else if (y == 2 && y2 == 4 &&                                        //ход на 2 поля вперёд из нач. поз.
                        Board.getField(x, 3).getFigure() == null) {                 //нет фигуры на поле горизонтали 3
                   if (move)
                        inPass = this;
                   return true;
               }
            //взятие фигуры:
            if (y + 1 == y2 && Math.abs(x - x2) == 1 &&                                             //следующая горизонталь, соседняя вертикаль
                field.getFigure() != null && field.getFigure().getColor() == black) {               //на клетке есть фигура противника
                if (move)
                    inPass = null;
                return true;
            }
            //взятие на проходе:
            if (y == 5 && y2 == 6 && Math.abs(x - x2) == 1 &&                                       //ход с горизонтали 5 на 6, соседняя вертикаль
                Board.getField(field.getFile(), 5).getFigure() == inPass && inPass != null) {       //пешка на проходе
                if (move)
                    takeInPass = true;
                return true;
            }
        }
        else {                                                                       //ход (чёрная пешка):
            if (x == x2 && field.getFigure() == null)                                //одна вертикаль и нет фигуры на поле перемещения
                if (y - 1 == y2) {                                                   //следующая горизонталь
                    if (move)
                        inPass = null;
                    return true;
                }
                else if (y == 7 && y2 == 5 &&                                        //ход на 2 поля вперёд из нач. поз.
                        Board.getField(x, 6).getFigure() == null) {                  //нет фигуры на поле горизонтали 6
                    if (move)
                        inPass = this;
                    return true;
                }
            //взятие фигуры:
            if (y - 1 == y2 && Math.abs(x - x2) == 1 &&                                             //следующая горизонталь, соседняя вертикаль
                    field.getFigure() != null && field.getFigure().getColor() == white) {           //на клетке есть фигура противника
                if (move)
                    inPass = null;
                return true;
            }
            //взятие на проходе:
            if (y == 4 && y2 == 3 && Math.abs(x - x2) == 1 &&                                       //ход с горизонтали 4 на 3, соседняя вертикаль
                    Board.getField(field.getFile(), 4).getFigure() == inPass && inPass != null) {   //пешка на проходе
                if (move)
                    takeInPass = true;
                return true;
            }
        }
        return false;
    }

    // Превращение пешки:
    public static void promotion(Pawn pawn, Field field, int numFigure) {

        switch (numFigure) {
            case 1:
                Queen queen = new Queen(pawn.getColor(), field);
                Game.moveWrite += " = " + queen.toChar();
                field.setFigure(queen);
                if (pawn.getColor() == white)
                    for (int i = 1; i < 9; i++)
                        if (Queen.queensWhite[i] == null)
                            Queen.queensWhite[i] = queen;
                if (pawn.getColor() == black)
                    for (int i = 1; i < 9; i++)
                        if (Queen.queensBlack[i] == null)
                            Queen.queensBlack[i] = queen;
                break;

            case 2:
                Rook rook = new Rook(pawn.getColor(), field);
                Game.moveWrite += " = " + rook.toChar();
                field.setFigure(rook);
                if (pawn.getColor() == white)
                    for (int i = 1; i < 9; i++)
                        if (Rook.rooksWhite[i] == null)
                            Rook.rooksWhite[i] = rook;
                if (pawn.getColor() == black)
                    for (int i = 1; i < 9; i++)
                        if (Rook.rooksBlack[i] == null)
                            Rook.rooksBlack[i] = rook;
                break;

            case 3:
                Bishop bishop = new Bishop(pawn.getColor(), field);
                Game.moveWrite += " = " + bishop.toChar();
                field.setFigure(bishop);
                if (pawn.getColor() == white)
                    for (int i = 1; i < 9; i++)
                        if (Bishop.bishopsWhite[i] == null)
                            Bishop.bishopsWhite[i] = bishop;
                if (pawn.getColor() == black)
                    for (int i = 1; i < 9; i++)
                        if (Bishop.bishopsBlack[i] == null)
                            Bishop.bishopsBlack[i] = bishop;
                break;

            case 4:
                Knight knight = new Knight(pawn.getColor(), field);
                Game.moveWrite += " = " + knight.toChar();
                field.setFigure(knight);
                if (pawn.getColor() == white)
                    for (int i = 1; i < 9; i++)
                        if (Knight.knightsWhite[i] == null)
                            Knight.knightsWhite[i] = knight;
                if (pawn.getColor() == black)
                    for (int i = 1; i < 9; i++)
                        if (Knight.knightsBlack[i] == null)
                            Knight.knightsBlack[i] = knight;
                break;
        }

        pawn.setField(null);
        pawn.remove();

        if (pawn.getColor() == white)             //Проверка шаха
            kingE8.setCheck(kingE8.moveOnCheck(kingE8.getField() ));
        else
            kingE1.setCheck(kingE1.moveOnCheck(kingE1.getField() ));
    }

    private Pawn(Color color, Field field) {
        super(color, field);
    }

    //Фигуры
    //Белые пешки:
    public static final Pawn pawnA2 = new Pawn(white, Board.a2);
    public static final Pawn pawnB2 = new Pawn(white, Board.b2);
    public static final Pawn pawnC2 = new Pawn(white, Board.c2);
    public static final Pawn pawnD2 = new Pawn(white, Board.d2);
    public static final Pawn pawnE2 = new Pawn(white, Board.e2);
    public static final Pawn pawnF2 = new Pawn(white, Board.f2);
    public static final Pawn pawnG2 = new Pawn(white, Board.g2);
    public static final Pawn pawnH2 = new Pawn(white, Board.h2);
    public static final Pawn[] pawnsWhite = new Pawn[] {pawnA2, pawnB2, pawnC2, pawnD2, pawnE2, pawnF2, pawnG2, pawnH2};
    //Чёрные пешки:
    public static final Pawn pawnA7 = new Pawn(black, Board.a7);
    public static final Pawn pawnB7 = new Pawn(black, Board.b7);
    public static final Pawn pawnC7 = new Pawn(black, Board.c7);
    public static final Pawn pawnD7 = new Pawn(black, Board.d7);
    public static final Pawn pawnE7 = new Pawn(black, Board.e7);
    public static final Pawn pawnF7 = new Pawn(black, Board.f7);
    public static final Pawn pawnG7 = new Pawn(black, Board.g7);
    public static final Pawn pawnH7 = new Pawn(black, Board.h7);
    public static final Pawn[] pawnsBlack = new Pawn[] {pawnA7, pawnB7, pawnC7, pawnD7, pawnE7, pawnF7, pawnG7, pawnH7};

    @Override
    public String toString() {
        return this.getColor() + "pawn";
    }
    @Override
    public char toChar() {
        if (this.getColor() == white)
            return '♙';
        return '♟';
    }

//        ♛♚♜♝♞♟
//                ♙♘♗♖♕♔
    public static void init() {}
}
