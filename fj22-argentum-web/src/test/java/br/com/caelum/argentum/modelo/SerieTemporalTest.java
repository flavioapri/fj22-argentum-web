package br.com.caelum.argentum.modelo;

import java.util.ArrayList;

import org.junit.Test;

public class SerieTemporalTest {

	@Test(expected = IllegalArgumentException.class)
	public void naoRecebeListaNula() {
		ArrayList<Candle> candles = new ArrayList<Candle>();
		SerieTemporal serie = new SerieTemporal(candles);
	}
}
