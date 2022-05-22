package lean.class14;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集：哈希表性能比数组差在常数，整体差10倍
 */
public class UnionFind<E> {
    private static class Node<E> {
        E val;

        public Node(E val) {
            this.val = val;
        }
    }

    HashMap<E, Node<E>> data;  // 存放原始数据
    HashMap<Node<E>, Node<E>> parent;  // 代替node的parent指针，记录node的父亲
    HashMap<Node<E>, Integer> size;  // 每个祖先节点会记录自己有多少元素

    public UnionFind(List<E> values) {
        data = new HashMap<>();
        parent = new HashMap<>();
        size = new HashMap<>();
        for (E value : values) {
            Node<E> node = new Node<>(value);
            data.put(value, node); // 原始数据和包装后的数据映射
            parent.put(node, node);  // 默认所有节点的父节点是自己
            size.put(node, 1);  // 每个数据作为一个单独的集合，集合大小自然是1
        }
    }

    /**
     * 查祖先节点，顺便优化应用关系 “父节点改为祖先” 下次找父节点就是O(1)
     */
    public Node<E> findFather(Node<E> cur) {
        Stack<Node<E>> path = new Stack<>();  // 栈可以用数组替换性能更好
        while (cur != parent.get(cur)) { // 还未找到祖先节点
            path.push(cur);
            cur = parent.get(cur);  // 下轮让父节点向上找
        }

        while (!path.isEmpty()) {
            Node<E> passNode = path.pop();
            parent.put(passNode, cur);  // 沿途遇到的节点，更新他们的父节点为祖先节点
        }

        return cur;  // 找到祖先
    }

    public boolean isSameParent(Node<E> n1, Node<E> n2) {
        return findFather(n1) == findFather(n2);
    }

    /**
     * 合并集合，只需要将小集合的祖先节点挂在大集合的下面
     */
    public void union(E v1, E v2) {
        Node<E> f1 = findFather(data.get(v1));
        Node<E> f2 = findFather(data.get(v2));
        if (f1 != f2) { // 当两个集合的祖先不同才需要合并
            int size1 = size.get(f1);
            int size2 = size.get(f2);
            Node<E> big = size1 > size2 ? f1 : f2; // 找出大集合作为新祖先
            Node<E> small = big == f1 ? f2 : f1;  // 找出小集合
            parent.put(small, big);
            size.put(big, size1 + size2);
            size.remove(small);
        }
    }

    public static void main(String[] args) {

    }
}
