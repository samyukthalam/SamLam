package LibraryFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ReadOrWriteDataFiles.ReadWriteDataPropertyFiles;

public class DBConnection {
	public static List<HashMap<String, String>> getMultipleResultsAsList(String query) {
        Statement statement = null;
        Connection conn = null;
        ResultSet res = null;
        List<HashMap<String, String>> rows = new ArrayList<HashMap<String,String>>(); 
        
        try {
               Class.forName(ReadWriteDataPropertyFiles.FileRead("GlobalSettings.properties","jdbcDriver"));
               conn = DriverManager.getConnection(
            		   ReadWriteDataPropertyFiles.FileRead("GlobalSettings.properties","jdbcDriverUrl"),
            		   ReadWriteDataPropertyFiles.FileRead("GlobalSettings.properties","DatabaseUsername"),
            		   ReadWriteDataPropertyFiles.FileRead("GlobalSettings.properties","DatabasePassword"));
               statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
               System.out.println(" query :::"+query);
               res = statement.executeQuery(query);
                                   
               ResultSetMetaData rsmd = res.getMetaData();
               int rowCount = rsmd.getColumnCount();

               while(res.next()) {
                     HashMap<String, String> rowMap = new HashMap<String, String>();
                     for (int i = 0; i < rowCount; i++) {
                            String colName = rsmd.getColumnName(i + 1);
                            rowMap.put(colName, res.getString(colName));
                            
                     }
                     rows.add(rowMap);
               }
               statement.close();
               res.close();
               conn.close();
        } catch (SQLException e) {
               e.printStackTrace();
        } catch (ClassNotFoundException e) {
               e.printStackTrace();
        } finally {
               try {
                     if (null != conn)
                            conn.close();
                     if (null != statement)
                            statement.close();
                     if (null != res)
                            res.close();
               } catch (Exception e) {
                     
               }
        }

        return rows;
 }

	
 /**
  * @author 
 * This method is used to fetch one record as Map which contains the column name as key
 */
 public static List<String> getResultList(String query) {
        Statement statement = null;
        Connection conn = null;
        ResultSet res = null;
        List<String> list = new ArrayList<String>();
        try {
        	Class.forName(ReadWriteDataPropertyFiles.FileRead("GlobalSettings.properties","jdbc.driver"));
            conn = DriverManager.getConnection(
         		   ReadWriteDataPropertyFiles.FileRead("GlobalSettings.properties","jdbc.driver.url"),
         		   ReadWriteDataPropertyFiles.FileRead("GlobalSettings.properties","DatabaseUsername"),
         		   ReadWriteDataPropertyFiles.FileRead("GlobalSettings.properties","DatabasePassword"));
               statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
               res = statement.executeQuery(query);
               ResultSetMetaData rsmd = res.getMetaData();
               int rowCount = rsmd.getColumnCount();
               
               while (res.next()) {
                     list.add(res.getString(1));
               }
               statement.close();
               res.close();
               conn.close();
        } catch (SQLException e) {
               e.printStackTrace();
        } catch (ClassNotFoundException e) {
               e.printStackTrace();
        } finally {
               try {
                     if (null != conn)
                            conn.close();
                     if (null != statement)
                            statement.close();
                     if (null != res)
                            res.close();
               } catch (Exception e) {
                    
               }
        }

        return list;
 }
}
