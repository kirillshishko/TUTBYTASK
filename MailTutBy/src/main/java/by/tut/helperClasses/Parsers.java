package by.tut.helperClasses;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.sql.*;

public class Parsers {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private static final String url = "jdbc:mysql://localhost:3306/testemails";
    private static final String user = "root";
    private static final String password = "mypass";


    private static String[] ParseXML() {

        String[] testEmails = new String[21];
        try {
            File inputFile = new File("testEmails.xml");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("user");
            int a = 0;

            for (int i = 0; i < 20; i += 2) {
                Node node = nList.item(a++);
                Element eElement = (Element) node;
                testEmails[i] = eElement.getElementsByTagName("username").item(0).getTextContent();
                testEmails[i+1] = eElement.getElementsByTagName("password").item(0).getTextContent();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return testEmails;
    }

    private static String[] parseCSV() {
        String[] testEmails = new String[20];

        String csvFile = "testEmails.csv";

        BufferedReader br = null;

        String line;

        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                testEmails = line.split(cvsSplitBy);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return testEmails;
    }

    private static String[] parseSQL() {

        String query = "select username, password from users";
        String testEmails[] =  new String[20];
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            for (int i = 0; resultSet.next(); i += 2) {
                String usersLogin = resultSet.getString(1);
                testEmails[i] = usersLogin;
                String usersPassword = resultSet.getString(2);
                testEmails[i + 1] = usersPassword;
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException se) {
            }
            try {
                statement.close();
            } catch (SQLException se) {
            }
            try {
                resultSet.close();
            } catch (SQLException se) {
            }
        }

        return testEmails;
    }

    public enum ParserTypes {xml, csv, sql}

    public static String[] recieveDataFrom(ParserTypes parser) {
        String[] data = null;

        if (parser == ParserTypes.xml) {
            data = ParseXML();

        } else if (parser == ParserTypes.csv) {
            data = parseCSV();
        }
        if(parser == ParserTypes.sql){
            data = parseSQL();
        }
        return data;
    }
}

