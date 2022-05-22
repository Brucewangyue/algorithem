package basic.tree;

/**
 * 求整棵树，任意一个节点间的最大距离
 */
public class MaxDistance {
    public static void main(String[] args) {
        Node head = generateBT();
        System.out.println(process(head).maxDistance);
    }

    static Info process(Node head) {
        if (head == null) { //base case
            return new Info(0, 0);
        }

        Info lInfo = process(head.left);  // 该方法告知父节点：我左子树的高度和所有节点中最大节点的距离
        Info rInfo = process(head.right);   // 告知右节点信息

        int maxDistance = Math.max(Math.max(lInfo.maxDistance, rInfo.maxDistance), lInfo.height + rInfo.height + 1);  // 当前节点的左右子树总高度+1  对比  左右子树中节点最多的一颗
        int height = Math.max(lInfo.height, rInfo.height) + 1;

        return new Info(height, maxDistance);  // 当前节点统计完自己的最大高度和节点间最大距离，向上告知父节点
    }

    private static class Info {
        int height;  // 当前节点的所在高度
        int maxDistance;  // 当前节点所拥有的最大距离

        public Info(int height, int distance) {
            this.height = height;
            this.maxDistance = distance;
        }
    }

    private static Node generateBT() {
        Node head = new Node();

        head.left = new Node();

        head.left.left = new Node();
        head.left.right = new Node();

        head.left.left.left = new Node();

        head.left.left.left.left = new Node();
        head.left.left.left.right = new Node();

        head.left.left.left.left.left = new Node();
        head.left.left.left.right.right = new Node();

        head.left.left.left.right.right.right = new Node();

        head.left.left.left.right.right.right.right = new Node();

        return head;
    }
}
