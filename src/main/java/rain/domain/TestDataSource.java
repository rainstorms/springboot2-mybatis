package rain.domain;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class TestDataSource implements javax.sql.DataSource {
    @Override public Connection getConnection() throws SQLException {
        //1.JDBC所需的四个参数4个准备：user,password,url,driverClass(连接数据库所需的驱动)
        String url = "jdbc:mysql://test.go.easy-hi.com:33306/hanergyweiwei?useUnicode=true&&characterEncoding=UTF-8&connectTimeout=1000&autoReconnect=true";
        String user = "hanergyweiwei";
        String password = "AABC-2AFBB52E1E8";
        String driverClass = "com.mysql.jdbc.Driver";

        try {
            //2.加载JDBC驱动程序
            Class.forName(driverClass);
            //3.创建数据库连接:三种连接方案
            //方案一：connection = DriverManager.getConnection(jdbcUrl);
            //方案二：connection = DriverManager.getConnection(url,user,password);
            //方案三：connection = DriverManager.getConnection(url,info);
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            //log.error("Not found driver class, load driver failed.");
        }
        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
