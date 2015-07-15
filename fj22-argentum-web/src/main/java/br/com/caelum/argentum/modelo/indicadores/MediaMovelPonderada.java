package br.com.caelum.argentum.modelo.indicadores;

import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelPonderada implements Indicador {

	private int intervalo;

	public MediaMovelPonderada(int intervalo) {
		this.intervalo = intervalo;
	}

	@Override
	public double calcula(int posicao, SerieTemporal serie) {

		double soma = 0.0;
		int peso = intervalo;
		int pesoTotal = intervalo;

		for (int i = posicao; i > posicao - intervalo; i--) {
			Candle c = serie.getCandle(i);
			soma += c.getFechamento() * peso;
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