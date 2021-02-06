package Utils;

import java.sql.*;
import java.util.Map;

public class DatabaseWorker {
    public static final String CONNECTION_URL = "jdbc:sqlite:Database/words.db";

    private static Connection getConnection(){
        try{
            return DriverManager.getConnection(CONNECTION_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Возникла ошибка при установке соединения");
            System.out.println("Соединение не может быть установлено");
            return null;
        }
    }

    public static void appendWordToDatabase(String word, int count, String website){
        Connection connection = getConnection();
        try{
            Statement statement = connection.createStatement();
            if(!isContain(word,count,website)){
                statement.executeUpdate("insert into words (word, count, website) " +
                        "VALUES ('"+word+"','"+count+"','"+website+"');");
            }

            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Возникли проблемы с добавлением элемента:");
            System.out.println(word+" "+count+" "+website);
        }
    }

    public static void appendWordToDatabase(Map<String,Integer> words, String website){
        Connection connection = getConnection();
        try{
            Statement statement = connection.createStatement();
            for(Map.Entry<String,Integer> pair:words.entrySet()){
                if(!isContain(pair.getKey(),pair.getValue(),website)){
                    statement.executeUpdate("insert into words (word, count, website) " +
                            "VALUES ('"+pair.getKey()+"','"+pair.getValue()+"','"+website+"');");
                }
            }
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Возникли проблемы с добавлением элемента из списка'");
        }
    }

    public static boolean isContain(String word, int count, String website){
        Connection connection = getConnection();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from words where word = '"+word
                    +"' AND count = '"+count+
                    "' AND website='"+website+"';");
            connection.close();
            return resultSet.next();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Проблема при проверки наличия элемента в таблице");
            return false;
        }

    }
}
