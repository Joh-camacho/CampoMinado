package br.com.zluck;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class DificuldadeBuilder extends JPanel {

	private final ButtonGroup buttonGroup = new ButtonGroup();

	public DificuldadeBuilder(JFrame jFrame) {
		JLabel texto = new JLabel("Escolha a dificuldade");
		texto.setBounds(80, 11, 186, 25);
		texto.setFont(new Font("Tahoma", Font.PLAIN, 20));
		texto.setBackground(Color.WHITE);
		texto.setHorizontalAlignment(SwingConstants.CENTER);
		add(texto);

		JRadioButton facil = new JRadioButton("Fácil");
		facil.setBounds(20, 69, 59, 31);
		facil.setHorizontalAlignment(SwingConstants.CENTER);
		facil.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(facil);

		JRadioButton medio = new JRadioButton("Médio");
		medio.setBounds(132, 69, 73, 31);
		medio.setHorizontalAlignment(SwingConstants.CENTER);
		medio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(medio);

		JRadioButton dificil = new JRadioButton("Difícil");
		dificil.setBounds(250, 69, 67, 31);
		dificil.setHorizontalAlignment(SwingConstants.CENTER);
		dificil.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(dificil);

		buttonGroup.add(facil);
		buttonGroup.add(medio);
		buttonGroup.add(dificil);

		JButton ok = new JButton("OK");
		ok.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) { }

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Implementar painel tabuleiro
			}

			public void mouseExited(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseClicked(MouseEvent e) { }
		});

		ok.setBounds(116, 139, 106, 25);
		add(ok);

		setLayout(null);
	}

}
