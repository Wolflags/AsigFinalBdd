package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;

public class Inicio extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
					//Y que e
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicio() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/Images/inicio.png")));
		setBackground(new Color(255, 255, 255));
		setTitle("Inicio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setSize(1043, 725);
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (screenSize.width - getWidth()) / 2;
		    int y = (screenSize.height - getHeight()-30) / 2;
		    setLocation(x, y);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(64, 0, 64));
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setSelectedIcon(null);
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setIcon(new ImageIcon(Inicio.class.getResource("/Images/examen5.png")));
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Crea una nueva instancia de tu JDialog
		        ListarGrupos dialog = new ListarGrupos();
		        dialog.setVisible(true); // Muestra el JDialog
		        // Cierra el JFrame actual
		       
			}
		});
		btnNewButton.setBounds(310, 354, 173, 175);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setIcon(new ImageIcon(Inicio.class.getResource("/Images/calendario3.png")));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Crea una nueva instancia de tu JDialog
				PeriodoAcademico dialog = new PeriodoAcademico(null);
				dialog.setVisible(true);//Muestra el JDialog
				//Cierra el JFrame actual
				
			}
		});
		btnNewButton_2.setBounds(772, 85, 173, 175);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		btnNewButton_3.setIcon(new ImageIcon(Inicio.class.getResource("/Images/Boton1.png")));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarAsignatura dialog = new ListarAsignatura();
				dialog.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(542, 359, 173, 175);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_1 = new JLabel("Asignaturas");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(574, 545, 111, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nuevo Periodo");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(802, 272, 164, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setIcon(new ImageIcon(Inicio.class.getResource("/Images/estudiante4.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarEstudiantes dialog = new ListarEstudiantes();
				dialog.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(56, 354, 173, 175);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("Estudiantes");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(90, 545, 109, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setBackground(new Color(255, 255, 255));
		btnNewButton_4.setIcon(new ImageIcon(Inicio.class.getResource("/Images/inscripcion3.png")));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Asignatura dialog = new Asignatura(null);
				dialog.setVisible(true);//Muestra el JDialog
				//Cierra el JFrame actual
				
			}
		});
		btnNewButton_4.setBounds(542, 85, 173, 175);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_4 = new JLabel("Nueva Asignatura");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(563, 272, 164, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_3_1 = new JLabel("Grupos");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3_1.setBounds(363, 545, 109, 14);
		contentPane.add(lblNewLabel_3_1);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setIcon(new ImageIcon(Inicio.class.getResource("/Images/clase1.png")));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearModificarGrupos dialog = new CrearModificarGrupos(null, null);
				dialog.setVisible(true);
			}
		});
		btnNewButton_5.setForeground(Color.WHITE);
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_5.setBackground(new Color(255, 255, 255));
		btnNewButton_5.setBounds(310, 85, 173, 175);
		contentPane.add(btnNewButton_5);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Nuevo Grupo");
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3_1_1.setBounds(349, 271, 109, 14);
		contentPane.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("Nuevo Estudiante");
		lblNewLabel_3_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3_1_1_1.setBounds(77, 271, 173, 14);
		contentPane.add(lblNewLabel_3_1_1_1);
		
		JButton btnNewButton_5_1 = new JButton("");
		btnNewButton_5_1.setIcon(new ImageIcon(Inicio.class.getResource("/Images/foto2.png")));
		btnNewButton_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estudiante dialog = new Estudiante(null);
				dialog.setVisible(true);
			}
		});
		btnNewButton_5_1.setForeground(Color.WHITE);
		btnNewButton_5_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_5_1.setBackground(new Color(255, 255, 255));
		btnNewButton_5_1.setBounds(56, 85, 173, 175);
		contentPane.add(btnNewButton_5_1);
		
		JButton btnNewButton_5_2 = new JButton("");
		btnNewButton_5_2.setIcon(new ImageIcon(Inicio.class.getResource("/Images/programar2.png")));
		btnNewButton_5_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarPeriodoAcademico dialog = new ListarPeriodoAcademico();
				dialog.setVisible(true);
			}
		});
		btnNewButton_5_2.setForeground(Color.WHITE);
		btnNewButton_5_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_5_2.setBackground(new Color(255, 255, 255));
		btnNewButton_5_2.setBounds(772, 359, 173, 175);
		contentPane.add(btnNewButton_5_2);
		
		JLabel lblNewLabel_3_1_1_2 = new JLabel("Periodos");
		lblNewLabel_3_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3_1_1_2.setBounds(829, 545, 109, 14);
		contentPane.add(lblNewLabel_3_1_1_2);
		
	}
}
