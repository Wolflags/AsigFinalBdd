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
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;

public class HorarioEstudiante extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static JTable tablaClases;
	private JTextField txtMatricula;
	private JTextField txtNombre;
	private JTextField txtCarrera;
	JTextArea txtFormatoReducido = new JTextArea();
	JComboBox cmbPeriodo = new JComboBox();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HorarioEstudiante dialog = new HorarioEstudiante("10304050","Juan C. Núñez M.","ICC");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HorarioEstudiante(String matricula,String nombre, String carrera) {
		setModal(true);
		setBounds(100, 100, 943, 633);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 927, 557);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Horario de Clases");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 44));
		lblNewLabel.setBounds(302, 29, 418, 54);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 148, 907, 277);
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
		
		txtMatricula = new JTextField();
		txtMatricula.setEditable(false);
		txtMatricula.setBounds(76, 117, 93, 20);
		panel.add(txtMatricula);
		txtMatricula.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setBounds(186, 120, 69, 14);
		panel.add(lblNewLabel_2);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(237, 117, 298, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Carrera:");
		lblNewLabel_3.setBounds(545, 120, 61, 14);
		panel.add(lblNewLabel_3);
		
		txtCarrera = new JTextField();
		txtCarrera.setEditable(false);
		txtCarrera.setBounds(599, 117, 83, 20);
		panel.add(txtCarrera);
		txtCarrera.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Periodo:");
		lblNewLabel_4.setBounds(692, 120, 61, 14);
		panel.add(lblNewLabel_4);
		
		cmbPeriodo = new JComboBox();
		cmbPeriodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTabla();
			}
		});
		cmbPeriodo.setBounds(742, 116, 175, 22);
		panel.add(cmbPeriodo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Regresar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		llenarComboBox(cmbPeriodo);
		txtMatricula.setText(matricula);
		txtNombre.setText(nombre);
		txtCarrera.setText(carrera);
		
		JLabel lblNewLabel_5 = new JLabel("Horario Reducido:");
		lblNewLabel_5.setBounds(10, 436, 124, 14);
		panel.add(lblNewLabel_5);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 461, 907, 85);
		panel.add(scrollPane_1);
		
		txtFormatoReducido = new JTextArea();
		txtFormatoReducido.setEditable(false);
		scrollPane_1.setViewportView(txtFormatoReducido);
		actualizarTabla();
	}
	private void actualizarTabla() {
		Connection con = ConexionDB.getConnection();
        if (con != null) {
		
		try {
		    // 1. Llama al stored procedure
		    CallableStatement stmt = con.prepareCall("{call sp_HorarioEstudiante(?,?)}");
		    stmt.setString(1, txtMatricula.getText());
		    stmt.setString(2, cmbPeriodo.getSelectedItem().toString());
		    ResultSet rs = stmt.executeQuery();

		    // 2. Procesa el ResultSet
		    ResultSetMetaData rsmd = rs.getMetaData();
		    int columnCount = rsmd.getColumnCount();

		    // 3. Crea un DefaultTableModel y llena el modelo con los datos del ResultSet
		    DefaultTableModel model = (DefaultTableModel) tablaClases.getModel();
		    model.setRowCount(0);
		    while (rs.next()) {
		        Object[] row = new Object[columnCount];
		        for (int i = 1; i <= columnCount; i++) {
		            row[i - 1] = rs.getObject(i);
		        }
		        model.addRow(row);
		    }

		    // 4. Establece el modelo de la JTable con el DefaultTableModel creado
		    tablaClases.setModel(model);
		    HorarioReducido();

		} catch (SQLException e) {
		    e.printStackTrace();
		}
        } else {
            System.out.println("Error en la conexión");
        }
		
		
	}
	
	public void llenarComboBox(JComboBox<String> comboBox) {
        Connection con = ConexionDB.getConnection();
        if (con != null) {
            String sql = "SELECT DISTINCT CodPeriodoAcad FROM PeriodoAcademico";
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    comboBox.addItem(rs.getString("CodPeriodoAcad"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión");
        }
    }
	
	public void HorarioReducido() {
	try {
	    Connection con = ConexionDB.getConnection();

	    String sql = "{? = CALL dbo.fn_GetConcatenatedValues(?, ?)}";
	    CallableStatement stmt = con.prepareCall(sql);

	    stmt.registerOutParameter(1, Types.NVARCHAR);
	    stmt.setString(2, "10304050");
	    stmt.setString(3, "2020-2021/3");

	    stmt.execute();

	    String result = stmt.getString(1);

	    txtFormatoReducido.setText(result);

	} catch (SQLException e) {
	    e.printStackTrace();
	}
	}
	
}






