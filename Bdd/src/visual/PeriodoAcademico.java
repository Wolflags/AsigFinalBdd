package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;

import logico.ConexionDB;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;

public class PeriodoAcademico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPeriodoAca;
	private JTextField txtDescripcion;
	private JTextField txtFechaIni;
	private JTextField txtFechaFin;
	private JTextField txtIniClases;
	private JTextField txtFinClases;
	private JTextField txtLimitePago;
	private JTextField txtLimitePrematricula;
	private JTextField txtLimiteRetiro;
	private JTextField txtNotas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PeriodoAcademico dialog = new PeriodoAcademico(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PeriodoAcademico(String idPeriodoAcademico) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PeriodoAcademico.class.getResource("/Images/calendario3.png")));
		setTitle("Periodo Académico");
		setModal(true);
		setSize(469, 600);
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    int x = (screenSize.width - getWidth()) / 2;
		    int y = (screenSize.height - getHeight()-30) / 2;
		    setLocation(x, y);
		//setBounds(100, 100, 469, 547);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 453, 524);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Periodo Académico:");
		lblNewLabel.setBounds(59, 93, 147, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripción:");
		lblNewLabel_1.setBounds(59, 139, 109, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Inicio de Clases:");
		lblNewLabel_2.setBounds(59, 258, 109, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha Inicio:");
		lblNewLabel_3.setBounds(59, 180, 109, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Fin de Clases:");
		lblNewLabel_4.setBounds(59, 298, 109, 14);
		panel.add(lblNewLabel_4);
		
		txtPeriodoAca = new JTextField();
		txtPeriodoAca.setBounds(256, 93, 139, 20);
		panel.add(txtPeriodoAca);
		txtPeriodoAca.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Fecha Fin:");
		lblNewLabel_5.setBounds(59, 218, 109, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Fecha Límite de Pago:");
		lblNewLabel_6.setBounds(59, 340, 147, 14);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Fecha Límite de Prematricula:");
		lblNewLabel_7.setBounds(59, 385, 187, 14);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Fecha Límite de Retiro:");
		lblNewLabel_8.setBounds(59, 424, 165, 14);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Fecha Límite de Publicación:");
		lblNewLabel_9.setBounds(59, 469, 165, 14);
		panel.add(lblNewLabel_9);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(256, 136, 139, 20);
		panel.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtFechaIni = new JTextField();
		txtFechaIni.setBounds(256, 177, 139, 20);
		panel.add(txtFechaIni);
		txtFechaIni.setColumns(10);
		
		txtFechaFin = new JTextField();
		txtFechaFin.setColumns(10);
		txtFechaFin.setBounds(256, 215, 139, 20);
		panel.add(txtFechaFin);
		
		txtIniClases = new JTextField();
		txtIniClases.setColumns(10);
		txtIniClases.setBounds(256, 258, 139, 20);
		panel.add(txtIniClases);
		
		txtFinClases = new JTextField();
		txtFinClases.setColumns(10);
		txtFinClases.setBounds(256, 298, 139, 20);
		panel.add(txtFinClases);
		
		txtLimitePago = new JTextField();
		txtLimitePago.setColumns(10);
		txtLimitePago.setBounds(256, 340, 139, 20);
		panel.add(txtLimitePago);
		
		txtLimitePrematricula = new JTextField();
		txtLimitePrematricula.setColumns(10);
		txtLimitePrematricula.setBounds(256, 382, 139, 20);
		panel.add(txtLimitePrematricula);
		
		txtLimiteRetiro = new JTextField();
		txtLimiteRetiro.setColumns(10);
		txtLimiteRetiro.setBounds(256, 421, 139, 20);
		panel.add(txtLimiteRetiro);
		
		txtNotas = new JTextField();
		txtNotas.setColumns(10);
		txtNotas.setBounds(256, 466, 139, 20);
		panel.add(txtNotas);
		
		JLabel lblNewLabel_10 = new JLabel("Periodo Académico");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		lblNewLabel_10.setBounds(30, 11, 433, 45);
		panel.add(lblNewLabel_10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				if(idPeriodoAcademico==null) {
				JButton btnAgregar = new JButton("Agregar");
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						insertarPeriodoAca();
						JOptionPane.showMessageDialog(null, "Los datos se insertaron correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
				});
				btnAgregar.setActionCommand("OK");
				buttonPane.add(btnAgregar);
				getRootPane().setDefaultButton(btnAgregar);
				}else{
					JButton btnNewButton = new JButton("Modificar");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							updatePeriodo(idPeriodoAcademico);
							JOptionPane.showMessageDialog(null, "Los datos se actualizaron correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							}
							
						
					});
					buttonPane.add(btnNewButton);
				}
			}
			{
				JButton btnRegresar = new JButton("Regresar");
				btnRegresar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnRegresar.setActionCommand("Cancel");
				buttonPane.add(btnRegresar);
			}
		}
		if(idPeriodoAcademico!=null) {
			cargarPeriodo(idPeriodoAcademico);
			}
	}
	public void insertarPeriodoAca() {
	    Connection con = ConexionDB.getConnection();

	    if (con != null) {
	        try {
	            String sql = "INSERT INTO PeriodoAcademico (CodPeriodoAcad, Descripcion, FechaInicio, FechaFin, FechaInicioClases, FechaFinClases, FechaLimitePago, FechaLimitePrematricula, FechaLimiteRetiro, FechaLimitePublicacionCalif) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement ps = con.prepareStatement(sql);

	            ps.setString(1, txtPeriodoAca.getText());
	            ps.setString(2, txtDescripcion.getText());
	            
	            //Convertir fecha inicio a un objeto java.sql.Date
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date utilDate = dateFormat.parse(txtFechaIni.getText());
	            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	            ps.setDate(3, sqlDate);
	            
	            // Convertir la fecha fin a un objeto java.sql.Date
	            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date utilDate1 = dateFormat1.parse(txtFechaFin.getText());
	            java.sql.Date sqlDate1 = new java.sql.Date(utilDate1.getTime());
	            ps.setDate(4, sqlDate1);
	            
	            // Convertir la fecha de inicio de clases a un objeto java.sql.Date
	            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date utilDate2 = dateFormat2.parse(txtIniClases.getText());
	            java.sql.Date sqlDate2 = new java.sql.Date(utilDate2.getTime());
	            ps.setDate(5, sqlDate2);
	            
	            // Convertir la fecha de fin de clases a un objeto java.sql.Date
	            SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date utilDate3 = dateFormat3.parse(txtFinClases.getText());
	            java.sql.Date sqlDate3 = new java.sql.Date(utilDate3.getTime());
	            ps.setDate(6, sqlDate3);

	            // Convertir la fecha limite de pago a un objeto java.sql.Date
	            SimpleDateFormat dateFormat4 = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date utilDate4 = dateFormat4.parse(txtLimitePago.getText());
	            java.sql.Date sqlDate4 = new java.sql.Date(utilDate4.getTime());
	            ps.setDate(7, sqlDate4);

	            // Convertir la fecha limite de prematricula a un objeto java.sql.Date
	            SimpleDateFormat dateFormat5 = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date utilDate5 = dateFormat5.parse(txtLimitePrematricula.getText());
	            java.sql.Date sqlDate5 = new java.sql.Date(utilDate5.getTime());
	            ps.setDate(8, sqlDate5);

	            // Convertir la fecha limite retiro a un objeto java.sql.Date
	            SimpleDateFormat dateFormat6 = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date utilDate6 = dateFormat6.parse(txtLimiteRetiro.getText());
	            java.sql.Date sqlDate6 = new java.sql.Date(utilDate6.getTime());
	            ps.setDate(9, sqlDate6);
	            
	            // Convertir la fecha de entrega de notas a un objeto java.sql.Date
	            SimpleDateFormat dateFormat7 = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date utilDate7 = dateFormat7.parse(txtNotas.getText());
	            java.sql.Date sqlDate7 = new java.sql.Date(utilDate7.getTime());
	            ps.setDate(10, sqlDate7);

	            ps.executeUpdate();

	            ps.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    } else {
	        System.out.println("Error en la conexión");
	    }
	}
	public void updatePeriodo(String periodo) {
	    Connection con = ConexionDB.getConnection();

	    if (con != null) {
	        try {
	            String sql = "UPDATE PeriodoAcademico SET CodPeriodoAcad = ?, Descripcion = ?, FechaInicio = ?, FechaFin = ?, FechaInicioClases = ?, FechaFinClases = ?, FechaLimitePago = ?, FechaLimitePrematricula = ?, FechaLimiteRetiro = ?, FechaLimitePublicacionCalif = ? WHERE CodPeriodoAcad='" + periodo + "'";
	            PreparedStatement ps = con.prepareStatement(sql);

	            ps.setString(1, txtPeriodoAca.getText());
	            ps.setString(2, txtDescripcion.getText());
	           
	            
	            //Convertir fecha inicio a un objeto java.sql.Date
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date utilDate = dateFormat.parse(txtFechaIni.getText());
	            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	            ps.setDate(3, sqlDate);
	            
	            // Convertir la fecha fin a un objeto java.sql.Date
	            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date utilDate1 = dateFormat1.parse(txtFechaFin.getText());
	            java.sql.Date sqlDate1 = new java.sql.Date(utilDate1.getTime());
	            ps.setDate(4, sqlDate1);
	            
	            // Convertir la fecha de inicio de clases a un objeto java.sql.Date
	            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date utilDate2 = dateFormat2.parse(txtIniClases.getText());
	            java.sql.Date sqlDate2 = new java.sql.Date(utilDate2.getTime());
	            ps.setDate(5, sqlDate2);
	            
	            // Convertir la fecha de fin de clases a un objeto java.sql.Date
	            SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date utilDate3 = dateFormat3.parse(txtFinClases.getText());
	            java.sql.Date sqlDate3 = new java.sql.Date(utilDate3.getTime());
	            ps.setDate(6, sqlDate3);

	            // Convertir la fecha limite de pago a un objeto java.sql.Date
	            SimpleDateFormat dateFormat4 = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date utilDate4 = dateFormat4.parse(txtLimitePago.getText());
	            java.sql.Date sqlDate4 = new java.sql.Date(utilDate4.getTime());
	            ps.setDate(7, sqlDate4);

	            // Convertir la fecha limite de prematricula a un objeto java.sql.Date
	            SimpleDateFormat dateFormat5 = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date utilDate5 = dateFormat5.parse(txtLimitePrematricula.getText());
	            java.sql.Date sqlDate5 = new java.sql.Date(utilDate5.getTime());
	            ps.setDate(8, sqlDate5);

	            // Convertir la fecha limite retiro a un objeto java.sql.Date
	            SimpleDateFormat dateFormat6 = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date utilDate6 = dateFormat6.parse(txtLimiteRetiro.getText());
	            java.sql.Date sqlDate6 = new java.sql.Date(utilDate6.getTime());
	            ps.setDate(9, sqlDate6);
	            
	            // Convertir la fecha de entrega de notas a un objeto java.sql.Date
	            SimpleDateFormat dateFormat7 = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date utilDate7 = dateFormat7.parse(txtNotas.getText());
	            java.sql.Date sqlDate7 = new java.sql.Date(utilDate7.getTime());
	            ps.setDate(10, sqlDate7);

	           

	            ps.executeUpdate();

	            ps.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    } else {
	        System.out.println("Error en la conexión");
	    }
	}
	
	public void cargarPeriodo(String periodo) {
	    Connection con = ConexionDB.getConnection();

	    if (con != null) {
	            String sql = "SELECT CodPeriodoAcad, Descripcion, FechaInicio, FechaFin, FechaInicioClases, FechaFinClases, FechaLimitePago, FechaLimitePrematricula, FechaLimiteRetiro, FechaLimitePublicacionCalif FROM PeriodoAcademico WHERE CodPeriodoAcad='" + periodo + "'";
	            try (Statement stmt = con.createStatement();
	                    ResultSet rs = stmt.executeQuery(sql)) {
	                   ResultSetMetaData rsmd = rs.getMetaData();
	                   
	                   while (rs.next()) {
	                       
	                           String academico = (String) rs.getObject(1);
	                           txtPeriodoAca.setText(academico);
	                           String descripcion = (String) rs.getObject(2);
	                           txtDescripcion.setText(descripcion);
	                         
	                          
	                           java.sql.Date fechaSql = rs.getDate("Fecha Inicio");
	   	                    if (fechaSql != null) {
	   	                        SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
	   	                        String fechaFormateada = fecha.format(fechaSql);
	   	                     txtFechaIni.setText(fechaFormateada);
	   	                    }
	                          
	                   }
	               } catch (SQLException e) {
	                   e.printStackTrace();
	               }
	    } else {
	        System.out.println("Error en la conexión");
	    }
	}
}
