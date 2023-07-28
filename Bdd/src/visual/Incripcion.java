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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Incripcion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static JTextField txtNombre;
	public static JTextField txtMatricula;
	public static JTable gruposInsc;

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
				btnNewButton_1.setBounds(644, 136, 121, 45);
				panel.add(btnNewButton_1);
			}
			{
				JButton btnNewButton_2 = new JButton("Eliminar");
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
