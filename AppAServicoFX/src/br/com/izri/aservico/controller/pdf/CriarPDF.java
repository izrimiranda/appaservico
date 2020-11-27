package br.com.izri.aservico.controller.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.izri.aservico.controller.tesouraria.ControllerTable;
import br.com.izri.aservico.model.entity.Dizimo;
import br.com.izri.aservico.model.entity.Oferta;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author Izrí
 *
 *         Classe para gerar os pdf's.
 *
 */
public class CriarPDF {

	private static String LOGO_IGREJA_IMAGE_FILE = "src/imagens/Logo-Igreja-150.png";
	// private static String LOGO_ASERVICO_IMAGE_FILE =
	// "src/imagens/logoAppAServico200.png";

	/**
	 *
	 * @param data
	 *            (Calendar)
	 *
	 * @throws DocumentException
	 * @throws IOException
	 * @throws ParseException
	 */

	public String getPath() {
		System.out.println("DENTRO DO JFILECHOOSER");

		DirectoryChooser directoryChooser = new DirectoryChooser();

		return directoryChooser.showDialog(null).getAbsolutePath() + "\\";
	}

	public void gerarRelatorioTesourariaDizimo(String ano, String mes, List<Dizimo> listaDizimo) throws DocumentException, IOException, ParseException {
		String path = this.getPath();

		Document doc = null;
		OutputStream os = null;

		doc = new Document(PageSize.A4, 72, 72, 20, 72);
		os = new FileOutputStream(path + "RELATORIO_TESOURARIA_DIZIMO_" + mes + "_" + ano + ".pdf");

		PdfWriter.getInstance(doc, os);

		doc.open();

		PdfPTable table = ControllerTable.criarCabecalhoTabelaDizimo();

		doc.add(this.getImagemLogoIgreja());
		doc.add(this.setCabecalho("Relatório de Dízimos"));

		doc.add(this.setData(mes, ano));
		doc.add(Chunk.NEWLINE);

		ControllerTable.preencherDadosDizimo(doc, table, listaDizimo);

		doc.close();

		System.out.println("PDF GERADO COM SUCESSO!");
	}

	public void gerarRelatorioTesourariaOferta(String ano, String mes, List<Oferta> listaOferta) throws DocumentException, IOException, ParseException {
		String path = this.getPath();

		Document doc = null;
		OutputStream os = null;

		doc = new Document(PageSize.A4, 72, 72, 20, 72);
		os = new FileOutputStream(path + "RELATORIO_TESOURARIA_OFERTA_" + mes + "_" + ano + ".pdf");

		PdfWriter.getInstance(doc, os);

		doc.open();

		PdfPTable table = ControllerTable.criarCabecalhoTabelaOferta();

		doc.add(this.getImagemLogoIgreja());
		doc.add(this.setCabecalho("Relatório de Ofertas"));

		doc.add(this.setData(mes, ano));
		doc.add(Chunk.NEWLINE);

		ControllerTable.preencherDadosOferta(doc, table, listaOferta);

		doc.close();

		System.out.println("PDF GERADO COM SUCESSO!");
	}

	public String verificarMesDizimo(List<Dizimo> lista) {
		for (Dizimo d : lista) {

		}
		return null;
	}

	private Image getImagemLogoIgreja() throws BadElementException, MalformedURLException, IOException {

		Image img = Image.getInstance(CriarPDF.LOGO_IGREJA_IMAGE_FILE);
		img.setAlignment(Element.ALIGN_CENTER);

		return img;
	}

	private Paragraph setCabecalho(String texto) {

		Font font = new Font(FontFamily.TIMES_ROMAN, 15, Font.BOLD);

		Paragraph p = new Paragraph(texto, font);
		p.setAlignment(Element.ALIGN_CENTER);

		return p;
	}

	private Paragraph setData(String mes, String ano) throws ParseException {

		Paragraph p = null;
		Font font = null;

		font = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);

		p = new Paragraph("Ano: " + ano + " | Mês: " + mes, font);
		p.setAlignment(Element.ALIGN_CENTER);

		return p;
	}
}
