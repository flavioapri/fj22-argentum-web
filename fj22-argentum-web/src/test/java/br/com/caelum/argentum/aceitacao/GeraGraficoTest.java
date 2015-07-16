package br.com.caelum.argentum.aceitacao;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GeraGraficoTest {

	private static final String URL = "http://localhost:8080/fj22-argentum-web/index.xhtml";
	private String property = "webdriver.chrome.driver";
	private WebDriver driver;
	private File file;

	@Before
	public void setUp() {
		file = new File("C:\\eclipse\\selenium\\chromedriver.exe");
		System.setProperty(property, file.getAbsolutePath());
		driver = new ChromeDriver();
	}

	@After
	public void tearDown() {
		driver.close();
	}

	@Test
	public void testeAoGerarGraficoSemTituloUmaMensagemEhApresentada() {
		driver.navigate().to(URL);
		WebElement titulo = driver.findElement(By.id("dadosGrafico:titulo"));

		titulo.sendKeys();
		titulo.submit();

		boolean existeMensagem = driver.getPageSource().contains("Erro");

		assertTrue(existeMensagem);
	}
}
