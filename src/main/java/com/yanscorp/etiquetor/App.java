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
 */
public class App {
    static final int FONT_SIZE = 12;

    public static void main(String[] args) throws IOException {
        String filename = "hello.pdf";

        try (PDDocument doc = new PDDocument()) {
            TemplateCodigoBarras template = new TemplateCodigoBarras();
            PDPage page = new PDPage();
            doc.addPage(page);

            PDFont font = PDType1Font.TIMES_ROMAN;

            try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
                float pageWidth = page.getMediaBox().getWidth();
                float pageHeight = page.getMediaBox().getHeight();

                float elementWidth = template.getTamanho().getX();
                float elementHeight = template.getTamanho().getY();

                float elementMargin = 20;

                int columns = (int) (pageWidth / (elementMargin * 2 + elementWidth));
                int lines = (int) (pageHeight / (elementMargin * 2 + elementHeight));

                float pageMarginX = (pageWidth - (template.getTamanho().getX() + elementMargin * 2) * columns) / 2;
                float pageMarginY = (pageHeight - (template.getTamanho().getY() + elementMargin * 2) * lines) / 2;

                contents.setFont(font, FONT_SIZE);

                contents.transform(Matrix.getTranslateInstance(pageMarginX, pageMarginY));

                for (int i = 0; i < lines; i++) {
                    contents.transform(Matrix.getTranslateInstance(0, elementMargin));
                    contents.saveGraphicsState();

                    for (int j = 0; j < columns; j++) {
                        contents.transform(Matrix.getTranslateInstance(elementMargin, 0));
                        contents.setStrokingColor(Color.BLACK);
                        contents.addRect(0, 0, template.getTamanho().getX(), template.getTamanho().getY());
                        contents.stroke();

                        for (ElementoCodigoBarras elemento : template.getElementos()) {
                            contents.saveGraphicsState();
                            Vetor tamanho;


                            switch (elemento.getTipo()) {
                                case CODIGO:
                                    tamanho = new Vetor(elementWidth * elemento.getTamanho().getX() / 100.0f, elementHeight * elemento.getTamanho().getY() / 100.0f);
                                    break;
                                default:
                                    tamanho = new Vetor(font.getStringWidth("lbl") / 1000 * FONT_SIZE, FONT_SIZE -1);
                                    break;
                            }

                            Matrix m = new Matrix();

                            switch (elemento.getAlinhamentoHorizontal()) {
                                case MEIO:
                                    m.translate((elementWidth - tamanho.getX()) / 2, 0);
                                    break;
                                case DIREITA:
                                    m.translate(elementWidth - tamanho.getX(), 0);
                                default:
                                    break;
                            }


                            switch (elemento.getAlinhamentoVertical()) {
                                case TOPO:
                                    m.translate(0, elementHeight - tamanho.getY());
                                    break;
                                case MEIO:
                                    m.translate(0, (elementHeight - tamanho.getY()) / 2);
                                    break;
                                default:
                                    break;
                            }

                            m.translate(elemento.getAjuste().getX(), -1 * elemento.getAjuste().getY());

                            m.translate(tamanho.getX() / 2, tamanho.getY() / 2);
                            m.rotate(elemento.getRotacao() * Math.PI / 180);
                            m.translate(-1 * tamanho.getX() / 2, -1 * tamanho.getY() / 2);



                            contents.transform(m);

                            switch (elemento.getTipo()) {
                                case CODIGO:
                                    contents.setNonStrokingColor(Color.LIGHT_GRAY);
                                    contents.addRect(0, 0, tamanho.getX(), tamanho.getY());
                                    contents.fill();
                                    break;

                                default:
                                    contents.beginText();
                                    contents.setNonStrokingColor(Color.DARK_GRAY);
                                    contents.newLineAtOffset(0, 0);
                                    contents.showText("lbl");
                                    contents.endText();
                                    break;
                            }

                            contents.restoreGraphicsState();
                        }

                        contents.transform(Matrix.getTranslateInstance(elementWidth + elementMargin, 0));
                    }
                    contents.restoreGraphicsState();
                    contents.transform(Matrix.getTranslateInstance(0, elementHeight + elementMargin));
                }


            }

            doc.save(filename);
        }

    }
}
