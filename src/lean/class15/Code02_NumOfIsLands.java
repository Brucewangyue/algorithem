package lean.class15;

/**
 * 岛屿数量
 */
public class Code02_NumOfIsLands {
    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'0', '1', '0', '1', '1'},
                {'0', '0', '0', '1', '1'},
                {'0', '0', '0', '0', '0'},
                {'0', '0', '1', '1', '1'}};
        System.out.println(numOfIsLands(grid));
    }

    public static int numOfIsLands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        UnionFind unionFind = new UnionFind(grid);
        // 处理第一行，判断当前的左上是否1
        for (int y = 1; y < n; y++) {
            if (grid[0][y - 1] == '1' && grid[0][y] == '1') {  // 一直看左边是否跟自己是1，是就合并
                unionFind.union(0, y - 1, 0, y);
            }
        }

        // 处理第一列
        for (int x = 1; x < m; x++) {
            if (grid[x - 1][0] == '1' && grid[x][0] == '1') {  // 一直看上边是否跟自己是1，是就合并
                unionFind.union(x - 1, 0, x, 0);
            }
        }

        // 处理剩余
        for (int x = 1; x < m; x++) {
            for (int y = 1; y < n; y++) {
                if (grid[x][y] == '1') {
                    if (grid[x][y - 1] == '1') {  // 看左边
                        unionFind.union(x, y - 1, x, y);
                    }

                    if (grid[x - 1][y] == '1') {
                        unionFind.union(x - 1, y, x, y);
                    }
                }
            }
        }

        return unionFind.sets;
    }

    private static class UnionFind {
        int[] parent;
        int[] size;
        int[] help;
        int sets;
        int col;

        public UnionFind(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int N = m * n;
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            col = n;
            for (int x = 0; x < m; x++) {  // 遍历将所有为1的坐标作为一个并查集合
                for (int y = 0; y < n; y++) {
                    if (grid[x][y] == '1') {
                        int index = getIndex(x, y);
                        parent[index] = index;
                        size[index] = 1;
                        sets++;
                    }
                }
            }
        }

        /**
         * 将坐标映射为一维数组
         */
        public int getIndex(int x, int y) {
            return x * col + y;
        }

        public int find(int x, int y) {
            int e = getIndex(x, y);
            int curIndex = 0;
            while (e != parent[e]) {
                help[curIndex++] = e;  // 这里注意要先设置进临时数组，再替换
                e = parent[e];
            }

            for (int i = 0; i < curIndex; i++) {  // 这里的边界要注意，curIndex 已经++了，等于数组长度了
                parent[help[i]] = e;
            }

            return e;
        }

        public void union(int x1, int y1, int x2, int y2) {
            int aHead = find(x1, y1);
            int bHead = find(x2, y2);
            if (aHead != bHead) { // 合并
                if (size[aHead] >= size[bHead]) {
                    parent[bHead] = aHead;
                    size[aHead] += size[bHead];
                } else {
                    parent[aHead] = bHead;
                    size[bHead] += size[aHead];
                }
                sets--;
            }
        }
    }
}
