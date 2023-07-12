
package za.co.groupA.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import za.co.groupA.Dao.DBPoolManagerBasic;

@WebListener
public class ContextListener implements ServletContextListener{
  @Override
  public void contextInitialized(ServletContextEvent sce){
    ServletContext sc = sce.getServletContext();
    String driver = sc.getInitParameter("driver");
    String url = sc.getInitParameter("url");
    String user_name = sc.getInitParameter("user_name");
    String password = sc.getInitParameter("password");
    String database = sc.getInitParameter("database");
    DBPoolManagerBasic db = new DBPoolManagerBasic(driver, url + database, user_name, password);
    System.out.println("DB Pool Manager Loaded ---: " + (db != null));
    sc.setAttribute("dbconn", db);
    System.out.println("Driver loaded in Context ---");
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce){
    ServletContext sc = sce.getServletContext();
    //DBPoolManager db = (DBPoolManager) sc.getAttribute("dbconn");
    DBPoolManagerBasic db = (DBPoolManagerBasic) sc.getAttribute("dbconn");
    if(db != null){
      db.closePool();
    }
  }
}
