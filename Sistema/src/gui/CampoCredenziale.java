package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class CampoCredenziale extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String hint = "";
	
	public String get() {
		
		return this.getText();
	}
	
	public CampoCredenziale(String x) {
		
		super();
		
		this.setPreferredSize(new Dimension(400, 40));
		this.setLayout(new FlowLayout());
		this.setForeground(Color.GRAY);
		
		this.setHint(x);
				
		this.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (getText().equals(hint)) {
					setText("");
					setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {

				if (getText().equals("")) {
					setHint(hint);
					setForeground(Color.GRAY);
				}
			}
			
		});

	}
	
	void setLunghezza(int x) {
		
		setPreferredSize(new Dimension(x, 40));
	}
	
	void setHint(String x) {
		
		setText(x);
		hint = x;
	}
	
	String getHint() {
		
		return hint;
	}
}
