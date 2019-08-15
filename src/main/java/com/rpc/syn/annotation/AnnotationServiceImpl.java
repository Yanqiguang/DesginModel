package com.rpc.syn.annotation;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import org.springframework.stereotype.Component;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2018-12-21 14:20
 **/
@Component
@SofaService(interfaceType = AnnotationService.class, bindings = {@SofaServiceBinding(bindingType = "bolt"),
        @SofaServiceBinding(bindingType = "dubbo"), @SofaServiceBinding(bindingType = "rest")})
public class AnnotationServiceImpl implements AnnotationService {

    @Override
    public String annotationMethod(String value) {
        return " rpc server say:" + value;
    }
}
