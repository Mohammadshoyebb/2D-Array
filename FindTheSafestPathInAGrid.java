/*

2812. Find the Safest Path in a Grid
Medium
Topics
Companies
Hint
You are given a 0-indexed 2D matrix grid of size n x n, where (r, c) represents:

A cell containing a thief if grid[r][c] = 1
An empty cell if grid[r][c] = 0
You are initially positioned at cell (0, 0). In one move, you can move to any adjacent cell in the grid, including cells containing thieves.

The safeness factor of a path on the grid is defined as the minimum manhattan distance from any cell in the path to any thief in the grid.

Return the maximum safeness factor of all paths leading to cell (n - 1, n - 1).

An adjacent cell of cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) and (r - 1, c) if it exists.

The Manhattan distance between two cells (a, b) and (x, y) is equal to |a - x| + |b - y|, where |val| denotes the absolute value of val.

 

Example 1:


Input: grid = [[1,0,0],[0,0,0],[0,0,1]]
Output: 0
Explanation: All paths from (0, 0) to (n - 1, n - 1) go through the thieves in cells (0, 0) and (n - 1, n - 1).
Example 2:


Input: grid = [[0,0,1],[0,0,0],[0,0,0]]
Output: 2
Explanation: The path depicted in the picture above has a safeness factor of 2 since:
- The closest cell of the path to the thief at cell (0, 2) is cell (0, 0). The distance between them is | 0 - 0 | + | 0 - 2 | = 2.
It can be shown that there are no other paths with a higher safeness factor.
Example 3:


Input: grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
Output: 2
Explanation: The path depicted in the picture above has a safeness factor of 2 since:
- The closest cell of the path to the thief at cell (0, 3) is cell (1, 2). The distance between them is | 0 - 1 | + | 3 - 2 | = 2.
- The closest cell of the path to the thief at cell (3, 0) is cell (3, 2). The distance between them is | 3 - 3 | + | 0 - 2 | = 2.
It can be shown that there are no other paths with a higher safeness factor.
 

Constraints:

1 <= grid.length == n <= 400
grid[i].length == n
grid[i][j] is either 0 or 1.
There is at least one thief in the grid.
Seen this question in a real interview before?
1/5
Yes
No
Accepted
75.1K
Submissions
153.4K
Acceptance Rate
48.9%
Topics
Companies
Hint 1
Consider using both BFS and binary search together.
Hint 2
Launch a BFS starting from all the cells containing thieves to calculate d[x][y] which is the smallest Manhattan distance from (x, y) to the nearest grid that contains thieves.
Hint 3
To check if the bottom-right cell of the grid can be reached **through a path of safeness factor v**, eliminate all cells (x, y) such that grid[x][y] < v. if (0, 0) and (n - 1, n - 1) are still connected, there exists a path between (0, 0) and (n - 1, n - 1) of safeness factor v.
Hint 4
Binary search over the final safeness factor v.

*/

//============================================================================SOLUTION=============================================================================================

import java.util.*;

public class FindTheSafestPathInAGrid {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) return 0;
        int[][] cost = new int[n][n];
        for (var v : cost) Arrays.fill(v, Integer.MAX_VALUE);
        bfs(cost, grid, n);
        int l = 1, r = n * n;
        int ans = 0;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            if (possible(0, 0, cost, mid, n, new boolean[n][n])) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public boolean possible(int i, int j, int[][] cost, int mid, int n, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= n || j >= n) return false;
        if (cost[i][j] == Integer.MAX_VALUE || cost[i][j] < mid) return false;
        if (i == n - 1 && j == n - 1) return true;
        if (visited[i][j]) return false;
        visited[i][j] = true;
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        boolean ans = false;
        for (var v : dir) {
            int ii = i + v[0];
            int jj = j + v[1];
            ans |= possible(ii, jj, cost, mid, n, visited);
            if (ans) return true;
        }
        return ans;
    }

    public void bfs(int[][] cost, List<List<Integer>> grid, int n) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.size(); j++) {
                if (grid.get(i).get(j) == 1) {
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        int level = 1;
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                var v = q.poll();
                var temp = v;
                for (var val : dir) {
                    int ii = temp[0] + val[0];
                    int jj = temp[1] + val[1];
                    if (isValid(ii, jj, n) && !visited[ii][jj]) {
                        q.add(new int[]{ii, jj});
                        cost[ii][jj] = Math.min(cost[ii][jj], level);
                        visited[ii][jj] = true;
                    }
                }
            }
            level++;
        }
    }

    public boolean isValid(int i, int j, int n) {
        return (i >= 0 && j >= 0 && i < n && j < n);
    }

    public static void main(String[] args) {
        FindTheSafestPathInAGrid solution = new FindTheSafestPathInAGrid();

        List<List<Integer>> grid1 = new ArrayList<>(Arrays.asList(
                Arrays.asList(1, 1, 1),
                Arrays.asList(0, 1, 1),
                Arrays.asList(0, 0, 0)
        ));

        List<List<Integer>> grid2 = new ArrayList<>(Arrays.asList(
                Arrays.asList(1, 0, 0),
                Arrays.asList(0, 1, 0),
                Arrays.asList(0, 0, 1)
        ));

        List<List<Integer>> grid3 = new ArrayList<>(Arrays.asList(
                Arrays.asList(0, 0, 0, 1),
                Arrays.asList(0, 1, 0, 0),
                Arrays.asList(0, 0, 1, 0),
                Arrays.asList(1, 0, 0, 0)
        ));

        System.out.println(solution.maximumSafenessFactor(grid1));  // Output: 0
        System.out.println(solution.maximumSafenessFactor(grid2));  // Output: 0
        System.out.println(solution.maximumSafenessFactor(grid3));  // Output: 2
    }
}

