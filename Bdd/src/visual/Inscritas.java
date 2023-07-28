package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.ConexionDB;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Inscritas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtMatricula;
	private JTextField txtNombre;
	JComboBox cmbPeriodo = new JComboBox();
	public JTable grupos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Inscritas dialog = new Inscritas("10304050","Juan C. Núñez M.");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error");
		}
	}

	/**
	 * Create the dialog.
	 */
	public Inscritas(String matricula,String nombre) {
		setModal(true);
		setBounds(100, 100, 681, 469);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(0, 0, 665, 393);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Grupos Inscritos:");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 43));
				lblNewLabel.setBounds(158, 11, 352, 38);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Matricula:");
				lblNewLabel_1.setBounds(10, 88, 67, 14);
				panel.add(lblNewLabel_1);
			}
			{
				txtMatricula = new JTextField();
				txtMatricula.setEditable(false);
				txtMatricula.setBounds(70, 85, 105, 20);
				panel.add(txtMatricula);
				txtMatricula.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Nombre:");
				lblNewLabel_2.setBounds(185, 88, 58, 14);
				panel.add(lblNewLabel_2);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setEditable(false);
				txtNombre.setBounds(236, 85, 192, 20);
				panel.add(txtNombre);
				txtNombre.setColumns(10);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Periodo:");
				lblNewLabel_3.setBounds(438, 88, 72, 14);
				panel.add(lblNewLabel_3);
			}
			{
				cmbPeriodo = new JComboBox();
				cmbPeriodo.setBounds(494, 84, 161, 22);
				panel.add(cmbPeriodo);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 116, 645, 266);
				panel.add(scrollPane);
				{
					grupos = new JTable();
					scrollPane.setViewportView(grupos);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Dar de baja");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int selectedRow = grupos.getSelectedRow();
						 if (selectedRow != -1) {
					        
					        String numGrupo = grupos.getValueAt(selectedRow, 0).toString();
					        String matricula=txtMatricula.getText();
					        String periodo=cmbPeriodo.getSelectedItem().toString();
					        String asignatura= grupos.getValueAt(selectedRow, 1).toString();
					        EliminarAsignatura(numGrupo,matricula,periodo,asignatura);
						 }else {
							 JOptionPane.showMessageDialog(null, "Por favor, selecciona una fila.");
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
		actualizarTabla();
	}
	public void EliminarAsignatura(String numGrupo, String matricula, String periodo, String asignatura) {
	    // Crea la conexión
	    Connection con = ConexionDB.getConnection();

	    if (con != null) {
	        String sql = "DELETE FROM dbo.Inscripcion WHERE NumGrupo = ? AND Id = ? AND CodPeriodoAcad = ? AND CodAsignatura = ?";

	        try (PreparedStatement stmt = con.prepareStatement(sql)) {
	            stmt.setString(1, numGrupo);
	            stmt.setString(2, matricula);
	            stmt.setString(3, periodo);
	            stmt.setString(4, asignatura);

	            int rowsAffected = stmt.executeUpdate();
	            JOptionPane.showMessageDialog(null, "Clase retirada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            actualizarTabla();

	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Error");
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
                JOptionPane.showMessageDialog(null, "Error");
            }
        } else {
            System.out.println("Error en la conexión");
        }
    }
	public void actualizarTabla() {
		Connection con = ConexionDB.getConnection();
        if (con != null) {
        	
        	
            String sql = "SELECT G.NumGrupo,G.CodAsignatura,G.Horario,G.CupoGrupo FROM Grupo G, Inscripcion I WHERE G.CodPeriodoAcad='"+cmbPeriodo.getSelectedItem()+"' AND I.Id = '"+txtMatricula.getText()+"' AND G.CodPeriodoAcad=I.CodPeriodoAcad AND G.NumGrupo=I.NumGrupo ORDER BY NumGrupo";
   
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
                grupos.setModel(model);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error");
            }
        } else {
            System.out.println("Error en la conexión");
        }
	}
	
}





