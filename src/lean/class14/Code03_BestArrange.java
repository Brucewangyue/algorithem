package lean.class14;

/**
 * 会议安排：怎样安排会议能让会议开最多的场次
 * <p>
 * 贪心
 */
public class Code03_BestArrange {
    public static void main(String[] args) {
        Meeting[] meetings = new Meeting[5];
        meetings[0] = new Meeting(1, 2);
        meetings[1] = new Meeting(1, 4);
        meetings[2] = new Meeting(2, 9);
        meetings[3] = new Meeting(3, 10);
        meetings[4] = new Meeting(9, 12);
        System.out.println(bestArrange(meetings));
        System.out.println(bestArrange1(meetings));
    }

    /**
     * 贪心策略：哪场会议先结束，就最先安排
     */
    static int bestArrange(Meeting[] meetings) {
        int timeline = 0; // 记住上一次会议的结束时间
        int ans = 0;
        for (Meeting meeting : meetings) {
            if (timeline <= meeting.start) {
                ans++;
                timeline = meeting.end;
            }
        }

        return ans;
    }

    /**
     * 暴力：所有情况都尝试
     * <p>
     * 类似字符串数组的全排列
     */
    static int bestArrange1(Meeting[] meetings) {
        return process(meetings, 0, 0, 0);
    }

    static int process(Meeting[] meetings, int index, int ans, int timeline) {
        if (meetings.length == index) {  // base case : 没有会议了，直接返回会议场次
            return ans;
        }

        // 还有会议处理
        int res = ans;
        for (int i = index; i < meetings.length; i++) {
            swap(meetings, i, index); // 遍历尝试将每一个会议放在最前i位置

            if (timeline <= meetings[index].start) {  // 主要要使用表示当前位置的所有index, i 只是用来把值放到index位置
                res = Math.max(res, process(meetings, index + 1, ans + 1, meetings[index].end));
            }

            swap(meetings, i, index);  // 恢复现场
        }

        return res;
    }

    static void swap(Meeting[] arr, int i, int j) {
        Meeting temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    private static class Meeting {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
