package PdfMerger.PdfMerger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class Interface extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<String> graphicListFiles;
	private JButton buttonAddFile, buttonQuit, buttonCombine, buttonUp, buttonDown, buttonDel;
	private JScrollPane listScroller;
	private DefaultListModel<String> modelList;
	private JFileChooser pathToSave;
	final JFileChooser fc = new JFileChooser();
	public Interface() throws IOException 
	{
		super("PdfMerger");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 600);
		this.setLayout(null);
		modelList = new DefaultListModel<String>();
		graphicListFiles = new JList<String>(modelList);
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
		BufferedImage iconButtonUp = ImageIO.read(new File("./Resources/arrowUp.png"));
		buttonUp = new JButton(new ImageIcon(iconButtonUp));
		buttonUp.setBorder(BorderFactory.createEmptyBorder());
		buttonUp.setContentAreaFilled(false);
		buttonUp.setFocusPainted(false);
		buttonUp.setBounds(510, 200, 50, 30);
		buttonUp.addActionListener(this);
		BufferedImage iconButtonDel = ImageIO.read(new File("./Resources/buttonDel.png"));
		buttonDel = new JButton(new ImageIcon(iconButtonDel));
		buttonDel.setBorder(BorderFactory.createEmptyBorder());
		buttonDel.setContentAreaFilled(false);
		buttonDel.setFocusPainted(false);
		buttonDel.setBounds(510, 250, 50, 30);
		buttonDel.addActionListener(this);
		BufferedImage iconButtonDown = ImageIO.read(new File("./Resources/arrowDown.png"));
		buttonDown = new JButton(new ImageIcon(iconButtonDown));
		buttonDown.setBorder(BorderFactory.createEmptyBorder());
		buttonDown.setContentAreaFilled(false);
		buttonDown.setFocusPainted(false);
		buttonDown.setBounds(510, 300, 50, 30);
		buttonDown.addActionListener(this);
		this.add(buttonAddFile);
		this.add(buttonCombine);
		this.add(buttonQuit);
		this.add(buttonUp);
		this.add(buttonDel);
		this.add(buttonDown);
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
		else if(source == buttonUp)
		{
			int selectedPosition = graphicListFiles.getSelectedIndex();
			if(selectedPosition == 0 || selectedPosition == -1)
			{
				return;
			}
			String tmp = modelList.getElementAt(selectedPosition);
			modelList.remove(selectedPosition);
			modelList.add(selectedPosition - 1, tmp);
			refreshListFiles();
			graphicListFiles.setSelectedIndex(selectedPosition - 1);
		}
		else if(source == buttonDel)
		{
			int selectedPosition = graphicListFiles.getSelectedIndex();
			if(selectedPosition == -1)
			{
				return;
			}
			modelList.remove(selectedPosition);
			refreshListFiles();
		}
		else if(source == buttonDown)
		{
			int selectedPosition = graphicListFiles.getSelectedIndex();
			if(selectedPosition == modelList.size() - 1 || selectedPosition == -1)
			{
				return;
			}
			String tmp = modelList.getElementAt(selectedPosition);
			modelList.remove(selectedPosition);
			modelList.add(selectedPosition + 1, tmp);
			refreshListFiles();
			graphicListFiles.setSelectedIndex(selectedPosition + 1);
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
