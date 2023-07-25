package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logico.ConexionDB;

import javax.swing.JTextField;

public class ListarAsignatura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtAsignatura;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarAsignatura dialog = new ListarAsignatura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarAsignatura() {
		setModal(true);
		setBounds(100, 100, 764, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(0, 0, 748, 378);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Asignaturas");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
				lblNewLabel.setBounds(235, 11, 272, 61);
				panel.add(lblNewLabel);
			}
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 107, 728, 249);
			panel.add(scrollPane);
			
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null},
					{null, null, null, null, null},
				},
				new String[] {
					"New column", "New column", "New column", "New column", "New column"
				}
			));
			scrollPane.setViewportView(table);
			
			JLabel lblNewLabel_1 = new JLabel("Buscar Asignatura:");
			lblNewLabel_1.setBounds(10, 82, 110, 14);
			panel.add(lblNewLabel_1);
			
			txtAsignatura = new JTextField();
			txtAsignatura.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					actualizarTablaAsignatura(table);
				}
			});
			txtAsignatura.setBounds(130, 79, 97, 20);
			panel.add(txtAsignatura);
			txtAsignatura.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Modificar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton btnEliminar = new JButton("Eliminar");
				btnEliminar.setActionCommand("Cancel");
				buttonPane.add(btnEliminar);
			}
			{
				JButton cancelButton = new JButton("Regresar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		actualizarTablaAsignatura(table);
	}
	public void actualizarTablaAsignatura(JTable table) {
        Connection con = ConexionDB.getConnection();
        if (con != null) {
        	String asignatura = "";
        	if (txtAsignatura != null) {
        	    asignatura = txtAsignatura.getText();
        	}

        	
        	String sql = "SELECT CodAsignatura, Nombre, Creditos, HorasTeoricas, HorasPracticas FROM Asignatura";

        	if (!asignatura.isEmpty()) {
        	    // Si el campo estudiante no está vacío, agregamos la condición de búsqueda
        	    sql += " WHERE CodAsignatura LIKE '%" + asignatura + "%' OR Nombre LIKE '%" + asignatura + "%'";
        	}

        
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                // Obtén los metadatos del ResultSet
                ResultSetMetaData rsmd = rs.getMetaData();
                // El modelo de la tabla
                DefaultTableModel model = new DefaultTableModel();
                // Llena el modelo con los nombres de las columnas
                int columnCount = rsmd.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    model.addColumn(rsmd.getColumnName(i));
                }
                // Llena el modelo con los registros
                while (rs.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = rs.getObject(i);
                    }
                    model.addRow(row);
                }
                // Asigna el modelo a la tabla
                table.setModel(model);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión");
        }
    }
}
