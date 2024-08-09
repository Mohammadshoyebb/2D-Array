// 840. Magic Squares In Grid
// A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 
// such that each row, column, and both diagonals all have the same sum.
// Given a row x col grid of integers, how many 3 x 3 contiguous magic square subgrids are there?

// Example 1:
// Input: grid = [[4,3,8,4],[9,5,1,9],[2,7,6,2]]
// Output: 1
// Explanation: 
// The following subgrid is a 3 x 3 magic square:
// 4 3 8
// 9 5 1
// 2 7 6
// while this one is not:
// 3 8 4
// 5 1 9
// 7 6 2
// In total, there is only one magic square inside the given grid.

// Example 2:
// Input: grid = [[8]]
// Output: 0

// Constraints:
// row == grid.length
// col == grid[i].length
// 1 <= row, col <= 10
// 0 <= grid[i][j] <= 15

import java.util.*;

public class MagicSquaresInGrid {

    public int numMagicSquaresInside(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length - 2; i++) {
            for (int j = 0; j < grid[0].length - 2; j++) {
                if (isMagic(grid, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isMagic(int[][] grid, int row, int col) {
        int[] digits = new int[10];
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                int num = grid[i][j];
                if (num < 1 || num > 9 || digits[num] == 1) {
                    return false;
                }
                digits[num] = 1;
            }
        }

        int sum = grid[row][col] + grid[row][col + 1] + grid[row][col + 2];
        for (int i = 0; i < 3; i++) {
            if (grid[row + i][col] + grid[row + i][col + 1] + grid[row + i][col + 2] != sum) {
                return false;
            }
            if (grid[row][col + i] + grid[row + 1][col + i] + grid[row + 2][col + i] != sum) {
                return false;
            }
        }

        if (grid[row][col] + grid[row + 1][col + 1] + grid[row + 2][col + 2] != sum) {
            return false;
        }
        if (grid[row][col + 2] + grid[row + 1][col + 1] + grid[row + 2][col] != sum) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        MagicSquaresInGrid solution = new MagicSquaresInGrid();

        int[][] grid1 = {
            {4, 3, 8, 4},
            {9, 5, 1, 9},
            {2, 7, 6, 2}
        };
        System.out.println("Number of magic squares: " + solution.numMagicSquaresInside(grid1)); // Output: 1

        int[][] grid2 = {
            {8}
        };
        System.out.println("Number of magic squares: " + solution.numMagicSquaresInside(grid2)); // Output: 0
    }
}
