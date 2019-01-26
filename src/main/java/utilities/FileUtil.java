package utilities;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileUtil {

	private  File file = null;
	
	/**
     *  Description
     *  This method  is used to create a <code>File</code>  in a certain directory with a given name
     *  
     * @param directory - the  path for the folder where to create the file,passed as <code>String</code>
     * @param fileName - the  file name,passed as <code>String</code>
     * @return <code>true</code> if the file is created now
     * otherwise  it returns  <Code>false</Code>.
     */ 
	public boolean createFile(String directory,String fileName){
		File dir = new File(directory);
		if (dir.exists() == false) {
			dir.mkdirs(); // the directory is created
		}
		file = new File(directory + "//"+fileName );

		try {
			if(file.exists()){
				System.out.println("File exists!");
				return false; 
			}else{
				file.createNewFile();
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
     *  Description
     *  This method  <code>getFiles</code>  from a certain folder
 	*
     * @param folder - the  path for the folder
     * it is passed as String
     * @return <code>null</code> if folder is not a directory
     * otherwise it returns the files  as a <Code>Strig[]</Code>.
     */ 
    public static String[] getFiles(String folder) {
    	File file = new File(folder);
    	String [] files = file.list();
    	if(files == null)
    		System.out.println("Not a directory!");
        return files;
    }
    
    
    /**
     *  Description
     *  This method  <code>deleteFiles</code>  from a certain folder
 	*
     * @param filePath - the  path for the folder
     * it is passed as String
        * @return <code>true</code> if the file is deleted 
     * otherwise  it returns  <Code>false</Code>.
     */ 
	public boolean deleteFile(String filePath){
            File file = new File(filePath );
            if (file.exists()){
				file.delete();
                return true;
            }else{
            	return false;
			}

        } 
	
	
	/**
     *  Description
     *  This method  adds a string to a <code>Files</code>  with a certain path
 	 * @param filePath - the  path for the file
 	 *  it is passed as String
     * @param adder - the string
     * it is passed as String
     */ 
	public static void writeStringToFile(String filePath,String adder){
		try{
			File file = new File(filePath);
			FileUtils.writeStringToFile(file, adder);
		} catch (IOException e) {
			 e.printStackTrace();
		}
	}

	
	/**
     *  Description
     *  This method  copies a <code>File</code>  in a directory or in another file given in destination
     *  if the destination does not exist, it is created
 	 * @param source - the  path for the file
 	 *  it is passed as String
     * @param destination - the string
     * it is passed as String
     */ 
    public  void copyFile(String source,String destination){
    			try{
    				File src = new File(source);
    				File dest = new File(destination);
    				if(dest.exists()==false){
    					dest.mkdirs(); // the directory is created
    				}
    				if(dest.isDirectory()){
    					//create file in the wanted directory with the same name
    					file = new File(destination + "//"+src.getName() );
    					FileUtils.copyFile(src, file);
    				}else{
    					//creates a file with the name as in  the destination
    					FileUtils.copyFile(src, dest);
    				}

    				}catch(IOException e){
    					e.printStackTrace();
    				}
    }
    
    
    /**
     *  Description
     *  This method  moves a <code>File</code>  in a directory or in another file given in destination
     *  if the destination does not exist, it is created
 	 * @param source - the  path for the file
 	 *  it is passed as String
     * @param destination - the string
     * it is passed as String
     */ 
    public  void moveFile(String source,String destination){
		try{
			File src = new File(source);
			File dest = new File(destination);
			if(dest.isDirectory()){
				//create file in the wanted directory with the same name
				file = new File(destination + "//"+src.getName() );
				FileUtils.copyFile(src, file);
			}else{
				FileUtils.copyFile(src, dest);
			}
			src.delete();

			}catch(IOException e){
				e.printStackTrace();
			}
    }

    /**
     * Deletes all the files within the directory
     * @param path
     */
    public void cleanDirectory(String path){
    	try {
    		File directory = new File(path);
    		if (directory.exists())
    			FileUtils.cleanDirectory(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
    
    
    /**
     * Deletes all files within the given directory as parameter
     * @param path
     */
    public void clearFolder(String path){
    	File inputFile = new File(path);
    	for (File currentFile : inputFile.listFiles()) {
			if (currentFile.isFile() ){
				if( !currentFile.delete())
					currentFile.deleteOnExit();
			}
		}
    }


	
}
