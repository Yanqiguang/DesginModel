package com.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.rest.dto.Request;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @program: DesignModel
 * @description:
 * @author: lester.yan
 * @create: 2018-12-28 20:41
 **/
@Path("/rest")
public interface RestMethodsFacade {

    @Path("/json")
    @POST
    @Produces("application/json;charset=utf-8")
    @Consumes("text/plain;charset=utf-8")
    String jsonTestMethod(@RequestBody Request request);
}
