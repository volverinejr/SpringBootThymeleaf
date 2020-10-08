package com.claudemirojr.app.view.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.claudemirojr.app.model.entity.Fatura;
import com.claudemirojr.app.model.entity.ItemFatura;
import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("fatura/ver")
public class FaturaPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Fatura fatura = (Fatura) model.get("fatura");
		
		PdfPTable tabela01 = new PdfPTable(1);
		tabela01.setSpacingAfter(20);
		tabela01.addCell("Dados do Cliente");
		tabela01.addCell( fatura.getCliente().getNome() + " "  + fatura.getCliente().getApelido() );
		tabela01.addCell( fatura.getCliente().getEmail() );
		
		PdfPTable tabela02 = new PdfPTable(1);
		tabela02.setSpacingAfter(20);
		tabela02.addCell("Dados da Fatura");
		tabela02.addCell("Código: " + fatura.getId() );
		tabela02.addCell("Descrição: " + fatura.getDescricao() );
		tabela02.addCell("Criada em: " + fatura.getCriadoEm() );
		
		PdfPTable tabela03 = new PdfPTable(4);
		tabela03.setWidths(new float[] {2.5f, 1, 1, 1});
		tabela03.addCell("Produto");
		tabela03.addCell("Preço");
		tabela03.addCell("Quantidade");
		tabela03.addCell("Total");
		

		
		for (ItemFatura item: fatura.getItems()) {
			tabela03.addCell(item.getProduto().getNome());
			tabela03.addCell(item.getProduto().getPreco().toString());
			tabela03.addCell(item.getQuantidade().toString());
			tabela03.addCell(item.calcularTotal().toString());
		}
		PdfPCell cell = new PdfPCell(new Phrase("Total :"));
		cell.setColspan(3);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		tabela03.addCell(cell);
		tabela03.addCell(fatura.getTotal().toString());
		
		

		
		document.add(tabela01);
		document.add(tabela02);
		document.add(tabela03);
		
	}

}
