package br.com.zluck.model;

@FunctionalInterface
public interface CampoObservador {

	void eventoOcorreu(Campo campo, CampoEvento evento);

}
