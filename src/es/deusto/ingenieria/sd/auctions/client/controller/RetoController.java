package es.deusto.ingenieria.sd.auctions.client.controller;

import java.rmi.RemoteException;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.client.remote.ServiceLocator;

public class RetoController {
	private ServiceLocator serviceLocator;
	
	private Reto r;
	
	
	public RetoController(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
	public boolean crearReto(String nombreReto, String fechaIni, String fechaFin, float distancia, int tiempo, String deporte) {
		try {
			this.r = this.serviceLocator.getReto(nombreReto, fechaIni, fechaFin, distancia, tiempo, deporte);	
			return true;
		} catch (RemoteException e) {
			System.out.println("# Error en la creación del Reto: " + e);
			return false;
		}
	}
	
	public List<RetoDTO> getRetos() {
		try {
			return this.serviceLocator.getService().getRetos();
		} catch (RemoteException e) {
			System.out.println("# Error lista de retos: " + e);
			return null;
		}
	}
	
	public Reto getUsuario() {
		return r;
	}
}
