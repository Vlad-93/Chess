package board;

import figurs.Figure;

import static board.Color.black;
import static board.Color.white;
import static board.File.*;

public class Board {

    public static class Field {
        private final Color color;
        private final File file;
        private final int range; // 1 - 8
        private Figure figure;

        public Color getColor() {
            return color;
        }

        public File getFile() {
            return file;
        }

        public int getRange() {
            return range;
        }

        public Figure getFigure() {
            return figure;
        }

        public void setFigure(Figure figure) {
            this.figure = figure;
        }

        @Override
        public String toString() {
            return file + "" + range;
        }

        public Field(Color color, File file, int range) {
            this.color = color;
            this.file = file;
            this.range = range;
        }
    }

    //Поля доски:
   public static final Field a1 = new Field(black, a, 1);
   public static final Field a2 = new Field(white, a, 2);
   public static final Field a3 = new Field(black, a, 3);
   public static final Field a4 = new Field(white, a, 4);
   public static final Field a5 = new Field(black, a, 5);
   public static final Field a6 = new Field(white, a, 6);
   public static final Field a7 = new Field(black, a, 7);
   public static final Field a8 = new Field(white, a, 8);

   public static final Field b1 = new Field(white, b, 1);
   public static final Field b2 = new Field(black, b, 2);
   public static final Field b3 = new Field(white, b, 3);
   public static final Field b4 = new Field(black, b, 4);
   public static final Field b5 = new Field(white, b, 5);
   public static final Field b6 = new Field(black, b, 6);
   public static final Field b7 = new Field(white, b, 7);
   public static final Field b8 = new Field(black, b, 8);

   public static final Field c1 = new Field(black, c, 1);
   public static final Field c2 = new Field(white, c, 2);
   public static final Field c3 = new Field(black, c, 3);
   public static final Field c4 = new Field(white, c, 4);
   public static final Field c5 = new Field(black, c, 5);
   public static final Field c6 = new Field(white, c, 6);
   public static final Field c7 = new Field(black, c, 7);
   public static final Field c8 = new Field(white, c, 8);

   public static final Field d1 = new Field(white, d, 1);
   public static final Field d2 = new Field(black, d, 2);
   public static final Field d3 = new Field(white, d, 3);
   public static final Field d4 = new Field(black, d, 4);
   public static final Field d5 = new Field(white, d, 5);
   public static final Field d6 = new Field(black, d, 6);
   public static final Field d7 = new Field(white, d, 7);
   public static final Field d8 = new Field(black, d, 8);

   public static final Field e1 = new Field(black, e, 1);
   public static final Field e2 = new Field(white, e, 2);
   public static final Field e3 = new Field(black, e, 3);
   public static final Field e4 = new Field(white, e, 4);
   public static final Field e5 = new Field(black, e, 5);
   public static final Field e6 = new Field(white, e, 6);
   public static final Field e7 = new Field(black, e, 7);
   public static final Field e8 = new Field(white, e, 8);

   public static final Field f1 = new Field(white, f, 1);
   public static final Field f2 = new Field(black, f, 2);
   public static final Field f3 = new Field(white, f, 3);
   public static final Field f4 = new Field(black, f, 4);
   public static final Field f5 = new Field(white, f, 5);
   public static final Field f6 = new Field(black, f, 6);
   public static final Field f7 = new Field(white, f, 7);
   public static final Field f8 = new Field(black, f, 8);

   public static final Field g1 = new Field(black, g, 1);
   public static final Field g2 = new Field(white, g, 2);
   public static final Field g3 = new Field(black, g, 3);
   public static final Field g4 = new Field(white, g, 4);
   public static final Field g5 = new Field(black, g, 5);
   public static final Field g6 = new Field(white, g, 6);
   public static final Field g7 = new Field(black, g, 7);
   public static final Field g8 = new Field(white, g, 8);

   public static final Field h1 = new Field(white, h, 1);
   public static final Field h2 = new Field(black, h, 2);
   public static final Field h3 = new Field(white, h, 3);
   public static final Field h4 = new Field(black, h, 4);
   public static final Field h5 = new Field(white, h, 5);
   public static final Field h6 = new Field(black, h, 6);
   public static final Field h7 = new Field(white, h, 7);
   public static final Field h8 = new Field(black, h, 8);

