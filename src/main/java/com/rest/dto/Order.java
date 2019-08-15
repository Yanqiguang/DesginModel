package com.rest.dto;

import java.util.Date;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2018-12-28 20:44
 **/

public class Order {

    private static final long serialVersionUID = -3002886871245303082L;

    private Integer orderNo;

    private Date startDate;

    private Date endDate;

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
