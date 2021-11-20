package es.deusto.ingenieria.sd.strava.client.controller;

import java.rmi.RemoteException;

import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;

public class SesionController {
	private ServiceLocator serviceLocator;
	
	private Sesion s;
	
	public SesionController(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
	public boolean crearSesion(String titulo, float distancia, String fechaIni, String horaIni, int duracion) {
		try {
			this.s = this.serviceLocator.getSesion(titulo, distancia, fechaIni, horaIni, duracion);	
			return true;
		} catch (RemoteException e) {
			System.out.println("# Error en la creación de la sesión: " + e);
			return false;
		}
	}
	
	public Sesion getUsuario() {
		return s;
	}
}
