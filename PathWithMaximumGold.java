/*
 * 1219. Path with Maximum Gold
Solved
Medium
Topics
Companies
Hint
In a gold mine grid of size m x n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.

Return the maximum amount of gold you can collect under the conditions:

Every time you are located in a cell you will collect all the gold in that cell.
From your position, you can walk one step to the left, right, up, or down.
You can't visit the same cell more than once.
Never visit a cell with 0 gold.
You can start and stop collecting gold from any position in the grid that has some gold.
 

Example 1:

Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
Output: 24
Explanation:
[[0,6,0],
 [5,8,7],
 [0,9,0]]
Path to get the maximum gold, 9 -> 8 -> 7.
Example 2:

Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
Output: 28
Explanation:
[[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 15
0 <= grid[i][j] <= 100
There are at most 25 cells containing gold.
 */


 //==================================================================SOLUTION==================================================================
 
public class PathWithMaximumGold {

    /**
     * Main method to test the solution.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int[][] grid1 = {
            {0, 6, 0},
            {5, 8, 7},
            {0, 9, 0}
        };
        System.out.println("Output for example 1: " + getMaximumGold(grid1)); // Output: 24

        int[][] grid2 = {
            {1, 0, 7},
            {2, 0, 6},
            {3, 4, 5},
            {0, 3, 0},
            {9, 0, 20}
        };
        System.out.println("Output for example 2: " + getMaximumGold(grid2)); // Output: 28
    }

    /**
     * Method to find the maximum amount of gold that can be collected from the grid.
     * @param grid The 2D array representing the grid with gold amounts.
     * @return The maximum amount of gold that can be collected.
     */
    public static int getMaximumGold(int[][] grid) {
        int maxGold = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Calculate total gold in the grid
        int totalGold = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                totalGold += grid[i][j];
            }
        }

        // Iterate through each cell to find maximum gold
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Start DFS from cell with gold
                if (grid[i][j] != 0) {
                    maxGold = Math.max(maxGold, dfs(grid, i, j));

                    // If maximum gold equals total gold, no need to continue
                    if(maxGold == totalGold){
                        return maxGold;
                    }
                }
            }
        }
        
        return maxGold;
    }
    
    /**
     * Depth-first search to explore all possible paths and collect gold.
     * @param grid The 2D array representing the grid with gold amounts.
     * @param row The current row index.
     * @param col The current column index.
     * @return The maximum amount of gold that can be collected starting from the current cell.
     */
    public static int dfs(int[][] grid, int row, int col) {
        // Base cases: out of bound or cell has no gold
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0) {
            return 0;
        }
        
        int currentGold = grid[row][col];
        grid[row][col] = 0; // Mark the current cell as visited
        
        // Explore all possible directions (up, down, left, right)
        int maxGold = 0;
        maxGold = Math.max(maxGold, currentGold + dfs(grid, row - 1, col)); // Up
        maxGold = Math.max(maxGold, currentGold + dfs(grid, row + 1, col)); // Down
        maxGold = Math.max(maxGold, currentGold + dfs(grid, row, col - 1)); // Left
        maxGold = Math.max(maxGold, currentGold + dfs(grid, row, col + 1)); // Right
        
        grid[row][col] = currentGold; // Restore the cell's original value
        return maxGold;
    }
}
