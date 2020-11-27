package br.com.izri.aservico.auxiliar;

import java.util.Calendar;

public class Verificadores {

	public static boolean isDataNotNull(Calendar data) {
		boolean retorno = true;

		if (data == null) {
			retorno = false;
		}

		return retorno;
	}
}
