package com.claudemirojr.app.view.xlsx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.claudemirojr.app.model.entity.Fatura;
import com.claudemirojr.app.model.entity.ItemFatura;

@Component("fatura/ver.xlsx")
public class FaturaXlsxView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"fatura_view.xlsx\"");
		Fatura fatura = (Fatura) model.get("fatura");
		
		Sheet sheet = workbook.createSheet("Fatura Spring");
		
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue("Dados do Cliente");
		
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue( fatura.getCliente().getNome() + " "  + fatura.getCliente().getApelido() );
		
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue(  fatura.getCliente().getEmail() );
		
		
		
		sheet.createRow(4).createCell(0).setCellValue("Dados da Fatura");
		sheet.createRow(5).createCell(0).setCellValue("Código: " + fatura.getId().toString());
		sheet.createRow(6).createCell(0).setCellValue("Descrição: " + fatura.getDescricao().toString());
		sheet.createRow(7).createCell(0).setCellValue("Criada em: " + fatura.getCriadoEm().toString());
		
		
		Row header = sheet.createRow(9);
		header.createCell(0).setCellValue("Produto");
		header.createCell(1).setCellValue("Preço");
		header.createCell(2).setCellValue("Quantidade");
		header.createCell(3).setCellValue("Total");
		
		int rounum = 10;
		for (ItemFatura item: fatura.getItems()) {
			
			Row fila = sheet.createRow(rounum++);
			
			fila.createCell(0).setCellValue(item.getProduto().getNome());
			fila.createCell(1).setCellValue(item.getProduto().getPreco().toString());
			fila.createCell(2).setCellValue(item.getQuantidade().toString());
			fila.createCell(3).setCellValue(item.calcularTotal().toString());
			
		}	
		
		Row filaTotal = sheet.createRow(rounum++);
		filaTotal.createCell(2).setCellValue("TOTAL: ");
		filaTotal.createCell(3).setCellValue(fatura.getTotal().toString());
		
		
	}

}
