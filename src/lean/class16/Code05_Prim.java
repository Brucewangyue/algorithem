package lean.class16;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Prim算法
 * <p>
 * 最小生成树
 */
public class Code05_Prim {
    public static Set<Graph.Edge> primMST(Graph graph) {
        PriorityQueue<Graph.Edge> q = new PriorityQueue<>(new EdgeComparator());  // 解锁的边
        HashSet<Graph.Node> unLockNode = new HashSet<>();  // 解锁的点

        HashSet<Graph.Edge> ans = new HashSet<>();

        Graph.Node node = graph.nodes.get(0);  // 任一选一个点解锁
        unLockNode.add(node);
        q.addAll(node.edges); // 解锁点的所有边
        while (!q.isEmpty()) {
            Graph.Edge cur = q.poll();  // 弹出解锁的边
            if(!unLockNode.contains(cur.to)){  // 判断新解锁边的结束点（新点）是否还未解锁
                unLockNode.add(cur.to);
                q.addAll(cur.to.edges);
                ans.add(cur);
            }
        }

        return ans;
    }

    private static class EdgeComparator implements Comparator<Graph.Edge> {
        @Override
        public int compare(Graph.Edge o1, Graph.Edge o2) {
            return o1.weight - o2.weight;
        }
    }
}
