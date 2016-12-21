package PdfMerger.PdfMerger;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.peer.ButtonPeer;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

public class PdfVisualisation implements ActionListener
{
	private PDFPage page;
	private int nbPages, currentPage;
	private PDFFile pdfFile;
	private JFrame frame;
	private JButton buttonNextPage, buttonPreviousPage;
	
	public PdfVisualisation(String fileName) throws IOException 
	{
		File file = new File(fileName);
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		FileChannel channel = raf.getChannel();
		ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
		pdfFile = new PDFFile(buffer);
		nbPages = pdfFile.getNumPages();
		page = pdfFile.getPage(1);
		currentPage = 1;
		goToPage(currentPage);
		System.out.println("PdfVisualisation");
		//previewPdf();
	}
	
	public void goToPage(int nbpage) throws IndexOutOfBoundsException
	{
		if(nbpage<1||nbpage>pdfFile.getNumPages())
		{
			throw new IndexOutOfBoundsException("index invalide demande : "+nbpage);
		}
		page = pdfFile.getPage(nbpage); 
		previewPdf();
	}
	
	public void previewPdf() 
	{
		Rectangle rect = new Rectangle(0, 0, (int)page.getBBox().getWidth(), (int)page.getBBox().getHeight());
		Image img = page.getImage(rect.width, rect.height, rect, null, true, true);
		JScrollPane scroller = new JScrollPane(new JLabel(new ImageIcon(img)));
		frame = new JFrame("yes yes yes  PDF document");
		frame.setSize(rect.width, rect.height + 100);
		frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.add(new JLabel(new ImageIcon(img)));
        JLabel text = new JLabel("Page number " + currentPage +  " on " + nbPages);
        text.setBounds(0, 0, 200, 30);
        buttonPreviousPage = new JButton("Page precedente");
        buttonPreviousPage.setBounds(200, 0, 200, 40);
        buttonPreviousPage.addActionListener(this);
        buttonNextPage = new JButton("Page suivante");
        buttonNextPage.setBounds(500, 0, 200, 40);
        buttonNextPage.addActionListener(this);
        checkButtonActivation();
        frame.add(text);
        frame.add(buttonPreviousPage);
        frame.add(buttonNextPage);
        scroller.setBounds(0, 100, rect.width, rect.height);
        frame.add(scroller);
        frame.setVisible(true);
        
	}

	public void actionPerformed(ActionEvent e) 
	{
		Object source = e.getSource();
		if(source == buttonPreviousPage)
		{
			this.currentPage = this.currentPage - 1;
			goToPage(this.currentPage);
		}
		else if(source == buttonNextPage)
		{
			this.currentPage = this.currentPage + 1;
			goToPage(this.currentPage);
		}
		checkButtonActivation();
	}

	private void checkButtonActivation() 
	{
		if(this.currentPage == 1 && this.nbPages > 1)
		{
			buttonPreviousPage.setEnabled(false);
			buttonNextPage.setEnabled(true);
		}
		else if(this.currentPage == this.nbPages && this.nbPages > 1)
		{
			buttonPreviousPage.setEnabled(true);
			buttonNextPage.setEnabled(false);
		}
		else if(this.nbPages == 1)
		{
			buttonPreviousPage.setEnabled(false);
			buttonNextPage.setEnabled(false);
		}
		else
		{
			buttonPreviousPage.setEnabled(true);
			buttonNextPage.setEnabled(true);
		}
	}
}
