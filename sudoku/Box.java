package sudoku;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

public class Box {
	private JPanel content;
	private JFormattedTextField[][] fields;

	public Box() {
		initComponents();
	}

	private void initComponents() {
		content = new JPanel();
		content.setLayout(new GridLayout(3, 3, 1, 1));
		content.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		fields = new JFormattedTextField[3][3];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JFormattedTextField f = null;
				try {
					MaskFormatter mask = new customFormatter("#");
					mask.setPlaceholder(null);
					mask.setCommitsOnValidEdit(true);
					mask.setAllowsInvalid(true);
					f = new JFormattedTextField(mask);
				} catch (Exception e) {
					e.printStackTrace();
				}
				f.setFont(Sudoku.bold);
				f.setValue(null);
				f.setColumns(1);
				f.setEditable(true);
				f.addKeyListener(new KeyListener() {

					@Override
					public void keyPressed(KeyEvent e) {
						JFormattedTextField f = (JFormattedTextField) e
								.getSource();
						f.setFont(Sudoku.bold);
					}

					@Override
					public void keyReleased(KeyEvent e) {
					}

					@Override
					public void keyTyped(KeyEvent e) {
					}
				});

				fields[i][j] = f;
				content.add(fields[i][j]);
			}
		}
	}

	class customFormatter extends MaskFormatter {
		public customFormatter(String mask) throws ParseException {
			super(mask);
			super.setValidCharacters("123456789");
		}

		public String valueToString(Object object) throws ParseException {
			if (object == null) {
				return (null);
			}

			return super.valueToString(object);
		}

		public Object stringToValue(String string) throws ParseException {
			try {
				if (string == null || string.equals("") || string.equals(" ")) {
					return (null);
				}
				return (string);
			} catch (Exception e) {
				return (null);
			}
		}
	}

	public JFormattedTextField getField(int x, int y) {
		return (fields[x][y]);
	}

	public int getNum(int x, int y) {
		if (fields[x][y].getValue() == null) {
			return (-1);
		}
		String s = (String) fields[x][y].getValue();
		int value = Integer.parseInt(s);
		return (value);
	}

	public JPanel getPanel() {
		return (content);
	}
}