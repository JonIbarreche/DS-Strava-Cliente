package es.deusto.ingenieria.sd.strava.client;

import java.awt.EventQueue;
import java.util.List;

import es.deusto.ingenieria.sd.strava.client.controller.BidController;
import es.deusto.ingenieria.sd.strava.client.controller.LoginController;
import es.deusto.ingenieria.sd.strava.client.controller.RegistroController;
import es.deusto.ingenieria.sd.strava.client.controller.RetoController;
import es.deusto.ingenieria.sd.strava.client.controller.SesionController;
import es.deusto.ingenieria.sd.strava.client.gui.BidWindow;
import es.deusto.ingenieria.sd.strava.client.gui.LoginDialog;
import es.deusto.ingenieria.sd.strava.client.gui.LoginWindow;
import es.deusto.ingenieria.sd.strava.client.gui.VentanaMirar;
import es.deusto.ingenieria.sd.strava.client.gui.VentanaRegistro;
import es.deusto.ingenieria.sd.strava.client.gui.VentanaReto;
import es.deusto.ingenieria.sd.strava.client.gui.VentanaSesion;
import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;
import es.deusto.ingenieria.sd.strava.server.data.dto.ArticleDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.CategoryDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.RetoDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.SesionDTO;

public class MainProgram {


	public static void main(String[] args) {	
		/**
		 * Launch the application.
		 */
		
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						LoginWindow frame = new LoginWindow(null);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		ServiceLocator serviceLocator = new ServiceLocator();
		
		//args[0] = RMIRegistry IP
		//args[1] = RMIRegistry Port
		//args[2] = Service Name
		serviceLocator.setService(args[0], args[1], args[2]);
		
		LoginController loginController = new LoginController(serviceLocator);
		LoginWindow loginWindow = new LoginWindow(loginController);
		
		RegistroController registroController = new RegistroController(serviceLocator);
		String plataforma = "google";
		VentanaRegistro ventanaRegistro = new VentanaRegistro(plataforma, registroController);
		
		RetoController retoController = new RetoController(serviceLocator);
		String tod = "Tiempo";
		VentanaReto ventanaReto = new VentanaReto(tod, retoController);
		
		SesionController sesionController = new SesionController(serviceLocator);
		VentanaSesion ventanaSesion = new VentanaSesion(sesionController);
		
		
		LoginDialog loginDialog = new LoginDialog(loginController);			
		BidController bidController = new BidController(serviceLocator);			
		BidWindow bidWindow = new BidWindow(bidController);
		
		
		
		
		//Login
		loginDialog.login();	
		
		//lista de retos
		
		List<RetoDTO> retos = ventanaReto.getRetos();
		
		//lista de sesiones
		
		List<SesionDTO> sesiones = ventanaSesion.getSesiones();
		
		/**
		 * Crear Reto, Crear Sesion, Aceptar Reto
		 */
		
		
		//Get Categories
					//List<CategoryDTO> categories = bidWindow.getCategories();
		
		
		//Get Articles of a category (first category is selected)
				//List<ArticleDTO> articles = bidWindow.getArticles(categories.get(0).getName());
		//Convert currency to GBP
				//bidWindow.currencyToGBP(articles);
		//Convert currency to USD
				//bidWindow.currencyToUSD(articles);
		//Place a bid (first article of the category is selected; the token is stored in the BidController)
				//bidWindow.makeBid(loginController.getToken(), articles.get(0));
		//Get Articles to check if the bid has been done
				//articles = bidWindow.getArticles(categories.get(0).getName());
		//Logout
		loginDialog.logout();
		
		
		
	}
}