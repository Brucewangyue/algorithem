package lean.class16;

import java.util.*;

/**
 * 求最小生成树
 * <p>
 * Kruskal算法  贪心
 */
public class Code04_Kruskal {

    public static Set<Graph.Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind(graph.nodes.values());  // 并查集的作用：判断边所连的点是否在同一个集合
        // 找出所有边，按权重排序
        PriorityQueue<Graph.Edge> q = new PriorityQueue<>(new WeightComparator());
        q.addAll(graph.edges);

        HashSet<Graph.Edge> ans = new HashSet<>();
        while (!q.isEmpty()) {
            Graph.Edge cur = q.poll();
            if (!unionFind.isSameFather(cur.from, cur.to)) {  // 如果边所连的两个点已经在同一个集合，如果再加入肯定形成了环，舍弃
                ans.add(cur);
                unionFind.union(cur.from, cur.to);
            }
        }

        return ans;
    }

    private static class WeightComparator implements Comparator<Graph.Edge> {

        @Override
        public int compare(Graph.Edge o1, Graph.Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    private static class UnionFind {
        HashMap<Graph.Node, Graph.Node> parents;
        HashMap<Graph.Node, Integer> sizes;

        public UnionFind(Collection<Graph.Node> nodes) {
            parents = new HashMap<>();
            sizes = new HashMap<>();

            for (Graph.Node node : nodes) {
                parents.put(node, node);
                sizes.put(node, 1);
            }
        }

        private Graph.Node findFather(Graph.Node node) {
            if (node == null) return node;

            ArrayList<Graph.Node> passNodes = new ArrayList<>();
            while (node != parents.get(node)) {
                passNodes.add(node);
                node = parents.get(node);
            }

            // 重指向
            for (Graph.Node passNode : passNodes) {
                parents.put(passNode, node);
            }

            return node;
        }

        private boolean isSameFather(Graph.Node a, Graph.Node b) {
            return findFather(a) == findFather(b);
        }

        private void union(Graph.Node a, Graph.Node b) {
            Graph.Node aHead = findFather(parents.get(a));
            Graph.Node bHead = findFather(parents.get(b));
            if (aHead != bHead) {
                Integer aSize = sizes.get(aHead);
                Integer bSize = sizes.get(bHead);
                if (aSize >= bSize) {
                    sizes.put(aHead, aSize + bSize);
                    parents.put(bHead, aHead);
                } else {
                    sizes.put(bHead, bSize + aSize);
                    parents.put(aHead, bHead);
                }
            }
        }
    }
}
