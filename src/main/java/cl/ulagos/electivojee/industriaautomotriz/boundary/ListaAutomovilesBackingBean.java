package cl.ulagos.electivojee.industriaautomotriz.boundary;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cl.ulagos.electivojee.industriaautomotriz.entity.Automovil;

@Named("dtAutomoviles")
@ViewScoped
public class ListaAutomovilesBackingBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Automovil> automoviles;
	private Automovil automovilSeleccionado;

	@Inject 
	private ManufacturaAutomovil manufacturaAutomovil; 

	@PostConstruct
	public void init() {

		automoviles = manufacturaAutomovil.obtenerAutomoviles();
	}

	public List<Automovil> getAutomoviles(){

		return automoviles;
	}

	public Automovil getAutomovilSeleccionado() {

		return automovilSeleccionado;
	}

	public void setAutomovilSeleccionado(Automovil automovilSeleccionado) {

		this.automovilSeleccionado = automovilSeleccionado;
	}


}
