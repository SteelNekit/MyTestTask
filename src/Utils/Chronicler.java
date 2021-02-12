package Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Chronicler {
    public static String CHRONICLES_PATH = "LogFolder/chronicles.txt";

    public void writeAboutWebPage(String url){
        try{
            preventFileTroubles();
            FileWriter fileWriter = new FileWriter(CHRONICLES_PATH,true);
            fileWriter.write(header()+"Пользователь изъявил желание постучаться на "+url+"\n");
            fileWriter.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Проблема с логированием(0)");
        }
    }

    public void writeAboutWebPageTroubles(String url,String filePath){
        try{
            preventFileTroubles();
            FileWriter fileWriter = new FileWriter(CHRONICLES_PATH,true);
            fileWriter.write(header()+"Проблема при скачивании веб страницы "+url + "или файлом для записи "+filePath+"\n");
            fileWriter.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Проблема с логированием(1)");
        }
    }

    public void writeAboutJob(){
        try{
            preventFileTroubles();
            FileWriter fileWriter = new FileWriter(CHRONICLES_PATH,true);
            fileWriter.write(header()+"Статистика по словам собрана"+"\n");
            fileWriter.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Проблема с логированием(2)");
        }
    }

    public void writeAboutDatabase(){
        try{
            preventFileTroubles();
            FileWriter fileWriter = new FileWriter(CHRONICLES_PATH,true);
            fileWriter.write(header()+"Отправка статистики в базу"+"\n");
            fileWriter.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Проблема с логированием(3)");
        }
    }

    public void writeAboutDatabaseConnection(){
        try{
            preventFileTroubles();
            FileWriter fileWriter = new FileWriter(CHRONICLES_PATH,true);
            fileWriter.write(header()+"Удалось соединиться с базой"+"\n");
            fileWriter.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Проблема с логированием(4)");
        }
    }

    public void writeAboutDatabaseConnectionError(){
        try{
            preventFileTroubles();
            FileWriter fileWriter = new FileWriter(CHRONICLES_PATH,true);
            fileWriter.write(header()+"Попытка соединения неудачна"+"\n");
            fileWriter.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Проблема с логированием(5)");
        }
    }

    public void writeAboutDatabaseAppendError(){
        try{
            preventFileTroubles();
            FileWriter fileWriter = new FileWriter(CHRONICLES_PATH,true);
            fileWriter.write(header()+"Попытка добавления группы элементов неудачна"+"\n");
            fileWriter.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Проблема с логированием(6)");
        }
    }

    public void writeAboutDatabaseAppendError(String word, int count, String website){
        try{
            preventFileTroubles();
            FileWriter fileWriter = new FileWriter(CHRONICLES_PATH,true);
            fileWriter.write(header()+"Попытка добавления элемента("+word+","+count+","+website+") неудачна"+"\n");
            fileWriter.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Проблема с логированием(7)");
        }
    }

    private void preventFileTroubles(){
        File f = new File(CHRONICLES_PATH);
        try{
            if(!f.exists()){
                f.createNewFile();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("Не могу создать файл для логов");
            System.out.println("Исправьте это сами, удачи");
            System.exit(0);
        }

    }

    private String header(){
        return LocalDateTime.now() + " -||- ";
    }
}
