package es.deusto.ingenieria.sd.auctions.client.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import es.deusto.ingenieria.sd.auctions.client.controller.RegistroController;
import es.deusto.ingenieria.sd.auctions.client.gui.*;

public class VentanaRegistro extends JFrame {
	
	protected JPanel panel,panelEmail,panelNombre,panelFecha,panelPass,panelPeso,panelAltura,panelMax,panelRep,panelBotones;
	protected Container cp;
	protected JLabel labelEmail,labelNom,labelFecha,labelPass,labelPeso,labelAltura,labelMax,labelRep;
	protected JTextField textoEmail,textoNom,textoFecha,textoPass,textoPeso,textoAltura,textoMax,textoRep;
	protected JButton botonRegistro,botonVolver;
	
	
	public VentanaRegistro(String plataforma, RegistroController registroController) {
		
		cp = this.getContentPane();
		this.setTitle("Registro");
		
		//panelPrinc
		panel = new JPanel();
		panel.setLayout(new GridLayout(9,1));
		
		//panelEmail;
		panelEmail = new JPanel();
		panelEmail.setLayout(new FlowLayout());
		
		labelEmail = new JLabel("Email:");
		textoEmail = new JTextField();
		textoEmail.setPreferredSize(new Dimension(200, 25));
		
		//panelNombre;
		panelNombre = new JPanel();
		panelNombre.setLayout(new FlowLayout());
		
		labelNom = new JLabel("Nombre:");
		textoNom= new JTextField();
		textoNom.setPreferredSize(new Dimension(200, 25));
		
		//panelFecha
		panelFecha = new JPanel();
		panelFecha.setLayout(new FlowLayout());
		
		labelFecha = new JLabel("Fecha:");
		textoFecha = new JTextField();
		textoFecha.setPreferredSize(new Dimension(200, 25));
		
		//panelPass;
		panelPass = new JPanel();
		panelPass.setLayout(new FlowLayout());
		
		labelPass = new JLabel("Contrase�a:");
		textoPass = new JTextField();
		textoPass.setPreferredSize(new Dimension(200, 25));
		
		//panelPeso;;
		panelPeso = new JPanel();
		panelPeso.setLayout(new FlowLayout());
		
		labelPeso = new JLabel("Peso:");
		textoPeso = new JTextField();
		textoPeso.setPreferredSize(new Dimension(200, 25));
		
		//panelAltura;
		panelAltura = new JPanel();
		panelAltura.setLayout(new FlowLayout());
		
		labelAltura = new JLabel("Altura:");
		textoAltura = new JTextField();
		textoAltura.setPreferredSize(new Dimension(200, 25));
		
		//panelMax;
		panelMax = new JPanel();
		panelMax.setLayout(new FlowLayout());
		
		labelMax = new JLabel("Frecuencia card�aca m�x:");
		textoMax = new JTextField();
		textoMax.setPreferredSize(new Dimension(200, 25));
		
		//panelRep;
		panelRep = new JPanel();
		panelRep.setLayout(new FlowLayout());
		
		labelRep = new JLabel("Frecuencia card�aca m�x:");
		textoRep = new JTextField();
		textoRep.setPreferredSize(new Dimension(200, 25));
		
		//panelBotones
		panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		//botones
		botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LoginWindow lw = new LoginWindow();
				lw.setVisible(true);
				dispose();
				
			}
		});
		
		botonRegistro = new JButton("Registrarme");
		botonRegistro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Usuario u = new Usuario(textoEmail.getText(), textoNom.getText(), textoFecha.getText(), 
						textoPass.getText(), Integer.parseInt(textoPeso.getText()), Integer.parseInt(textoAltura.getText()), 
						Integer.parseInt(textoMax.getText()), Integer.parseInt(textoRep.getText()));
				VentanaPrincipal i = new VentanaPrincipal();
				
			}
		});
		
		
		cp.add(panel);
		
		panel.add(panelEmail);
		panel.add(panelNombre);
		panel.add(panelFecha);
		panel.add(panelPass);
		panel.add(panelPeso);
		panel.add(panelAltura);
		panel.add(panelAltura);
		panel.add(panelMax);
		panel.add(panelRep);
		panel.add(panelBotones);
		
		panelEmail.add(labelEmail);
		panelEmail.add(textoEmail);

		panelNombre.add(labelNom);
		panelNombre.add(textoNom);
		
		panelEmail.add(labelEmail);
		panelEmail.add(textoEmail);
		
		panelFecha.add(labelFecha);
		panelFecha.add(textoFecha);
		
		panelPass.add(labelPass);
		panelPass.add(textoPass);
		
		panelPeso.add(labelPeso);
		panelPeso.add(textoPeso);
		
		
		panelAltura.add(labelAltura);
		panelAltura.add(textoAltura);
		
		panelMax.add(labelMax);
		panelMax.add(textoMax);
		
		panelRep.add(labelRep);
		panelRep.add(textoRep);
		
		panelBotones.add(botonVolver);
		panelBotones.add(botonRegistro);
		
		if (plataforma == "facebook" || plataforma == "google") {
			panelPass.setVisible(false);
		}
		
		setVisible(true);
		pack();
		setSize(500,350);
	}
}