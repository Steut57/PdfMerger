package PdfMerger.PdfMerger;

import java.io.File;
import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException
    {   
        new Interface();
    }
    
    public static void createTmpDir()
    {
    	new File("./tmp").mkdirs();
    }
    
    public static void removeTmpDir(File dir)
    {
    	if (dir.isDirectory()) 
    	{
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) 
            {
                for (File aFile : files) 
                {
                    removeTmpDir(aFile);
                }
            }
            dir.delete();
        } else 
        {
            dir.delete();
        }
    }
}
