package es.deusto.ingenieria.sd.strava.client.controller;

import java.rmi.RemoteException;

import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;
import es.deusto.ingenieria.sd.strava.server.data.domain.Tipo;

//This class implements Controller pattern.
public class LoginController {

	//Reference to the Service Locator
	private ServiceLocator serviceLocator;
	//This attibute stores the token when login success
	private long token = -1; //-1 = login has not been done or fails

	public LoginController(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public boolean login(String email, String password, Tipo plataforma) {
		try {
			this.token = this.serviceLocator.getService().login(email, password, plataforma);
			if (token != -1) {
				return true;
			} else {
				return false;
			}
		} catch (RemoteException e) {
			System.out.println("# Error during login: " + e);
			this.token = -1;
			return false;
		}
	}

	public void logout() {
		try {
			this.serviceLocator.getService().logout(this.token);
			this.token = -1;
		} catch (RemoteException e) {
			System.out.println("# Error during logout: " + e);
		}
	}

	public long getToken() {
		return token;
	}
}