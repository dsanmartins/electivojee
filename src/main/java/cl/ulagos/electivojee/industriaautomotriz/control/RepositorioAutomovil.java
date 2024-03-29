package cl.ulagos.electivojee.industriaautomotriz.control;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import cl.ulagos.electivojee.industriaautomotriz.entity.Automovil;
import cl.ulagos.electivojee.industriaautomotriz.entity.Color;
import cl.ulagos.electivojee.industriaautomotriz.entity.TipoMotor;

public class RepositorioAutomovil {

	List<Automovil> listaAutomovil = new ArrayList<Automovil>();

	public void store(Automovil automovil) {
		//store
		listaAutomovil.add(automovil);
	}

	public Automovil obtenerAutomovil(String id) {

		for (Automovil automovil : listaAutomovil)
		{
			if (automovil.getIdentificador().equals(id))
				return automovil;
		}

		return null;
	}

	public List<Automovil> cargarAutomoviles(){

		return listaAutomovil;
	}

	public List<Automovil> cargarAutomoviles(TipoMotor tipo){

		return listaAutomovil.stream().filter(automovil->automovil.getTipoMotor().equals(tipo)).collect(Collectors.toList());
	}

	public void inicializar() {

		Automovil automovil = new Automovil();
		automovil.setIdentificador(UUID.randomUUID().toString());
		automovil.setTipoMotor(TipoMotor.ELECTRICO);
		automovil.setColor(Color.NEGRO);
		listaAutomovil.add(automovil);

		automovil = new Automovil();
		automovil.setIdentificador(UUID.randomUUID().toString());
		automovil.setTipoMotor(TipoMotor.GASOLINA);
		automovil.setColor(Color.BLANCO);
		listaAutomovil.add(automovil);
	}

}
