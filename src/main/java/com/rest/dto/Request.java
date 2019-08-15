package com.rest.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rpc.syn.code.Person;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2018-12-28 20:43
 **/

public class Request {

    private static final long serialVersionUID = -3002886871245303082L;

    private String requestId;

    private String reqquestTime;

    private Person person;

    private List<Order> orders =new ArrayList<>(0);

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getReqquestTime() {
        return reqquestTime;
    }

    public void setReqquestTime(String reqquestTime) {
        this.reqquestTime = reqquestTime;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
