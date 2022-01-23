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
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import es.deusto.ingenieria.sd.strava.client.controller.RegistroController;
import es.deusto.ingenieria.sd.strava.client.controller.RetoController;
import es.deusto.ingenieria.sd.strava.client.controller.SesionController;
import es.deusto.ingenieria.sd.strava.server.data.dto.SesionDTO;

public class VentanaMirarSesiones extends JFrame{
	protected Container cp;
	protected JPanel panel, panelBoton, panelTabla;
	protected JButton botonVolver;
	protected JMenuBar menuBar;
	protected JMenu menu;
	protected JMenuItem menuItem;
	protected JTable tabla;
	protected JScrollPane scrpTabla;

	public String[] prepararParaLista(SesionDTO s) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaIni = sdf.format(s.getFechaIni());

		String[] preparado = {s.getTitulo(), String.valueOf(s.getDistancia()), fechaIni, s.getHoraIni(), String.valueOf(s.getDuracion())};
		return preparado;


	}

	public VentanaMirarSesiones(String usuario, List<SesionDTO> lista, RegistroController regCtrl,
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

		String[] nombresColumna = {"Titulo", "Distancia", "F. Ini", "Hora Ini", "Duracion"};
		String[][] sesiones = new String[100][100];
		for (int i = 0; i < lista.size(); i++) {
			String[] preparado = prepararParaLista(lista.get(i));
			sesiones[i] = preparado;
		}


		tabla = new JTable(sesiones, nombresColumna);

		scrpTabla = new JScrollPane(tabla);
		Dimension d = tabla.getPreferredSize();
		scrpTabla.setPreferredSize(
		    new Dimension(d.width,tabla.getRowHeight()*12+1));

		panelBoton = new JPanel();
		panel.setLayout(new FlowLayout());

		botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaPrincipal k = new VentanaPrincipal(usuario, regCtrl, retCtrl, sesCtrl);
				k.setVisible(true);
				dispose();
			}
		});


		cp.add(panel);
		setJMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(menuItem);

		panel.add(panelTabla);
		panel.add(panelBoton);

		panelBoton.add(botonVolver);

		panelTabla.add(scrpTabla);

		setVisible(true);
		pack();
		setSize(628,316);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
