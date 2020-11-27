package br.com.izri.aservico.controller.tesouraria;

import java.text.NumberFormat;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import br.com.izri.aservico.model.entity.Dizimo;
import br.com.izri.aservico.model.entity.Oferta;
import br.com.izri.aservico.utils.DateUtils;
import br.com.izri.aservico.utils.DateUtils.PATTERN;

public class ControllerTable {

	private ControllerTable() {

	}

	private static ControllerTable instance;

	public static ControllerTable getInstance() {
		if (ControllerTable.instance == null) {
			ControllerTable.instance = new ControllerTable();
		}
		return ControllerTable.instance;
	}

	public static void preencherDadosDizimo(Document document, PdfPTable table, List<Dizimo> listaDizimo) throws DocumentException {
		if (document.isOpen()) {
			for (Dizimo d : listaDizimo) {
				PdfPCell celulaData = new PdfPCell(new Phrase(DateUtils.format(d.getDataEntradaDizimo(), PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN)));
				PdfPCell celulaNomeMembro = new PdfPCell(new Phrase(d.getMembro().getNomeCompleto()));
				PdfPCell celulaValorDizimo = new PdfPCell(new Phrase(NumberFormat.getCurrencyInstance().format(d.getValor()).toString()));

				table.addCell(celulaData);
				table.addCell(celulaNomeMembro);
				table.addCell(celulaValorDizimo);
			}

			document.add(table);
		}
	}

	public static void preencherDadosOferta(Document document, PdfPTable table, List<Oferta> listaOferta) throws DocumentException {
		if (document.isOpen()) {
			for (Oferta o : listaOferta) {
				PdfPCell celulaData = new PdfPCell(new Phrase(DateUtils.format(o.getDataEntradaOferta(), PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN)));
				PdfPCell celulaValorOferta = new PdfPCell(new Phrase(NumberFormat.getCurrencyInstance().format(o.getValor()).toString()));

				table.addCell(celulaData);
				table.addCell(celulaValorOferta);
			}

			document.add(table);
		}
	}

	public static PdfPTable criarCabecalhoTabelaDizimo() throws DocumentException {
		PdfPTable table = new PdfPTable(new float[] { 5f, 12f, 5f });

		PdfPCell celulaDataEntrada = new PdfPCell(new Phrase("Data Entrada"));
		celulaDataEntrada.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell celulaNomeMembro = new PdfPCell(new Phrase("Nome do Membro"));
		celulaNomeMembro.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell celulaValorDizimo = new PdfPCell(new Phrase("Valor"));
		celulaValorDizimo.setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell(celulaDataEntrada);
		table.addCell(celulaNomeMembro);
		table.addCell(celulaValorDizimo);

		return table;
	}

	public static PdfPTable criarCabecalhoTabelaOferta() throws DocumentException {
		PdfPTable table = new PdfPTable(new float[] { 5f, 5f });

		PdfPCell celulaDataEntrada = new PdfPCell(new Phrase("Data Entrada"));
		celulaDataEntrada.setHorizontalAlignment(Element.ALIGN_CENTER);

		PdfPCell celulaValorOferta = new PdfPCell(new Phrase("Valor Oferta"));
		celulaValorOferta.setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell(celulaDataEntrada);
		table.addCell(celulaValorOferta);

		return table;
	}
}
