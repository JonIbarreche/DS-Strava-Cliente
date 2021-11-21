package es.deusto.ingenieria.sd.strava.client.controller;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;
import es.deusto.ingenieria.sd.strava.server.data.domain.Sesion;
import es.deusto.ingenieria.sd.strava.server.data.dto.SesionDTO;

public class SesionController {
	private ServiceLocator serviceLocator;
	
	private SesionDTO s;
	
	public SesionController(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
	public boolean crearSesion(String titulo, float distancia, String fechaIni, String horaIni, int duracion) {
		try {
			this.s = this.serviceLocator.getService().crearSesion(titulo, distancia, fechaIni, horaIni, duracion);	
			return true;
		} catch (RemoteException e) {
			System.out.println("# Error en la creación de la sesión: " + e);
			return false;
		}
	}
	
	public List<SesionDTO> getSesiones(){
		try {
			return this.serviceLocator.getService().getSesiones();
		} catch (Exception e) {
			System.out.println("no se pueden obtener las sesiones" + e);
			return null;
		}
	}
	
	public SesionDTO getUsuario() {
		return s;
	}
}
