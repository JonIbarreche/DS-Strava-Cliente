package es.deusto.ingenieria.sd.strava.client.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import es.deusto.ingenieria.sd.strava.client.controller.RegistroController;
import es.deusto.ingenieria.sd.strava.client.controller.RetoController;
import es.deusto.ingenieria.sd.strava.client.controller.SesionController;
import es.deusto.ingenieria.sd.strava.server.data.dto.RetoDTO;

public class VentanaMirarRetosActivos extends JFrame{
	protected Container cp;
	protected JPanel panel, panelBoton, panelTabla;
	protected JMenuBar menuBar;
	protected JMenu menu;
	protected JMenuItem menuItem;
	protected JTable tabla;
	protected JScrollPane scrpTabla;
	private JPanel panelVolver;
	private JButton botonVolver;
	private JPanel panelAceptar;
	private JButton botonAceptar;
	private JPanel panelElegir;
	private JComboBox comboBox;

	public String[] prepararParaLista(RetoDTO r) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaIni = sdf.format(r.getFechaIni());
		String fechaFin = sdf.format(r.getFechaFin());
		if(r.getTiempo() == 0) {
			String[] preparado = {r.getNombreReto(), fechaIni, fechaFin, String.valueOf(r.getDistancia()), "null", r.getDeporte()};
			return preparado;
		} else if(r.getDistancia() == 0) {
			String[] preparado = {r.getNombreReto(), fechaIni, fechaFin, "null", String.valueOf(r.getTiempo()), r.getDeporte()};
			return preparado;
		} else {
			String[] preparado = {r.getNombreReto(), fechaIni, fechaFin, String.valueOf(r.getDistancia()), String.valueOf(r.getTiempo()), r.getDeporte()};
			return preparado;
		}

	}

	public VentanaMirarRetosActivos(String usuario, List<RetoDTO> lista, RegistroController regCtrl,
			RetoController retCtrl, SesionController sesCtrl) {
		cp = this.getContentPane();
		this.setTitle("Registro");

		panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));

		menuBar = new JMenuBar();
		menu = new JMenu("M�s opciones");

		menuItem = new JMenuItem("Log out");
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose(); //sierra ventana

			}
		});
		panelTabla = new JPanel();
		panelTabla.setLayout(new FlowLayout());

		String[] nombresColumna = {"Nombre", "F. Inicio", "F. Fin", "Distancia", "Tiempo", "Deporte"};
		String[][] retos = new String[100][100];
		for (int i = 0; i < lista.size(); i++) {
			String[] preparado = prepararParaLista(lista.get(i));
			retos[i] = preparado;
		}


		tabla = new JTable(retos, nombresColumna);

		scrpTabla = new JScrollPane(tabla);
		Dimension d = tabla.getPreferredSize();
		scrpTabla.setPreferredSize(
		    new Dimension(d.width,tabla.getRowHeight()*12+1));
		
		
		panelBoton = new JPanel();
		panel.setLayout(new FlowLayout());


		cp.add(panel);
		setJMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(menuItem);

		panel.add(panelTabla);
		panel.add(panelBoton);
		panelBoton.setLayout(new GridLayout(0, 1, 0, 0));
		
		panelVolver = new JPanel();
		panelBoton.add(panelVolver);
		
		botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal vp = new VentanaPrincipal(usuario, regCtrl, retCtrl, sesCtrl);
				dispose();

			}

		});

		
		panelVolver.add(botonVolver);
		

		panelTabla.add(scrpTabla);

		setVisible(true);
		pack();
		setSize(628,316);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
