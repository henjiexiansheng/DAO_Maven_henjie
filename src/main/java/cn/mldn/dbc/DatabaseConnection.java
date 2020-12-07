package cn.mldn.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Auther: henjie
 * @Date: 2020/12/6 - 12 - 06 - 13:00
 * @Description: cn.mldn.dbc  数据库的专属连接类
 * @version: 1.0
 * 本类专门负责数据库的连接与关闭操作，在实例化本类对象时就意味着要进行数据库的开发。
 * 所以在本类的构造方法要进行数据库驱动加载与数据库连接取得以及关闭操作。
 */
public class DatabaseConnection {
    private static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
    private static final String DBUSER = "root";
    private static final String DBPASS = "chixin12356";
    private Connection conn = null;


    public DatabaseConnection() {   //构造方法负责加载驱动，连接数据库。
        try {
            Class.forName(DBDRIVER);
            this.conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
        } catch (Exception e) {     //虽然此处有异常但抛出的意义不大。
            e.printStackTrace();
        }
    }

    public Connection getConnection() {   //取得数据库的连接对象，返回Connection示例化对象。
        return this.conn;
    }

    public void close()
    {
        if(this.conn != null)   //表示存在连接
        {
            try {
                this.conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
