package lean.class17;

import java.util.ArrayList;
import java.util.List;

/**
 * 难度：中等
 */
public class Code03_PrintAllPermutations {
    public static void main(String[] args) {
        for (String s : subsPermutations("abca")) {
            System.out.println(s);
        }
        System.out.println("=================");
        for (String s : permutations("abca")) {
            System.out.println(s);
        }
    }

    /**
     * 字符串全排列
     * 恢复现场
     */
    static List<String> subsPermutations(String s) {
        String path = "";
        List<String> ans = new ArrayList<>();
        ArrayList<Character> rest = new ArrayList<>();
        for (char cha : s.toCharArray()) {
            rest.add(cha);
        }

        f(rest, ans, path);
        return ans;
    }

    static void f(List<Character> rest, List<String> ans, String path) {
        if (rest.isEmpty()) {
            if (!ans.contains(path))
                ans.add(path);
            return;
        }

        int N = rest.size();  // 当前剩余字符数
        for (int i = 0; i < N; i++) {
            char cur = rest.get(i);  // 遍历获取当前剩余字符的每一个
            rest.remove(i);  // 向下传递时排除当前字符，因为当前字符在下面已经被使用来组合新字符了

            f(rest, ans, path + cur);  // 得到新字符组合，并且传入排除当前字符的新缩减的数组继续找组合

            rest.add(i, cur); // 恢复现场
        }
    }

    /**
     * 优解，深度优先
     */
    static ArrayList<String> permutations(String s) {
        ArrayList<String> ans = new ArrayList<>();
        if (s == null || s.equals(""))
            return ans;

        f3(s.toCharArray(), 0, ans);

        return ans;
    }

    // 全排列
    static void f2(char[] chas, int index, List<String> ans) {
        if (index == chas.length) {
            ans.add(String.valueOf(chas));  // 传入的就是最终结果，上一层会恢复现场
        } else {
            for (int i = index; i < chas.length; i++) {
                // 每一层的索引都能轮流在第一个位置，然后往下传，下层的也是剩余的每个字符都能轮流在首位，直到最后超出了索引长度
                swap(chas, i, index);  // 当前位置值和后面的每一个做交换

                f2(chas, index + 1, ans);  // 索引+1，往下传

                swap(chas, index, i);  // 恢复现场，需要把交换到第一个位置的字符交换回到原来的位置，下轮让别的字符交换到首位
            }
        }
    }

    /**
     * 去重全排列
     *
     * 剪枝：发现分支走过直接跳过
     *      如果没有剪枝就要全部分支都走，然后在最终添加结果处过滤
     */
    static void f3(char[] chas, int index, List<String> ans) {
        if (index == chas.length) {
//            if(ans.contains(String.valueOf(chas)))   过滤
            ans.add(String.valueOf(chas));
        } else {
            boolean[] visited = new boolean[256];    // 用于记录当前层已经做过首位的字符
            for (int i = index; i < chas.length; i++) {
                if(visited[chas[i]])  // 如果字符做过首位就跳过
                    continue;

                visited[chas[i]] = true;  // 记录当前作为首位的字符
                swap(chas, i, index);
                f3(chas, index + 1, ans);
                swap(chas, index, i);
            }
        }
    }

    static void swap(char[] chas, int i, int j) {
        char temp = chas[i];
        chas[i] = chas[j];
        chas[j] = temp;
    }
}
