/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandchelem;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author charl
 */
public class TestReadingFile {
    
    
    public static void readCsv() throws IOException{
        FileReader myFile = null;
        FileWriter out=null;
        try{
            myFile = new FileReader("joueurs.csv");
            out = new FileWriter("test.txt");
            
            int c;
            while((c=myFile.read())!=-1){
                out.write(c);
            }    
        }
        finally{
            if (myFile != null)myFile.close();
            if (out != null)out.close();
        }
        
    }
}
