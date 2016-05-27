package PdfMerger.PdfMerger;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class Interface extends JFrame implements ActionListener
{
	private JList<String> graphicListFiles;
	private JButton buttonAddFile, buttonQuit, buttonCombine;
	private JScrollPane listScroller;
	private DefaultListModel<String> modelList;
	private JFileChooser pathToSave;
	final JFileChooser fc = new JFileChooser();
	public Interface() 
	{
		super("PdfMerger");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 600);
		this.setLayout(null);
		modelList = new DefaultListModel<String>();
		graphicListFiles = new JList(modelList);
		listScroller = new JScrollPane(graphicListFiles);
		listScroller.setBounds(0, 0, 500, 500);
		this.add(listScroller);
		buttonAddFile = new JButton("Ajouter un fichier");
		buttonAddFile.setBounds(100, 500, 200, 40);
		buttonAddFile.addActionListener(this);
		buttonCombine = new JButton("Combiner");
		buttonCombine.setBounds(350, 500, 200, 40);
		buttonCombine.addActionListener(this);	
		buttonQuit = new JButton("Quitter l'application");
		buttonQuit.setBounds(600, 500, 200, 40);
		buttonQuit.addActionListener(this);
		this.add(buttonAddFile);
		this.add(buttonCombine);
		this.add(buttonQuit);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == buttonAddFile)
		{
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) 
			{
				File file = fc.getSelectedFile();
				modelList.addElement(file.getAbsolutePath());
				refreshListFiles();
				System.out.println("file " + file.getAbsolutePath());				
			} 
			else 
			{
				System.out.println("cancel");
			}
		}
		else if(source == buttonCombine)
		{
			if(modelList.size() == 0)
			{
				JOptionPane.showMessageDialog(this, "Aucun fichier à combiner", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				pathToSave = new JFileChooser();
				int result = pathToSave.showSaveDialog(this);
				String fileToCreate;
				if(result == JFileChooser.APPROVE_OPTION)
				{					
					fileToCreate = pathToSave.getSelectedFile().getAbsolutePath();
			    	new Merger(fileToCreate, modelList);
					modelList.clear();
					JOptionPane.showMessageDialog(this, "Le fichier à bien été enregistré", "Enregistré", JOptionPane.INFORMATION_MESSAGE);
				}
			}			
		}
		else if(source == buttonQuit)
		{
			System.exit(0);
		}
		
	}

	private void refreshListFiles() 
	{
		graphicListFiles.repaint();	
		graphicListFiles.revalidate();
		listScroller.repaint();
		listScroller.revalidate();
	}
}
