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
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

public class CrearModificarGrupos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtAsignatura;
	public static JTable Horarios;
	JComboBox periodoAcademico = new JComboBox();
	JSpinner cupo = new JSpinner();
	String horarioReducido="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearModificarGrupos dialog = new CrearModificarGrupos("ISC-205-T");
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
			periodoAcademico = new JComboBox();
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
		
		cupo = new JSpinner();
		cupo.setModel(new SpinnerNumberModel(30, 1, 50, 1));
		cupo.setBounds(127, 205, 183, 20);
		contentPanel.add(cupo);
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
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarRegistro(Horarios);
				}
			});
			btnNewButton_1.setBounds(221, 359, 89, 23);
			contentPanel.add(btnNewButton_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton insertar = new JButton("Insertar");
				insertar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(Horarios.getRowCount()==0) {
							JOptionPane.showMessageDialog(null, "Debe agregar al menos un horario", "Error", JOptionPane.ERROR_MESSAGE);
						}else {
							if(!existeHorario(periodoAcademico.getSelectedItem().toString(),formatHorario(Horarios))) {
						insertarGrupo();
						JOptionPane.showMessageDialog(null, "Los datos se insertaron correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(null, "Este grupo posee conflictos de horario con otro", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				});
				insertar.setActionCommand("OK");
				buttonPane.add(insertar);
				getRootPane().setDefaultButton(insertar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
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
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    Object[] newRow = {dia, inicio, fin};
	    model.addRow(newRow);
	}
	
	public void eliminarRegistro(JTable table) {
	    int filaSeleccionada = table.getSelectedRow();
	    if (filaSeleccionada == -1) {
	        return;
	    }

	    int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este horario?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
	    if (respuesta == JOptionPane.YES_OPTION) {
	        DefaultTableModel model = (DefaultTableModel) table.getModel();

	        model.removeRow(filaSeleccionada);
	    }
	}
	
	public void insertarGrupo() {
	    Connection con = ConexionDB.getConnection();

	    if (con != null) {
	        try {
	            String sql = "INSERT INTO Grupo (CodPeriodoAcad, CodAsignatura, NumGrupo, CupoGrupo, Horario) VALUES (?, ?, ?, ?, ?)";
	            PreparedStatement ps = con.prepareStatement(sql);

	            ps.setString(1, periodoAcademico.getSelectedItem().toString());
	            ps.setString(2, txtAsignatura.getText());
	            ps.setString(3, "007");
	            ps.setInt(4, (Integer) cupo.getValue());
	            ps.setString(5, formatHorario(Horarios));

	            ps.executeUpdate();

	            ps.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    } else {
	        System.out.println("Error en la conexión");
	    }
	}
	
	
	

	public String formatHorario(JTable table) {
	    // Crea una lista para almacenar los horarios
	    List<String> horarios = new ArrayList<>();

	    // Recorre todas las filas de la tabla
	    for (int i = 0; i < table.getRowCount(); i++) {
	        // Obtiene el día, la hora de inicio y la hora de fin de la fila
	        String dia = (String) table.getValueAt(i, 0);
	        String inicio = (String) table.getValueAt(i, 1);
	        String fin = (String) table.getValueAt(i, 2);

	        // Convierte las horas de inicio y fin a sus formatos abreviados
	        String inicioAbreviado = inicio.split(":")[0];
	        String finAbreviado = fin.split(":")[0];

	        // Convierte el día a su abreviatura
	        String diaAbreviado = "";
	        switch (dia) {
	            case "Lunes":
	                diaAbreviado = "Lu";
	                break;
	            case "Martes":
	                diaAbreviado = "Ma";
	                break;
	            case "Miercoles":
	                diaAbreviado = "Mi";
	                break;
	            case "Jueves":
	                diaAbreviado = "Ju";
	                break;
	            case "Viernes":
	                diaAbreviado = "Vi";
	                break;
	            case "Sabado":
	                diaAbreviado = "Sa";
	                break;
	            case "Domingo":
	                diaAbreviado = "Do";
	                break;
	        }

	        // Crea el horario de la fila
	        String horarioFila = inicioAbreviado + "-" + finAbreviado;

	        // Busca el horario en la lista de horarios
	        int index = -1;
	        for (int j = 0; j < horarios.size(); j++) {
	            if (horarios.get(j).startsWith(horarioFila)) {
	                index = j;
	                break;
	            }
	        }

	        // Si el horario ya está en la lista, añade el día a ese horario
	        if (index != -1) {
	            horarios.set(index, horarios.get(index) + diaAbreviado);
	        }
	        // Si el horario no está en la lista, añade un nuevo horario a la lista
	        else {
	            horarios.add(horarioFila + diaAbreviado);
	        }
	    }

	    // Crea un StringBuilder para construir la cadena de horario
	    StringBuilder horario = new StringBuilder();
	    for (String h : horarios) {
	        horario.append(h).append(" ");
	    }

	    // Devuelve la cadena de horario
	    return horario.toString().trim();
	}

	
	public boolean existeHorario(String periodoAcademico, String horario) {
	    Connection con = ConexionDB.getConnection();
	    String sql = "SELECT * FROM Grupo WHERE CodPeriodoAcad = ? AND Horario = ?";

	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, periodoAcademico);
	        ps.setString(2, horario);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
}