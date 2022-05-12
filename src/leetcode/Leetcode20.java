package leetcode;

import java.util.Stack;

/**
 * 有效括号数量
 */
public class Leetcode20 {
    public static void main(String[] args) {
//        String str = "[[]{}()]()";
        String str = "[";
        System.out.println(isValidParenthesis(str));
    }

    static boolean isValidParenthesis(String s) {
        Stack<Character> stack = new Stack<>();

        for (char cha : s.toCharArray()) {
            if (cha == '{' || cha == '[' || cha == '(') {  // 只要是左括号就入栈
//                stack.push(cha);
                stack.push(cha == '{' ? '}' : cha == '[' ? ']' : '}');  // 使用三元运算符，简化最后的判断
            } else {
                if (stack.isEmpty())  // 如果第一个是反向直接不满足
                    return false;

                Character last = stack.pop();  // 出栈只要不是反向都是不满足
//                if ((last == '{' && cha != '}') || last == '[' && cha != ']' || last == '(' && cha != ')')
                if (last != cha)
                    return false;
            }
        }

        return stack.isEmpty();
    }

    static boolean validParenthesis(String str) {
        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else {
                if (stack.size() == 0)
                    return false;

                Character lc = stack.pop();
                if (lc == '{' && c != '}')
                    return false;
                else if (lc == '[' && c != ']')
                    return false;
                else if (lc == '(' && c != ')')
                    return false;
            }
        }

        return true;
    }
}
