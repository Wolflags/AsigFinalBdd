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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setSize(1375, 725);
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (screenSize.width - getWidth()) / 2;
		    int y = (screenSize.height - getHeight()-30) / 2;
		    setLocation(x, y);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

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
		
		btnNewButton_1.setBounds(318, 88, 185, 48);
		contentPane.add(btnNewButton_1);
		
	}
}
