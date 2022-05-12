package leetcode;

import java.util.Stack;

/**
 * 最长有效括号
 * 较难
 */
public class Leetcode32 {
    public static void main(String[] args) {
        System.out.println(longStr("(((()))"));
        System.out.println(longStr("(((()))"));
    }

    static int longStr(String s) {
        // 动态规划：i表示最长有效括号对
        int[] dp = new int[s.length()];
        // 只压入左括号
        Stack<Character> stack = new Stack<>();
        int pairs = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                stack.push('(');
            else {
                // 右括号处理
                if (stack.isEmpty())
                    continue;

                // 出现有效括号对
                stack.pop();  // 将左括号出栈
                pairs = 1 + dp[i - 1];

                // 还需要查看当前括号对的左边界是否也是一个有效括号对，如果是则要相加
                int prePairsIndex = i - pairs * 2;
                if (prePairsIndex >= 0) {
                    pairs += dp[prePairsIndex];
                }

                dp[i] = pairs;
            }
        }

        // 从动态规划中找出最大值返回
        int max = 0;
        for (int num : dp) {
            if (num > max)
                max = num;
        }

        return max * 2;
    }
}
