package com.yanscorp.etiquetor;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.Matrix;

/**
 * Hello world!
 *
 */
public class App 
{
	static final int FONT_SIZE = 12;
	
    public static void main( String[] args ) throws IOException
    {
        String filename = "hello.pdf";
        
        try (PDDocument doc = new PDDocument())
        {
        	TemplateCodigoBarras template = new TemplateCodigoBarras();
            PDPage page = new PDPage();
            doc.addPage(page);
            
            PDFont font = PDType1Font.TIMES_ROMAN;

            try (PDPageContentStream contents = new PDPageContentStream(doc, page))
            {
            	float pageWidth = page.getMediaBox().getWidth();
            	float pageHeight = page.getMediaBox().getHeight();
            	
            	float elementWidth = template.getTamanho().getX();
            	float elementHeight = template.getTamanho().getY();
            	
            	float elementMargin = 20;
            	
            	int elementsPerLine = (int)( pageWidth / (elementMargin * 2 + elementWidth));
            	int elementsPerColumn = (int)(pageHeight / (elementMargin * 2 + elementHeight));
            	
            	float pageMarginX = (pageWidth - (template.getTamanho().getX() + elementMargin * 2) * elementsPerLine) / 2;
            	float pageMarginY = (pageHeight - (template.getTamanho().getY() + elementMargin * 2) * elementsPerColumn) / 2;
            	
            	Matrix m = new Matrix();
            	m.translate(pageMarginX, pageMarginY);
            	contents.setNonStrokingColor(Color.CYAN);
            	
            	for (int i = 0; i < elementsPerColumn; i++) {
            		contents.saveGraphicsState();
            		contents.transform(m);
            		
					for (int j = 0; j < elementsPerLine; j++) {
						contents.addRect(elementMargin, elementMargin, template.getTamanho().getX(), template.getTamanho().getY());
						contents.fill();
						
						for (ElementoCodigoBarras elemento : template.getElementos()) {
							contents.saveGraphicsState();
							Vetor tamanho;
							
							switch (elemento.getTipo()) {
							case CODIGO:
								tamanho = new Vetor(elementWidth * elemento.getTamanho().getX() / 100.0f, elementHeight * elemento.getTamanho().getY() / 100.0f);
								contents.transform(Matrix.getTranslateInstance(elementMargin + (elementWidth -  tamanho.getX()) / 2, elementMargin + (elementHeight - tamanho.getY()) / 2));
								contents.setNonStrokingColor(Color.RED);
								contents.addRect(0, 0, tamanho.getX(), tamanho.getY());
								contents.fill();
								break;

							default:
								contents.setFont(font, FONT_SIZE);
								tamanho = new Vetor(font.getStringWidth("Elemento") / 1000 * FONT_SIZE, FONT_SIZE);
								contents.beginText();
								contents.transform(Matrix.getTranslateInstance(elementMargin + (elementWidth -  tamanho.getX()) / 2, elementMargin + (elementHeight - tamanho.getY()) / 2));
								
								contents.setNonStrokingColor(Color.GREEN);
				                
				                contents.newLineAtOffset(0, 0);
				                contents.showText("Mensagem");
				                contents.endText();
								break;
							}
							
							contents.restoreGraphicsState();
						}
						
						contents.transform(Matrix.getTranslateInstance(elementWidth + elementMargin * 2, 0));
					}
					m = m.multiply(Matrix.getTranslateInstance(0, elementHeight + elementMargin * 2));
					contents.restoreGraphicsState();            		
				}
            }
            
            doc.save(filename);
        }

    }
}
