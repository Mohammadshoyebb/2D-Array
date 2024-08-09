// 885. Spiral Matrix III
// You start at the cell (rStart, cStart) of an rows x cols grid facing east. 
// The northwest corner is at the first row and column in the grid, and the southeast corner is at the last row and column.
// You will walk in a clockwise spiral shape to visit every position in this grid. 
// Whenever you move outside the grid's boundary, we continue our walk outside the grid (but may return to the grid boundary later.). 
// Eventually, we reach all rows * cols spaces of the grid.
// Return an array of coordinates representing the positions of the grid in the order you visited them.

// Example 1:
// Input: rows = 1, cols = 4, rStart = 0, cStart = 0
// Output: [[0,0],[0,1],[0,2],[0,3]]

// Example 2:
// Input: rows = 5, cols = 6, rStart = 1, cStart = 4
// Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]

// Constraints:
// 1 <= rows, cols <= 100
// 0 <= rStart < rows
// 0 <= cStart < cols

import java.util.*;

public class SpiralMatrixIII {

    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] result = new int[rows * cols][2];
        int index = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // East, South, West, North
        int steps = 1;
        int dir = 0;
        int r = rStart, c = cStart;

        result[index++] = new int[]{r, c};

        while (index < rows * cols) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < steps; j++) {
                    r += directions[dir][0];
                    c += directions[dir][1];
                    if (r >= 0 && r < rows && c >= 0 && c < cols) {
                        result[index++] = new int[]{r, c};
                    }
                }
                dir = (dir + 1) % 4;
            }
            steps++;
        }

        return result;
    }

    public static void main(String[] args) {
        SpiralMatrixIII solution = new SpiralMatrixIII();
        
        int rows1 = 1, cols1 = 4, rStart1 = 0, cStart1 = 0;
        int[][] result1 = solution.spiralMatrixIII(rows1, cols1, rStart1, cStart1);
        System.out.println("Output for Example 1:");
        for (int[] coord : result1) {
            System.out.println(Arrays.toString(coord));
        }

        int rows2 = 5, cols2 = 6, rStart2 = 1, cStart2 = 4;
        int[][] result2 = solution.spiralMatrixIII(rows2, cols2, rStart2, cStart2);
        System.out.println("Output for Example 2:");
        for (int[] coord : result2) {
            System.out.println(Arrays.toString(coord));
        }
    }
}

