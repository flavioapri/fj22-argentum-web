package br.com.caelum.argentum.modelo;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class OlaMundoBean {

	private String mensagem = "Quem é você?";
	private String nome;

	public String getMensagem() {
		return mensagem;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void nomeFoiDigitado() {
		System.out.println("\nChamou o botão");
	}

}
