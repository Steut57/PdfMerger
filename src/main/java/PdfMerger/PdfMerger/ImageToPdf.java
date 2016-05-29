package PdfMerger.PdfMerger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;


public class ImageToPdf {

	public ImageToPdf(String pathJpg, String pathPdf) throws MalformedURLException, IOException, DocumentException
	{
		Document convertJpgToPdf = new Document();
		PdfWriter.getInstance(convertJpgToPdf, new FileOutputStream(pathPdf));
		Rectangle r = new Rectangle(0, 0, PageSize.A4.getWidth(), PageSize.A4.getHeight());
		convertJpgToPdf.open();
		Image convertJpg=Image.getInstance(pathJpg);
		convertJpgToPdf.setPageSize(convertJpg);
		if(convertJpg.getWidth() > PageSize.A4.getWidth() || convertJpg.getHeight() > PageSize.A4.getHeight())
		{
			convertJpg.scaleToFit(r);
		}
		convertJpg.setAlignment(Image.MIDDLE);
		convertJpgToPdf.add(convertJpg);
		convertJpgToPdf.close();
	}
}
