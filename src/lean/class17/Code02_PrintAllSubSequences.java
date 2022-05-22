package lean.class17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Code02_PrintAllSubSequences {
    public static void main(String[] args) {
        String str = "accc";
//        for (String s : subs(str)) {
        for (String s : subsNoRepeat(str)) {
            System.out.println(s);
        }
    }

    /**
     * 打印所有子序列
     */
    static List<String> subs(String s) {
        String path = "";
        List<String> ans = new ArrayList<>();
        process(s.toCharArray(), 0, ans, path);
        return ans;
    }

    static void process(char[] chas, int index, List<String> ans, String path) {
        if (index == chas.length) { // base case 超出字符索引时得到最后字符
            ans.add(path);
            return;
        }

        process(chas, index + 1, ans, path);  // 不要当前字符，跳到下个索引判断，把当前字符往下传
        process(chas, index + 1, ans, path + chas[index]);  // 要当前字符，追加当前字符，跳到下个索引判断，往下传
    }

    /**
     * 打印所有不重复的子序列
     */
    static HashSet<String> subsNoRepeat(String s){
        String path = "";
        HashSet<String> ans = new HashSet<>();  // HashSet自动去重
        process2(s.toCharArray(),0,ans,path);
        return ans;
    }

    static void process2(char[] chas,int i,HashSet<String> ans,String path){
        if(i==chas.length){
            ans.add(path);
        }

        process2(chas,i+1,ans,path);
        process2(chas,i+1,ans,path + chas[i]);
    }
}
