package br.com.felipemarinho.registrocliente.api.response;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {
	
	private T data;
	
	private List<String> erros;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErros() {
		if (this.erros == null) {
			this.erros = new ArrayList<>();
		}
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}

	public boolean temErro() {
		return this.erros != null && !this.erros.isEmpty();
	}
	
	

}
