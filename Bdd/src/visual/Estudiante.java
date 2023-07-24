package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.ConexionDB;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Estudiante extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre1;
	private JTextField txtNombre2;
	private JTextField txtApellido1;
	private JTextField txtApellido2;
	private JTextField txtCarrera;
	private JTextField txtNacionalidad;
	private JTextField txtDireccion;
	private JTextField txtFechaNacimiento;
	JComboBox cbxCategoriaPago = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Estudiante dialog = new Estudiante();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Estudiante() {
		setModal(true);
		//setSize(450,360);
		/* Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (screenSize.width - getWidth()) / 2;
		    int y = (screenSize.height - getHeight()-30) / 2;
		    setLocation(x, y);*/
		setBounds(100, 100, 495, 388);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 479, 312);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Matricula:");
		lblNewLabel.setBounds(10, 94, 57, 14);
		panel.add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setBounds(66, 91, 103, 20);
		panel.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 14, 57, 14);
		panel.add(lblNewLabel_1);
		
		txtNombre1 = new JTextField();
		txtNombre1.setBounds(66, 11, 103, 20);
		panel.add(txtNombre1);
		txtNombre1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Segundo Nombre:");
		lblNewLabel_2.setBounds(208, 14, 103, 14);
		panel.add(lblNewLabel_2);
		
		txtNombre2 = new JTextField();
		txtNombre2.setBounds(321, 11, 124, 20);
		panel.add(txtNombre2);
		txtNombre2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Apellido:");
		lblNewLabel_3.setBounds(10, 55, 46, 14);
		panel.add(lblNewLabel_3);
		
		txtApellido1 = new JTextField();
		txtApellido1.setBounds(66, 52, 103, 20);
		panel.add(txtApellido1);
		txtApellido1.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Segundo Apellido:");
		lblNewLabel_2_1.setBounds(208, 55, 103, 14);
		panel.add(lblNewLabel_2_1);
		
		txtApellido2 = new JTextField();
		txtApellido2.setBounds(321, 52, 124, 20);
		panel.add(txtApellido2);
		txtApellido2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Carrera:");
		lblNewLabel_4.setBounds(208, 94, 46, 14);
		panel.add(lblNewLabel_4);
		
		txtCarrera = new JTextField();
		txtCarrera.setBounds(264, 91, 163, 20);
		panel.add(txtCarrera);
		txtCarrera.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Categoria de Pago:");
		lblNewLabel_5.setBounds(10, 259, 103, 14);
		panel.add(lblNewLabel_5);
		
		cbxCategoriaPago = new JComboBox();
		cbxCategoriaPago.setModel(new DefaultComboBoxModel(new String[] {"Tra", "Efe"}));
		cbxCategoriaPago.setBounds(123, 255, 129, 22);
		panel.add(cbxCategoriaPago);
		
		JLabel lblNewLabel_6 = new JLabel("Nacionalidad:");
		lblNewLabel_6.setBounds(10, 132, 78, 14);
		panel.add(lblNewLabel_6);
		
		txtNacionalidad = new JTextField();
		txtNacionalidad.setBounds(91, 129, 266, 20);
		panel.add(txtNacionalidad);
		txtNacionalidad.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Dirección:");
		lblNewLabel_7.setBounds(10, 178, 67, 14);
		panel.add(lblNewLabel_7);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(91, 175, 266, 20);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Fecha de Nacimiento:");
		lblNewLabel_8.setBounds(10, 218, 114, 14);
		panel.add(lblNewLabel_8);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setBounds(143, 215, 219, 20);
		panel.add(txtFechaNacimiento);
		txtFechaNacimiento.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnNewButton = new JButton("Agregar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					insertarEstudiante();
					JOptionPane.showMessageDialog(null, "Los datos se insertaron correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					}
					
				
			});
			buttonPane.add(btnNewButton);
			{
				JButton btnRegresar = new JButton("Regresar");
				btnRegresar.setActionCommand("Cancel");
				buttonPane.add(btnRegresar);
			}
		}
	}
	/*public void insertarEstudiante() {
	    Connection con = ConexionDB.getConnection();

	    if (con != null) {
	        try {
	            String sql = "INSERT INTO Estudiante (Id, Nombre1, Nombre2, Apellido1, Apellido2, Carrera, CategoriaPago,Nacionalidad,Direccion,FechaNacimiento) VALUES (?, ?, ?, ?, ?,?,?,?,?,?)";
	            PreparedStatement ps = con.prepareStatement(sql);

	            ps.setString(1, txtNombre1.getText() );
	            ps.setString(2, txtNombre2.getText());
	            ps.setString(3, txtApellido1.getText());
	            ps.setString(4, txtApellido2.getText());
	            ps.setString(5, txtId.getText());
	            ps.setString(6, txtCarrera.getText());
	            ps.setString(7, txtNacionalidad.getText());
	            ps.setString(8, txtDireccion.getText());
	            ps.setString(9, txtFechaNacimiento.getText());
	            ps.setString(10, (String) cbxCategoriaPago.getSelectedItem());


	            ps.executeUpdate();

	            ps.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    } else {
	        System.out.println("Error en la conexión");
	    }
	}*/
	
	public void insertarEstudiante() {
	    Connection con = ConexionDB.getConnection();

	    if (con != null) {
	        try {
	            String sql = "INSERT INTO Estudiante (Id, Nombre1, Nombre2, Apellido1, Apellido2, Carrera, CategoriaPago, Nacionalidad, Direccion, FechaNacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement ps = con.prepareStatement(sql);

	            ps.setString(1, txtId.getText());
	            ps.setString(2, txtNombre1.getText());
	            ps.setString(3, txtNombre2.getText());
	            ps.setString(4, txtApellido1.getText());
	            ps.setString(5, txtApellido2.getText());
	            ps.setString(6, txtCarrera.getText());
	            ps.setString(7, (String) cbxCategoriaPago.getSelectedItem());
	            ps.setString(8, txtNacionalidad.getText());
	            ps.setString(9, txtDireccion.getText());
	            
	            // Convertir la fecha de nacimiento a un objeto java.sql.Date
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date utilDate = dateFormat.parse(txtFechaNacimiento.getText());
	            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	            ps.setDate(10, sqlDate);

	           

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
