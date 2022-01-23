package es.deusto.ingenieria.sd.strava.client.gui;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import es.deusto.ingenieria.sd.strava.client.controller.RegistroController;
import es.deusto.ingenieria.sd.strava.client.controller.RetoController;
import es.deusto.ingenieria.sd.strava.client.controller.SesionController;
import es.deusto.ingenieria.sd.strava.server.data.dto.RetoDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.SesionDTO;

public class VentanaPrincipal extends JFrame{
	protected Container cp;
	protected JPanel panel, panelBoton1,panelBoton2,panelBoton3;
	protected JButton crearReto, mirarRetos, crearSesion;
	protected JMenuBar menuBar;
	protected JMenu menu;
	protected JMenuItem menuItem;
	protected JOptionPane opcionCreacion;
	private JPanel panelBoton4;
	private JButton verSesiones;
	private JPanel panelBoton5;
	private JButton verRetosActivos;
	private JPanel panelBoton6;
	private JButton verSesionesUsuario;


	public VentanaPrincipal(String usuario, RegistroController regCtrl,
			RetoController retCtrl, SesionController sesCtrl) {

		List<RetoDTO> listaReto = retCtrl.getRetos();

		List<SesionDTO> listaSesion = sesCtrl.getSesiones();
		
		List<RetoDTO> listaRetoActivo = retCtrl.getRetosActivos(usuario);
		
		List<SesionDTO> listaSesionesUsuario = sesCtrl.getSesionesUsuario(usuario);
		
		cp = this.getContentPane();
		this.setTitle("Registro");

		panel = new JPanel();
		panel.setLayout(new GridLayout(3,1));

		menuBar = new JMenuBar();
		menu = new JMenu("M�s opciones");

		menuItem = new JMenuItem("Log out");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose(); //sierra ventana

			}
		});

		panelBoton1 = new JPanel();
		panelBoton1.setLayout(new FlowLayout());

		panelBoton2 = new JPanel();
		panelBoton2.setLayout(new FlowLayout());

		panelBoton3 = new JPanel();
		panelBoton3.setLayout(new FlowLayout());

		crearReto = new JButton("Crear Nuevo Reto");
		crearReto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				opcionCreacion = new JOptionPane();
				int seleccion = JOptionPane.showOptionDialog(null,"Seleccione una opcion",
						  "Selector de opciones",JOptionPane.YES_NO_CANCEL_OPTION,
						   JOptionPane.QUESTION_MESSAGE,null,// null para icono por defecto.
						  new Object[] { "Distancia", "Tiempo"},"opcion 1");

				if (seleccion != -1) {
					seleccion += 1;
					if (seleccion == 1) {
						VentanaReto i = new VentanaReto(usuario, "Distancia",regCtrl, retCtrl, sesCtrl);
					} else if (seleccion == 2) {
						VentanaReto i = new VentanaReto(usuario, "Tiempo", regCtrl, retCtrl, sesCtrl);
					}
				}
				dispose();

			}

		});

		mirarRetos = new JButton("Ver Todos Retos");
		mirarRetos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaMirarRetos l = new VentanaMirarRetos(usuario, listaReto, regCtrl, retCtrl, sesCtrl);
				dispose();
			}

		});

		crearSesion = new JButton("Crear Una Nueva Sesion");
		crearSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaSesion j = new VentanaSesion(usuario, regCtrl, retCtrl, sesCtrl);
				dispose();

			}

		});




		cp.add(panel);
		setJMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(menuItem);

		panel.add(panelBoton1);
		panel.add(panelBoton2);
		panel.add(panelBoton3);

		panelBoton1.add(crearReto);
		panelBoton2.add(crearSesion);
		panelBoton3.add(mirarRetos);

		panelBoton4 = new JPanel();
		panel.add(panelBoton4);

		verSesiones = new JButton("Ver Sesiones");
		verSesiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMirarSesiones vms = new VentanaMirarSesiones(usuario, listaSesion, regCtrl, retCtrl, sesCtrl);
				dispose();
			}
		});
		panelBoton4.add(verSesiones);

		panelBoton5 = new JPanel();
		panel.add(panelBoton5);

		verRetosActivos = new JButton("Ver Retos Activos");
		verRetosActivos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaMirarRetosActivos vmra = new VentanaMirarRetosActivos(usuario, listaRetoActivo, regCtrl, retCtrl, sesCtrl);
				dispose();
			}
		});
		panelBoton5.add(verRetosActivos);
		
		panelBoton6 = new JPanel();
		panel.add(panelBoton6);
		
		verSesionesUsuario = new JButton("Ver tus Sesiones");
		verSesionesUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMirarSesionesUsuario vmsu = new VentanaMirarSesionesUsuario(usuario, listaSesionesUsuario, regCtrl, retCtrl, sesCtrl);
			}
		});
		panelBoton6.add(verSesionesUsuario);

		setVisible(true);
		pack();
		setSize(473,276);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
