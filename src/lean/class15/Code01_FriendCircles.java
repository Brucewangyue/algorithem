package lean.class15;

/**
 * leetcode 547   并查集
 * 提示
 * M.length == M[0].length
 */
public class Code01_FriendCircles {
    public static void main(String[] args) {
        int[][] grid = new int[5][5];

        System.out.println(findFriendCircle(grid));
    }

    public static int findFriendCircle(int[][] M) {
        int m = M.length;
        int n = M[0].length;

        UnionFind unionFind = new UnionFind(m);
        for (int i = 0; i < m; i++) {  // 遍历i去和所有的j是否是认识的（==1）
            for (int j = i+1; j < n; j++) { // 只循环右半部分的数据，因为对角线两边的数据一样
                if (M[i][j] == 1) {  // i 和 j 认识
                    unionFind.union(i, j);
                }
            }
        }

        return unionFind.sets;
    }

    /**
     * 一开始n个人表示为n个集合，也就是5个人一开始是完全不认识的5个朋友圈
     * 每次循环遇到一个 M[i][j] == 1 表示 i和j是认识的，应该组成朋友圈，那么就用
     */
    private static class UnionFind {
        //        int[] data;
        int[] parents;  // 下标和父元素的映射
        int[] sizes;  // 头元素会记录集合大小
        int[] help;  // 记录查找祖先时所有沿途的元素，用来替代栈
        int sets; // 一共有几个集合

        public UnionFind(int N) {
//            data = new int[N];
            parents = new int[N];
            sizes = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
//                data[i] = i;  // 原始数据
                parents[i] = i;  // 数据的父亲是自己
                sizes[i] = 1;  // 数据集的大小
            }
        }

        /**
         * 找到指定元素的祖先、沿途元素指向祖先
         */
        public int findHead(int e) {
            int curIndex = 0;
            while (e != parents[e]) {  // 向上找出沿途所有元素
                help[curIndex++] = e;
                e = parents[e];
            }

            // e 最终是祖先元素的索引
            for (int j = 0; j < curIndex + 1; j++) {
                parents[help[j]] = e;
            }

            return e;
        }

        public void union(int a, int b) {
            int aHead = findHead(a);
            int bHead = findHead(b);
            if (aHead != bHead) { // 祖先不同就合并
                if (sizes[aHead] >= sizes[bHead]) { // aHead 是大集合
                    parents[bHead] = aHead;  // 替换祖先
                    sizes[aHead] += sizes[bHead];  // 大集合数量增加
                } else {  // bHead 是大集合
                    parents[aHead] = bHead;
                    sizes[bHead] += sizes[aHead];
                }
//                sizes[small] = 0;
                sets--;  // 每做一次集合合并，集合总数量就减少1
            }
        }
    }
}
