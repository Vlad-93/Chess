package sample;

import board.Board;
import board.Board.Field;
import figurs.Figure;
import figurs.Pawn;
import game.Conditional;
import game.Game;
import game.GameResult;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import static board.Board.fields;
import static board.Color.black;
import static board.Color.white;

public class Controller {

    @FXML
    private GridPane board;
    @FXML
    private Pane windowResult, promotionBlack, promotionWhite;

    @FXML
    private ImageView whitequeen, whiterook, whitebishop, whiteknight, blackqueen, blackrook, blackbishop, blackknight;
    @FXML
    private Label textResult, textCondition;
    @FXML
    private Button reverse1, reverse2, ok;
    @FXML
    private Label A9, B9, C9, D9, E9, F9, G9, H9,
                  A0, B0, C0, D0, E0, F0, G0, H0,
                  Z1, Z2, Z3, Z4, Z5, Z6, Z7, Z8,
                  I1, I2, I3, I4, I5, I6, I7, I8;

    private Label[] labelInit() {
        return new Label[] {A9, B9, C9, D9, E9, F9, G9, H9,
                            A0, B0, C0, D0, E0, F0, G0, H0,
                            Z1, Z2, Z3, Z4, Z5, Z6, Z7, Z8,
                            I1, I2, I3, I4, I5, I6, I7, I8};
    }

    @FXML
    private ImageView a8, b8, c8, d8, e8, f8, g8, h8,
                      a7, b7, c7, d7, e7, f7, g7, h7,
                      a6, b6, c6, d6, e6, f6, g6, h6,
                      a5, b5, c5, d5, e5, f5, g5, h5,
                      a4, b4, c4, d4, e4, f4, g4, h4,
                      a3, b3, c3, d3, e3, f3, g3, h3,
                      a2, b2, c2, d2, e2, f2, g2, h2,
                      a1, b1, c1, d1, e1, f1, g1, h1;

    private ImageView[] imageViewsInit() {
        return new ImageView[] {a8, b8, c8, d8, e8, f8, g8, h8,
                                a7, b7, c7, d7, e7, f7, g7, h7,
                                a6, b6, c6, d6, e6, f6, g6, h6,
                                a5, b5, c5, d5, e5, f5, g5, h5,
                                a4, b4, c4, d4, e4, f4, g4, h4,
                                a3, b3, c3, d3, e3, f3, g3, h3,
                                a2, b2, c2, d2, e2, f2, g2, h2,
                                a1, b1, c1, d1, e1, f1, g1, h1};
    }
    @FXML
    private Button  A8, B8, C8, D8, E8, F8, G8, H8,
                    A7, B7, C7, D7, E7, F7, G7, H7,
                    A6, B6, C6, D6, E6, F6, G6, H6,
                    A5, B5, C5, D5, E5, F5, G5, H5,
                    A4, B4, C4, D4, E4, F4, G4, H4,
                    A3, B3, C3, D3, E3, F3, G3, H3,
                    A2, B2, C2, D2, E2, F2, G2, H2,
                    A1, B1, C1, D1, E1, F1, G1, H1;

    private Button[] buttonsInit() {
        return new Button[] {A8, B8, C8, D8, E8, F8, G8, H8,
                             A7, B7, C7, D7, E7, F7, G7, H7,
                             A6, B6, C6, D6, E6, F6, G6, H6,
                             A5, B5, C5, D5, E5, F5, G5, H5,
                             A4, B4, C4, D4, E4, F4, G4, H4,
                             A3, B3, C3, D3, E3, F3, G3, H3,
                             A2, B2, C2, D2, E2, F2, G2, H2,
                             A1, B1, C1, D1, E1, F1, G1, H1};
    }

//    private Button getButton (Field field) {
//        int x = field.getFile().getValue();
//        int y = field.getRange();
//
//        return buttons[x - 1 + (8 - y) * 8];
//    }

    private void show() {
        ImageView[] imageView = imageViewsInit();

        for (int i = 0; i < 64; i++) {
            imageView[i].setImage(null);
            if (fields[i].getFigure() != null)
                imageView[i].setImage(new Image("/" + fields[i].getFigure() + ".png"));
        }
    }

    private void gameEnd() {
        windowResult.setVisible(true);

        textResult.setText(Conditional.result);
        textCondition.setText(Conditional.condition);
    }

    private Pawn pawnPromotion;
    private Field fieldPromotion;

