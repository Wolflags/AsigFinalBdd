package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logico.ConexionDB;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ListarEstudiantes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEstudiante;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarEstudiantes dialog = new ListarEstudiantes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarEstudiantes() {
		setModal(true);
		setBounds(100, 100, 1296, 526);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(0, 0, 1280, 454);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Estudiantes");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
				lblNewLabel.setBounds(588, 46, 292, 67);
				panel.add(lblNewLabel);
			}
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 142, 1260, 312);
			panel.add(scrollPane);
			
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null, null, null},
				},
				new String[] {
					"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
				}
			));
			scrollPane.setViewportView(table);
			
			JLabel lblNewLabel_1 = new JLabel("Buscar Estudiante:");
			lblNewLabel_1.setBounds(23, 117, 114, 14);
			panel.add(lblNewLabel_1);
			{
		
				txtEstudiante = new JTextField();
				txtEstudiante.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						actualizarTablaEstudiante(table);
					}
				});
				txtEstudiante.setBounds(131, 114, 245, 20);
				panel.add(txtEstudiante);
				txtEstudiante.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnNewButton = new JButton("Modificar");
				buttonPane.add(btnNewButton);
			}
			{
				JButton okButton = new JButton("Eliminar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Regresar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Crea una nueva instancia de tu JDialog
				        Inicio dialog = new Inicio();
				        dialog.setVisible(true); // Muestra el JDialog
				        // Cierra el JFrame actual
				        dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		actualizarTablaEstudiante(table);
	}
	
	public void actualizarTablaEstudiante(JTable table) {
        Connection con = ConexionDB.getConnection();
        if (con != null) {
        	String estudiante = "";
        	if(txtEstudiante!=null) {
        	estudiante = txtEstudiante.getText();
        	}
        	
            String sql = "SELECT Id as 'Matricula', Nombre1 as 'Nombre', Nombre2 as 'Segundo Nombre', Apellido1 as 'Apellido', Apellido2 as 'Segundo Apellido', Carrera, CategoriaPago as 'Pago',Nacionalidad, Direccion, FechaNacimiento as 'Fecha de Nacimiento' FROM Estudiante ";
   
            if (!estudiante.isEmpty()) {//si el txtEstudiante no esta vacia entonces...
               //verifica si el valor ingresado esta en una de las columnas
                sql += "WHERE Nombre1 LIKE '%" + estudiante + "%' OR Nombre2 LIKE '%" + estudiante + "%' OR Apellido1 LIKE '%" + estudiante + "%' OR Apellido2 LIKE '%" + estudiante + "%'";
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
