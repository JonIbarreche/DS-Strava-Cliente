package es.deusto.ingenieria.sd.strava.client.gui;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import es.deusto.ingenieria.sd.auctions.client.controller.BidController;
import es.deusto.ingenieria.sd.auctions.client.controller.RetoController;
import es.deusto.ingenieria.sd.auctions.client.gui.*;

public class VentanaReto extends JFrame {
	
	private RetoController controller;
	
	protected JPanel panel,panelElige,panelNomReto,panelFechaIni,panelFechaFin,panelTod,panelDeporte,panelBotones;
	protected Container cp;
	protected JLabel labelElige,labelNomReto,labelFechaIni,labelFechaFin,labelTod,labelDeporte;
	protected JTextField textoNomReto,textoFechaIni,textoFechaFin,textoTod;
	protected ButtonGroup grupoDeporte;
	protected JRadioButton rbMtb,rbCicl,rbRun;
	protected JButton botonCrear,botonVolver;
	protected JOptionPane creado;
	protected JMenuBar menuBar;
	protected JMenu menu;
	protected JMenuItem menuItem;
	
	
	public VentanaReto(String tod, RetoController retoController) {
		
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
		textoFechaIni = new JTextField();
		textoFechaIni.setPreferredSize(new Dimension(200, 25));
		
		//panel fecha de inicio
		panelFechaFin = new JPanel();
		panelFechaFin.setLayout(new FlowLayout());
		
		labelFechaFin = new JLabel("Fecha Fin (DD/MM/AAAA):");
		textoFechaFin = new JTextField();
		textoFechaFin.setPreferredSize(new Dimension(200, 25));
		
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
				VentanaPrincipal k = new VentanaPrincipal();
				k.setVisible(true);
				dispose();
			}
		});
		
		botonCrear = new JButton("Crear");
		botonCrear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String nombreReto = textoNomReto.getText();
				String fechaIni = textoFechaIni.getText();
				String fechaFin = textoFechaFin.getText();
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
				
				Reto nuevoReto = new Reto(nombreReto, fechaIni, fechaFin, distancia, tiempo, deporte);
				
				textoNomReto.setText("");
				textoFechaIni.setText("");
				textoFechaFin.setText("");
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
		panelFechaIni.add(textoFechaIni);
		
		panelFechaFin.add(labelFechaFin);
		panelFechaFin.add(textoFechaFin);
		
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
	
	public List<RetoDTO> getRetos() { 		
		System.out.println(" - Getting retos...");
		
		List<RetoDTO> retos = this.controller.getRetos();
	
		for (RetoDTO reto : retos) {
			System.out.println("\t* " + reto.getNombreReto() + " - " + 
		                                 reto.getFechaIni() + " - " + 
		                                 reto.getFechaFin() + "/" +
		                                 reto.getDistancia() + " - (" + 
		                                 reto.getTiempo() + " - " +
		                                 reto.getDeporte();
		}
			
		return retos;		
	}

}