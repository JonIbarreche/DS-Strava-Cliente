package es.deusto.ingenieria.sd.strava.client.controller;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;
import es.deusto.ingenieria.sd.strava.server.data.dto.RetoDTO;

public class RetoController {
	private ServiceLocator serviceLocator;

	private RetoDTO r;


	public RetoController(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public RetoDTO crearReto(String nombreReto, Date fechaIni, Date fechaFin, float distancia, int tiempo, String deporte) {
		try {
			this.r = this.serviceLocator.getService().crearReto(nombreReto, fechaIni, fechaFin, distancia, tiempo, deporte);
			return r;
		} catch (RemoteException e) {
			System.out.println("# Error en la creación del Reto: " + e);
			return null;
		}
	}

	public List<RetoDTO> getRetos() {
		try {
			List<RetoDTO> l = this.serviceLocator.getService().getRetos();
			if (l != null) {
				return l;
			} else {
				return null;
			}
		} catch (RemoteException e) {
			System.out.println("# Error lista de retos: " + e);
			return null;
		}
	}
	
	public List<RetoDTO> getRetosActivos(String mail) {
		try {
			List<RetoDTO> l = this.serviceLocator.getService().getRetosActivos(mail);
			if (l != null) {
				return l;
			} else {
				return null;
			}
		} catch (RemoteException e) {
			System.out.println("# Error lista de retos: " + e);
			return null;
		}
	}
	
	public RetoDTO getUsuario() {
		return r;
	}
	
	public boolean aceptarReto(String r, String u) {
		try {
			return this.serviceLocator.getService().aceptarReto(r, u);
		} catch (Exception e) {
			System.out.println("no se ha podido aceptar el reto");
			return false;
		}
	}
}
