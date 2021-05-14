package cl.ulagos.electivojee.industriaautomotriz.boundary;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import cl.ulagos.electivojee.industriaautomotriz.entity.Automovil;
import cl.ulagos.electivojee.industriaautomotriz.entity.Especificacion;

@Path("automoviles")
public class AutomovilResource {

	@Inject
	ManufacturaAutomovil manufacturaAutomovil;

	
	@Context
	UriInfo uriInfo;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Automovil> obtenerAutomoviles(){

		return manufacturaAutomovil.obtenerAutomoviles();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearAutomovil(@Valid @NotNull Especificacion especificacion) throws SecurityException, IllegalStateException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {

		Automovil automovil = manufacturaAutomovil.manufacturaAutomovil(especificacion);
		
		URI uri = uriInfo.getBaseUriBuilder()
				.path(AutomovilResource.class)
				.path(AutomovilResource.class, "obtenerAutomovil")
				.build(automovil.getIdentificador());
		
		return Response.created(uri).build();	
	}
	
}

