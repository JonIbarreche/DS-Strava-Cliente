package es.deusto.ingenieria.sd.strava.client.gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import es.deusto.ingenieria.sd.strava.client.gui.*;

public class VentanaOpcion extends JFrame{
	protected Container cp;
	protected JPanel panel, panelBoton1,panelBoton2,panelBoton3;
	protected JButton mail, facebook, google;
	
	public VentanaOpcion() {
		
		cp = this.getContentPane();
		this.setTitle("Registro");
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(3,1));
		
		panelBoton1 = new JPanel();
		panelBoton1.setLayout(new FlowLayout());
		
		panelBoton2 = new JPanel();
		panelBoton2.setLayout(new FlowLayout());
		
		panelBoton3 = new JPanel();
		panelBoton3.setLayout(new FlowLayout());
		
		mail = new JButton("Mail");
		mail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaRegistro i = new VentanaRegistro("mail");
				
				dispose();		   
				
			}
			
		});
		
		facebook = new JButton("Facebook");
		facebook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaRegistro i = new VentanaRegistro("facebook");
				dispose();
			}
			
		});
		
		google = new JButton("Google");
		google.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaRegistro i = new VentanaRegistro("google");
				dispose();
				
			}
			
		});
		
		
		
		
		cp.add(panel);

		
		panel.add(panelBoton1);
		panel.add(panelBoton2);
		panel.add(panelBoton3);
		
		panelBoton1.add(mail);
		panelBoton2.add(facebook);
		panelBoton3.add(google);
		
		setVisible(true);
		pack();
		setSize(500,350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
