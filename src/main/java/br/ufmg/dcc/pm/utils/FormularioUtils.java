package br.ufmg.dcc.pm.utils;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class FormularioUtils {


	public static JPanel generatePanel() {
		String encodedColumnSpec = "170px";
		String encodedColumnSpec2 = "260px";
		String encodedRowSpec = "40px";

		JPanel panel = new JPanel();

		panel.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec.decode(encodedColumnSpec),
				ColumnSpec.decode(encodedColumnSpec2), }, new RowSpec[] { RowSpec.decode(encodedRowSpec), }));

		return panel;
	}
}
