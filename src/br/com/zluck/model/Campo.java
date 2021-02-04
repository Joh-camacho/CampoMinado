package br.com.zluck.model;

import java.util.ArrayList;
import java.util.List;

public class Campo {

	private final int x, y;

	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;

	private List<Campo> vizinhos = new ArrayList<>();

	private List<CampoObservador> observadores = new ArrayList<>();

	public Campo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean adicionarVizinho(Campo vizinho) {
		if (vizinho == null)
			throw new NullPointerException();
		if (isVizinho(vizinho))
			return true;

		boolean xNotEquals = getX() != vizinho.getX();
		boolean yNotEquals = getY() != vizinho.getY();
		boolean isDiagonal = xNotEquals && yNotEquals;

		int x = Math.abs(getX() - vizinho.getX());
		int y = Math.abs(getY() - vizinho.getY());

		int delta = x + y;

		if (isDiagonal && delta == 2) {
			vizinhos.add(vizinho);
			return true;
		} else if (!isDiagonal && delta == 1) {
			vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}
	}

	public void alternarMarcacao() {
		if (!aberto) {
			marcado = !marcado;

			if (marcado) {
				notificarObservador(CampoEvento.MARCAR);
			} else {
				notificarObservador(CampoEvento.DESMARCAR);
			}
		}
	}

	public boolean abrir() {
		if (!aberto && !marcado) {
			if (minado) {
				notificarObservador(CampoEvento.EXPLODIR);
				return true;
			}

			setAberto(true);

			if (vizinhacaSegura())
				vizinhos.forEach(Campo::abrir);

			return true;
		} else {
			return false;
		}
	}

	public boolean vizinhacaSegura() {
		return vizinhos.stream().noneMatch(Campo::isMinado);
	}

	public boolean isVizinho(Campo campo) {
		return vizinhos.contains(campo);
	}

	public boolean isAberto() {
		return aberto;
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;

		if (aberto) {
			notificarObservador(CampoEvento.ABRIR);
		}
	}

	public boolean isMinado() {
		return minado;
	}

	public void setMinado() {
		this.minado = true;
	}

	public boolean isMarcado() {
		return marcado;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;

		return desvendado || protegido;
	}

	public int minasNaVizinhanca() {
		return Long.valueOf(vizinhos.stream().filter(Campo::isMinado).count()).intValue();
	}

	public void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;

		notificarObservador(CampoEvento.REINICIAR);
	}

	public void registrarObservador(CampoObservador campoObservador) {
		observadores.add(campoObservador);
	}

	private void notificarObservador(CampoEvento campoEvento) {
		observadores.forEach(observador -> observador.eventoOcorreu(this, campoEvento));
	}

}
