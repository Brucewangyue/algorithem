package basic.tree;

/**
 * 最近公共祖先：给定一个头节点head，求a,b两个节点的最近公共祖先
 *
 *
 */
public class shortPublicParentNode {
    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        TreeNode a = new TreeNode(8);
        TreeNode b = new TreeNode(4);

        head.left = new TreeNode(2);

        head.left.left = new TreeNode(3);
        head.left.right = b;

        head.left.left.left = new TreeNode(5);

        head.left.left.left.left = new TreeNode(6);
        head.left.left.left.right = new TreeNode(7);

        head.left.left.left.left.left = a;
        head.left.left.left.right.right = new TreeNode(9);

        head.left.left.left.right.right.right = new TreeNode(10);

        head.left.left.left.right.right.right.right =  new TreeNode(11);

        System.out.println(getFirstParentNode(head,a,b).val);
    }

    private static TreeNode getFirstParentNode(TreeNode node,TreeNode a,TreeNode b){
        if(node == null)
            return node;

        return process(node,a,b).parent;
    }

    private static Info process(TreeNode node,TreeNode a,TreeNode b){
        if(node==null)
            return new Info(false,false,null);

        Info l = process(node.left,a,b);
        Info r = process(node.right,a,b);

        if(l.parent!=null || r.parent!=null)  // 在底层已经找到了最近公共节点，直接向上反
            return new Info(false,false,l.parent!=null?l.parent:r.parent);

        boolean isA = l.isA || r.isA || node==a;  // 其中一个是A就是A
        boolean isB = l.isB || r.isB || node==b;  // 其中一个是B就是B

        if(isA && isB) // 在当前x节点就是最近公共祖先
            return new Info(false,false,node);

        return new Info(isA,isB,null);
    }

    public static class Info{
        boolean isA;
        boolean isB;
        TreeNode parent;

        public Info(boolean isA, boolean isB, TreeNode parent) {
            this.isA = isA;
            this.isB = isB;
            this.parent = parent;
        }
    }
}