    private void mouseClicked(Field field, Button button) {
        if (Game.getResult() != GameResult.noResult || pawnPromotion != null)
            return;

        Button[] buttons = buttonsInit();

        if (Game.getFigure() != null) {            // фигура выбрана
            Figure figure = Game.getFigure();
            Game.chooseFigure(null);
            for (int i = 0; i < 64; i++)
                buttons[i].setStyle("-fx-background-color: " + fields[i].getColor());      // Возврат цветов полей
            if (figure.getField() != field)
                if (!figure.moveOnCheck(field) && figure.validMove(field, false)) {

                    if (figure instanceof Pawn && (field.getRange() == 1 || field.getRange() == 8) ) {
                        if (figure.getColor() == white)
                            promotionWhite.setVisible(true);
                        else
                            promotionBlack.setVisible(true);

                        fieldPromotion = field;
                        pawnPromotion = (Pawn) figure;
                        return;
                    }

                    Game.move(figure, field, 0);
                    show();

                    if (Game.getResult() != GameResult.noResult)
                        gameEnd();
                }
                else
                    mouseClicked(field, button);
        }
        else if (field.getFigure() != null &&                                           // Фигура есть на выбранном поле
                (Game.isMoveWhite() && field.getFigure().getColor() == white ||          // Ход белых и выбранная фигура белых или
                !Game.isMoveWhite() && field.getFigure().getColor() == black)) {          // Ход чёрных и выбранная фигура чёрных
            Figure figure = field.getFigure();
            Game.chooseFigure(figure);
            button.setStyle("-fx-background-color: yellow");
            for (int i = 0; i < 64; i++)
                if (!figure.moveOnCheck(fields[i]) && figure.validMove(fields[i], false) )     // Определение возможных ходов
                    if (fields[i].getFigure() != null || figure instanceof Pawn && figure.getField().getFile() != fields[i].getFile() )
                        buttons[i].setStyle("-fx-background-color: #ff00007f");             // Взятие фигуры
                    else
                        buttons[i].setStyle("-fx-background-color: #007f007f");             // Ход
        }
    }

    @FXML
    private void mouseClickedA1() { mouseClicked(Board.a1, A1); }
    @FXML
    private void mouseClickedA2() { mouseClicked(Board.a2, A2); }
    @FXML
    private void mouseClickedA3() { mouseClicked(Board.a3, A3); }
    @FXML
    private void mouseClickedA4() { mouseClicked(Board.a4, A4); }
    @FXML
    private void mouseClickedA5() { mouseClicked(Board.a5, A5); }
    @FXML
    private void mouseClickedA6() { mouseClicked(Board.a6, A6); }
    @FXML
    private void mouseClickedA7() { mouseClicked(Board.a7, A7); }
    @FXML
    private void mouseClickedA8() { mouseClicked(Board.a8, A8); }
    @FXML
    private void mouseClickedB1() { mouseClicked(Board.b1, B1); }
    @FXML
    private void mouseClickedB2() { mouseClicked(Board.b2, B2); }
    @FXML
    private void mouseClickedB3() { mouseClicked(Board.b3, B3); }
    @FXML
    private void mouseClickedB4() { mouseClicked(Board.b4, B4); }
    @FXML
    private void mouseClickedB5() { mouseClicked(Board.b5, B5); }
    @FXML
    private void mouseClickedB6() { mouseClicked(Board.b6, B6); }
    @FXML
    private void mouseClickedB7() { mouseClicked(Board.b7, B7); }
    @FXML
    private void mouseClickedB8() { mouseClicked(Board.b8, B8); }
    @FXML
    private void mouseClickedC1() { mouseClicked(Board.c1, C1); }
    @FXML
    private void mouseClickedC2() { mouseClicked(Board.c2, C2); }
    @FXML
    private void mouseClickedC3() { mouseClicked(Board.c3, C3); }
    @FXML
    private void mouseClickedC4() { mouseClicked(Board.c4, C4); }
    @FXML
    private void mouseClickedC5() { mouseClicked(Board.c5, C5); }
    @FXML
    private void mouseClickedC6() { mouseClicked(Board.c6, C6); }
    @FXML
    private void mouseClickedC7() { mouseClicked(Board.c7, C7); }
    @FXML
    private void mouseClickedC8() { mouseClicked(Board.c8, C8); }
    @FXML
    private void mouseClickedD1() { mouseClicked(Board.d1, D1); }
    @FXML
    private void mouseClickedD2() { mouseClicked(Board.d2, D2); }
    @FXML
    private void mouseClickedD3() { mouseClicked(Board.d3, D3); }
    @FXML
    private void mouseClickedD4() { mouseClicked(Board.d4, D4); }
    @FXML
    private void mouseClickedD5() { mouseClicked(Board.d5, D5); }
    @FXML
    private void mouseClickedD6() { mouseClicked(Board.d6, D6); }
    @FXML
    private void mouseClickedD7() { mouseClicked(Board.d7, D7); }
    @FXML
    private void mouseClickedD8() { mouseClicked(Board.d8, D8); }
    @FXML
    private void mouseClickedE1() { mouseClicked(Board.e1, E1); }
    @FXML
    private void mouseClickedE2() { mouseClicked(Board.e2, E2); }
    @FXML
    private void mouseClickedE3() { mouseClicked(Board.e3, E3); }
    @FXML
    private void mouseClickedE4() { mouseClicked(Board.e4, E4); }
    @FXML
    private void mouseClickedE5() { mouseClicked(Board.e5, E5); }
    @FXML
    private void mouseClickedE6() { mouseClicked(Board.e6, E6); }
    @FXML
    private void mouseClickedE7() { mouseClicked(Board.e7, E7); }
    @FXML
    private void mouseClickedE8() { mouseClicked(Board.e8, E8); }
    @FXML
    private void mouseClickedF1() { mouseClicked(Board.f1, F1); }
    @FXML
    private void mouseClickedF2() { mouseClicked(Board.f2, F2); }
    @FXML
    private void mouseClickedF3() { mouseClicked(Board.f3, F3); }
    @FXML
    private void mouseClickedF4() { mouseClicked(Board.f4, F4); }
    @FXML
    private void mouseClickedF5() { mouseClicked(Board.f5, F5); }
    @FXML
    private void mouseClickedF6() { mouseClicked(Board.f6, F6); }
    @FXML
    private void mouseClickedF7() { mouseClicked(Board.f7, F7); }
    @FXML
    private void mouseClickedF8() { mouseClicked(Board.f8, F8); }
    @FXML
    private void mouseClickedG1() { mouseClicked(Board.g1, G1); }
    @FXML
    private void mouseClickedG2() { mouseClicked(Board.g2, G2); }
    @FXML
    private void mouseClickedG3() { mouseClicked(Board.g3, G3); }
    @FXML
    private void mouseClickedG4() { mouseClicked(Board.g4, G4); }
    @FXML
    private void mouseClickedG5() { mouseClicked(Board.g5, G5); }
    @FXML
    private void mouseClickedG6() { mouseClicked(Board.g6, G6); }
    @FXML
    private void mouseClickedG7() { mouseClicked(Board.g7, G7); }
    @FXML
    private void mouseClickedG8() { mouseClicked(Board.g8, G8); }
    @FXML
    private void mouseClickedH1() { mouseClicked(Board.h1, H1); }
    @FXML
    private void mouseClickedH2() { mouseClicked(Board.h2, H2); }
    @FXML
    private void mouseClickedH3() { mouseClicked(Board.h3, H3); }
    @FXML
    private void mouseClickedH4() { mouseClicked(Board.h4, H4); }
    @FXML
    private void mouseClickedH5() { mouseClicked(Board.h5, H5); }
    @FXML
    private void mouseClickedH6() { mouseClicked(Board.h6, H6); }
    @FXML
    private void mouseClickedH7() { mouseClicked(Board.h7, H7); }
    @FXML
    private void mouseClickedH8() { mouseClicked(Board.h8, H8); }

