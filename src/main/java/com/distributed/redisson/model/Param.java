package com.distributed.redisson.model;

import org.redisson.api.RLock;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2019-01-22 20:55
 **/

public class Param {

    private  boolean locked;

    private RLock lockkk;

    private String key;

    private Long partyId;


    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public RLock getLockkk() {
        return lockkk;
    }

    public void setLockkk(RLock lockkk) {
        this.lockkk = lockkk;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }
}
