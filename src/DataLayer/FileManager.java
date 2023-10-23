/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataLayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tanng
 */
public class FileManager implements IFileManager{
    private String fileName; 

    public FileManager() {
    }
    //--------------------------------------------------        
    public FileManager(String fileName) {
        this.fileName = fileName;
    }
    //--------------------------------------------------
    @Override
    public List<String> readDataFromFile() throws IOException {
        File file = new File(this.fileName);
        List<String> result = Files.readAllLines(file.toPath());
        if (result.get(0).equals("")){
            return new ArrayList<>();
        }
        return result;
    }

    @Override
    public void writeDataToFile(List result) throws Exception {
        Files.write(new File(fileName).toPath(), result, StandardOpenOption.WRITE);
    }
    
}
