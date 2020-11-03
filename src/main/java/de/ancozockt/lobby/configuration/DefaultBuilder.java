package de.ancozockt.lobby.configuration;

import de.ancozockt.utility.database.FileManager;

import java.io.*;

public class DefaultBuilder {

    public File createTempFile(String filename, String tempFileName, FileManager manager){
        InputStream inputStream =  this.getClass().getClassLoader().getResourceAsStream(filename);

        File file = manager.getTempFile(tempFileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ignored) { }
        }

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            PrintWriter pw = new PrintWriter(outputStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String readLine = null;
            while ((readLine = br.readLine()) != null) {
                pw.println(readLine);
            }
            br.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;
    }

    public void copyTemplate(String templateFile, File file){
        InputStream inputStream =  this.getClass().getClassLoader().getResourceAsStream(templateFile);

        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            PrintWriter pw = new PrintWriter(outputStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String readLine = null;
            while ((readLine = br.readLine()) != null) {
                pw.println(readLine);
            }
            br.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] buildDefault(String name, String value){
        return new String[]{name, value};
    }

    public Object[] buildDefault(String name, Object value){
        return new Object[]{name, value};
    }

}
