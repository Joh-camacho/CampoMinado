package com.zluck.cm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Tabuleiro implements CampoObservador {

	private int linhas, colunas, minas;

	private final List<Campo> campos = new ArrayList<>();
	private final List<Consumer<ResultadoEvento>> observadores = new ArrayList<>();

	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;

		gerarCampos();
		associarVizinhos();
		sortearMinas();
	}

	public void abrir(int linha, int coluna) {
		campos.parallelStream().filter(campo -> campo.getX() == linha && campo.getY() == coluna).findFirst()
				.ifPresent(campo -> campo.abrir());
	}

	public void marcar(int linha, int coluna) {
		campos.parallelStream().filter(campo -> campo.getX() == linha && campo.getY() == coluna).findFirst()
				.ifPresent(campo -> campo.alternarMarcacao());
	}

	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(Campo::objetivoAlcancado);
	}

	public void reiniciarJogo() {
		campos.forEach(Campo::reiniciar);
		sortearMinas();
	}

	public void paraCadaCampo(Consumer<Campo> funcao) {
		campos.forEach(funcao);
	}

	public void registrarObservador(Consumer<ResultadoEvento> resultado) {
		observadores.add(resultado);
	}

	private void notificarObservador(boolean resultado) {
		observadores.forEach(evento -> evento.accept(new ResultadoEvento(resultado)));
	}

	private void gerarCampos() {
		for (int linha = 0; linha < linhas; linha++) {
			for (int coluna = 0; coluna < colunas; coluna++) {
				Campo campo = new Campo(linha, coluna);

				campo.registrarObservador(this);
				campos.add(campo);
			}
		}
	}

	private void associarVizinhos() {
		for (Campo campo1 : campos) {
			for (Campo campo2 : campos) {
				campo1.adicionarVizinho(campo2);
			}
		}
	}

	private void sortearMinas() {
		int minasArmadas = 0;

		while (minasArmadas < minas) {
//			int random = new Random().nextInt(campos.size());
			int random = (int) (Math.random() * campos.size());
			Campo campo = campos.get(random);

			System.out.println(random);
			
			if (!campo.isMinado()) {
				campo.setMinado();

				minasArmadas++;
			}
		}
	}

	private void mostrarMinas() {
		campos.stream().filter(campo -> campo.isMinado()).forEach(campo -> campo.setAberto(true));
	}

	@Override
	public void eventoOcorreu(Campo campo, CampoEvento evento) {
		if (evento == CampoEvento.EXPLODIR) {
			mostrarMinas();
			notificarObservador(false);
		} else if (objetivoAlcancado()) {
			notificarObservador(true);
		}
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

}
