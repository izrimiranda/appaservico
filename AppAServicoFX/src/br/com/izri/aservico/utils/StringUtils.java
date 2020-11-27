package br.com.izri.aservico.utils;

import java.util.List;

public class StringUtils {

	public static boolean isEmpty(String str) {
		if ((str == null) || str.trim().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String str) {
		if (StringUtils.isEmpty(str)) {
			return false;
		} else {
			return true;
		}
	}

	public static String ListToString(List<String> lista) {
		final StringBuilder retorno = new StringBuilder();

		for (final String s : lista) {
			retorno.append(s);
			retorno.append(",");

		}

		return retorno.delete(retorno.length() - 2, retorno.length()).toString();
	}

	public static String montaPathFoto(String path) {

		String novoPath = "";

		final char[] barraChar = "\\".toCharArray();

		for (final char c : path.toCharArray()) {

			if (c == barraChar[0]) {
				novoPath = novoPath + c + "\\";
			} else {
				novoPath = novoPath + c;
			}
		}
		return novoPath;
	}

}
