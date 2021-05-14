package cl.ulagos.electivojee.industriaautomotriz.boundary;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import cl.ulagos.electivojee.industriaautomotriz.entity.Color;
import cl.ulagos.electivojee.industriaautomotriz.entity.Especificacion;
import cl.ulagos.electivojee.industriaautomotriz.entity.TipoMotor;

@Named("automovilBacking")
@SessionScoped
public class AutomovilBackingBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject 
	private ManufacturaAutomovil manufacturaAutomovil; 
	
	private String color;
	
	private String  tipoMotor;
	
	public String crearAutomovil() throws SecurityException, IllegalStateException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException
	{
		Especificacion especifiacion = new Especificacion(Color.valueOf(this.color), TipoMotor.valueOf(tipoMotor));
		manufacturaAutomovil.manufacturaAutomovil(especifiacion);
		FacesContext.getCurrentInstance().addMessage("formulario:mensaje", new FacesMessage("Automovil Creado", "Automovil Creado"));

		return "Automovil Creado";
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTipoMotor() {
		return tipoMotor;
	}

	public void setTipoMotor(String tipoMotor) {
		this.tipoMotor = tipoMotor;
	}
	
	
}
