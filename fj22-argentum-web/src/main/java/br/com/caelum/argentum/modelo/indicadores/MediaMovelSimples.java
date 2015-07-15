package br.com.caelum.argentum.modelo.indicadores;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelSimples implements Indicador {

	private int intervalo;

	public MediaMovelSimples(int intervalo) {
		this.intervalo = intervalo;
	}

	@Override
	public double calcula(int posicao, SerieTemporal serie) {

		double soma = 0.0;
		for (int i = posicao; i > posicao - intervalo; i--) {
			soma += serie.getCandle(i).getFechamento();
		}
		return soma / intervalo;
	}

	@Override
	public String toString() {
		return "MMS de Fechamento";
	}
}
