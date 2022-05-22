package lean.class16;

/**
 * 将二维数组转为图
 */
public class GridGraphAdapter {
    /**
     * @param grid 第一位是权重，第二位是起始点，第三位是结束点
     *             [[5，0，7],
     *             [3，1，3],
     *             [2，3，7]]
     */
    private static Graph convert(int[][] grid) {
        Graph graph = new Graph();
        for (int i = 0; i < grid.length; i++) {
            int edgeWeight = grid[i][0];
            int from = grid[i][1];
            int to = grid[i][2];

            Graph.Node fromNode = graph.nodes.get(from);
            Graph.Node toNode = graph.nodes.get(to);
            if (fromNode == null) {  // 创建新点
                fromNode = new Graph.Node(from);
                graph.nodes.put(to, fromNode);
            }

            if (toNode == null) {  // 创建新邻居点
                toNode = new Graph.Node(to);
                graph.nodes.put(to, toNode);
            }

            Graph.Edge edge = new Graph.Edge(edgeWeight, fromNode, toNode);  // 创建点到点的边
            fromNode.edges.add(edge);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            graph.edges.add(edge);
            toNode.in++;
        }

        return graph;
    }
}
