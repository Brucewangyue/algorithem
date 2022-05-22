package basic.tree;

/**
 * 求树中最大的二叉搜索树子树 的 节点个数
 * <p>
 * 二叉搜索树:
 * 左孩子整体需要小于等于当前节点、右孩子整体需要大于等于当前节点
 */
public class MaxSubBSTSize {
    public static void main(String[] args) {
        TreeNode head = new TreeNode();
        System.out.println(maxSubBSTSize(head));
    }

    private static int maxSubBSTSize(TreeNode head) {
        if (head == null)
            return 0;

        return process(head).maxSubBSTSize;
    }

    /**
     * 原理就是后续遍历
     * 在树上左动态规划，树形DP
     * O(N),每个节点处理一次
     */
    private static Info process(TreeNode head) {
        if (head == null)
            return null;

        Info lInfo = process(head.left);
        Info rInfo = process(head.right);

        int max = head.val;  // 处理最大值
        int min = head.val;  // 处理最小值
        if (lInfo != null) {
            max = Math.max(head.val, lInfo.max);
            min = Math.min(head.val, lInfo.min);
        }
        if (rInfo != null) {
            max = Math.max(head.val, rInfo.max);
            min = Math.min(head.val, rInfo.min);
        }

        boolean isBST = true;  // 处理是否是二叉搜索树
        if ((lInfo != null && !lInfo.isBST) || (rInfo != null && !rInfo.isBST)) // 如果左右孩子任一一个不是搜索二叉树，那么x就不是搜索二叉树
            isBST = false;

        // 还得判断左右孩子大小
        boolean leftMaxLeesX = lInfo == null || (lInfo.max <= head.val);
        boolean rightMinGreaterX = rInfo == null || (rInfo.min >= head.val);
        if (!leftMaxLeesX || !rightMinGreaterX)  // 任一一边不满足，则x不是搜索二叉树
            isBST = false;

        int leftMaxSubSBTSize = lInfo == null ? 0 : lInfo.maxSubBSTSize;
        int rightMaxSubSBTSize = rInfo == null ? 0 : rInfo.maxSubBSTSize;
        int curMaxSubBSTSize = !isBST ? 0 : leftMaxSubSBTSize + rightMaxSubSBTSize + 1;

        return new Info(Math.max(curMaxSubBSTSize, Math.max(leftMaxSubSBTSize, rightMaxSubSBTSize)), isBST, max, min);
    }

    private static class Info {
        int maxSubBSTSize;  // 最大搜索二叉子树
        boolean isBST;
        int max;  // 左树最大值
        int min;  // 右树最大值

        public Info(int nodes, boolean isBST, int max, int min) {
            this.maxSubBSTSize = nodes;
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }
}
