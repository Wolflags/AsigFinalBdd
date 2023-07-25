package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
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
		setModal(true);
		setBounds(100, 100, 923, 517);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(0, 0, 907, 441);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Periodo");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
			lblNewLabel.setBounds(322, 11, 194, 72);
			panel.add(lblNewLabel);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 110, 887, 300);
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
				lblNewLabel_1.setBounds(10, 88, 46, 14);
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
				txtAcademico.setBounds(66, 85, 86, 20);
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

        	
        	String sql = "SELECT PA.CodPeriodoAcad, PA.Descripcion,PA.FechaInicio as 'Fecha Inicio', PA.FechaFin as 'Fecha Fin', PA.FechaInicioClases as 'Inicio de Clases', PA.FechaFinClases as 'Fin de Clases', PA.FechaLimitePago as 'Limite de Pago', PA.FechaLimitePrematricula as 'Limite de Prematricula',PA.FechaLimiteRetiro as 'Limite de Retiro', PA.FechaLimitePublicacionCalif as 'Limite de Notas' FROM PeriodoAcademico PA ";

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
}
