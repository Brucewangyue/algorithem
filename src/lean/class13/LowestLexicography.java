package lean.class13;

import java.util.*;

/**
 * 最小拼接字符串
 *
 * 贪心
 */
public class LowestLexicography {
    public static void main(String[] args) {
//        String[] arr = {"abc","aac","aaa"};
//        String[] arr1 = {"abc","aac","aaa"};
//        String[] arr = {"abc","cks","bct"};
//        String[] arr1 = {"abc","cks","bct"};
                String[] arr = {"b","ba"};
        String[] arr1 =  {"b","ba"};


        System.out.println(process1(arr));
        System.out.println(lowestString(arr1));

    }

    /**
     * 贪心
     */
    private static String process1(String[] arr){
        System.out.println("b".compareTo("ba"));

        Arrays.sort(arr,new MyComparator());
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }
        Set<Integer> ans = new HashSet<>();
        return sb.toString();
    }

    private static class MyComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            // 为什么需要比较器？因为会出现“b”，“ba”的情况，如果直接使用单个字符串比大小，那么就会出现"bba"的结果
            // "bba" 是比 "bab"大的，所以 "ba" 要在 "b" 的前面
            // 所以不能单纯使用两个字符串比较
            // 如果o1+o2 比 o2+l1 返回的-1，那么o1小于o2
            return (o1+o2).compareTo(o2+o1);
        }
    }

    /**
     * 暴力解，字符串全排列
     */
    private static String lowestString(String[] arr){
        ArrayList<String> ans = new ArrayList<>();
        process(arr,0,ans,"");

        // 使用TreeSet不需要排序
        String res = ans.get(0);
        for(String s:ans){
            if(res.compareTo(s)>0){
                res=s;
             }
        }

        return res;
    }

    private static void process(String[] arr,int index,ArrayList<String> ans,String path){
        if(index==arr.length){
            ans.add(path);
            return;
        }

        ArrayList<String> existList = new ArrayList<>();  // 剪枝
        for (int i = index; i < arr.length; i++) {
            if(existList.contains(arr[i]))
                continue;

            existList.add(arr[i]);
            swap(arr,index,i);
            process(arr,index+1,ans,path+ arr[index]);
            swap(arr,i,index);
        }
    }

    private static void swap(String[] arr,int i, int j){
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
