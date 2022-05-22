package lean.class17;

/**
 * 汉诺塔游戏
 */
public class Code01_Hanoi {
    public static void main(String[] args) {
        leftToRight(3);
        System.out.println("=========================");
        process(3);
    }

    private static void process(int n) {
        f(n, "left", "right", "mid");
    }

    /**
     * 通过增加参数，对以下6个方法的抽象
     * 递归通过增加参数变得可以处理更复杂的逻辑
     *
     * @param n     罗盘数量
     * @param from  从哪里
     * @param to    到哪里
     * @param other 另外一个桶位
     */
    private static void f(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("Move 1 from " + from + " to " + to);
        } else {
            f(n - 1, from, other, to);
            System.out.println(n + " from " + from + " to " + to);
            f(n - 1, other, to, from);
        }
    }

    private static void leftToRight(int n) {
        if (n == 1) { // base case
            System.out.println(n + " from left to right");  // 最后一块圆盘，肯定是最小的，且可以直接移动到任意位置
            return;
        }

        leftToMid(n - 1); // 需要给最底部的圆盘让路，这里说明了，leftToMid这个方法肯定能给我提供一个让底部以上的圆盘能整体到达中间，然后底部圆盘移动到右边
        System.out.println(n + " from left to right");
        midToRight(n - 1); // 将中间的圆盘整体移动到右边，完成！！
    }

    private static void leftToMid(int n) {
        if (n == 1) { // base case
            System.out.println(n + " from left to mid");  // 最后一块圆盘，肯定是最小的，且可以直接移动到任意位置
            return;
        }

        leftToRight(n - 1);
        System.out.println(n + " from left to mid");
        rightToMid(n - 1);
    }

    private static void midToRight(int n) {
        if (n == 1) { // base case
            System.out.println(n + " from mid to right");  // 最后一块圆盘，肯定是最小的，且可以直接移动到任意位置
            return;
        }

        midToLeft(n - 1);
        System.out.println(n + " from mid to right");
        leftToRight(n - 1);
    }

    private static void midToLeft(int n) {
        if (n == 1) { // base case
            System.out.println(n + " from mid to left");  // 最后一块圆盘，肯定是最小的，且可以直接移动到任意位置
            return;
        }

        midToRight(n - 1);
        System.out.println(n + " from mid to left");
        rightToLeft(n - 1);
    }

    private static void rightToMid(int n) {
        if (n == 1) { // base case
            System.out.println(n + " from right to mid");  // 最后一块圆盘，肯定是最小的，且可以直接移动到任意位置
            return;
        }

        rightToLeft(n - 1);
        System.out.println(n + " from right to mid");
        leftToMid(n - 1);
    }

    private static void rightToLeft(int n) {
        if (n == 1) { // base case
            System.out.println(n + " from right to left");  // 最后一块圆盘，肯定是最小的，且可以直接移动到任意位置
            return;
        }

        rightToMid(n - 1);
        System.out.println(n + " from right to left");
        midToLeft(n - 1);
    }
}
