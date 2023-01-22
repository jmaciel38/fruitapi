package com.ccjmteclab.apitemplate.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import com.ccjmteclab.apitemplate.domain.Fruit;
import com.ccjmteclab.apitemplate.services.FruitService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * FruitResource class
 * 
 * @author Jose Maciel <jose.s.maciel@outlook.com>
 * @version 1.0
 */
@ApplicationScoped
@Path("/fruits")
public class FruitResource {

    private FruitService fruitService;

    private static final Logger LOGGER = Logger.getLogger(FruitResource.class.getName());

    public FruitResource(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    /** 
     * @return Response
     */
    @GET
    public Response list() {

        return Response.ok(fruitService.findAll()).status(200).build();
    }

    /** 
     * @param id
     * @return Response
     */
    @GET
    @Path("{id}")
    public Response getById(Long id) {

        Fruit entity = fruitService.findById(id);

        if (entity == null) {
            throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
        }

        return Response.ok(entity).status(200).build();
    }

    /** 
     * @param fruit
     * @return Response
     */
    @POST
    public Response create(Fruit fruit) {

        if (fruit.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        fruitService.save(fruit);

        return Response.ok(fruit).status(201).build();
    }

    /** 
     * @param id
     * @param fruit
     * @return Response
     */
    @PUT
    @Path("{id}")
    public Response update(Long id, Fruit fruit) {
        if (fruit.name == null) {
            throw new WebApplicationException("Fruit Name was not set on request.", 422);
        }

        Fruit entity = fruitService.findById(id);

        if (entity == null) {
            throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
        }

        entity = fruitService.update(id, fruit);

        return Response.ok(entity).status(200).build();
    }

    /** 
     * @param id
     * @return Response
     */
    @DELETE
    @Path("{id}")
    public Response delete(Long id) {

        Fruit entity = fruitService.findById(id);

        if (entity == null) {
            throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
        }

        fruitService.delete(id);

        return Response.status(204).build();
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception exception) {
            LOGGER.error("Failed to handle request", exception);

            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            ObjectNode exceptionJson = objectMapper.createObjectNode();
            exceptionJson.put("exceptionType", exception.getClass().getName());
            exceptionJson.put("code", code);

            if (exception.getMessage() != null) {
                exceptionJson.put("error", exception.getMessage());
            }

            return Response.status(code)
                    .entity(exceptionJson)
                    .build();
        }

    }
}
