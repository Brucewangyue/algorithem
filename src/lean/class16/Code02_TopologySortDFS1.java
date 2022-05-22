package lean.class16;

import java.util.*;

/**
 * https://www.lintcode.com/problem/127/
 * <p>
 * 给定一个有向图，图节点的拓扑排序定义如下:
 * <p>
 * 对于图中的每一条有向边 A -> B , 在拓扑排序中A一定在B之前.
 * 拓扑排序中的第一个节点可以是图中的任何一个没有其他节点指向它的节点.
 * 针对给定的有向图找到任意一种拓扑排序的顺序.
 *
 * 点次 18.80 %
 */
public class Code02_TopologySortDFS1 {

    /**
     * 对给定图中的所有点进行排序
     *
     * @param graph 已知图中所有的点，每个点都知道自己的所有邻居点
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Info> cache = new HashMap<>();  // 缓存已经计算过的邻居点次
        for (DirectedGraphNode node : graph) {
            process(node, cache);  // 对每个点计算邻居点次
        }

        ArrayList<Info> infos = new ArrayList<>(cache.values());
        infos.sort(new LabelComparator());  // 结果按点次倒叙

        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (Info info : infos) {
            ans.add(info.node);
        }

        return ans;
    }

    private Info process(DirectedGraphNode node, HashMap<DirectedGraphNode, Info> cache) {
        if (cache.containsKey(node)) { // base case 如果cache中已经存在node了，证明之前已经记录过了
            return cache.get(node);
        }

        // 之前没有处理过当前点
        long nodes = 0;  // 汇总所有邻居的电次
        for (DirectedGraphNode neighbor : node.neighbors) {
            nodes += process(neighbor, cache).nodes;
        }

        Info info = new Info(nodes + 1, node);
        cache.put(node, info);
        return info;
    }

    private static class Info {
        DirectedGraphNode node;
        long nodes; // 记录node所有经过的点次总和

        public Info(long nodes, DirectedGraphNode node) {
            this.nodes = nodes;
            this.node = node;
        }
    }

    private static class LabelComparator implements Comparator<Info> {
        @Override
        public int compare(Info o1, Info o2) {
            return Long.compare(o2.nodes, o1.nodes);
        }
    }

    private static class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }
}
