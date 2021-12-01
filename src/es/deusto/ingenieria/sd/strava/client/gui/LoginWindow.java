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

	private JPanel contentPane;
	private JTextField txtMail;
	private JTextField txtPassword;
	protected ButtonGroup grupoLogin;
	protected JRadioButton rbMail,rbFb,rbGoogle;
	
	

	/**
	 * Create the frame.
	 * @param loginController 
	 */
	public LoginWindow(LoginController logCtrl, RegistroController regCtrl, 
			RetoController retCtrl, SesionController sesCtrl) {
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
		
		grupoLogin = new ButtonGroup();
		rbMail = new JRadioButton("Mail");
		rbFb = new JRadioButton("Facebook");
		rbGoogle = new JRadioButton("Google");
		grupoLogin.add(rbMail);
		grupoLogin.add(rbFb);
		grupoLogin.add(rbGoogle);
		contentPane.add(rbMail);
		contentPane.add(rbFb);
		contentPane.add(rbGoogle);
		rbMail.setVisible(true);
		
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
				boolean login;
				if (rbFb.isSelected()) {
					login = logCtrl.login(txtMail.getText(), "" , "Facebook");
				} else if (rbGoogle.isSelected()) {
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
		btnIniciarSesion.setBounds(336, 223, 172, 46);
		contentPane.add(btnIniciarSesion);
		
		JButton btnRegistrarse = new JButton("REGISTRARSE");
		btnRegistrarse.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				VentanaOpcion vo = new VentanaOpcion(logCtrl, regCtrl, retCtrl, sesCtrl);
				vo.setVisible(true);
				dispose();
			}
		});
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRegistrarse.setBounds(63, 224, 172, 46);
		contentPane.add(btnRegistrarse);
		setVisible(true);
	}
	
}
