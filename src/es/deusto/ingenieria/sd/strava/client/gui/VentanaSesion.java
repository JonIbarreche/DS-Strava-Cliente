package es.deusto.ingenieria.sd.strava.client.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import es.deusto.ingenieria.sd.strava.client.controller.RegistroController;
import es.deusto.ingenieria.sd.strava.client.controller.RetoController;
import es.deusto.ingenieria.sd.strava.client.controller.SesionController;
import es.deusto.ingenieria.sd.strava.server.data.dto.SesionDTO;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

public class VentanaSesion extends JFrame{

	protected JPanel panel,panelElige,panelTitulo,panelFechaIni,panelHoraIni,panelDuracion,panelDistancia,panelBotones;
	protected Container cp;
	protected JLabel labelElige,labelTitulo,labelFechaIni,labelHoraIni,labelDuracion,labelDistancia;
	protected JTextField textoTitulo,textoHoraIni,textoDuracion,textoDistancia;
	protected JButton botonCrear,botonVolver;
	protected JOptionPane creado;
	protected JMenuBar menuBar;
	protected JMenu menu;
	protected JMenuItem menuItem;
	private JSpinner spinnerDia;
	private JMonthChooser monthChooser;
	private JYearChooser yearChooser;


	public VentanaSesion(String usuario, RegistroController regCtrl,
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
		panelTitulo= new JPanel();
		panelTitulo.setLayout(new FlowLayout());

		labelTitulo = new JLabel("Titulo de la Sesi�n:");
		textoTitulo = new JTextField();
		textoTitulo.setPreferredSize(new Dimension(200, 25));

		//panel fecha inicio
		panelFechaIni = new JPanel();
		panelFechaIni.setLayout(new FlowLayout());

		labelFechaIni = new JLabel("Fecha de Inicio (DD/MM/AAAA):");

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
				VentanaPrincipal k = new VentanaPrincipal(usuario, regCtrl, retCtrl, sesCtrl);
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
				int anyoIni = yearChooser.getValue();
                int mesIni = monthChooser.getMonth();
                int diaIni = (Integer)spinnerDia.getValue();
                String strIni = Integer.toString(diaIni) + " " + Integer.toString(mesIni) + " " + Integer.toString(anyoIni);
                SimpleDateFormat df = new SimpleDateFormat("MM dd yyyy");
                long tiempoIni = 0;
                try {
                    tiempoIni = df.parse(strIni).getTime();
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Date fechaIni = new Date(tiempoIni);
                
				String horaIni = textoHoraIni.getText();
				int Duracion = Integer.parseInt(textoDuracion.getText());

				SesionDTO nuevaSesion = new SesionDTO();
				
				sesCtrl.crearSesion(titulo, distancia, fechaIni, horaIni, Duracion);
				
				System.out.println(nuevaSesion.toString());
				textoTitulo.setText("");
				textoHoraIni.setText("");
				textoDistancia.setText("");
				textoDuracion.setText("");
				creado = new JOptionPane();
				JOptionPane.showMessageDialog(null, "Nueva Sesion Creada Correctamente");


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
		
		SpinnerModel value = new SpinnerNumberModel(1, 0, 31, 1);
		spinnerDia = new JSpinner(value);
		panelFechaIni.add(spinnerDia);
		
		monthChooser = new JMonthChooser();
		panelFechaIni.add(monthChooser);
		
		yearChooser = new JYearChooser();
		panelFechaIni.add(yearChooser);

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
	/*
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
	}*/
}
