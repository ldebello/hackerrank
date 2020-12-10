package generic.spiral_order;

import org.junit.jupiter.api.Test;

import static generic.spiral_order.Solution.spiralOrder;

public class Tests {

    @Test
    public void squareMatrix() {
        int[][] matrix = new int[3][3];

        matrix[0][0] = 1;
        matrix[0][1] = 2;
        matrix[0][2] = 3;

        matrix[1][0] = 4;
        matrix[1][1] = 5;
        matrix[1][2] = 6;

        matrix[2][0] = 7;
        matrix[2][1] = 8;
        matrix[2][2] = 9;

        System.out.println(spiralOrder(matrix));
    }

    @Test
    public void multipleRowsSingleColumn() {
        int[][] matrix = new int[3][1];

        matrix[0][0] = 1;
        matrix[1][0] = 2;
        matrix[2][0] = 3;

        System.out.println(spiralOrder(matrix));
    }

    @Test
    public void singleRowMultipleColumns() {
        int[][] matrix = new int[1][3];

        matrix[0][0] = 1;
        matrix[0][1] = 2;
        matrix[0][2] = 3;

        System.out.println(spiralOrder(matrix));
    }

    @Test
    public void threeRowsTwoColumns() {
        int[][] matrix = new int[3][2];

        matrix[0][0] = 1;
        matrix[1][0] = 2;
        matrix[2][0] = 3;

        matrix[0][1] = 4;
        matrix[1][1] = 5;
        matrix[2][1] = 6;

        System.out.println(spiralOrder(matrix));
    }

    @Test
    public void twoRowsThreeColumns() {
        int[][] matrix = new int[2][3];

        matrix[0][0] = 1;
        matrix[0][1] = 2;
        matrix[0][2] = 3;

        matrix[1][0] = 4;
        matrix[1][1] = 5;
        matrix[1][2] = 6;

        System.out.println(spiralOrder(matrix));
    }

}
