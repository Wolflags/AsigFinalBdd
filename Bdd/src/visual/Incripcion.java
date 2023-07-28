package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logico.ConexionDB;

public class Incripcion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static JTextField txtNombre;
	public static JTextField txtMatricula;
	public static JTable gruposInsc;
	public static String txtPeriodo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Incripcion dialog = new Incripcion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Incripcion() {
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
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(0, 0, 790, 412);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel inscripcion = new JLabel("Inscripcion");
				inscripcion.setFont(new Font("Tahoma", Font.PLAIN, 42));
				inscripcion.setBounds(297, 11, 230, 69);
				panel.add(inscripcion);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(49, 136, 585, 265);
				panel.add(scrollPane);
				
				gruposInsc = new JTable();
				gruposInsc.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Num. Grupo", "Cod. Asignatura","Asignatura", "Horario","Cupo"
					}
				));
				scrollPane.setViewportView(gruposInsc);
			}
			{
				JLabel lblNewLabel = new JLabel("Nombre:");
				lblNewLabel.setBounds(251, 91, 82, 14);
				panel.add(lblNewLabel);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setEditable(false);
				txtNombre.setBounds(318, 88, 316, 20);
				panel.add(txtNombre);
				txtNombre.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Matricula:");
				lblNewLabel_1.setBounds(49, 91, 73, 14);
				panel.add(lblNewLabel_1);
			}
			{
				txtMatricula = new JTextField();
				txtMatricula.setEditable(false);
				txtMatricula.setBounds(121, 88, 112, 20);
				panel.add(txtMatricula);
				txtMatricula.setColumns(10);
			}
			{
				JButton btnNewButton = new JButton("Seleccionar");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SeleccionarEstudiante dialog = new SeleccionarEstudiante();
						dialog.setVisible(true);
					}
				});
				btnNewButton.setBounds(644, 87, 121, 23);
				panel.add(btnNewButton);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Grupos:");
				lblNewLabel_2.setBounds(49, 120, 56, 14);
				panel.add(lblNewLabel_2);
			}
			{
				JButton btnNewButton_1 = new JButton("Agregar");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SeleccionarGrupo dialog = new SeleccionarGrupo();
						dialog.setVisible(true);
					}
				});
				btnNewButton_1.setBounds(644, 136, 121, 45);
				panel.add(btnNewButton_1);
			}
			{
				JButton btnNewButton_2 = new JButton("Eliminar");
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int filaSeleccionada = gruposInsc.getSelectedRow();
					    if (filaSeleccionada == -1) {
					        return;
					    }

					    int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este grupo?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
					    if (respuesta == JOptionPane.YES_OPTION) {
					        DefaultTableModel model = (DefaultTableModel) gruposInsc.getModel();

					        model.removeRow(filaSeleccionada);
					    }
					}
				});
				btnNewButton_2.setBounds(644, 193, 121, 45);
				panel.add(btnNewButton_2);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Inscribir");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(verificarDatos()) {
							inscribir();
						}
					}

					
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
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
	}
	private void inscribir() {
		Connection con = ConexionDB.getConnection();
        if (con != null) {

        	for (int i = 0; i < gruposInsc.getRowCount(); i++) {
                String sql = "INSERT INTO Inscripcion (CodPeriodoAcad, Id, CodAsignatura, NumGrupo) VALUES (?, ?, ?, ?)";

                try {
                    PreparedStatement pstmt = con.prepareStatement(sql);
                    getPeriodoAcademico();
                    pstmt.setString(1, txtPeriodo);
                    pstmt.setString(2, txtMatricula.getText());
                    pstmt.setString(3, (String) gruposInsc.getValueAt(i, 2));
                    pstmt.setString(4, (String) gruposInsc.getValueAt(i, 0));
                    int rowsAffected = pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "El estudiante se ha inscrito correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

        } else {
            System.out.println("Error en la conexión");
        }
						
					}
	public boolean verificarDatos() {
        if(txtMatricula.getText().isEmpty()) {
        	JOptionPane.showMessageDialog(null, "Debe seleccionar un estudiante", "Error", JOptionPane.ERROR_MESSAGE);
        	return false;
        }
        DefaultTableModel model = (DefaultTableModel) gruposInsc.getModel();
        if (model.getRowCount() == 0) {
        	JOptionPane.showMessageDialog(null, "Debe agregar al menos un grupo", "Error", JOptionPane.ERROR_MESSAGE);
        	return false;
        }
        
        return true;
    }
	
	public String getPeriodoAcademico() {
		Connection con = ConexionDB.getConnection();
        if (con != null) {
            String sql = "SELECT DISTINCT TOP 1 CodPeriodoAcad FROM Grupo";
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                // Llena el comboBox con los periodos académicos
                while (rs.next()) {
                    txtPeriodo=(rs.getString("CodPeriodoAcad"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error en la conexión");
        }
        return null;
		
	}
}
