package br.com.caelum.argentum.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.ChartModel;

import br.com.caelum.argentum.grafico.GeradorModeloGrafico;
import br.com.caelum.argentum.indicadores.IndicadorFactory;
import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.CandlestickFactory;
import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.modelo.SerieTemporal;
import br.com.caelum.argentum.modelo.ws.ClienteWebService;

@ManagedBean
@ViewScoped
public class ArgentumBean implements Serializable {

	private List<Negociacao> negociacoes;
	private ChartModel modeloGrafico;
	private String nomeMedia;
	private String nomeIndicadorBase;
	private int intervalo;
	private int comeco;
	private String titulo;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNomeIndicadorBase() {
		return nomeIndicadorBase;
	}

	public void setNomeIndicadorBase(String nomeIndicadorBase) {
		this.nomeIndicadorBase = nomeIndicadorBase;
	}

	public String getNomeMedia() {
		return nomeMedia;
	}

	public void setNomeMedia(String nomeMedia) {
		this.nomeMedia = nomeMedia;
	}

	public ArgentumBean() {
		this.negociacoes = new ClienteWebService().getNegociacoes();
		// TODO receber intervalo e comeco na view
		this.intervalo = 3;
		this.comeco = 2;
		geraGrafico();
	}

	public void geraGrafico() {
		List<Candle> candles = new CandlestickFactory().constroiCandles(this.negociacoes);
		SerieTemporal serie = new SerieTemporal(candles);

		// TODO fazer essa validação na view
		if (comeco < (intervalo - 1)) {
			throw new IllegalArgumentException(
					"A posicao não é válida. Ela envolve no cálculo com posições menores que 0 na série");
		}

		IndicadorFactory fabrica = new IndicadorFactory(intervalo, nomeMedia, nomeIndicadorBase);
		GeradorModeloGrafico geradorGrafico = new GeradorModeloGrafico(serie, comeco, serie.getUltimaPosicao(), titulo);
		geradorGrafico.plotaIndicador(fabrica.defineIndicador());
		this.modeloGrafico = geradorGrafico.getModeloGrafico();
	}

	public List<Negociacao> getNegociacoes() {
		return negociacoes;
	}

	public ChartModel getModeloGrafico() {
		return modeloGrafico;
	}
}
