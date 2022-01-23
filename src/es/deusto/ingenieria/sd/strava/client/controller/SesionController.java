package es.deusto.ingenieria.sd.strava.client.controller;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;
import es.deusto.ingenieria.sd.strava.server.data.dto.SesionDTO;

public class SesionController {
	private ServiceLocator serviceLocator;

	private SesionDTO s;

	public SesionController(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public boolean crearSesion(String titulo, float distancia, Date fechaIni, String horaIni, int duracion) {
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
	
	public List<SesionDTO> getSesionesUsuario(String usuario){
		try {
			return this.serviceLocator.getService().getSesionesUsuario(usuario);
		} catch (Exception e) {
			System.out.println("no se pueden obtener las sesiones" + e);
			return null;
		}
	}
	
	public void aceptarSesion(String usuario, String sesion) {
		try {
			this.serviceLocator.getService().aceptarSesion(usuario, sesion);
		} catch (Exception e) {
			System.out.println("no se ha podido aceptar la sesion"+e);
		}
	}
	
	public SesionDTO getUsuario() {
		return s;
	}
}
