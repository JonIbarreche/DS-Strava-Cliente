package es.deusto.ingenieria.sd.auctions.client.gui;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import es.deusto.ingenieria.sd.auctions.client.gui.*;

public class VentanaPrincipal extends JFrame{
	protected Container cp;
	protected JPanel panel, panelBoton1,panelBoton2,panelBoton3;
	protected JButton crearReto, mirarRetos, crearSesion;
	protected JMenuBar menuBar;
	protected JMenu menu;
	protected JMenuItem menuItem;
	protected JOptionPane opcionCreacion;
	
	public VentanaPrincipal() {
		Reto r1 = new Reto("a", "b", "c", 0, 1, "d");
		Reto r2 = new Reto("a", "b", "c", 1, 0, "d");
		Reto r3 = new Reto("a", "b", "c", 1, 1, "d");
		
		Reto[] lista = {r1, r2, r3};
		
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
				int seleccion = opcionCreacion.showOptionDialog(null,"Seleccione una opcion",
						  "Selector de opciones",JOptionPane.YES_NO_CANCEL_OPTION,
						   JOptionPane.QUESTION_MESSAGE,null,// null para icono por defecto.
						  new Object[] { "Distancia", "Tiempo"},"opcion 1");
				
				if (seleccion != -1) {
					seleccion += 1;
					if (seleccion == 1) {
						VentanaReto i = new VentanaReto("Distancia");
					} else if (seleccion == 2) {
						VentanaReto i = new VentanaReto("Tiempo");
					}
				}
				dispose();		   
				
			}
			
		});
		
		mirarRetos = new JButton("Ver Retos Activos");
		mirarRetos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaMirar l = new VentanaMirar(lista);
				dispose();
			}
			
		});
		
		crearSesion = new JButton("Crear Una Nueva Sesion");
		crearSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaSesion j = new VentanaSesion();
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
		
		setVisible(true);
		pack();
		setSize(473,276);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
