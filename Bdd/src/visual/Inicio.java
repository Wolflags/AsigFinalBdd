package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

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
		setTitle("Academico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setSize(1377, 725);
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (screenSize.width - getWidth()) / 2;
		    int y = (screenSize.height - getHeight()-30) / 2;
		    setLocation(x, y);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Grupos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Crea una nueva instancia de tu JDialog
		        ListarGrupos dialog = new ListarGrupos();
		        dialog.setVisible(true); // Muestra el JDialog
		        // Cierra el JFrame actual
		        dispose();
			}
		});
		btnNewButton.setBounds(83, 86, 185, 53);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Estudiantes");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Crea una nueva instancia de tu JDialog
				Estudiante dialog = new Estudiante();
				dialog.setVisible(true);//Muestra el JDialog
				//Cierra el JFrame actual
				dispose();
			}
		});
		
		btnNewButton_1.setBounds(318, 86, 185, 50);
		contentPane.add(btnNewButton_1);
		
	
		
		btnNewButton_1.setBounds(318, 88, 185, 48);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Periodo Academico");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Crea una nueva instancia de tu JDialog
				PeriodoAcademico dialog = new PeriodoAcademico();
				dialog.setVisible(true);//Muestra el JDialog
				//Cierra el JFrame actual
				dispose();
			}
		});
		btnNewButton_2.setBounds(545, 88, 164, 51);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Asignaturas");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Asignatura dialog = new Asignatura();
				dialog.setVisible(true);//Muestra el JDialog
				//Cierra el JFrame actual
				dispose();
			}
		});
		btnNewButton_3.setBounds(83, 208, 185, 53);
		contentPane.add(btnNewButton_3);
		
	}
}
