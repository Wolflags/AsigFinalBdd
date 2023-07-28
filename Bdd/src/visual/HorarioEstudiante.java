package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.ConexionDB;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class HorarioEstudiante extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static JTable tablaClases;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HorarioEstudiante dialog = new HorarioEstudiante();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HorarioEstudiante() {
		setBounds(100, 100, 943, 633);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 927, 557);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Horario de Clases");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 44));
		lblNewLabel.setBounds(302, 29, 418, 54);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 148, 907, 398);
		panel.add(scrollPane);
		
		tablaClases = new JTable();
		tablaClases.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Hora/Dia", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"
			}
		));
		scrollPane.setViewportView(tablaClases);
		
		JLabel lblNewLabel_1 = new JLabel("Matricula:");
		lblNewLabel_1.setBounds(10, 120, 99, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(76, 117, 93, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setBounds(186, 120, 69, 14);
		panel.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(237, 117, 298, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Carrera:");
		lblNewLabel_3.setBounds(545, 120, 61, 14);
		panel.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(599, 117, 83, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Periodo:");
		lblNewLabel_4.setBounds(692, 120, 61, 14);
		panel.add(lblNewLabel_4);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(742, 116, 175, 22);
		panel.add(comboBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Regresar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		actualizarTabla();
	}
	private void actualizarTabla() {
		Connection con = ConexionDB.getConnection();
        if (con != null) {
		
		try {
		    // 1. Llama al stored procedure
		    CallableStatement stmt = con.prepareCall("{call sp_HorarioEstudiante(?,?)}");
		    stmt.setString(1, "10304050");
		    stmt.setString(2, "2020-2021/3");
		    ResultSet rs = stmt.executeQuery();

		    // 2. Procesa el ResultSet
		    ResultSetMetaData rsmd = rs.getMetaData();
		    int columnCount = rsmd.getColumnCount();

		    // 3. Crea un DefaultTableModel y llena el modelo con los datos del ResultSet
		    DefaultTableModel model = (DefaultTableModel) tablaClases.getModel();
		    
		    while (rs.next()) {
		        Object[] row = new Object[columnCount];
		        for (int i = 1; i <= columnCount; i++) {
		            row[i - 1] = rs.getObject(i);
		        }
		        model.addRow(row);
		    }

		    // 4. Establece el modelo de la JTable con el DefaultTableModel creado
		    tablaClases.setModel(model);

		} catch (SQLException e) {
		    e.printStackTrace();
		}
        } else {
            System.out.println("Error en la conexión");
        }
		
		
	}
	
}






