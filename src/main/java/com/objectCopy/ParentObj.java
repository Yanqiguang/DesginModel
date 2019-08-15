package com.objectCopy;

import java.io.Serializable;
import java.util.List;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2019-08-01 17:58
 **/

public class ParentObj implements Cloneable, Serializable {


    private static final long serialVersionUID = 6795624003812730880L;

    private String name;

    private List<String> listStr;

    private ChildObj childObj;

    private List<ChildObj> childObjs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getListStr() {
        return listStr;
    }

    public void setListStr(List<String> listStr) {
        this.listStr = listStr;
    }

    public ChildObj getChildObj() {
        return childObj;
    }

    public void setChildObj(ChildObj childObj) {
        this.childObj = childObj;
    }

    public List<ChildObj> getChildObjs() {
        return childObjs;
    }

    public void setChildObjs(List<ChildObj> childObjs) {
        this.childObjs = childObjs;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        ParentObj clonObject = (ParentObj) super.clone();
        clonObject.childObj = (ChildObj) this.childObj.clone();
        return clonObject;
    }


}