    // Все поля доски:
   public static final Field[] fields = {a8, b8, c8, d8, e8, f8, g8, h8,
                                         a7, b7, c7, d7, e7, f7, g7, h7,
                                         a6, b6, c6, d6, e6, f6, g6, h6,
                                         a5, b5, c5, d5, e5, f5, g5, h5,
                                         a4, b4, c4, d4, e4, f4, g4, h4,
                                         a3, b3, c3, d3, e3, f3, g3, h3,
                                         a2, b2, c2, d2, e2, f2, g2, h2,
                                         a1, b1, c1, d1, e1, f1, g1, h1};
    // Вертикали:
   public static final Field[] fileLineA = {a1, a2, a3, a4, a5, a6, a7, a8};
   public static final Field[] fileLineB = {b1, b2, b3, b4, b5, b6, b7, b8};
   public static final Field[] fileLineC = {c1, c2, c3, c4, c5, c6, c7, c8};
   public static final Field[] fileLineD = {d1, d2, d3, d4, d5, d6, d7, d8};
   public static final Field[] fileLineE = {e1, e2, e3, e4, e5, e6, e7, e8};
   public static final Field[] fileLineF = {f1, f2, f3, f4, f5, f6, f7, f8};
   public static final Field[] fileLineG = {g1, g2, g3, g4, g5, g6, g7, g8};
   public static final Field[] fileLineH = {h1, h2, h3, h4, h5, h6, h7, h8};
   public static final Field[][] fileLines = {fileLineA, fileLineB, fileLineC, fileLineD, fileLineE, fileLineF, fileLineG, fileLineH};
    // Горизонтали:
   public static final Field[] rangeLine1 = {a1, b1, c1, d1, e1, f1, g1, h1};
   public static final Field[] rangeLine2 = {a2, b2, c2, d2, e2, f2, g2, h2};
   public static final Field[] rangeLine3 = {a3, b3, c3, d3, e3, f3, g3, h3};
   public static final Field[] rangeLine4 = {a4, b4, c4, d4, e4, f4, g4, h4};
   public static final Field[] rangeLine5 = {a5, b5, c5, d5, e5, f5, g5, h5};
   public static final Field[] rangeLine6 = {a6, b6, c6, d6, e6, f6, g6, h6};
   public static final Field[] rangeLine7 = {a7, b7, c7, d7, e7, f7, g7, h7};
   public static final Field[] rangeLine8 = {a8, b8, c8, d8, e8, f8, g8, h8};
   public static final Field[][] rangeLines = {rangeLine8, rangeLine7, rangeLine6, rangeLine5, rangeLine4, rangeLine3, rangeLine2, rangeLine1};

    //Поиск поля по координатам:
   public static Field getField(File file, int range) {
       for (Field field : fields)
           if (field.file == file && field.range == range)
               return field;
       return null;
   }
   public static Field getField(int fileValue, int range) {
       for (Field field : fields)
           if (field.file.getValue() == fileValue && field.range == range)
               return field;
       return null;
   }

    //Поиск вертикали по полю:
    public static Field[] getFileLine(Field field) {
        for (Field[] fileLine: fileLines)
            if (fileLine[0].getFile() == field.getFile())
                return fileLine;
        return null;
    }
    //Поиск горизонтали по полю:
    public static Field[] getRangeLine(Field field) {
        for (Field[] rangeLine: rangeLines)
            if (rangeLine[0].getRange() == field.getRange())
                return rangeLine;
        return null;
    }

    //Поиск вертикали по значению:
    public static Field[] getFileLine(File file) {
        for (Field[] fileLine: fileLines)
            if (fileLine[0].getFile() == file)
                return fileLine;
        return null;
    }
    public static Field[] getFileLine(int file) {
        for (Field[] fileLine: fileLines)
            if (fileLine[0].getFile().getValue() == file)
                return fileLine;
        return null;
    }
    //Поиск горизонтали по значению:
    public static Field[] getRangeLine(int range) {
        for (Field[] rangeLine: rangeLines)
            if (rangeLine[0].getRange() == range)
                return rangeLine;
        return null;
    }

//    public static void show() {                       // Отображение доски в консоле
//        for (Field[] range: Board.rangeLines) {
//            for (Field field: range) {
//                if (field.getFigure() == null)
//                    System.out.print("  ");
//                else
//                    System.out.print(field.getFigure());
//            }
//            System.out.println();
//        }
//    }

    private Board() {}
    public static void init() {}
}