    // Превращение пешки:
    private void chooseFigure(int num) {
        promotionWhite.setVisible(false);
        promotionBlack.setVisible(false);

        Game.move(pawnPromotion, fieldPromotion, num);
        show();

        if (Game.getResult() != GameResult.noResult)
            gameEnd();

        pawnPromotion = null;
        fieldPromotion = null;
    }

    @FXML
    private void chooseQueen() { chooseFigure(1); }
    @FXML
    private void chooseRook() {
        chooseFigure(2);
    }
    @FXML
    private void chooseBishop() {
        chooseFigure(3);
    }
    @FXML
    private void chooseKnight() {
        chooseFigure(4);
    }

    // Разворот доски:
    @FXML
    private void reverse1() {
        reverse1.setDisable(true);
        reverse1.setVisible(false);

        reverse2.setDisable(false);
        reverse2.setVisible(true);

        board.setRotate(90);

        Label[] labels = labelInit();
        ImageView[] imageViews = imageViewsInit();

        for (ImageView imageView: imageViews)
            imageView.setRotate(180);

        for (Label label: labels)
            label.setRotate(270);
    }

    @FXML
    private void reverse2() {
        reverse1.setDisable(false);
        reverse1.setVisible(true);

        reverse2.setDisable(true);
        reverse2.setVisible(false);

        board.setRotate(270);

        Label[] labels = labelInit();
        ImageView[] imageViews = imageViewsInit();

        for (ImageView imageView: imageViews)
                imageView.setRotate(0);

        for (Label label: labels)
                label.setRotate(90);
    }

    // Скрытие окна результата партии:
    @FXML
    private void hideWindow() {
        windowResult.setVisible(false);

        textResult.setText("");
        textCondition.setText("");
    }

//    @FXML
//    private void mouseDraggedA1() {
//        System.out.println("mouseDragged");
//    }

}
