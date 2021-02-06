package com.zluck.cm.model;

@FunctionalInterface
public interface CampoObservador {

	void eventoOcorreu(Campo campo, CampoEvento evento);

}
