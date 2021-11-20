package es.deusto.ingenieria.sd.strava.client.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

import es.deusto.ingenieria.sd.strava.client.gui.*;
import es.deusto.ingenieria.sd.strava.server.data.domain.Reto;


public class VentanaMirar extends JFrame{
	protected Container cp;
	protected JPanel panel, panelBoton, panelTabla;
	protected JButton botonVolver;
	protected JMenuBar menuBar;
	protected JMenu menu;
	protected JMenuItem menuItem;
	protected JTable tabla;
	protected JScrollPane scrpTabla;

	public String[] prepararParaLista(Reto r) {
		if(r.getTiempo() == 0) {
			String[] preparado = {r.getNombreReto(), r.getFechaIni(), r.getFechaFin(), String.valueOf(r.getDistancia()), "null", r.getDeporte()};
			return preparado;
		} else if(r.getDistancia() == 0) {
			String[] preparado = {r.getNombreReto(), r.getFechaIni(), r.getFechaFin(), "null", String.valueOf(r.getTiempo()), r.getDeporte()};
			return preparado;
		} else {
			String[] preparado = {r.getNombreReto(), r.getFechaIni(), r.getFechaFin(), String.valueOf(r.getDistancia()), String.valueOf(r.getTiempo()), r.getDeporte()};
			return preparado;
		}

	}
	
	public VentanaMirar(Reto[] lista) {
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
		for (int i = 0; i < lista.length; i++) {
			String[] preparado = prepararParaLista(lista[i]);
			retos[i] = preparado;
		}
		
		
		tabla = new JTable(retos, nombresColumna);
		
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
				VentanaPrincipal k = new VentanaPrincipal();
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
