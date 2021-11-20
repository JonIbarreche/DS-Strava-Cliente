package es.deusto.ingenieria.sd.strava.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import es.deusto.ingenieria.sd.strava.client.controller.LoginController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtMail;
	private JTextField txtPassword;

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param loginController 
	 */
	public LoginWindow(LoginController loginController) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 377);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStravaCliente = new JLabel("STRAVA CLIENTE");
		lblStravaCliente.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblStravaCliente.setBounds(188, 13, 229, 62);
		contentPane.add(lblStravaCliente);
		
		txtMail = new JTextField();
		txtMail.setText("Mail");
		txtMail.setBounds(209, 103, 146, 28);
		contentPane.add(txtMail);
		txtMail.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setText("Password");
		txtPassword.setBounds(209, 164, 146, 28);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnIniciarSesion = new JButton("INICIAR SESION");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal vp = new VentanaPrincipal();
				vp.setVisible(true);
				dispose();
			}
		});
		btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnIniciarSesion.setBounds(336, 223, 172, 46);
		contentPane.add(btnIniciarSesion);
		
		JButton btnRegistrarse = new JButton("REGISTRARSE");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaOpcion vo = new VentanaOpcion();
				vo.setVisible(true);
				dispose();
			}
		});
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRegistrarse.setBounds(63, 224, 172, 46);
		contentPane.add(btnRegistrarse);
	}
}
