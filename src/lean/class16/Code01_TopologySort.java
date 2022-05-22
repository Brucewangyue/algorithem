package lean.class16;

import java.util.*;

/**
 * 拓扑排序
 * 结果可以存在多种，同一级的所有点顺序无所谓
 */
public class Code01_TopologySort {
    public static List<Graph.Node> sortedTopology(Graph graph) {
        HashMap<Graph.Node, Integer> inMap = new HashMap<>();  // 记录每个点的当前入度
        PriorityQueue<Graph.Node> queue = new PriorityQueue<>();  // 这个队列只会放入度是0的点
        for (int i = 0; i < graph.nodes.size(); i++) {
            Graph.Node node = graph.nodes.get(i);
            inMap.put(node, node.in);
            if (node.in == 0)
                queue.add(node);
        }

        List<Graph.Node> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            Graph.Node cur = queue.poll();
            res.add(cur);

            for (Graph.Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0)
                    queue.add(next);
            }
        }

        return res;
    }
}
