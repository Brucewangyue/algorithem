package leetcode;

public class Leetcode62 {
    public static void main(String[] args) {
        int m = 3;
        int n = 7;
        System.out.println(pathCount(m, n));
    }

    static int pathCount(int m, int n) {
        int[][] grid = new int[m][n];
        // 设置边界路径值
        // 两条边路径的每个格子只有一次经过机会
        for (int x = 0; x < m; x++)
            grid[x][0] = 1;

        for (int y = 0; y < n; y++)
            grid[0][y] = 1;

        /**
         * [1, 1, 1, 1, 1, 1, 1] y
         * [1, 0, 0, 0, 0, 0, 0]
         * [1, 0, 0, 0, 0, 0, 0]
         * x
         */

        for (int x = 1; x < m; x++) {
            for (int y = 1; y < n; y++) {
                // 从左下到右下来计算最后格子路径
                grid[x][y] = grid[x][y - 1] + grid[x - 1][y];
            }
        }

        return grid[m-1][n-1];
    }
}
