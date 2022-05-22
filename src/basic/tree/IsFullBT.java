package basic.tree;

/**
 * 判断满二叉树
 *
 * 满二叉树：
 * 树的高度是h，满二叉树的总节点数是2^h-1
 *
 * 合计所有左右子树的总节点数，最后根据公式判断是否正确
 */
public class IsFullBT {
    public static void main(String[] args) {
        Node head = new Node();
        System.out.println(isFullBT(head));
    }

    private static boolean isFullBT(Node head) {
        if(head==null)
            return true;

        Info info = process(head);
        return (1 << info.height) - 1 == info.nodes;
    }

    private static Info process(Node head) {
        if (head == null) {  // base case
            return new Info(0, 0);
        }

        Info lInfo = process(head.left);
        Info rInfo = process(head.right);

        int height = Math.max(lInfo.height, rInfo.height) + 1;  // 计算高度
        int subNodeCount = lInfo.nodes + rInfo.nodes + 1;  // 合计所有节点数

        return new Info(height, subNodeCount);
    }

    private static class Info {
        int height;
        int nodes;

        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }
}
