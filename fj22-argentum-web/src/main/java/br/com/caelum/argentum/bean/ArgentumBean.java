package br.com.caelum.argentum.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.ChartModel;

import br.com.caelum.argentum.grafico.GeradorModeloGrafico;
import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.CandlestickFactory;
import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.modelo.SerieTemporal;
import br.com.caelum.argentum.modelo.indicadores.IndicadorAbertura;
import br.com.caelum.argentum.modelo.indicadores.IndicadorFechamento;
import br.com.caelum.argentum.modelo.indicadores.MediaMovelPonderada;
import br.com.caelum.argentum.modelo.indicadores.MediaMovelSimples;
import br.com.caelum.argentum.modelo.ws.ClienteWebService;

@ManagedBean
@ViewScoped
public class ArgentumBean implements Serializable {

	private List<Negociacao> negociacoes;
	private ChartModel modeloGrafico;

	public ArgentumBean() {
		this.negociacoes = new ClienteWebService().getNegociacoes();
		List<Candle> candles = new CandlestickFactory().constroiCandles(negociacoes);
		SerieTemporal serie = new SerieTemporal(candles);

		// TODO receber os parâmetros intervalo e comeco pela view
		int intervalo = 3;
		int comeco = 2;
		if (comeco < (intervalo - 1)) {
			throw new IllegalArgumentException(
					"A posicao não é válida. Ela envolve no cálculo com posições menores que 0 na série");
		}

		GeradorModeloGrafico geradorGrafico = new GeradorModeloGrafico(serie, comeco, serie.getUltimaPosicao());
		geradorGrafico.plotaIndicador(new MediaMovelPonderada(intervalo));
		geradorGrafico.plotaIndicador(new MediaMovelSimples(intervalo));
		geradorGrafico.plotaIndicador(new IndicadorAbertura());
		geradorGrafico.plotaIndicador(new IndicadorFechamento());
		this.modeloGrafico = geradorGrafico.getModeloGrafico();
	}

	public List<Negociacao> getNegociacoes() {
		return negociacoes;
	}

	public ChartModel getModeloGrafico() {
		return modeloGrafico;
	}
}
