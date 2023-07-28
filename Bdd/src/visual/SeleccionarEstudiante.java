package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class SeleccionarEstudiante extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEstudiante;
	
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SeleccionarEstudiante dialog = new SeleccionarEstudiante();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SeleccionarEstudiante() {
		setTitle("Estudiantes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarEstudiantes.class.getResource("/Images/estudiante4.png")));
		

		setModal(true);
		setSize(970, 488);
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (screenSize.width - getWidth()) / 2;
		    int y = (screenSize.height - getHeight()-30) / 2;
		    setLocation(x, y);
		//setBounds(100, 100, 969, 526);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(0, 0, 953, 454);
			setLocationRelativeTo(null);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Estudiantes");
				lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 50));
				lblNewLabel.setBounds(357, 33, 292, 67);
				panel.add(lblNewLabel);
			}
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 142, 933, 301);
			panel.add(scrollPane);
			
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
					{null, null},
				},
				new String[] {
					"New column", "New column"
				}
			));
			scrollPane.setViewportView(table);
			
			JLabel lblNewLabel_1 = new JLabel("Buscar Estudiante:");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel_1.setBounds(84, 114, 114, 14);
			panel.add(lblNewLabel_1);
			{
		
				txtEstudiante = new JTextField();
				txtEstudiante.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						actualizarTablaEstudiante(table);
					}
				});
				txtEstudiante.setBounds(208, 111, 245, 20);
				panel.add(txtEstudiante);
				txtEstudiante.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnNewButton = new JButton("Seleccionar");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						int selectedRow = table.getSelectedRow();
		                if (selectedRow != -1) {
		                    String Id = (String) table.getValueAt(selectedRow, 0);
		                    Incripcion.txtMatricula.setText(Id);;
		                    String Nombre = (String) table.getValueAt(selectedRow, 1);
		                    Incripcion.txtNombre.setText(Nombre);;
		                    dispose();
		                } else {
		                    JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar.");
		                }
								
					}
				});
				buttonPane.add(btnNewButton);
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
        	if (txtEstudiante != null) {
        	    estudiante = txtEstudiante.getText();
        	}

        	
        	String sql = "SELECT E.Id,(SELECT * FROM dbo.FormatoNombre(E.Id)) AS Nombre , E.Carrera, E.CategoriaPago AS 'Categoria de Pago', E.Nacionalidad, E.Direccion, E.FechaNacimiento AS 'Fecha de Nacimiento' FROM Estudiante E";

        	if (!estudiante.isEmpty()) {
        	    // Si el campo estudiante no está vacío, agregamos la condición de búsqueda
        	    sql += " WHERE Id LIKE '%" + estudiante + "%' OR Nombre1 LIKE '%" + estudiante + "%' OR Nombre2 LIKE '%" + estudiante + "%' OR Apellido1 LIKE '%" + estudiante + "%' OR Apellido2 LIKE '%" + estudiante + "%'";
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

