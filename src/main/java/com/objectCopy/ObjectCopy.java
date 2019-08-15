package com.objectCopy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.google.common.primitives.Ints;
import org.assertj.core.util.Lists;

/**
 * @program: DesignModel
 * @description: 深、浅对象copy
 * @author: lester.yan
 * @create: 2019-08-01 17:57
 **/

public class ObjectCopy {

    /**
     * @Description: 用序列化与反序列化进行深copy
     * @Param: []
     * @return: void
     * @Author: lester.yan
     * @Date: 2019/8/5
     */
    public static void deepCopyBySerializable() {
        ParentObj parentObj = new ParentObj();
        ChildObj childObj = new ChildObj();
        childObj.setChildName("Son");
        parentObj.setChildObj(childObj);
        List<ChildObj> childObjs = Lists.newArrayList();
        childObjs.add(childObj);
        parentObj.setChildObjs(childObjs);
        parentObj.setName("Parent");
        parentObj.setListStr(Arrays.asList(new String[]{"1", "2", "3"}));

        ParentObj copy = JSON.parseObject(JSON.toJSONString(parentObj), ParentObj.class);
        System.out.println(Ints.compare(copy.getChildObj().hashCode(), parentObj.getChildObj().hashCode()));
    }

    /**
    * @Description: 重写clone 方法 进行深copy
    * @Param: []
    * @return: void
    * @Author: lester.yan
    * @Date: 2019/8/5
    */
    public static void deepCopyBySerializable2() throws CloneNotSupportedException {
        ParentObj parentObj = new ParentObj();
        ChildObj childObj = new ChildObj();
        childObj.setChildName("Son");
        parentObj.setChildObj(childObj);
        List<ChildObj> childObjs = Lists.newArrayList();
        childObjs.add(childObj);
        parentObj.setChildObjs(childObjs);
        parentObj.setName("Parent");
        parentObj.setListStr(Arrays.asList(new String[]{"1", "2", "3"}));

        ParentObj copy = (ParentObj) parentObj.clone();
        System.out.println(Ints.compare(copy.getChildObj().hashCode(), parentObj.getChildObj().hashCode()));
    }

    /** 
    * @Description: 浅copy
    * @Param: []  
    * @return: void 
    * @Author: lester.yan 
    * @Date: 2019/8/5 
    */ 
    public static void shallowCopyBySerializable() throws CloneNotSupportedException {
        ParentObj parentObj = new ParentObj();
        ChildObj childObj = new ChildObj();
        childObj.setChildName("Son");
        parentObj.setChildObj(childObj);
        List<ChildObj> childObjs = Lists.newArrayList();
        childObjs.add(childObj);
        parentObj.setChildObjs(childObjs);
        parentObj.setName("Parent");
        parentObj.setListStr(Arrays.asList(new String[]{"1", "2", "3"}));

        ParentObj copy = (ParentObj) parentObj.clone();
        System.out.println(Ints.compare(copy.getChildObjs().hashCode(), parentObj.getChildObjs().hashCode()));
    }

    public static void main(String[] args) throws Exception{
        deepCopyBySerializable();
        deepCopyBySerializable2();
        shallowCopyBySerializable();
    }
}
