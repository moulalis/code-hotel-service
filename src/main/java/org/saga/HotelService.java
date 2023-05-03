package org.saga;

import org.eclipse.microprofile.lra.annotation.Compensate;
import org.eclipse.microprofile.lra.annotation.Complete;
import org.eclipse.microprofile.lra.annotation.ws.rs.LRA;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/hotel")
public class HotelService {

    @LRA(end = false)
    @GET
    @Path("/book")
    public Response book(@HeaderParam(LRA.LRA_HTTP_CONTEXT_HEADER) String lraId){
        logNicely("Booking hotel "+lraId);
        performBooking();
        return Response.status(500).build();
      // return Response.ok().build();
    }
    @Compensate
    @PUT
    @Path("/cancel")
    public Response cancel(@HeaderParam(LRA.LRA_HTTP_CONTEXT_HEADER) String lraId){
        logNicely("Cancelling hotel "+lraId);
        return Response.ok().build();
    }

    @Complete
    @PUT
    @Path("/complete")
    public Response complete(@HeaderParam(LRA.LRA_HTTP_CONTEXT_HEADER) String lraId){
        logNicely("Complete hotel "+lraId);
        return Response.ok().build();
    }

    private void performBooking() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void logNicely(String value) {
        System.out.println("==============================");
        System.out.println("Value: "+value);
        System.out.println("==============================");
    }
}
//@HeaderParam(LRA.LRA_HTTP_CONTEXT_HEADER) String lraId