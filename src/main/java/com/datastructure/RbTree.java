package com.datastructure;


/**
 * @program: DesignModel
 * @description: 红黑树
 * @author: lester.yan
 * @create: 2019-04-24 17:52
 **/

public class RbTree<T extends Comparable<T>> {

    private Rbnode<T> root;

    private static boolean RED = true;

    private static boolean BLACK = false;


    /**
     * 插入节点
     *
     * @param key
     */
    public void insertNode(T key) {
        Rbnode<T> node = new Rbnode<T>(key, RED, null, null, null);
        Rbnode<T> current = null;
        Rbnode<T> rootNode = this.root;
        //找插入位置
        while (rootNode != null) {
            current = rootNode;
            int cmp = node.key.compareTo(rootNode.key);
            if (cmp < 0) {
                rootNode = rootNode.left;
            } else {
                rootNode = rootNode.right;
            }
        }
        node.parent = current;
        //判断是左节点还是右节点
        if (current != null) {
            int cmp = node.key.compareTo(current.key);
            if (cmp > 0) {
                current.right = node;
            } else {
                current.left = node;
            }
        }
        //fixup(node);
    }

    /**
     * 重新调整树为红黑树有三种情况
     * 1.插入节点的父节点与其叔叔节点都为红色。把当前节点的父节点与叔叔节点都变为黑色，并将祖父变红。
     * 2.插入节点的父节点是红色，叔叔节点树黑色，且插入节点是父节点的右子节点
     * 3.插入节点的父节点是红色，叔叔节点树黑色，且插入节点是父节点的左子节点
     *
     * @param node
     */
    private void fixup(Rbnode<T> node) {
        //定义父节点和祖父节点
        Rbnode<T> parentNode;
        Rbnode<T> grandParentNode;
        while ((parentNode = node.parent) != null && parentNode.color == RED) {
            grandParentNode = parentNode.parent;
            //判断插入节点的父节点是祖父节点左/右节点
            if (parentNode == grandParentNode.left) {
                //获取叔叔节点
                Rbnode<T> uncle = grandParentNode.right;
                //叔叔节点为红色
                if (uncle != null && uncle.color == RED) {
                    parentNode.color = BLACK;
                    uncle.color = BLACK;
                    grandParentNode.color = RED;
                    //在祖父节点位置继续判断
                    node = grandParentNode;
                    continue;
                }
                //叔叔节点为黑色，且为右子节点,右旋
                if (uncle == grandParentNode.right) {
                    leftRotate(parentNode);
                    Rbnode<T> tmp = parentNode;
                    parentNode = node;
                    node = tmp;
                }
                //叔叔、父节点都变为黑色
                parentNode.color = BLACK;
                grandParentNode.color = RED;
                rightRotate(grandParentNode);
            } else {
                //获取叔叔节点
                Rbnode<T> uncle = grandParentNode.left;
                //叔叔节点为红色
                if (uncle != null && uncle.color == RED) {
                    parentNode.color = BLACK;
                    uncle.color = BLACK;
                    grandParentNode.color = RED;
                    //在祖父节点位置继续判断
                    node = grandParentNode;
                    continue;
                }
                //叔叔节点为黑色，且为左子节点,右旋
                if (uncle == grandParentNode.left) {
                    rightRotate(parentNode);
                    Rbnode<T> tmp = parentNode;
                    parentNode = node;
                    node = tmp;
                }
                //叔叔、父节点都变为黑色
                parentNode.color = BLACK;
                grandParentNode.color = RED;
                leftRotate(grandParentNode);
            }
        }
    }


    /**
     * 左旋
     * p                   p
     * /                   /
     * x                   y
     * /  \                 / \
     * lx   y      ---->     x  ry
     * /  \             /  \
     * ly  ry           lx   ly
     * <p>
     * 1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
     * 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
     * 3. 将y的左子节点设为x，将x的父节点设为y
     *
     * @param x
     */
    private void leftRotate(Rbnode<T> x) {
        Rbnode<T> y = x.right;
        //将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        //将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else {
            if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }
        //将y的左子节点设为x，将x的父节点设为y
        y.left = x;
        x.parent = y;

    }

    /**
     * 右旋
     * p                   p
     * /                   /
     * y                   x
     * /  \                 / \
     * x   ry      ---->   lx  y
     * / \                    /  \
     * lx  rx               rx   ry
     * 1.把x的右子节点赋给y的左子节点，同时  y赋值给x的右子节点（rx非空）
     * 2.把y的父节点p赋值给 x的父节点，把p的子节点变为x
     * 3.把y赋值给x的右子节点，把y的父节点赋值为x
     *
     * @param y
     */
    private void rightRotate(Rbnode<T> y) {
        Rbnode<T> x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == null) {
            this.root = x;
        } else {
            if (y == y.parent.left) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        }
        x.right = y;
        y.parent = x;

    }


}
