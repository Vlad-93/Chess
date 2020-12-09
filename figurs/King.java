package figurs;

import board.Board;
import board.Board.Field;
import board.Color;
import board.File;
import game.Game;

import static board.Color.black;
import static board.Color.white;

public final class King extends Figure {

    private boolean startPosition = true;
    private boolean check;
    //Для рокировки:
    public static boolean castle;
    public static Rook castleRook;
    public static Field castleRookField;

    public boolean isStartPosition() {
        return startPosition;
    }

    public void setStartPosition(boolean startPosition) {
        this.startPosition = startPosition;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public boolean validMove(Field field, boolean move) {

        int x = this.getField().getFile().getValue();
        int x2 = field.getFile().getValue();
        int y = this.getField().getRange();
        int y2 = field.getRange();
        Figure figure = Board.getField(File.getFile((x > x2)? 1 : 8), y).getFigure();
        Field field1 = Board.getField(File.getFile((x > x2)? 4 : 6), y);
        Field field2 = Board.getField(File.getFile((x > x2)? 3 : 7), y);

        if (y == y2 && (Math.abs(x - x2) == 2) &&                           //Рокировка
            startPosition && figure instanceof Rook && ((Rook) figure).isStartPosition() &&    //Король и ладья не делали ходов
            !check && !moveOnCheck(field1) && !moveOnCheck(field2) &&       //Не блокируют ли поля передвижения короля вражеские фигуры
            figure.validMove(field1, false) && field1.getFigure() == null) {      //Горизонталь для рокировки свободна
                if (move) {
                    castle = true;
                    castleRook = (Rook) figure;
                    castleRookField = field1;
                    if (castleRook == Rook.rookH1 || castleRook == Rook.rookH8)
                        Game.moveWrite = "    0-0";
                    else
                        Game.moveWrite = "   0-0-0";
                }
                return true;
            }

        return (x == x2 && Math.abs(y - y2) == 1 ||                     //одна вертикаль, соседняя горизонталь
                y == y2 && Math.abs(x - x2) == 1 ||                     //одна горизонталь, соседняя вертикаль
                Math.abs(x - x2) == 1 && Math.abs(y - y2) == 1) &&      //одна диагональ, соседняя клетка
               (field.getFigure() == null || field.getFigure().getColor() != this.getColor() );
    }

    @Override
    public boolean moveOnCheck(Field field) {
        Figure figure = field.getFigure();
        field.setFigure(null);                      //Для проверки валидности хода
        this.getField().setFigure(null);            //Перед return возврат исходных значений. Метод ничего не изменяет

        if (this.getColor() == white) {
            for (Figure f : figuresBlack)
                if (f instanceof Pawn) {                       // следующая горизонталь, соседняя вертикаль:
                    if (f.getField().getRange() - field.getRange() == 1 && Math.abs(f.getField().getFile().getValue() - field.getFile().getValue()) == 1) {
                        field.setFigure(figure);
                        this.getField().setFigure(this);
                        return true;
                    }
                }
                else if (f.validMove(field, false) && f != figure) {
                    field.setFigure(figure);
                    this.getField().setFigure(this);
                    return true;
                }
        }
        else {
            for (Figure f : figuresWhite)
                if (f instanceof Pawn) {                       // следующая горизонталь, соседняя вертикаль:
                    if (f.getField().getRange() - field.getRange() == -1 && Math.abs(f.getField().getFile().getValue() - field.getFile().getValue()) == 1) {
                        field.setFigure(figure);
                        this.getField().setFigure(this);
                        return true;
                    }
                }
                else if (f.validMove(field, false) && f != figure) {
                    field.setFigure(figure);
                    this.getField().setFigure(this);
                    return true;
                }
        }

        field.setFigure(figure);
        this.getField().setFigure(this);
        return false;
    }

    private King(Color color, Field field) {
        super(color, field);
    }

    //Белый король:
    public static final King kingE1 = new King(white, Board.e1);
    //Чёрный король:
    public static final King kingE8 = new King(black, Board.e8);

    @Override
    public String toString() {
        return this.getColor() + "king";
    }
    @Override
    public char toChar() {
        if (this.getColor() == white)
            return '♔';
        return '♚';
    }

    public static void init() {}
}
