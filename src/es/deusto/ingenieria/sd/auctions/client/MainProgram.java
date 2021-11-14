package es.deusto.ingenieria.sd.auctions.client;

import java.awt.EventQueue;
import java.util.List;

import es.deusto.ingenieria.sd.auctions.client.controller.BidController;
import es.deusto.ingenieria.sd.auctions.client.controller.LoginController;
import es.deusto.ingenieria.sd.auctions.client.controller.RegistroController;
import es.deusto.ingenieria.sd.auctions.client.controller.RetoController;
import es.deusto.ingenieria.sd.auctions.client.controller.SesionController;
import es.deusto.ingenieria.sd.auctions.client.gui.BidWindow;
import es.deusto.ingenieria.sd.auctions.client.gui.LoginDialog;
import es.deusto.ingenieria.sd.auctions.client.gui.LoginWindow;
import es.deusto.ingenieria.sd.auctions.client.gui.VentanaMirar;
import es.deusto.ingenieria.sd.auctions.client.gui.VentanaRegistro;
import es.deusto.ingenieria.sd.auctions.client.gui.VentanaReto;
import es.deusto.ingenieria.sd.auctions.client.gui.VentanaSesion;
import es.deusto.ingenieria.sd.auctions.client.remote.ServiceLocator;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ArticleDTO;
import es.deusto.ingenieria.sd.auctions.server.data.dto.CategoryDTO;

public class MainProgram {


	public static void main(String[] args) {	
		/**
		 * Launch the application.
		 */
		
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						LoginWindow frame = new LoginWindow();
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
		
		//Get Categories
					//List<CategoryDTO> categories = bidWindow.getCategories();
		
		
		//Get Articles of a category (first category is selected)
		List<ArticleDTO> articles = bidWindow.getArticles(categories.get(0).getName());
		//Convert currency to GBP
		bidWindow.currencyToGBP(articles);
		//Convert currency to USD
		bidWindow.currencyToUSD(articles);
		//Place a bid (first article of the category is selected; the token is stored in the BidController)
		bidWindow.makeBid(loginController.getToken(), articles.get(0));
		//Get Articles to check if the bid has been done
		articles = bidWindow.getArticles(categories.get(0).getName());
		//Logout
		loginDialog.logout();
		
		
		
	}
}