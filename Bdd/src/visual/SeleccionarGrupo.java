package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import logico.ConexionDB;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class SeleccionarGrupo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static JTable table;
	private static JTextField txtAsignatura;
	private static JTextField txtNumGrupo;
	public static String txtPeriodo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SeleccionarGrupo dialog = new SeleccionarGrupo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SeleccionarGrupo() {
		setTitle("Grupos");
		setModal(true);
		setSize(806, 488);
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (screenSize.width - getWidth()) / 2;
		    int y = (screenSize.height - getHeight()-30) / 2;
		    setLocation(x, y);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 790, 412);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(112, 159, 573, 234);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Seleccionar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int selectedRow = table.getSelectedRow();
		                if (selectedRow != -1) {
		                    String Id = (String) table.getValueAt(selectedRow, 0);
		                    
		                    
		                    
		                    
		                    
		                    
		                    
		                    
		                    
		                    
		                    
		                    
		                    
		                    
		                    
		                    Connection con = ConexionDB.getConnection();
		                    if (con != null) {
		                    	
		                        String sql = "SELECT G.NumGrupo,G.Horario,A.CodAsignatura,A.Nombre,G.CupoGrupo FROM Grupo G, Asignatura A WHERE G.NumGrupo = '"+Id+"' AND G.CodAsignatura=A.CodAsignatura";
		               
		                        try (Statement stmt = con.createStatement();
		                             ResultSet rs = stmt.executeQuery(sql)) {
		                        	if (rs.next()) {
		                                DefaultTableModel model = (DefaultTableModel) Incripcion.gruposInsc.getModel();

		                                Object[] newRow = {rs.getString("NumGrupo"), rs.getString("Horario"), rs.getString("CodAsignatura"),rs.getString("Nombre"),rs.getString("CupoGrupo")};
		                                model.addRow(newRow);

		                                Incripcion.gruposInsc.setModel(model);
		                                dispose();
		                            }
		                        } catch (SQLException e1) {
		                            e1.printStackTrace();
		                        }
		                    } else {
		                        System.out.println("Error en la conexión");
		                    }
		                    
		                    
		                    
		                    
		                    
		                    
		                    
		                    
		                    
		                    
		                    dispose();
		                } else {
		                    JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar.");
		                }
					}
				});
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
		
		
		
		
		
		getPeriodoAcademico();
		actualizarTabla(table);
		
		JLabel lblNewLabel_1 = new JLabel("GRUPOS");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		lblNewLabel_1.setBounds(304, 11, 209, 95);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Buscar Asignatura:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(388, 131, 117, 14);
		panel.add(lblNewLabel_2);
		
		txtAsignatura = new JTextField();
		txtAsignatura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				actualizarTabla(table);
			}
		});
		
		txtAsignatura.setBounds(521, 128, 164, 20);
		panel.add(txtAsignatura);
		txtAsignatura.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Buscar Grupo:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1.setBounds(112, 131, 117, 14);
		panel.add(lblNewLabel_2_1);
		
		txtNumGrupo = new JTextField();
		txtNumGrupo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				actualizarTabla(table);
			}
		});
		txtNumGrupo.setColumns(10);
		txtNumGrupo.setBounds(199, 128, 164, 20);
		panel.add(txtNumGrupo);
		
		
	}
	public static void actualizarext() {
		try {
			actualizarTabla(table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void actualizarTabla(JTable table) {
        Connection con = ConexionDB.getConnection();
        if (con != null) {
        	String asignatura = "";
        	String grupo="";
        	if(txtAsignatura!=null) {
        	asignatura = txtAsignatura.getText();
        	}
        	if(txtNumGrupo!=null) {
            	grupo = txtNumGrupo.getText();
            	}
        	
            String sql = "SELECT NumGrupo,CodAsignatura,Horario,CupoGrupo FROM Grupo WHERE CodPeriodoAcad='"+txtPeriodo+"' AND CodAsignatura LIKE '%"+asignatura+"%' AND NumGrupo LIKE '%"+grupo+"%' ORDER BY NumGrupo";
   
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
	
	private void deleteFrom(String numGrupoToDelete) {
		String deleteQuery = "DELETE FROM Grupo WHERE NumGrupo = ?";

        try (Connection connection = ConexionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setString(1, numGrupoToDelete);
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
	
	public void llenarComboBox(JComboBox<String> comboBox) {
        Connection con = ConexionDB.getConnection();
        if (con != null) {
            String sql = "SELECT DISTINCT CodPeriodoAcad FROM Grupo";
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                // Llena el comboBox con los periodos académicos
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
	
	
	public void getPeriodoAcademico() {
		Connection con = ConexionDB.getConnection();
        if (con != null) {
            String sql = "SELECT DISTINCT TOP 1 CodPeriodoAcad FROM Grupo";
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                // Llena el comboBox con los periodos académicos
                while (rs.next()) {
                    txtPeriodo = (rs.getString("CodPeriodoAcad"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión");
        }
		
	}
}
