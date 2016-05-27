package PdfMerger.PdfMerger;

import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;

public class Merger 
{
	public Merger()
	{
		PDFMergerUtility ut = new PDFMergerUtility();
	    ut.addSource("./Resources/test1.pdf");
	    ut.addSource("./Resources/test2.pdf");
	    ut.setDestinationFileName("./Resources/test.pdf");
	    try {
			ut.mergeDocuments();
		} catch (COSVisitorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
