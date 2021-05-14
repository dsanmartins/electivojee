package cl.ulagos.electivojee.industriaautomotriz.boundary;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import cl.ulagos.electivojee.industriaautomotriz.control.FactoriaAutomovil;
import cl.ulagos.electivojee.industriaautomotriz.entity.Automovil;
import cl.ulagos.electivojee.industriaautomotriz.entity.AutomovilCreado;
import cl.ulagos.electivojee.industriaautomotriz.entity.Especificacion;


@Named
@RequestScoped
public class ManufacturaAutomovil implements Serializable{

	private static final long serialVersionUID = 1L;

	@Resource
    private UserTransaction utx;
	
	@Inject
	FactoriaAutomovil factoriaAutomovil;

	@PersistenceContext
	EntityManager entityManager;

	@Inject
	Event<AutomovilCreado> automovilCreado; 
	

	public Automovil manufacturaAutomovil(Especificacion especificacion) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {

		Automovil automovil = factoriaAutomovil.manufacturaAutomovil(especificacion);
		utx.begin();
		entityManager.persist(automovil);
		utx.commit();
		automovilCreado.fire(new AutomovilCreado(automovil.getIdentificador()));
		return automovil;
	}

	public List<Automovil> obtenerAutomoviles(){

		List<Automovil> lista = entityManager.createNamedQuery("TODOS_AUTOMOVILES", Automovil.class).getResultList();
		return lista;
	}
}
