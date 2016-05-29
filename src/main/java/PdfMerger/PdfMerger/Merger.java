package PdfMerger.PdfMerger;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.DefaultListModel;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;

import com.itextpdf.text.DocumentException;

public class Merger 
{
	public Merger(String namePdf, DefaultListModel<String> listFiles)
	{
		PDFMergerUtility ut = new PDFMergerUtility();
		App.createTmpDir();
		for(int i = 0; i < listFiles.size(); i++)
		{
			if(listFiles.getElementAt(i).endsWith(".jpg") || listFiles.getElementAt(i).endsWith(".jpeg") || listFiles.getElementAt(i).endsWith(".png"))
			{
				try {
					new ImageToPdf(listFiles.getElementAt(i), "./tmp/tmp"+i+".pdf");
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ut.addSource("./tmp/tmp"+i+".pdf");
			}
			else
			{
				System.out.println("pdf file");
				ut.addSource(listFiles.getElementAt(i));
			}
			
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
	    App.removeTmpDir(new File("./tmp"));
	}
}
