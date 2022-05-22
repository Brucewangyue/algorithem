package lean.class14;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入: 正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出：你最后获得的最大钱数。
 */
public class Code04_IPO {
    public static void main(String[] args) {
        int[] costs = {5, 1, 2, 6};  // 项目花费
        int[] profits = {1, 3, 5, 4};  // 项目利润
        int k = 2;
        int m = 2;
        System.out.println(findMaximized(costs, profits, k, m));
    }

    /**
     * 贪心
     */
    static int findMaximized(int[] costs, int[] profits, int k, int m) {
        int N = costs.length;
        int ans = m;
        PriorityQueue<Info> queue1 = new PriorityQueue<>(new CostAscComparator());  // 按项目花费的小根堆
        PriorityQueue<Info> queue2 = new PriorityQueue<>(new ProfitDescComparator());  // 按项目利润的大根堆

        for (int i = 0; i < N; i++) {  // 遍历把项目内容放入 按项目花费的小根堆
            queue1.add(new Info(costs[i], profits[i]));
        }

        for (int i = 0; i < k; i++) {  // 遍历能做几个项目
            // 弹出所有当前钱包能做的项目，压入 按项目利润的大根堆
            while (!queue1.isEmpty() && queue1.peek().cost <= ans) {
                queue2.add(queue1.poll());
            }

            if (queue2.isEmpty()) return ans;  // 证明当前的钱包没有项目可以做了

            ans += queue2.poll().profit;  // 每次选一个当前钱包能做，且利润最高的项目
        }

        return ans;
    }

    private static class Info {
        int cost;
        int profit;

        public Info(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    private static class CostAscComparator implements Comparator<Info> {

        @Override
        public int compare(Info o1, Info o2) {
            return o1.cost - o2.cost;
        }
    }

    private static class ProfitDescComparator implements Comparator<Info> {

        @Override
        public int compare(Info o1, Info o2) {
            return o2.profit - o1.profit;
        }
    }
}
