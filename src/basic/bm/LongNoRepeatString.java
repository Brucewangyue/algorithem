package basic.bm;

import java.util.ArrayList;
import java.util.List;

/**
 * 最长不重复子串
 */
public class LongNoRepeatString {
    public static void main(String[] args) {
        System.out.println(getNoRepeatString("abcbabcdccc"));
        System.out.println(getNoRepeatString("cccaabccc"));
        System.out.println(getNoRepeatString("cccc"));
    }

    static String getNoRepeatString(String str) {
        int N = str.length();

        String res = "";
//        List<Character> arr = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int second = 1; second < N - 1; second++) {
            // 查找到存在不重复子串
            if (str.charAt(second - 1) != str.charAt(second)) {
                int i = second;
                sb.append(str.charAt(i - 1));
                while (!exist(sb, str.charAt(i))) { // 与新子串的所有字符判断都不相同
                    sb.append(str.charAt(i));
                    i++;
                }

                if (sb.length() > res.length()) {
                    // 替换
                    res = sb.toString();
                }

                // 重置
                sb.delete(0, sb.length());
            }
        }

        return res;
    }

    static boolean exist(StringBuilder sb, char c) {
        return sb.indexOf(String.valueOf(c)) >= 0;
    }
}
