package basic.stack;

import java.util.Stack;

/**
 * 不使用任何的额外空间，倒叙输出
 *
 * 递归栈
 */
public class ReversedStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");

        reverse(stack);

        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    static void reverse(Stack<String> stack){
        if(stack.isEmpty())
            return;

        String last = f(stack);  // 弹出最后一个
        reverse(stack);   // 递归弹出最后一个
        stack.push(last);  // 将最后一个压入
    }

    // 递归
    static String f(Stack<String> stack){
        // 弹出当前的栈顶
        String result = stack.pop();
        if(stack.isEmpty()){ // 到了最底层的栈底，就是结果
            return result;
        }else {// 非栈底，继续放下探
            String last = f(stack); // 接收栈底的结果
            stack.push(result); // 从新入栈，保持原来的顺序
            return last; // 将栈底返回
        }
    }
}
