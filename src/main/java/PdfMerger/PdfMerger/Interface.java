package PdfMerger.PdfMerger;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class Interface extends JFrame implements ActionListener
{
	private JList<String> graphicListFiles;
	private ArrayList<String> listFiles;
	private JButton buttonAddFile, buttonQuit;
	final JFileChooser fc = new JFileChooser();
	public Interface() 
	{
		super("PdfMerger");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLayout(null);
		listFiles = new ArrayList<String>();		
		graphicListFiles = new JList(listFiles.toArray());
		JScrollPane listScroller = new JScrollPane(graphicListFiles);
		listScroller.setBounds(0, 0, 500, 500);
		this.add(listScroller);
		buttonAddFile = new JButton("Ajouter un fichier");
		buttonAddFile.setBounds(200, 500, 200, 40);
		buttonAddFile.addActionListener(this);
		buttonQuit = new JButton("Quitter l'application");
		buttonQuit.setBounds(500, 500, 200, 40);
		buttonQuit.addActionListener(this);
		this.add(buttonAddFile);
		this.add(buttonQuit);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == buttonAddFile)
		{
			System.out.println("COUCOU");
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) 
			{
				File file = fc.getSelectedFile();
				listFiles.add(file.getAbsolutePath());
				refreshListFiles();
				System.out.println("file " + file.getAbsolutePath());				
			} 
			else 
			{
				System.out.println("cancel");
			}
		}
		else if(source == buttonQuit)
		{
			System.out.println("QUIT");
			System.exit(0);
		}
		
	}

	private void refreshListFiles() 
	{
		graphicListFiles.repaint();	
	}
}
