package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;

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
			Asignatura dialog = new Asignatura(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Asignatura(String codAsignatura) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Asignatura.class.getResource("/Images/Boton.png")));
		setTitle("Asignaturas");
		setModal(true);
		setSize(519, 289);
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (screenSize.width - getWidth()) / 2;
		    int y = (screenSize.height - getHeight()-30) / 2;
		    setLocation(x, y);
		//setBounds(100, 100, 519, 226);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 503, 213);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Código de Asignatura:");
		lblNewLabel.setBounds(10, 79, 137, 14);
		panel.add(lblNewLabel);
		
		txtcodAsig = new JTextField();
		txtcodAsig.setBounds(157, 76, 193, 20);
		panel.add(txtcodAsig);
		txtcodAsig.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 116, 75, 14);
		panel.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(157, 113, 193, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Créditos:");
		lblNewLabel_2.setBounds(360, 163, 70, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Horas Teóricas:");
		lblNewLabel_3.setBounds(23, 163, 103, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Horas Prácticas:");
		lblNewLabel_4.setBounds(186, 163, 104, 14);
		panel.add(lblNewLabel_4);
		
		 creditos = new JSpinner();
		creditos.setBounds(440, 160, 53, 20);
		panel.add(creditos);
		
		teorica = new JSpinner();
		teorica.setBounds(123, 160, 53, 20);
		panel.add(teorica);
		
		practica = new JSpinner();
		practica.setBounds(297, 160, 53, 20);
		panel.add(practica);
		
		JLabel lblNewLabel_5 = new JLabel("Asignaturas");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		lblNewLabel_5.setBounds(138, 11, 325, 54);
		panel.add(lblNewLabel_5);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
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
		if(codAsignatura!=null) {
			cargarAsignatura(codAsignatura);
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
	public void updateAsignatura(String cod) {
	    Connection con = ConexionDB.getConnection();

	    if (con != null) {
	        try {
	            String sql = "UPDATE Asignatura SET CodAsignatura = ?, Nombre = ?, Creditos = ?, HorasTeoricas = ?, HorasPracticas = ? WHERE CodAsignatura='" + cod + "'";
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
	public void cargarAsignatura(String cod) {
	    Connection con = ConexionDB.getConnection();

	    if (con != null) {
	            String sql = "SELECT CodAsignatura, Nombre, Creditos, HorasTeoricas, HorasPracticas FROM Asignatura WHERE CodAsignatura='" + cod + "'";
	            try (Statement stmt = con.createStatement();
	                    ResultSet rs = stmt.executeQuery(sql)) {
	                   ResultSetMetaData rsmd = rs.getMetaData();
	                   
	                   while (rs.next()) {
	                       
	                           String codigo = (String) rs.getObject(1);
	                           txtcodAsig.setText(codigo);
	                           String nombre = (String) rs.getObject(2);
	                           txtNombre.setText(nombre);
	                           int credito = (int) rs.getObject(3);
	                           creditos.setValue(credito);
	                           int teoricas = (int) rs.getObject(3);
	                           teorica.setValue(teoricas);
	                           int practicas = (int) rs.getObject(3);
	                           practica.setValue(practicas);
	                   }
	               } catch (SQLException e) {
	                   e.printStackTrace();
	               }
	    } else {
	        System.out.println("Error en la conexión");
	    }
	}
}
