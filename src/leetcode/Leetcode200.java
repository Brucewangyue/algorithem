package leetcode;

// 岛屿问题
public class Leetcode200 {
    public static void main(String[] args) {
        int[][] grid = new int[4][5];
        grid[0] = new int[]{1, 1, 0, 1, 1};
        grid[1] = new int[]{1, 0, 0, 0, 0};
        grid[2] = new int[]{0, 0, 0, 0, 1};
        grid[3] = new int[]{1, 1, 0, 1, 1};

        System.out.println(numIslands(grid));
    }

    static int numIslands(int[][] grid){
        int res = 0;
        int m =grid.length,n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(isLand(grid[i][j])){ // 遇到陆地
                    res++;
                    downLand(grid,i,j);
                }
            }
        }

        return res;
    }

    static boolean isLand(int num){
        return num==1;
    }

    static void downLand(int[][] grid , int x, int y){
        int m =grid.length,n = grid[0].length;

        // 遇到边界,遇到海水 返回
        if(x<0 || y<0 || x>=m || y>=n || !isLand(grid[x][y]))
            return;

        grid[x][y] = 0;
        // 继续向外探索
        downLand(grid,x+1,y);
        downLand(grid,x-1,y);
        downLand(grid,x,y+1);
        downLand(grid,x,y-1);
    }
}
