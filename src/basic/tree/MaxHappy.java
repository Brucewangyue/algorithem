package basic.tree;

/**
 * 员工最大快乐值：
 *  一个树形层级表示公司职位的上下级关系，现在这个公司需要要主办一个活动，直接上下级不能同时参与这个活动，也就老板如果来参加，那么公司总经理不能来参加
 *
 * 多叉树
 */
public class MaxHappy {
    public static void main(String[] args) {
        MultiWayTreeNode head = new MultiWayTreeNode();

        System.out.println(maxHappy(head));
    }

    private static int maxHappy(MultiWayTreeNode head){
        if(head==null){
            return 0;
        }

        Info maxHappy = process(head);
        return Math.max(maxHappy.no,maxHappy.yes);
    }

    private static Info process(MultiWayTreeNode head) {
        if(head==null)
            return new Info(0,0);

        int yes = head.val;  // 如果我自己参与的快乐值
        int no = 0;   // 自己不参加的快乐值
        for (MultiWayTreeNode node : head.nodes){
            Info underling = process(node);

            yes += underling.no;  // 如果自己参加了，只能使用下属不参加时的最大快乐值，不能加上下属参加时的快乐值
            no += Math.max(underling.no,underling.yes);  // 如果自己不参加，看下属的哪种快乐值大就取哪种，因为自己不参加，所以可以让下属参加也可以不参加，看下属参加时的整体快乐值多还是不参加多
        }

        return new Info(yes,no);
    }

    private static class Info{
        int yes; // 下级参加的最大快乐值
        int no;  // 下级不参数的最大快乐值

        public Info(int yes,int no) {
            this.yes = yes;
            this.no = no;
        }
    }
}
