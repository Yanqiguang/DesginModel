package com.datastructure;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Vector;

/**
 * @program: DesignModel
 * @description: 栈 遵循先入后出 pop push 插入删除时间复杂度为O（1）、查找时间复杂度为O（n）
 * @author: lester.yan
 * @create: 2019-04-24 13:42
 **/

public class Stack<E> extends Vector<E> {

    protected int elementCount;

    protected Object[] elementData;

    protected int capacityIncrement;

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 元素入栈到栈顶
     *
     * @param item
     * @return
     */
    public E push(E item) {
        addElement(item);
        return item;
    }

    /**
     * 添加元素
     *
     * @param obj
     */
    @Override
    public synchronized void addElement(E obj) {
        modCount++;
        if (elementCount + 1 > elementData.length) {
            grow(elementCount + 1);
        }
        elementData[elementCount++] = obj;
    }

    /**
     * 扩容
     *
     * @param minCapacity
     */
    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + ((capacityIncrement > 0) ? capacityIncrement : oldCapacity);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            if (minCapacity < 0) {
                throw new OutOfMemoryError();
            }
            newCapacity = (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
        }
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    /**
     * 栈顶元素出栈
     *
     * @return
     */
    public synchronized E pop() {
        E obj;
        int len = elementCount;
        obj = peek();
        removeElementAt(len - 1);
        return obj;
    }


    /**
     * 查找栈顶元素，不移除
     *
     * @return
     */
    public synchronized E peek() {
        int len = size();
        if (len == 0) {
            throw new EmptyStackException();
        }
        int index = len - 1;
        if (index >= elementCount) {
            throw new ArrayIndexOutOfBoundsException(index + " >= " + elementCount);
        }
        return (E) elementData[index];
    }

    @Override
    public synchronized void removeElementAt(int index) {
        modCount++;
        if (index >= elementCount) {
            throw new ArrayIndexOutOfBoundsException(index + " >= " +
                    elementCount);
        } else if (index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        int j = elementCount - index - 1;
        if (j > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, j);
        }
        elementCount--;
        elementData[elementCount] = null;
    }
}
