package com.datastructure;


import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * @program: DesignModel
 * @description: 链表数据结构  链表分为:单向链表（只有next）、双向链表（next、prev）、循环链表（fist.prev=last last.next=first）  add、remove
 * 时间复杂度为O（1） query index 时间复杂度为O（n）
 * @author: lester.yan
 * @create: 2019-04-24 10:28
 **/

public class LinkedList<E> extends AbstractSequentialList<E> {


    /**
     * 链表大小
     */
    transient int size;

    /**
     * transient 不进行序列化
     */
    transient Node<E> first;


    transient Node<E> last;


    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        /**
         * 设置链表中的节点信息
         *
         * @param prev    前一个
         * @param element 元素信息
         * @param next    下一个节点
         */
        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * 获取第一个节点
     *
     * @return
     */
    public E getFirst() {
        final Node<E> l = first;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return l.item;
    }

    /**
     * 获取最后一个节点
     *
     * @return
     */
    public E getLast() {
        final Node<E> l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return l.item;
    }

    /**
     * 把新节点放到链表首节点
     *
     * @param e
     * @return
     */
    public void linkFirst(E e) {
        final Node<E> firstNode = first;
        final Node<E> newNode = new Node(null, e, first);
        first = newNode;
        if (firstNode == null) {
            last = newNode;
        } else {
            firstNode.prev = newNode;
        }
        size++;
        modCount++;//? 作用
    }

    /**
     * 链表末端添加节点
     *
     * @param e
     */
    public void linkLast(E e) {
        final Node<E> lastNode = last;
        final Node<E> newNode = new Node(null, e, last);
        last = lastNode;
        if (lastNode == null) {
            last = newNode;
        } else {
            lastNode.next = newNode;
        }
        size++;
        modCount++;//? 作用
    }

    /**
     * 删除并返回首节点
     *
     * @param f
     * @return
     */
    private E unlinkFirst(Node<E> f) {
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        //  help GC
        f.next = null;
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        modCount++;
        return element;
    }

    /**
     * 删除并返回尾节点
     *
     * @param l
     * @return
     */
    private E unlinkLast(Node<E> l) {
        final E element = l.item;
        final Node<E> prevNode = l.prev;
        l.item = null;
        l.prev = null;
        last = prevNode;
        if (prevNode == null) {
            first = null;
        } else {
            prevNode.next = null;
        }
        size--;
        modCount++;
        return element;
    }

    /**
     * 删除并返回某节点
     *
     * @param node
     * @return
     */
    private E unlink(Node<E> node) {
        final E elemet = node.item;
        final Node<E> prevNode = node.prev;
        final Node<E> nextNode = node.next;
        if (prevNode == null) {
            first = nextNode;
        } else {
            prevNode.next = nextNode;
            node.next = null;
        }
        if (nextNode == null) {
            last = prevNode;
        } else {
            nextNode.prev = prevNode;
            node.prev = null;
        }
        node.item = null;
        size--;
        modCount++;
        return elemet;
    }


    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }


    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {

    }

    @Override
    public void sort(Comparator<? super E> c) {

    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return false;
    }

    @Override
    public Stream<E> stream() {
        return null;
    }

    @Override
    public Stream<E> parallelStream() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super E> action) {

    }
}
