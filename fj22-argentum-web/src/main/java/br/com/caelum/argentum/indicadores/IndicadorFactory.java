package br.com.caelum.argentum.indicadores;

import java.lang.reflect.Constructor;

public class IndicadorFactory {

	private final String nomeMedia;
	private final String nomeIndicadorBase;
	private int intervalo;

	public IndicadorFactory(int intervalo, String nomeMedia, String nomeIndicadorBase) {
		this.nomeMedia = nomeMedia;
		this.nomeIndicadorBase = nomeIndicadorBase;
		this.intervalo = intervalo;
	}

	public Indicador defineIndicador() {
		if (nomeIndicadorBase == null || nomeMedia == null)
			return new MediaMovelPonderada(intervalo, new IndicadorFechamento());
		try {
			String pacote = "br.com.caelum.argentum.indicadores.";
			Class<?> classeIndicadorBase = Class.forName(pacote + nomeIndicadorBase);
			Indicador indicadorBase = (Indicador) classeIndicadorBase.newInstance();

			Class<?> classeMedia = Class.forName(pacote + nomeMedia);
			Constructor<?> construtorMedia = classeMedia.getConstructor(int.class, Indicador.class);
			Indicador indicador = (Indicador) construtorMedia.newInstance(intervalo, indicadorBase);
			return indicador;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}