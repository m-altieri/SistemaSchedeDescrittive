package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CampoCredenziale extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField testo;
	private String hint = "";
	
	public String get() {
		
		return testo.getText();
	}
	
	public CampoCredenziale(String x) {
		
		this(x, false);
	}
	
	public CampoCredenziale(String x, boolean isPassword) {
		
		super();
		
		this.setLayout(new FlowLayout());
		
		if (isPassword)
			testo = new JPasswordField();
		else {
			testo = new JTextField();
		}
		
		this.setHint(x);
		
		setLunghezza(400);
		
		testo.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (testo.getText().equals(hint))
					testo.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {

				if (testo.getText().equals(""))
					setHint(hint);
			}
			
		});
		
		
		this.add(testo);		
		
	}
	
	void setLunghezza(int x) {
		
		testo.setPreferredSize(new Dimension(x, 40));
	}
	
	void setHint(String x) {
		
		testo.setText(x);
		hint = x;
	}
}
