package com.objectCopy;

import java.io.Serializable;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2019-08-01 17:58
 **/

public class ChildObj implements Cloneable, Serializable {


    private static final long serialVersionUID = -8315197789410229158L;

    private String childName ;

    public String getChildName() {

        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
