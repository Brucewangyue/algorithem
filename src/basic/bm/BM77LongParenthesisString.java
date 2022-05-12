package basic.bm;

import java.util.Stack;

/**
 * 最长的括号子串
 * <p>
 * 其实就是最长回文子串
 */
public class BM77LongParenthesisString {
    public static void main(String[] args) {
        String str = ")()())";
//        String str = "(()";
        System.out.println(stack(str));
    }

    static int stack(String str) {
        Stack<Integer> stack = new Stack<>();

        int count = 0;
        int N = str.toCharArray().length;
        int i = 0;
        int start = 0;
        while (i < N) {
            if (str.charAt(i) == '(') {
                stack.push(i);
                if (stack.size() == 0)
                    start = i;
            } else if (stack.size() == 0) {
                count = i - start;
            } else {
                // 弹出
                stack.pop();
                if (stack.size() == 0)
                    count = i - start;
                else
                    count = i - stack.peek() - start;
            }

            i++;
        }

        return count;
    }

    static int getLongParenthesisStr(String str) {
        int N = str.length();
        int res = 0;
        for (int i = 0; i < N - 1; i++) {
            if (str.charAt(i) == '(') {
                int cur = i + 1;
                int l = 1; // 左括号个数
                int r = 0; // 右括号个数

                int temp = 0; // 记录过程中的每一次相等
                while (cur < N) {
                    if (str.charAt(cur) == ')')
                        r++;
                    else
                        l++;

                    if (l == r)
                        temp = l + r;

                    cur++;
                }

                if (temp > res)
                    res = temp;
            }
        }

        return res;
    }

    // 错误
    private static int manacher(String str) {
        StringBuilder sb = new StringBuilder();

        // 加虚轴  ()()  #(#)#(#)#
        for (char c : str.toCharArray()) {
            sb.append("#").append(c);
        }
        sb.append("#");

        int res = 0;
        int N = sb.length();
        char[] arr = sb.toString().toCharArray();
        for (int i = 1; i < N; i++) {
            int l = i - 1;
            int r = i + 1;
            int count = 1;
            while (l >= 0 && r < N) {
                if (arr[l] == '#' || arr[l] != arr[r])
                    count += 2;
                else
                    break;

                l--;
                r++;
            }

            if (count > res)
                res = count;
        }

        return res / 2;
    }
}
