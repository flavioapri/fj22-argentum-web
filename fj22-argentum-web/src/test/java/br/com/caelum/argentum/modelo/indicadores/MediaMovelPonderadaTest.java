package br.com.caelum.argentum.modelo.indicadores;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelPonderadaTest {

	@Test
	public void sequenciasSimplesDeCandles() {
		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 5, 6);
		int intervalo = 3;
		MediaMovelPonderada mmp = new MediaMovelPonderada(intervalo);

		// ex: calcula(2): 1*1 + 2*2 +3*3 = 14. Divide por 6, da 14/6
		assertEquals(14.0 / 6, mmp.calcula(2, serie), 0.00001);
		assertEquals(20.0 / 6, mmp.calcula(3, serie), 0.00001);
		assertEquals(26.0 / 6, mmp.calcula(4, serie), 0.00001);
		assertEquals(32.0 / 6, mmp.calcula(5, serie), 0.00001);

	}

	@Test(expected = IllegalArgumentException.class)
	public void naoAceitaPosicaoQuePossaEnvolverCalculoComPosicaoMenorQueZero() {
		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 3, 4, 5, 4, 3);
		int intervalo = 5;
		Indicador mms = new MediaMovelPonderada(intervalo);

		// ex: calcula(3): posicao 3 - 5 = -1. Posição é inválida para calculo
		mms.calcula(3, serie);
		mms.calcula(2, serie);
		mms.calcula(1, serie);
	}

	@Test
	public void calculaComIntervaloParametrizado() {
		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 5, 6);
		int intervalo = 4;
		MediaMovelPonderada mmp = new MediaMovelPonderada(intervalo);

		// ex: calcula(3): 4*4 + 3*3 + 2*2 + 1*1 = 30. Divide por 10, da 30/10
		assertEquals(30.0 / 10, mmp.calcula(3, serie), 0.00001);
		assertEquals(40 / 10, mmp.calcula(4, serie), 0.00001);
		assertEquals(50 / 10, mmp.calcula(5, serie), 0.00001);
	}
}
