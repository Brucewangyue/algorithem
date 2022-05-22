package lean.class16;

import java.util.*;

/**
 * 图结构
 */
public class Graph {
    HashMap<Integer, Node> nodes;  // 这里使用HasH，是因为Integer是输入给的一个值，我们内部是用Node来表示，所以这里做个映射
    HashSet<Edge> edges;  // 每条边都是唯一的

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    /**
     * 宽度优先遍历
     * 就是一个二叉树宽度遍历 + 遍历过的点的处理
     */
    public void BFS(Node start) {
        if (start == null) return;

        HashSet<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        visited.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();  // 按顺序弹出，处理当前点的所有邻居点
            System.out.println("弹出了：" + cur.val);
            for (Node next : cur.nexts) {
                if (!visited.contains(next)) {  // 入过队列的点跳过，防止回路情况
                    visited.add(next); // 记录已经入过队列的点
                    queue.add(next);  // 添加队列后，这一轮内的邻居节点是有序的
                }
            }
        }
    }

    /**
     * 深度优先遍历
     */
    public void DFS(Node start) {
        if (start == null) return;

        HashSet<Node> visited = new HashSet<>();
        Stack<Node> curPath = new Stack<>(); // 保存当前点的路径，替代递归中的虚拟机栈
        visited.add(start);
        curPath.push(start);
        System.out.println(start.val);
        while (!curPath.isEmpty()) {
            Node cur = curPath.pop();
            for (Node next : cur.nexts) {
                if (!visited.contains(next)) {  // 曾今经过的点直接跳过，因为经过的点，那个点后面的路也走完了
                    visited.add(next);
                    curPath.push(cur);
                    curPath.push(next);
                    System.out.println(next.val);
                    break;  // 这里是重点：只从一条路找，直到尽头
                }
            }
        }
    }

    /**
     * 图中的点
     */
    public static class Node {
        int val;
        int out; // 出路条数
        int in;  // 入路条数
        ArrayList<Node> nexts;  // 当前点出路的点
        ArrayList<Edge> edges;  // 当前点出路的边

        public Node(int val) {
            this.val = val;
            this.nexts = new ArrayList<>();
            this.edges = new ArrayList<>();
        }
    }

    /**
     * 图中的边
     */
    public static class Edge {
        int weight;
        Node from;  // 边的头
        Node to;  // 边的尾

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }
}
