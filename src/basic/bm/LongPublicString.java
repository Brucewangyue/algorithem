package basic.bm;

/**
 * 最长公共子串
 */
public class LongPublicString {
    public static void main(String[] args) {
        System.out.println(getLongPublicString("1AB2345CD", "12345EF"));
        System.out.println(getLongPublicString("2LQ74WK8Ld0x7d8FP8l61pD7Wsz1E9xOMp920hM948eGjL9Kb5KJt80", "U08U29zzuodz16CBZ8xfpmmn5SKD80smJbK83F2T37JRqYfE76vh6hrE451uFQ100ye9hog1Y52LDk0L52SuD948eGjLz0htzd5YF9J1Y6oI7562z4T2"));
    }

    static String getLongPublicString(String str1, String str2) {
        String res = "";
        int l1 = str1.length();
        int l2 = str2.length();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) { // j: str2子串索引
                // 遇到相同字符，开始判断子串
                if (str1.charAt(i) == str2.charAt(j)) {
//                    StringBuilder temp = new StringBuilder(); 对象的创建，性能较差

                    // 两边子串的起点
                    int curI = i;
                    int curJ = j;

                    // 找出所有余下的相同子串
                    while(curI<l1 && curJ<l2 && str1.charAt(curI) == str2.charAt(curJ)){
//                        resList.add(str1.charAt(curI));
                        temp.append(str1.charAt(curI));
                        curI++;
                        curJ++;
                    }

                    // 新子串长度更长
                    if(temp.length()>res.length()){
                        // 替换
                        res = temp.toString();
                    }

                    temp.delete(0,temp.length());
//                    resList.clear();  // 速度没有StringBuilder操作字符快
                }
            }
        }

        return res;
    }
}
