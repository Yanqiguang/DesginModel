package com.rpc.syn.annotation;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import org.springframework.stereotype.Component;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2018-12-21 14:23
 **/
@Component
public class AnnotationClient {

    @SofaReference(interfaceType = AnnotationService.class, binding = @SofaReferenceBinding(bindingType = "bolt"))
    private AnnotationService annotationService;

    public String annotationClient(String  value){
        return annotationService.annotationMethod(value);
    }
}
