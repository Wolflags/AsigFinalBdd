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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;

public class Estudiante extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre1;
	private JTextField txtNombre2;
	private JTextField txtApellido1;
	private JTextField txtApellido2;
	private JTextField txtCarrera;
	private JTextField txtNacionalidad;
	private JTextField txtFechaNacimiento;
	private JTextArea txtDireccion;
	JComboBox cbxCategoriaPago = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Estudiante dialog = new Estudiante(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Estudiante(String idEstudiante) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Estudiante.class.getResource("/Images/estudiante4.png")));
		setTitle("Estudiante");
		setModal(true);
		setSize(573,468);
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (screenSize.width - getWidth()) / 2;
		    int y = (screenSize.height - getHeight()-30) / 2;
		    setLocation(x, y);
		//setBounds(100, 100, 573, 388);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 557, 392);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Matrícula:");
		lblNewLabel.setBounds(10, 177, 71, 14);
		panel.add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setBounds(91, 174, 124, 20);
		panel.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 97, 71, 14);
		panel.add(lblNewLabel_1);
		
		txtNombre1 = new JTextField();
		txtNombre1.setBounds(91, 94, 124, 20);
		panel.add(txtNombre1);
		txtNombre1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Segundo Nombre:");
		lblNewLabel_2.setBounds(294, 97, 119, 14);
		panel.add(lblNewLabel_2);
		
		txtNombre2 = new JTextField();
		txtNombre2.setBounds(423, 94, 124, 20);
		panel.add(txtNombre2);
		txtNombre2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Apellido:");
		lblNewLabel_3.setBounds(10, 138, 71, 14);
		panel.add(lblNewLabel_3);
		
		txtApellido1 = new JTextField();
		txtApellido1.setBounds(91, 135, 124, 20);
		panel.add(txtApellido1);
		txtApellido1.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Segundo Apellido:");
		lblNewLabel_2_1.setBounds(294, 138, 119, 14);
		panel.add(lblNewLabel_2_1);
		
		txtApellido2 = new JTextField();
		txtApellido2.setBounds(423, 135, 124, 20);
		panel.add(txtApellido2);
		txtApellido2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Carrera:");
		lblNewLabel_4.setBounds(294, 177, 119, 14);
		panel.add(lblNewLabel_4);
		
		txtCarrera = new JTextField();
		txtCarrera.setBounds(423, 174, 124, 20);
		panel.add(txtCarrera);
		txtCarrera.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Categoria de Pago:");
		lblNewLabel_5.setBounds(10, 342, 133, 14);
		panel.add(lblNewLabel_5);
		
		cbxCategoriaPago = new JComboBox();
		cbxCategoriaPago.setModel(new DefaultComboBoxModel(new String[] {"TRA", "EFE"}));
		cbxCategoriaPago.setBounds(153, 338, 129, 22);
		panel.add(cbxCategoriaPago);
		
		JLabel lblNewLabel_6 = new JLabel("Nacionalidad:");
		lblNewLabel_6.setBounds(10, 215, 103, 14);
		panel.add(lblNewLabel_6);
		
		txtNacionalidad = new JTextField();
		txtNacionalidad.setBounds(123, 212, 266, 20);
		panel.add(txtNacionalidad);
		txtNacionalidad.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Dirección:");
		lblNewLabel_7.setBounds(10, 261, 78, 14);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Fecha de Nacimiento:");
		lblNewLabel_8.setBounds(10, 301, 124, 14);
		panel.add(lblNewLabel_8);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setBounds(143, 298, 219, 20);
		panel.add(txtFechaNacimiento);
		txtFechaNacimiento.setColumns(10);
		
		
		txtDireccion = new JTextArea();
		txtDireccion.setBackground(new Color(192, 192, 192));
        txtDireccion.setBounds(91, 256, 245, 22);
        panel.add(txtDireccion);
        
        JLabel lblNewLabel_9 = new JLabel("Estudiante");
        lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        lblNewLabel_9.setBounds(169, 11, 322, 54);
        panel.add(lblNewLabel_9);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			if(idEstudiante==null) {
			JButton btnNewButton = new JButton("Agregar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					insertarEstudiante();
					JOptionPane.showMessageDialog(null, "Los datos se insertaron correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					}
					
				
			});
			buttonPane.add(btnNewButton);
		}else{
			JButton btnNewButton = new JButton("Modificar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					updateEstudiante(idEstudiante);
					JOptionPane.showMessageDialog(null, "Los datos se actualizaron correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					}
					
				
			});
			buttonPane.add(btnNewButton);
		}
			{
				JButton btnRegresar = new JButton("Regresar");
				btnRegresar.setActionCommand("Cancel");
				buttonPane.add(btnRegresar);
			}
		}
		if(idEstudiante!=null) {
		cargarEstudiante(idEstudiante);
		}
	}

	
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
	
	public void updateEstudiante(String matricula) {
	    Connection con = ConexionDB.getConnection();

	    if (con != null) {
	        try {
	            String sql = "UPDATE Estudiante SET Id = ?, Nombre1 = ?, Nombre2 = ?, Apellido1 = ?, Apellido2 = ?, Carrera = ?, CategoriaPago = ?, Nacionalidad = ?, Direccion = ?, FechaNacimiento = ? WHERE Id='" + matricula + "'";
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
	
	public void cargarEstudiante(String matricula) {
	    Connection con = ConexionDB.getConnection();

	    if (con != null) {
	            String sql = "SELECT Nombre1,Nombre2,Apellido1,Apellido2,Id,Carrera,Nacionalidad,Direccion,FechaNacimiento,CategoriaPago FROM Estudiante WHERE Id='" + matricula + "'";
	            try (Statement stmt = con.createStatement();
	                    ResultSet rs = stmt.executeQuery(sql)) {
	                   ResultSetMetaData rsmd = rs.getMetaData();
	                   
	                   while (rs.next()) {
	                       
	                           String nombre1 = (String) rs.getObject(1);
	                           txtNombre1.setText(nombre1);
	                           String nombre2 = (String) rs.getObject(2);
	                           txtNombre2.setText(nombre2);
	                           String apellido1 = (String) rs.getObject(3);
	                           txtApellido1.setText(apellido1);
	                           String apellido2 = (String) rs.getObject(4);
	                           txtApellido2.setText(apellido2);
	                           String matriculas = (String) rs.getObject(5);
	                           txtId.setText(matriculas);
	                           String carrera = (String) rs.getObject(6);
	                           txtCarrera.setText(carrera);
	                           String nacionalidad = (String) rs.getObject(7);
	                           txtNacionalidad.setText(nacionalidad);
	                           String direccion = (String) rs.getObject(8);
	                           txtDireccion.setText(direccion);
	                           //String fechanacim = (String) rs.getObject(9);
	                           //txtFechaNacimiento.setText(fechanacim);
	                           java.sql.Date fechaSql = rs.getDate("FechaNacimiento");
	   	                    if (fechaSql != null) {
	   	                        SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
	   	                        String fechaFormateada = fecha.format(fechaSql);
	   	                        txtFechaNacimiento.setText(fechaFormateada);
	   	                    }
	                           if(((String) rs.getObject(10))!=null) {
	                           if(((String) rs.getObject(10)).equalsIgnoreCase("TRA")) {
	                        	   cbxCategoriaPago.setSelectedIndex(0);
	                           }else {
	                        	   cbxCategoriaPago.setSelectedIndex(1);
	                           }
	                           }
	                   }
	               } catch (SQLException e) {
	                   e.printStackTrace();
	               }
	    } else {
	        System.out.println("Error en la conexión");
	    }
	}
}
