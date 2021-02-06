package com.zluck.cm.view;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.zluck.cm.model.Tabuleiro;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel {

	public PainelTabuleiro(Tabuleiro tabuleiro) {
		setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));

//		int total = tabuleiro.getLinhas() * tabuleiro.getColunas();
//		for (int i = 0; i < total; i++)
//			add(new JButton());

		tabuleiro.paraCadaCampo(campo -> add(new BotaoCampo(this, campo)));
		tabuleiro.registrarObservador(e -> {

			SwingUtilities.invokeLater(() -> {
				if (e.isGanhou()) {
					JOptionPane.showMessageDialog(this, "Ganhou :)");
				} else {
					JOptionPane.showMessageDialog(this, "Perdeu :(");
				}

				tabuleiro.reiniciarJogo();
			});
		});
	}

}
