package es.deusto.ingenieria.sd.auctions.client.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import es.deusto.ingenieria.sd.auctions.client.controller.RetoController;
import es.deusto.ingenieria.sd.auctions.client.controller.SesionController;
import es.deusto.ingenieria.sd.auctions.client.gui.*;

public class VentanaSesion extends JFrame{
	
	private SesionController controller;
	
	protected JPanel panel,panelElige,panelTitulo,panelFechaIni,panelHoraIni,panelDuracion,panelDistancia,panelBotones;
	protected Container cp;
	protected JLabel labelElige,labelTitulo,labelFechaIni,labelHoraIni,labelDuracion,labelDistancia;
	protected JTextField textoTitulo,textoFechaIni,textoHoraIni,textoDuracion,textoDistancia;
	protected JButton botonCrear,botonVolver;
	protected JOptionPane creado;
	protected JMenuBar menuBar;
	protected JMenu menu;
	protected JMenuItem menuItem;
	
	
	public VentanaSesion(SesionController sesionController) {
		
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
		panelTitulo= new JPanel();
		panelTitulo.setLayout(new FlowLayout());
		
		labelTitulo = new JLabel("Titulo de la Sesi�n:");
		textoTitulo = new JTextField();
		textoTitulo.setPreferredSize(new Dimension(200, 25));
		
		//panel fecha inicio
		panelFechaIni = new JPanel();
		panelFechaIni.setLayout(new FlowLayout());
		
		labelFechaIni = new JLabel("Fecha de Inicio (DD/MM/AAAA):");
		textoFechaIni = new JTextField();
		textoFechaIni.setPreferredSize(new Dimension(200, 25));
		
		//panel fecha de inicio
		panelHoraIni = new JPanel();
		panelHoraIni.setLayout(new FlowLayout());
		
		labelHoraIni = new JLabel("Hora Inicio (HH:MM):");
		textoHoraIni = new JTextField();
		textoHoraIni.setPreferredSize(new Dimension(200, 25));
		
		//panel distancia
		panelDuracion = new JPanel();
		panelDuracion .setLayout(new FlowLayout());
		
		labelDuracion = new JLabel("Duracion de la Sesi�n:");
		textoDuracion = new JTextField();
		textoDuracion.setPreferredSize(new Dimension(200, 25));
		
		//panel deporte
		panelDistancia = new JPanel();
		panelDistancia.setLayout(new FlowLayout());
		
		labelDistancia= new JLabel("Distancia a Recorrer:");
		textoDistancia = new JTextField();
		textoDistancia.setPreferredSize(new Dimension(200, 25));
		
		//panelBotones
		panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		//botones
		botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaPrincipal k = new VentanaPrincipal();
				k.setVisible(true);
				dispose();
			}
		});
		
		botonCrear = new JButton("Crear");
		botonCrear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String titulo = textoTitulo.getText();
				float distancia = Float.parseFloat(textoDistancia.getText());
				String fechaIni = textoFechaIni.getText();
				String horaIni = textoHoraIni.getText();
				int Duracion = Integer.parseInt(textoDuracion.getText());
			
				Sesion nuevaSesion = new Sesion(titulo, distancia, fechaIni, horaIni, Duracion);
				System.out.println(nuevaSesion.toString());
				textoTitulo.setText("");
				textoFechaIni.setText("");
				textoHoraIni.setText("");
				textoDistancia.setText("");
				textoDuracion.setText("");
				
				creado = new JOptionPane();
				creado.showMessageDialog(null, "Nueva Sesion Creada Correctamente");
				

			}
		});
		
		
		cp.add(panel);
		setJMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(menuItem);
		
		panel.add(panelTitulo);
		panel.add(panelFechaIni);
		panel.add(panelHoraIni);
		panel.add(panelDistancia);
		panel.add(panelDuracion);

		panel.add(panelBotones);
		
		panelTitulo.add(labelTitulo);
		panelTitulo.add(textoTitulo);

		panelFechaIni.add(labelFechaIni);
		panelFechaIni.add(textoFechaIni);
		
		panelHoraIni.add(labelHoraIni);
		panelHoraIni.add(textoHoraIni);
		
		panelDuracion.add(labelDuracion);
		panelDuracion.add(textoDuracion);
		
		panelDistancia.add(labelDistancia);
		panelDistancia.add(textoDistancia);
		
		
		panelBotones.add(botonVolver);
		panelBotones.add(botonCrear);
		
		
		setVisible(true);
		pack();
		setSize(500,350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public List<SesionDTO> getSesiones() { 		
		System.out.println(" - Getting sesiones...");
		
		List<SesionDTO> sesiones = this.controller.getSesiones();
	
		for (SesionDTO sesion : sesiones) {
			System.out.println("\t* " + sesion.getTitulo() + " - " + 
		                                 sesion.getFechaIni() + " - " + 
		                                 sesion.getHoraIni() + "/" +
		                                 sesion.getDistancia() + " - (" + 
		                                 sesion.getDuracion());
		}
			
		return sesiones;		
	}
}
