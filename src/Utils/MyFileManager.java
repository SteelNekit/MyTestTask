package Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class MyFileManager {
    private String pageURL;
    private File targetFile;
    private final Chronicler chronicler = new Chronicler();

    public MyFileManager(){
        setPageURL();
        setTargetFile();
    }

    private void setPageURL(){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите адрес желаемого сайта:");
        pageURL = in.next();//http://www.google.com
    }

    private void setTargetFile(){
        JFileChooser fileOpen = new JFileChooser();
        //fileOpen.grabFocus();
        System.out.println("Сверните все окна и увидите диалоговое окно");
        System.out.println("Выберите файлик в который хотите записать web страничку и вернитесь сюда");
        int ret = fileOpen.showDialog(null, "Записать страницу в этот файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            targetFile = fileOpen.getSelectedFile();
        }else{
            System.out.println("Файл не выбран, выполняется закрытие программы");
            System.exit(0);
        }
    }

    public void fillTargetFile(){
        try{
            FileWriter writer = new FileWriter(targetFile);
            Document document = Jsoup.connect(pageURL).get();
            chronicler.writeAboutWebPage(pageURL);
            writer.write(document.toString());
            writer.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Возникли проблемы с файлом для записи или вы указали неверный адрес страницы");
            System.out.println("Выполняется закрытие программы");
            chronicler.writeAboutWebPageTroubles(pageURL,targetFile.getAbsolutePath());
            System.exit(0);
        }

    }

    public void doYourJob() throws IOException {
        Document document = Jsoup.parse(targetFile,"UTF-8");
        Map<String,Integer> words = StringWorker.getWordsStatistics(document.text());
        chronicler.writeAboutJob();
        sendMapToDatabase(words);
        chronicler.writeAboutDatabase();
        for (Map.Entry<String,Integer> pair:words.entrySet()){
            System.out.println(pair.getKey()+" - "+pair.getValue());
        }
    }

    public void sendMapToDatabase(Map<String,Integer> wordsMap){
        DatabaseWorker.appendWordToDatabase(wordsMap,pageURL);
    }
}
