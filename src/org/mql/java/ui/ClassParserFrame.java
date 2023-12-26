
package org.mql.java.ui;

import javax.swing.*;

import org.mql.java.reflection.ClassParser;
import org.mql.java.reflection.ClassParserInfo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassParserFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	private JButton button;
	private JPanel panel;
	private JScrollPane scrollPane;
	  Dimension buttonSize ;

	public ClassParserFrame() {
		super("Class Parser APP-EL MAALMI");

		textArea = new JTextArea();
		button = new JButton();
		panel = new JPanel();
		scrollPane = new JScrollPane(textArea);

		button.setText(" Analyser une Class");
		textArea.setEditable(false);
		panel.setLayout(new BorderLayout());
		   buttonSize = new Dimension(50, 30);
	       button.setPreferredSize(buttonSize);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String source = JOptionPane.showInputDialog("Veillez enter le nom complet de class :");
				if (!"".equals(source)) {
					if (source != null) {
						String content = ClassParserInfo.getInformation(source)+"\n"+ClassParser.toString(source);
						textArea.setText(content);
						if(content.isEmpty()) {
							  JOptionPane.showMessageDialog(ClassParserFrame.this, "Nom de classe invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
		                			
						}
						
						
					}
			 } else {
				 textArea.setText("");
                    JOptionPane.showMessageDialog(ClassParserFrame.this, "Nom de classe vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }			}
		});

		panel.add(BorderLayout.NORTH, button);
		panel.add(scrollPane);
		add(panel);
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ClassParserFrame();
	}

}
