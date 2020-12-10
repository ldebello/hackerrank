package generic.spiral_order;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Solution {

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        int numberOfLevels = (int) Math.ceil(Math.min(matrix.length, matrix[0].length) / 2d);

        for (int i = 0; i < numberOfLevels; i++) {
            iterate(matrix, i, result::add);
        }

        return result;
    }

    private static void iterate(int[][] matrix, int level, Consumer<Integer> consumer) {
        leftToRight(matrix, level, consumer);
        upToDown(matrix, level, consumer);
        rightToLeft(matrix, level, consumer);
        downToUp(matrix, level, consumer);
    }

    private static void leftToRight(int[][] matrix, int level, Consumer<Integer> consumer) {
        int row = level;

        for (int column = level; column < matrix[row].length - level; column++) {
            consumer.accept(matrix[row][column]);
        }
    }

    private static void upToDown(int[][] matrix, int level, Consumer<Integer> consumer) {
        int lastColumnPerLevel = matrix[level].length - level - 1;

        for (int row = level + 1; row < matrix.length - level; row++) {
            consumer.accept(matrix[row][lastColumnPerLevel]);
        }
    }

    private static void rightToLeft(int[][] matrix, int level, Consumer<Integer> consumer) {
        int rowPerLevel = matrix.length - level - 1;
        int firstColumnPerLevel = level;
        int lastColumnPerLevel = matrix[level].length - 2 - level;

        if (rowPerLevel > level) {
            for (int column = lastColumnPerLevel; column >= firstColumnPerLevel; column--) {
                consumer.accept(matrix[rowPerLevel][column]);
            }
        }
    }

    private static void downToUp(int[][] matrix, int level, Consumer<Integer> consumer) {
        int columnPerLevel = level;
        int bottomRowPerLevel = matrix.length - 2 - level;
        int topRowPerLevel = level + 1;

        if (matrix[level].length > level + 1) {
            for (int row = bottomRowPerLevel; row >= topRowPerLevel; row--) {
                consumer.accept(matrix[row][columnPerLevel]);
            }
        }
    }
}
