package es.deusto.ingenieria.sd.strava.client.gui;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import es.deusto.ingenieria.sd.strava.client.controller.RegistroController;
import es.deusto.ingenieria.sd.strava.client.controller.RetoController;
import es.deusto.ingenieria.sd.strava.client.controller.SesionController;
import es.deusto.ingenieria.sd.strava.server.data.dto.RetoDTO;
import com.toedter.calendar.JDateChooser;

public class VentanaReto extends JFrame {
	
	protected JPanel panel,panelElige,panelNomReto,panelFechaIni,panelFechaFin,panelTod,panelDeporte,panelBotones;
	protected Container cp;
	protected JLabel labelElige,labelNomReto,labelFechaIni,labelFechaFin,labelTod,labelDeporte;
	protected JTextField textoNomReto,textoTod;
	protected ButtonGroup grupoDeporte;
	protected JRadioButton rbMtb,rbCicl,rbRun;
	protected JButton botonCrear,botonVolver;
	protected JOptionPane creado;
	protected JMenuBar menuBar;
	protected JMenu menu;
	protected JMenuItem menuItem;
	private JDateChooser fechaIniChooser;
	private JDateChooser fechaFinChooser;
	
	
	public VentanaReto(String tod, RegistroController regCtrl, 
			RetoController retCtrl, SesionController sesCtrl) {
		
		cp = this.getContentPane();
		this.setTitle("CrearReto");
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(6,1));
		
		menuBar = new JMenuBar();
		menu = new JMenu("M�s opciones");
		
		menuItem = new JMenuItem("Log out");
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose(); //sierra ventana
				
			}
		});
		
		//panel nombre reto
		panelNomReto = new JPanel();
		panelNomReto.setLayout(new FlowLayout());
		
		labelNomReto = new JLabel("Nombre del Reto:");
		textoNomReto = new JTextField();
		textoNomReto.setPreferredSize(new Dimension(200, 25));
		
		//panel fecha inicio
		panelFechaIni = new JPanel();
		panelFechaIni.setLayout(new FlowLayout());
		
		labelFechaIni = new JLabel("Fecha de Inicio (DD/MM/AAAA):");
		
		//panel fecha de inicio
		panelFechaFin = new JPanel();
		panelFechaFin.setLayout(new FlowLayout());
		
		labelFechaFin = new JLabel("Fecha Fin (DD/MM/AAAA):");
		
		//panel distancia
		panelTod = new JPanel();
		panelTod .setLayout(new FlowLayout());
		
		if(tod == "Distancia") {
			labelTod = new JLabel("Distancia a Recorrer:");
		} else if(tod == "Tiempo") {
			labelTod = new JLabel("Tiempo L�mite (h):");
		}
		textoTod = new JTextField();
		textoTod.setPreferredSize(new Dimension(200, 25));
		
		//panel deporte
		panelDeporte = new JPanel();
		panelDeporte.setLayout(new FlowLayout());
		
		labelDeporte = new JLabel("Deporte:");
		grupoDeporte = new ButtonGroup();
		rbMtb = new JRadioButton("MTB");
		rbCicl = new JRadioButton("Ciclismo");
		rbRun = new JRadioButton("Running");
		grupoDeporte.add(rbMtb);
		grupoDeporte.add(rbCicl);
		grupoDeporte.add(rbRun);
		
		//panelBotones
		panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		//botones
		botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaPrincipal k = new VentanaPrincipal(regCtrl, retCtrl, sesCtrl);
				k.setVisible(true);
				dispose();
			}
		});
		
		botonCrear = new JButton("Crear");
		botonCrear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String nombreReto = textoNomReto.getText();
				Date fechaIni = fechaIniChooser.getDate();
				Date fechaFin = fechaFinChooser.getDate();
				int tiempo = 0;
				float distancia = 0;
				String deporte = "";
				
				if(rbCicl.isSelected() == true) {
					deporte = "MTB";
				} else if(rbMtb.isSelected() == true) {
					deporte = "Ciclismo";
				} else if(rbRun.isSelected() == true) {
					deporte = "Running";
				}
				if(tod == "Distancia") {
					distancia = Float.parseFloat(textoTod.getText());
				tiempo = 0;
				} else if(tod == "Tiempo") {
					distancia = 0;
					tiempo = Integer.parseInt(textoTod.getText());
				}
				
				RetoDTO nuevoReto = retCtrl.crearReto(nombreReto, fechaIni, fechaFin, distancia, tiempo, deporte);
				
				
				textoNomReto.setText("");
				textoTod.setText("");
				rbCicl.setSelected(false);
				rbMtb.setSelected(false);
				rbRun.setSelected(false);
				
				creado = new JOptionPane();
				creado.showMessageDialog(null, "Nuevo Reto Creado Correctamente");

			}
		});
		
		
		cp.add(panel);
		setJMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(menuItem);
		
		panel.add(panelNomReto);
		panel.add(panelFechaIni);
		panel.add(panelFechaFin);
		panel.add(panelTod);
		panel.add(panelDeporte);

		panel.add(panelBotones);
		
		panelNomReto.add(labelNomReto);
		panelNomReto.add(textoNomReto);

		panelFechaIni.add(labelFechaIni);
		
		fechaIniChooser = new JDateChooser();
		panelFechaIni.add(fechaIniChooser);
		
		panelFechaFin.add(labelFechaFin);
		
		fechaFinChooser = new JDateChooser();
		panelFechaFin.add(fechaFinChooser);
		
		panelTod.add(labelTod);
		panelTod.add(textoTod);
		
		panelDeporte.add(labelDeporte);
		panelDeporte.add(rbCicl);
		panelDeporte.add(rbMtb);
		panelDeporte.add(rbRun);
		
		
		
		panelBotones.add(botonVolver);
		panelBotones.add(botonCrear);
		
		
		setVisible(true);
		pack();
		setSize(500,350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}