package es.deusto.ingenieria.sd.strava.client.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import es.deusto.ingenieria.sd.strava.client.controller.LoginController;
import es.deusto.ingenieria.sd.strava.client.controller.RegistroController;
import es.deusto.ingenieria.sd.strava.client.controller.RetoController;
import es.deusto.ingenieria.sd.strava.client.controller.SesionController;

public class LoginWindow extends JFrame {

	private JPanel contentPane, panelbotones;
	private JTextField txtMail;
	private JTextField txtPassword;
	protected ButtonGroup grupoLogin;
	
	

	/**
	 * Create the frame.
	 * @param loginController 
	 */
	public LoginWindow(LoginController logCtrl, RegistroController regCtrl, 
			RetoController retCtrl, SesionController sesCtrl) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 419);
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
		
	
		
		JButton btnRegistrarse = new JButton("REGISTRARSE");
		btnRegistrarse.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				VentanaOpcion vo = new VentanaOpcion(logCtrl, regCtrl, retCtrl, sesCtrl);
				vo.setVisible(true);
				dispose();
			}
		});
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRegistrarse.setBounds(67, 284, 172, 46);
		contentPane.add(btnRegistrarse);
		
		grupoLogin = new ButtonGroup();		
		
		
		JRadioButton rdbtnMail = new JRadioButton("Mail");
		rdbtnMail.setBounds(102, 217, 127, 25);
		grupoLogin.add(rdbtnMail);
		contentPane.add(rdbtnMail);
		
		JRadioButton rdbtnGoogle = new JRadioButton("Google");
		rdbtnGoogle.setBounds(230, 217, 127, 25);
		grupoLogin.add(rdbtnGoogle);
		contentPane.add(rdbtnGoogle);
		
		JRadioButton rdbtnFacebook = new JRadioButton("Facebook");
		rdbtnFacebook.setBounds(358, 217, 127, 25);
		contentPane.add(rdbtnFacebook);
		grupoLogin.add(rdbtnFacebook);
		setVisible(true);
		
		JButton btnIniciarSesion = new JButton("INICIAR SESION");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean login;
				if (rdbtnFacebook.isSelected()) {
					login = logCtrl.login(txtMail.getText(), "" , "Facebook");
				} else if (rdbtnGoogle.isSelected()) {
					login = logCtrl.login(txtMail.getText(), "" , "Google");
				} else {
					login = logCtrl.login(txtMail.getText(), txtPassword.getText(), "Mail");
				}
				if(login == true) {
					System.out.println("entro");
					VentanaPrincipal vp = new VentanaPrincipal(regCtrl, retCtrl, sesCtrl);
					vp.setVisible(true);
					dispose();
				} else {
					System.out.println("vuele a intentarlo");
				}
				
			}
		});
		btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnIniciarSesion.setBounds(337, 284, 172, 46);
		contentPane.add(btnIniciarSesion);
	}
}
