import java.io.*;
import java.net.URL;

public class Downloader {
    public static void handleException(Exception e){
        System.out.println("Возникло исключение" + e.getMessage());
        e.printStackTrace();
    }
    public static void initialMessage(){
        System.out.println("**********");
        System.out.println("Введите адрес файла:");
    }
    public static void downloadFile(String url, String outputFile){
        try {
            BufferedInputStream bufStream = new BufferedInputStream(new URL(url).openStream(), 1024);
            FileOutputStream fos = new FileOutputStream(outputFile);
            int buf;
            byte[] buffer = new byte[1024];
            while ((buf = bufStream.read(buffer, 0, 1024)) != -1) {
                fos.write(buffer, 0, buf);
            }
            fos.flush();
            bufStream.close();
            fos.close();
        }
        catch(Exception e){
            handleException(e);
        }
    }
    public static void main(String[] args) {
        initialMessage();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String inputURL = null;
        try {
            inputURL = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Введите путь для сохранения файла:");
        String outputFilePath = null;
        try {
            outputFilePath = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        downloadFile(inputURL, outputFilePath);
        System.out.println("Загрузка завершена!");
    }
}
