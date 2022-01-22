package es.deusto.ingenieria.sd.strava.client.controller;

import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;
import es.deusto.ingenieria.sd.strava.server.data.domain.Tipo;
import es.deusto.ingenieria.sd.strava.server.data.dto.UsuarioDTO;


public class RegistroController {

	private ServiceLocator serviceLocator;

	private UsuarioDTO u;

	public RegistroController(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public UsuarioDTO registro(String email, String nombre, String fecha, int peso, int altura, int max,
			int rep, String contrasena, Tipo tipo) {
	    try {
			this.u = this.serviceLocator.getService().crearUsuario(email, nombre, fecha, peso, altura, max, rep, contrasena, tipo);
			return u;
		} catch (Exception e) {
			System.out.println("# Error en el registro: " + e);
			return null;
		}
	}

	public UsuarioDTO getUsuario() {
		return u;
	}
}
