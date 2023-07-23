package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SpinnerNumberModel;

public class SeleccionarHorario extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SeleccionarHorario dialog = new SeleccionarHorario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SeleccionarHorario() {
		setModal(true);
		setBounds(100, 100, 309, 300);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(0, 0, 293, 224);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Dia:");
				lblNewLabel.setBounds(59, 66, 46, 14);
				panel.add(lblNewLabel);
			}
			{
				JComboBox comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"}));
				comboBox.setBounds(102, 62, 121, 22);
				panel.add(comboBox);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Seleccionar Horario");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblNewLabel_1.setBounds(63, 11, 200, 22);
				panel.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Hora inicio:");
				lblNewLabel_2.setBounds(23, 106, 69, 14);
				panel.add(lblNewLabel_2);
			}
			{
				
				SpinnerDateModel model = new SpinnerDateModel();
				model.setCalendarField(Calendar.HOUR_OF_DAY);
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				model.setValue(calendar.getTime());
				JSpinner HoraInicio = new JSpinner(model);
				JSpinner.DateEditor de_HoraInicio = new JSpinner.DateEditor(HoraInicio, "HH:mm");
				HoraInicio.setEditor(de_HoraInicio);
				HoraInicio.setBounds(102, 103, 121, 20);
				panel.add(HoraInicio);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Hora fin:");
				lblNewLabel_3.setBounds(34, 153, 58, 14);
				panel.add(lblNewLabel_3);
			}
			{
				SpinnerDateModel model = new SpinnerDateModel();
				model.setCalendarField(Calendar.HOUR_OF_DAY);
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				model.setValue(calendar.getTime());
				JSpinner HoraFin = new JSpinner(model);
				JSpinner.DateEditor de_HoraFin = new JSpinner.DateEditor(HoraFin, "HH:mm");
				HoraFin.setEditor(de_HoraFin);
				HoraFin.setBounds(102, 150, 121, 20);
				panel.add(HoraFin);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Agregar");
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
