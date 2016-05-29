package PdfMerger.PdfMerger;

import java.io.File;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
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
