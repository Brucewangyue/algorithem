package lean.class16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * https://www.lintcode.com/problem/127/
 * 最大深度  43.60 %
 */
public class Code03_TopologySortDFS2 {

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
        int deep = 0;  // 取所有邻居中最大深度
        for (DirectedGraphNode neighbor : node.neighbors) {
            deep = Math.max(deep, process(neighbor, cache).deep);
        }

        Info info = new Info(deep + 1, node);
        cache.put(node, info);
        return info;
    }

    private static class Info {
        DirectedGraphNode node;
        int deep; // 记录node所有经过的点次总和

        public Info(int deep, DirectedGraphNode node) {
            this.deep = deep;
            this.node = node;
        }
    }

    private static class LabelComparator implements Comparator<Info> {
        @Override
        public int compare(Info o1, Info o2) {
            return o2.deep - o1.deep;
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
