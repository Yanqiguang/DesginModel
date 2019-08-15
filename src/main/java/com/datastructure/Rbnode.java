package com.datastructure;

/**
 * @program: DesignModel
 * @description: 红黑树节点
 * @author: lester.yan
 * @create: 2019-04-24 18:08
 **/

public class Rbnode<T extends Comparable<T>> {


    private static boolean RED = true;
    /**
     * 颜色
     */
    boolean color;
    /**
     * 关键字(键值)
     */
    T key;
    /**
     * 左子节点
     */
    Rbnode<T> left;
    /**
     * 右子节点
     */
    Rbnode<T> right;
    /**
     * 父节点
     */
    Rbnode<T> parent;

    public Rbnode(T key, boolean color, Rbnode<T> left, Rbnode<T> right, Rbnode<T> parent) {
        this.key = key;
        this.color = color;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public T getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "" + key + (this.color == RED ? "R" : "B");
    }

}