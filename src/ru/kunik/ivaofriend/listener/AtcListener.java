package ru.kunik.ivaofriend.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;
import javax.swing.JToggleButton;

import ru.kunik.ivaofriend.handler.AtcHandler;

public class AtcListener implements ActionListener, MouseListener {
	
	private final AtcHandler atcHandler;
	
	public AtcListener(AtcHandler atcHandler) {
		this.atcHandler = atcHandler;
	}
	
	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		Object mouseEventObject = mouseEvent.getSource();
		if(mouseEventObject instanceof JTextField) {
			JTextField textField = (JTextField) mouseEventObject;
			String textFieldName = textField.getName();
			if((textFieldName != null) && !textFieldName.isEmpty()) {
				if(atcHandler.getGUI().isFieldWithListener(textFieldName)) {
					if(textFieldName.equals("METAR")) {
						textFieldName = "ICAO";
					}
					atcHandler.setupAirportInformation(textFieldName);
				}
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		
	}
	
	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		
	}
	
	@Override
	public void mouseEntered(MouseEvent mouseEvent) {
		
	}
	
	@Override
	public void mouseExited(MouseEvent mouseEvent) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		Object actionEventObject = actionEvent.getSource();
		if(actionEventObject instanceof JToggleButton) {
			JToggleButton toggleButton = (JToggleButton) actionEventObject;
			if(toggleButton.getName().equals("Enable ATC")) {
				if(toggleButton.isSelected()) {
					if(atcHandler.startATC()) {
						toggleButton.setText("Disable ATC");
					} else {
						toggleButton.setSelected(false);
					}
				} else {
					atcHandler.stopATC();
				}
			}
		}
	}
}
