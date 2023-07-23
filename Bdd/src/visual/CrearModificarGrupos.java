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
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CrearModificarGrupos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearModificarGrupos dialog = new CrearModificarGrupos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearModificarGrupos() {
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
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(127, 101, 183, 22);
			contentPanel.add(comboBox);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Asignatura:");
			lblNewLabel_2.setBounds(47, 159, 96, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			textField = new JTextField();
			textField.setBounds(127, 156, 183, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Cupo:");
			lblNewLabel_3.setBounds(72, 208, 58, 14);
			contentPanel.add(lblNewLabel_3);
		}
		
		JSpinner spinner = new JSpinner();
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
				table = new JTable();
				scrollPane.setViewportView(table);
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
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
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
}
