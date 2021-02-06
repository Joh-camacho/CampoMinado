package com.zluck.cm.view;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {

	public TelaPrincipal() {
//		Tabuleiro tabuleiro = new Tabuleiro(16, 30, 80);
//		PainelTabuleiro painelTabuleiro = new PainelTabuleiro(tabuleiro);
//
//		add(painelTabuleiro);

		Dificuldade dificuldade = new Dificuldade(this);
		setContentPane(dificuldade);

		setTitle("Campo Minado");
		setSize(355, 220);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new TelaPrincipal();
	}

}
