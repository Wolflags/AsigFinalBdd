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
import javax.swing.table.DefaultTableModel;

import logico.ConexionDB;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

public class CrearModificarGrupos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtAsignatura;
	public static JTable Horarios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearModificarGrupos dialog = new CrearModificarGrupos("ICC-133");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearModificarGrupos(String asignatura) {
		setModal(true);
		setSize(422, 490);
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (screenSize.width - getWidth()) / 2;
		    int y = (screenSize.height - getHeight()-30) / 2;
		    setLocation(x, y);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Nuevo Grupo");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
			lblNewLabel.setBounds(74, 11, 257, 58);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Periodo:");
			lblNewLabel_1.setBounds(60, 105, 70, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JComboBox periodoAcademico = new JComboBox();
			periodoAcademico.setBounds(127, 101, 183, 22);
			contentPanel.add(periodoAcademico);
			llenarComboBox(periodoAcademico);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Asignatura:");
			lblNewLabel_2.setBounds(47, 159, 96, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			txtAsignatura = new JTextField();
			txtAsignatura.setEditable(false);
			txtAsignatura.setText(asignatura);
			txtAsignatura.setBounds(127, 156, 183, 20);
			contentPanel.add(txtAsignatura);
			txtAsignatura.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Cupo:");
			lblNewLabel_3.setBounds(72, 208, 58, 14);
			contentPanel.add(lblNewLabel_3);
		}
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(30, 1, 50, 1));
		spinner.setBounds(127, 205, 183, 20);
		contentPanel.add(spinner);
		{
			JLabel lblNewLabel_4 = new JLabel("Horario:");
			lblNewLabel_4.setBounds(60, 259, 58, 14);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(127, 258, 183, 90);
			contentPanel.add(scrollPane);
			{
				Horarios = new JTable();
				DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] {"Dia", "Inicio", "Fin"});
				scrollPane.setViewportView(Horarios);
				Horarios.setModel(model);
			}
		}
		{
			JButton btnNewButton = new JButton("Agregar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Crea una nueva instancia de tu JDialog
			        SeleccionarHorario dialog = new SeleccionarHorario();
			        dialog.setVisible(true);
				}
			});
			btnNewButton.setBounds(127, 359, 89, 23);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnNewButton_1 = new JButton("Eliminar");
			btnNewButton_1.setBounds(221, 359, 89, 23);
			contentPanel.add(btnNewButton_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Insertar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
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
	public static void actualizarTabla(JTable table, String dia, String inicio, String fin) {
	    // Obtiene el modelo de la tabla
	    DefaultTableModel model = (DefaultTableModel) table.getModel();

	    // Crea un nuevo renglón con los valores recibidos
	    Object[] newRow = {dia, inicio, fin};

	    // Añade el nuevo renglón al modelo de la tabla
	    model.addRow(newRow);
	}
}
