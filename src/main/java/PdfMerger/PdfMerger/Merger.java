package PdfMerger.PdfMerger;

import java.io.IOException;

import javax.swing.DefaultListModel;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;

public class Merger 
{
	public Merger(String namePdf, DefaultListModel<String> listFiles)
	{
		PDFMergerUtility ut = new PDFMergerUtility();
		for(int i = 0; i < listFiles.size(); i++)
		{
			ut.addSource(listFiles.getElementAt(i));
		}
	    ut.setDestinationFileName(namePdf+".pdf");
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
