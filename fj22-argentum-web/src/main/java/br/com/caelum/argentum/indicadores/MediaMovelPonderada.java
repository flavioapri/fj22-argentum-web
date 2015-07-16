package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelPonderada implements Indicador {

	private int intervalo;
	private Indicador outroIndicador;

	public MediaMovelPonderada() {
	}

	public MediaMovelPonderada(int intervalo, Indicador outroIndicador) {
		this.intervalo = intervalo;
		this.outroIndicador = outroIndicador;
	}

	@Override
	public double calcula(int posicao, SerieTemporal serie) {

		double soma = 0.0;
		int peso = intervalo;
		int pesoTotal = intervalo;

		for (int i = posicao; i > posicao - intervalo; i--) {
			Candle c = serie.getCandle(i);
			soma += outroIndicador.calcula(i, serie);
			peso--;
			pesoTotal += peso;
		}
		return soma / pesoTotal;
	}

	@Override
	public String toString() {
		return "MMP de Feachamento";
	}
}