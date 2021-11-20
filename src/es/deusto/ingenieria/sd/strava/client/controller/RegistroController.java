package es.deusto.ingenieria.sd.strava.client.controller;

import java.rmi.RemoteException;

import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;


public class RegistroController {
	
	private ServiceLocator serviceLocator;
	
	private Usuario u;
	
	public RegistroController(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
	public boolean registro(String email, String nombre, String fecha, String contraseña, int peso, int altura, int max,
			int rep) {
		try {
			this.u = this.serviceLocator.getService(email, nombre, fecha, contraseña, peso, altura, max, rep);	
			return true;
		} catch (RemoteException e) {
			System.out.println("# Error en el registro: " + e);
			return false;
		}
	}
	
	public Usuario getUsuario() {
		return u;
	}
}
