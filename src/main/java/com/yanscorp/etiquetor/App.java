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
    public static void main( String[] args ) throws IOException
    {
        String filename = "hello.pdf";
        
        try (PDDocument doc = new PDDocument())
        {
        	TemplateCodigoBarras template = new TemplateCodigoBarras();
            PDPage page = new PDPage();
            doc.addPage(page);
            
            PDFont font = PDType1Font.HELVETICA_BOLD;

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
							
							switch (elemento.getTipo()) {
							case CODIGO:
								float w = 10;
								break;

							default:
								break;
							}
						}
						
						contents.transform(Matrix.getTranslateInstance(elementWidth + elementMargin * 2, 0));
					}
					
					m = m.multiply(Matrix.getTranslateInstance(0, elementHeight + elementMargin * 2));
					contents.restoreGraphicsState();
            		
				}
                contents.beginText();
                contents.setFont(font, 12);
                contents.newLineAtOffset(100, 700);
                contents.showText("Mensagem");
                contents.endText();
            }
            
            doc.save(filename);
        }

    }
}
