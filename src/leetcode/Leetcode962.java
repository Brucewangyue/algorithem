package leetcode;

/**
 * 最大宽度坡
 * <p>
 * 给定一个整数数组 A，坡是元组 (i, j)，其中 i < j 且 A[i] <= A[j]。这样的坡的宽度为 j - i。
 * <p>
 * 找出 A 中的坡的最大宽度，如果不存在，返回 0 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[6,0,8,2,1,5]
 * 输出：4
 * 解释：
 * 最大宽度的坡为 (i, j) = (1, 5): A[1] = 0 且 A[5] = 5.
 * <p>
 * 示例 2：
 * <p>
 * 输入：[9,8,1,0,1,9,4,0,4,1]
 * 输出：7
 * 解释：
 * 最大宽度的坡为 (i, j) = (2, 9): A[2] = 1 且 A[9] = 1.
 */
public class Leetcode962 {
    public static void main(String[] args) {
        int[] arr = {6, 0, 8, 2, 1, 5};
//        int[] arr = {9,8,1,0,1,9,4,0,4,1};

        System.out.println(getLong(arr));
    }

    private static int getLong(int[] arr) {
        int N = arr.length;

        int max = 0;
        for (int i = 0; i < N-1; i++) {
            // 从当前索引值的下一位开始扫
            for (int next = i + 1; next < N; next++) {
                if (arr[i] <= arr[next]) { // 如果后面的值比前面的大就计算距离
                    if(next-i>max) // 距离比原来大就更新
                        max = next-i;
                }
            }
        }

        return max;
    }
}
