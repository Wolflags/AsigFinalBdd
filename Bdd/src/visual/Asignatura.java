package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.ConexionDB;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Asignatura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtcodAsig;
	private JTextField txtNombre;
	JSpinner creditos = new JSpinner();
	JSpinner teorica = new JSpinner();
	JSpinner practica = new JSpinner();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Asignatura dialog = new Asignatura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Asignatura() {
		setModal(true);
		setBounds(100, 100, 490, 226);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 474, 151);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo de Asignatura:");
		lblNewLabel.setBounds(10, 11, 137, 14);
		panel.add(lblNewLabel);
		
		txtcodAsig = new JTextField();
		txtcodAsig.setBounds(157, 8, 141, 20);
		panel.add(txtcodAsig);
		txtcodAsig.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 48, 63, 14);
		panel.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(83, 45, 178, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Creditos:");
		lblNewLabel_2.setBounds(271, 48, 53, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Horas Teoricas:");
		lblNewLabel_3.setBounds(10, 95, 92, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Horas Practicas:");
		lblNewLabel_4.setBounds(170, 95, 84, 14);
		panel.add(lblNewLabel_4);
		
		 creditos = new JSpinner();
		creditos.setBounds(319, 45, 53, 20);
		panel.add(creditos);
		
		teorica = new JSpinner();
		teorica.setBounds(94, 92, 53, 20);
		panel.add(teorica);
		
		practica = new JSpinner();
		practica.setBounds(264, 92, 53, 20);
		panel.add(practica);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Agregar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						insertarAsignatura();
						JOptionPane.showMessageDialog(null, "Los datos se insertaron correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Regresar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public void insertarAsignatura() {
	    Connection con = ConexionDB.getConnection();

	    if (con != null) {
	        try {
	            String sql = " INSERT INTO Asignatura (CodAsignatura, Nombre, Creditos, HorasTeoricas, HorasPracticas) VALUES (?, ?, ?, ?, ?);";
	            PreparedStatement ps = con.prepareStatement(sql);

	            ps.setString(1, txtcodAsig.getText());
	            ps.setString(2, txtNombre.getText());
	            ps.setInt(3, (Integer) creditos.getValue());
	            ps.setInt(4, (Integer) teorica.getValue());
	            ps.setInt(5, (Integer) practica.getValue());
	           
	            ps.executeUpdate();

	            ps.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    } else {
	        System.out.println("Error en la conexión");
	    }
	}
}
