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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import es.deusto.ingenieria.sd.strava.client.controller.RegistroController;
import es.deusto.ingenieria.sd.strava.client.controller.RetoController;
import es.deusto.ingenieria.sd.strava.client.controller.SesionController;
import es.deusto.ingenieria.sd.strava.server.data.dto.SesionDTO;
import javax.swing.JComboBox;

public class VentanaMirarSesiones extends JFrame{
	protected Container cp;
	protected JPanel panel, panelTabla;
	protected JMenuBar menuBar;
	protected JMenu menu;
	protected JMenuItem menuItem;
	protected JTable tabla;
	protected JScrollPane scrpTabla;
	private JPanel panelOpciones;
	private JPanel panelBoton;
	private JButton botonVolver;
	private JPanel panelComboBox;
	private JComboBox comboBox;
	private JPanel panelBotonAceptar;
	private JButton botonAceptar;

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
		menu = new JMenu("M???s opciones");

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
		panel.setLayout(new FlowLayout());


		cp.add(panel);
		setJMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(menuItem);

		panel.add(panelTabla);
		
		panelOpciones = new JPanel();
		panel.add(panelOpciones);
		panelOpciones.setLayout(new GridLayout(0, 1, 0, 0));
		
		panelBoton = new JPanel();
		panelOpciones.add(panelBoton);
		
		botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal vp = new VentanaPrincipal(usuario, regCtrl, retCtrl, sesCtrl);
				dispose();

			}

		});
		panelBoton.add(botonVolver);
		
		panelBotonAceptar = new JPanel();
		panelOpciones.add(panelBotonAceptar);
		
	
		
		panelComboBox = new JPanel();
		panelOpciones.add(panelComboBox);
		String[] nombresSesion = new String[100];
		int j = 0;
		for (int i = 0; i < lista.size(); i++) {
			nombresSesion[j] = lista.get(i).getTitulo();
			j++;
		}
		comboBox = new JComboBox(nombresSesion);
		panelComboBox.add(comboBox);
		
		botonAceptar = new JButton("Aceptar Sesion");
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sesCtrl.aceptarSesion(usuario, nombresSesion[comboBox.getSelectedIndex()]);
				JOptionPane creado = new JOptionPane();
				JOptionPane.showMessageDialog(null, "Sesion Aceptada Correctamente");
			}
		});
		panelBotonAceptar.add(botonAceptar);
		panelTabla.add(scrpTabla);

		setVisible(true);
		pack();
		setSize(628,316);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
