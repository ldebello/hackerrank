package generic.spiral_order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static generic.spiral_order.Solution.spiralOrder;
import static org.junit.jupiter.api.Assertions.*;

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

        List<Integer> numbers = spiralOrder(matrix);

        Integer[] expected = {1, 2, 3, 6, 9, 8, 7, 4, 5};
        Integer[] spiralNumbers = numbers.toArray(new Integer[numbers.size()]);
        assertArrayEquals(expected, spiralNumbers);
    }

    @Test
    public void singleElement() {
        int[][] matrix = new int[1][1];

        matrix[0][0] = 1;

        List<Integer> numbers = spiralOrder(matrix);

        Integer[] expected = {1};
        Integer[] spiralNumbers = numbers.toArray(new Integer[numbers.size()]);
        assertArrayEquals(expected, spiralNumbers);
    }

    @Test
    public void multipleRowsSingleColumn() {
        int[][] matrix = new int[3][1];

        matrix[0][0] = 1;
        matrix[1][0] = 2;
        matrix[2][0] = 3;

        List<Integer> numbers = spiralOrder(matrix);

        Integer[] expected = {1, 2, 3};
        Integer[] spiralNumbers = numbers.toArray(new Integer[numbers.size()]);
        assertArrayEquals(expected, spiralNumbers);
    }

    @Test
    public void singleRowMultipleColumns() {
        int[][] matrix = new int[1][3];

        matrix[0][0] = 1;
        matrix[0][1] = 2;
        matrix[0][2] = 3;

        List<Integer> numbers = spiralOrder(matrix);

        Integer[] expected = {1, 2, 3};
        Integer[] spiralNumbers = numbers.toArray(new Integer[numbers.size()]);
        assertArrayEquals(expected, spiralNumbers);
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

        List<Integer> numbers = spiralOrder(matrix);

        Integer[] expected = {1, 4, 5, 6, 3, 2};
        Integer[] spiralNumbers = numbers.toArray(new Integer[numbers.size()]);
        assertArrayEquals(expected, spiralNumbers);
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

        List<Integer> numbers = spiralOrder(matrix);

        Integer[] expected = {1, 2, 3, 6, 5, 4};
        Integer[] spiralNumbers = numbers.toArray(new Integer[numbers.size()]);
        assertArrayEquals(expected, spiralNumbers);
    }

    @Test
    @Disabled
    public void oddNumberOfRowsEvenNumberOfColumns() {
        int[][] matrix = new int[5][4];

        matrix[0][0] = 1;
        matrix[1][0] = 2;
        matrix[2][0] = 3;
        matrix[3][0] = 4;
        matrix[4][0] = 5;

        matrix[0][1] = 9;
        matrix[1][1] = 8;
        matrix[2][1] = 7;
        matrix[3][1] = 6;
        matrix[4][1] = 5;

        matrix[0][2] = -1;
        matrix[1][2] = -2;
        matrix[2][2] = -3;
        matrix[3][2] = -4;
        matrix[4][2] = -5;

        matrix[0][3] = -9;
        matrix[1][3] = -8;
        matrix[2][3] = -7;
        matrix[3][3] = -6;
        matrix[4][3] = -5;

        List<Integer> numbers = spiralOrder(matrix);

        Integer[] expected = {1, 9, -1, -9, -8, -7, -6, -5, -5, 5 , 5, 4, 3, 2, 8, -2, -3, -4, 6, 7};
        Integer[] spiralNumbers = numbers.toArray(new Integer[numbers.size()]);

        assertArrayEquals(expected, spiralNumbers);
    }

}
