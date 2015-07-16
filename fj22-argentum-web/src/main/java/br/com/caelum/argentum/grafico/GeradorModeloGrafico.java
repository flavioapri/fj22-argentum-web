package br.com.caelum.argentum.grafico;

import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.caelum.argentum.indicadores.Indicador;
import br.com.caelum.argentum.modelo.SerieTemporal;

public class GeradorModeloGrafico {

	private LineChartModel modeloGrafico;
	private int fim;
	private int comeco;
	private SerieTemporal serie;
	private String tituloGrafico;

	public GeradorModeloGrafico(SerieTemporal serie, int comeco, int fim, String tituloGrafico) {
		this.serie = serie;
		this.comeco = comeco;
		this.fim = fim;
		this.modeloGrafico = new LineChartModel();
		this.tituloGrafico = tituloGrafico;
	}

	public void plotaIndicador(Indicador indicador) {
		LineChartSeries chartSerie = new LineChartSeries(indicador.toString());

		for (int i = comeco; i <= fim; i++) {
			double valor = indicador.calcula(i, serie);
			chartSerie.set(i, valor);
		}
		this.modeloGrafico.addSeries(chartSerie);
		this.modeloGrafico.setLegendPosition("w");
		this.modeloGrafico.setTitle(tituloGrafico);
	}

	public ChartModel getModeloGrafico() {
		return this.modeloGrafico;
	}
}
