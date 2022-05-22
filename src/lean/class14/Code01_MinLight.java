package lean.class14;

/**
 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class Code01_MinLight {
    public static void main(String[] args) {
        char[] arr = {'X', '.', '.', '.', 'X', '.', '.', 'X', '.', '.', 'X','.'};
        System.out.println(minLight(arr));
    }

    /**
     * 贪心
     */
    private static int minLight(char[] arr) {
        int N = arr.length;
        int ans = 0;

//        for (int i = 0; i < N; i++) {
//            if(arr[i]=='X')  // 如果是墙，直接跳过
//                continue;
//
//            // 开始处理居民点
//            int index = i; // 居民点的位置
//            int homeCount = 0;
//            while(index<N){
//                if(arr[index]=='X')
//                    break;
//
//                homeCount++;
//                if(homeCount==3){ // 如果够3个居民房了，就必须要在前一个放灯了
//                    ans++;
//                    homeCount=0;
//                }
//                index++;
//            }
//
//            if(homeCount>0){// 前面的向前找墙行为中，找到了墙，并且有1或2个居民房还没有点灯
//                ans++;
//            }
//
//            i = index-1;
//        }

        int i = 0;
        while (i < N) {
            if (arr[i] == 'X') {  // 如果是墙，直接跳过
                i++;
            } else {
                ans++;  // 只要遇到居民房灯就点灯

                if (i + 1 == N) {  // 这里是最后一个位置，且是居民房，直接返回，前面已经点灯了
                   break;
                }else{
                    if (arr[i + 1] == 'X') {  // 第一个点的下一个位置如果是灯，那么就跳到灯的下一个位置判断
                        i = i + 2;
                    } else {   // 如果下一个点的位置不是X，那么不管第三个点是'X'还是'.'，那么逻辑都是一样直接跳到第三个点
                        i = i + 3;  // 进入这里说明第二个位置是'.'，如果第三个位置还是点，那么一样是要跳到第四个点判断（前三个连一起的点公用一个灯）
                    }
                }

//                if (i + 1 < N) {
//                    if (arr[i + 1] == 'X') {  // 第一个点的下一个位置如果是灯，那么就跳到灯的下一个位置判断
//                        i = i + 2;
//                    } else {   // 如果下一个点的位置不是X，那么不管第三个点是'X'还是'.'，那么逻辑都是一样直接跳到第三个点
//                        i = i + 3;  // 进入这里说明第二个位置是'.'，如果第三个位置还是点，那么一样是要跳到第四个点判断（前三个连一起的点公用一个灯）
//                    }
//                }else{
//                    i++;
//                }
            }
        }

        return ans;
    }

}
