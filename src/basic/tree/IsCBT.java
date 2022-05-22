package basic.tree;

/**
 * 是否是完全二叉树 （难题）
 *
 * 完全二叉树
 *  右树的高度不能高于左树
 *  当左树和右树一样高度时，x是满二叉树
 *  当左树比右树高一层，右树必须是满二叉树
 *
 * 满足以下任一情况就可以判断整棵树是完全二叉树
 *  左满，右满，左高==右高 || 左高=右高+1
 *  左完，右满，左高==右高+1
 *  左满，右完全，左高==右高
 */
public class IsCBT {
    public static void main(String[] args) {
        System.out.println(isCBT(new Node()));
    }

    private static boolean isCBT(Node node){
        return process(node).isCBT;
    }

    private static Info process(Node node) {
        if(node==null)
            return new Info(true,true,0);

        Info l = process(node.left);
        Info r = process(node.right);

        int height = Math.max(l.height,r.height) + 1;
        boolean isFull = l.isFull && r.isFull && l.height==r.height;
        boolean isCBT = false;
        if(l.isFull && r.isFull && (l.height==r.height || l.height==r.height+1))  // 左满，右满，左高==右高 || 左高=右高+1
            isCBT = true;
        else if(l.isCBT && r.isFull && l.height==r.height+1)  // 左完，右满，左高==右高+1
            isCBT = true;
        else if(l.isFull && r.isCBT && l.height==r.height)
            isCBT = true;

        return new Info(isFull,isCBT,height);
    }

    private static class Info{
        boolean isFull;
        boolean isCBT;
        int height;

        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }
}
