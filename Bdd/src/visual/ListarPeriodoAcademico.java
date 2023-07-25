package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logico.ConexionDB;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarPeriodoAcademico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtAcademico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarPeriodoAcademico dialog = new ListarPeriodoAcademico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarPeriodoAcademico() {
		setTitle("Periodo Académico");
		setModal(true);
		setBounds(100, 100, 1339, 517);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(0, 0, 1323, 441);
			setLocationRelativeTo(null);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Periodo");
			lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 50));
			lblNewLabel.setBounds(578, 26, 194, 72);
			panel.add(lblNewLabel);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 110, 1303, 300);
			panel.add(scrollPane);
			{
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null, null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null, null, null, null},
					},
					new String[] {
						"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
					}
				));
				scrollPane.setViewportView(table);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Periodo:");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblNewLabel_1.setBounds(10, 82, 62, 14);
				panel.add(lblNewLabel_1);
			}
			{
				txtAcademico = new JTextField();
				txtAcademico.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						actualizarTablaPeriodo(table);
					}
				});
				txtAcademico.setBounds(82, 79, 86, 20);
				panel.add(txtAcademico);
				txtAcademico.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnModificar = new JButton("Modificar");
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
			}
			{
				JButton okButton = new JButton("Eliminar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int selectedRow = table.getSelectedRow();
		                if (selectedRow != -1) {
		                    String codPeriodoAcademico = (String) table.getValueAt(selectedRow, 0);
		                    deleteFrom(codPeriodoAcademico);
		                } else {
		                    JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar.");
		                }
		                actualizarTablaPeriodo(table);
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
		actualizarTablaPeriodo(table);
	}
	public void actualizarTablaPeriodo(JTable table) {
        Connection con = ConexionDB.getConnection();
        if (con != null) {
        	String academico = "";
        	if (txtAcademico != null) {
        		academico = txtAcademico.getText();
        	}

        	
        	String sql = "SELECT PA.CodPeriodoAcad as 'Periodo', PA.Descripcion as 'Ciclo',PA.FechaInicio as 'P-Inicio', PA.FechaFin as 'P-Fin', PA.FechaInicioClases as 'Inicio de Clases', PA.FechaFinClases as 'Fin de Clases', PA.FechaLimitePago as 'Limite de Pago', PA.FechaLimitePrematricula as 'Limite de Prematricula',PA.FechaLimiteRetiro as 'Limite de Retiro', PA.FechaLimitePublicacionCalif as 'Limite de Notas' FROM PeriodoAcademico PA ";

        	 if (!academico.isEmpty()) {
                 // Si el campo periodo (academico) no está vacío, agregamos la condición de búsqueda
                 sql += " WHERE CodPeriodoAcad LIKE '%" + academico + "%' OR Descripcion LIKE '%" + academico + "%'";
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
	private void deleteFrom(String codPeriodoAcademico) {
		String deleteQuery = "DELETE FROM PeriodoAcademico WHERE CodPeriodoAcademico = ?";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setString(1, codPeriodoAcademico);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Registro eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún registro para eliminar.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el registro: " + ex.getMessage());
        }
    }
}
