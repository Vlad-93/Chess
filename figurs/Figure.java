package figurs;

import board.Board;
import board.Board.Field;
import board.Color;
import board.File;

import java.util.LinkedList;
import java.util.List;

import static board.Color.white;
import static figurs.King.kingE1;
import static figurs.King.kingE8;

abstract public class Figure {
    public static List<Figure> figuresWhite = new LinkedList<>();       //Фигуры белых
    public static List<Figure> figuresBlack = new LinkedList<>();       //Фигуры чёрных

    private final Color color;
    private Field field;

    public Figure(Color color, Field field) {
        this.color = color;
        this.field = field;
        field.setFigure(this);

        if (color == white)
            figuresWhite.add(this);
        else
            figuresBlack.add(this);
    }

    protected void remove() {
        if (this.color == white)
            figuresWhite.remove(this);
        else
            figuresBlack.remove(this);
    }

    public Color getColor() {return color;}
    public Field getField() {return field;}
    public void setField(Field field) {
        this.field = field;
    }

    public void move(Field field) {
        if (validMove(field, true) && !moveOnCheck(field) ) {
            this.field.setFigure(null);

            if (field.getFigure() != null) {
                field.getFigure().field = null;
                field.getFigure().remove();
            }

            if (Pawn.isTakeInPass()) {               //взятие на проходе
                Pawn.setTakeInPass(false);
                Pawn.getInPass().getField().setFigure(null);
                Pawn.getInPass().setField(null);
                Pawn.getInPass().remove();
                Pawn.setInPass(null);
            }

            this.field = field;
            field.setFigure(this);

            if (King.castle) {                               //Перемещение ладьи в случае хода с рокировкой
                King.castle = false;
                King.castleRook.getField().setFigure(null);
                King.castleRookField.setFigure(King.castleRook);
                King.castleRook.setField(King.castleRookField);
                King.castleRook = null;
                King.castleRookField = null;
            }

            if (this.color == white)             //Проверка шаха
                kingE8.setCheck(kingE8.moveOnCheck(kingE8.getField() ));
            else
                kingE1.setCheck(kingE1.moveOnCheck(kingE1.getField() ));

            //для фигур:
            if (!(this instanceof Pawn))
                Pawn.setInPass(null);
            if (this instanceof Rook)
                ((Rook) this).setStartPosition(false);
            if (this instanceof King)
                ((King) this).setStartPosition(false);
        }
    }

    public boolean moveOnCheck (Field field) {
        int x2, y2;                                            //Координаты проверяемой фигуры

        if (this.getColor() == white) {                                 //ход белых:
            int x1 = King.kingE1.getField().getFile().getValue();      //Координаты короля белых
            int y1 = King.kingE1.getField().getRange();
            if (this instanceof King) {
                x1 = field.getFile().getValue();
                y1 = field.getRange();
            }

            L: for (Figure figure : figuresBlack)
                if (figure != field.getFigure()) {
                    x2 = figure.getField().getFile().getValue();
                    y2 = figure.getField().getRange();

                    if (figure instanceof Pawn)
                        if (y2 - y1 == 1 && Math.abs(x2 - x1) == 1)           //Следующая горизонталь, соседняя вертикаль
                            return true;
                        else continue;

                    if (figure instanceof Knight)
                        if (Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 2 ||
                            Math.abs(x1 - x2) == 2 && Math.abs(y1 - y2) == 1)
                                return true;
                        else continue;

                    if (figure instanceof Bishop || figure instanceof Queen)
                        if (Math.abs(x1 - x2) == Math.abs(y1 - y2) ) {        //одна диагональ
                            int x3 = x1;
                            int y3 = y1;

                            while (x3 != x2) {
                                x3 = (x3 < x2) ? x3 + 1 : x3 - 1;
                                y3 = (y3 < y2) ? y3 + 1 : y3 - 1;

                                if (x3 == x2)
                                    return true;

                                if (Board.getField(x3, y3).getFigure() != null &&
                                        Board.getField(x3, y3).getFigure() != this ||
                                        Board.getField(x3, y3) == field)
                                    continue L;
                            }
                        }
                    if (figure instanceof Rook || figure instanceof  Queen) {
                        if (x1 == x2) {                                  //одна вертикаль
                            for (Field f : Board.getFileLine(x1))
                                if ((f.getFigure() != null && f.getFigure() != this || f == field) &&
                                        f.getRange() > Math.min(y1, y2) &&
                                        f.getRange() < Math.max(y1, y2))
                                    continue L;

                            return true;
                        }

                        if (y1 == y2) {                                  //одна горизонталь
                            for (Field f : Board.getRangeLine(y1))
                                if ((f.getFigure() != null && f.getFigure() != this || f == field) &&
                                        f.getFile().getValue() > Math.min(x1, x2) &&
                                        f.getFile().getValue() < Math.max(x1, x2))
                                    continue L;

                            return true;
                        }
                    }
                }
            return false;
        }
        else {                                                          //ход чёрных:
            int x1 = King.kingE8.getField().getFile().getValue();      //Координаты короля чёрных
            int y1 = King.kingE8.getField().getRange();
            if (this instanceof King) {
                x1 = field.getFile().getValue();
                y1 = field.getRange();
            }

            L: for (Figure figure : figuresWhite)
                if (figure != field.getFigure()) {
                    x2 = figure.getField().getFile().getValue();
                    y2 = figure.getField().getRange();

                    if (figure instanceof Pawn)
                        if (y1 - y2 == 1 && Math.abs(x1 - x2) == 1)           //Следующая горизонталь, соседняя вертикаль
                            return true;
                        else continue;

                    if (figure instanceof Knight)
                        if (Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 2 ||
                                Math.abs(x1 - x2) == 2 && Math.abs(y1 - y2) == 1)
                            return true;
                        else continue;

                    if (figure instanceof Bishop || figure instanceof Queen)
                        if (Math.abs(x1 - x2) == Math.abs(y1 - y2) ) {        //одна диагональ
                            int x3 = x1;
                            int y3 = y1;

                            while (x3 != x2) {
                                x3 = (x3 < x2) ? x3 + 1 : x3 - 1;
                                y3 = (y3 < y2) ? y3 + 1 : y3 - 1;

                                if (x3 == x2)
                                    return true;

                                if (Board.getField(File.getFile(x3), y3).getFigure() != null &&
                                        Board.getField(File.getFile(x3), y3).getFigure() != this ||
                                        Board.getField(File.getFile(x3), y3) == field)
                                    continue L;
                            }
                        }
                    if (figure instanceof Rook || figure instanceof  Queen) {
                        if (x1 == x2) {                                  //одна вертикаль
                            for (Field f : Board.getFileLine(x1))
                                if ((f.getFigure() != null && f.getFigure() != this || f == field) &&
                                        f.getRange() > Math.min(y1, y2) &&
                                        f.getRange() < Math.max(y1, y2))
                                    continue L;

                            return true;
                        }

                        if (y1 == y2) {                                  //одна горизонталь
                            for (Field f : Board.getRangeLine(y1))
                                if ((f.getFigure() != null && f.getFigure() != this || f == field) &&
                                        f.getFile().getValue() > Math.min(x1, x2) &&
                                        f.getFile().getValue() < Math.max(x1, x2))
                                    continue L;

                            return true;
                        }
                    }
                }
            return false;
        }
    }

    public abstract boolean validMove(Field field, boolean move);
    public abstract char toChar();
}
