package org.jluc.ctr.tools.calendrier.tools.bdd;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseService {

    /**
     * Connexion à la base.
     */
    private Connection mConnection;

    private Logger mLogger = LogManager.getLogger(DatabaseService.class);

    /**
     * Constructeur.
     * 
     * @param databasePath
     *            Chemin du fichier de base de données
     * @throws ClassNotFoundException
     *             Erreur de chargement de la library sqlite
     * @throws SQLException
     *             Erreur SQL
     * @throws IOException
     *             Erreur d'accés au fichier
     */
    public DatabaseService(final String databasePath) throws ClassNotFoundException, SQLException, IOException {
        Class.forName(org.sqlite.JDBC.class.getName());
        File dbFile = new File(databasePath);
        if (!dbFile.exists()) {
            if (!dbFile.getParentFile().exists()) {
                if (dbFile.getParentFile().mkdirs()) {
                    mLogger.debug("Chemin de la base de données créé ==> " + dbFile.getParentFile().getAbsolutePath());
                    dbFile.createNewFile();
                } else {
                    mLogger.error("Chemin de la base de données NON créé ==> " + dbFile.getParentFile().getAbsolutePath());
                }
            }
        }
        startService(databasePath);
    }

    public final void dropTable(final String tableName) throws SQLException {
        mLogger.debug("dropTable : " + "drop table if exists " + tableName);
        mConnection.createStatement().execute("drop table if exists " + tableName);
    }

    public final void createTable(final String tableName, final String attributesDefinition) throws SQLException {
        mLogger.debug("createTable : " + "create table " + tableName + attributesDefinition);
        mConnection.createStatement().executeUpdate("create table " + tableName + attributesDefinition);
    }

    public final void insertData(final String tableName, final String sqlColNames, final String sqlValues, List<String> types, List<Object> values) throws SQLException {
        String sql = "insert into " + tableName + "(" + sqlColNames + ") values(" + sqlValues + ")";

        try {
            PreparedStatement pstmt = mConnection.prepareStatement(sql);

            // set the corresponding param
            for (int i = 0; i < values.size(); i++) {
                String type = types.get(i);
                Object value = values.get(i);
                switch (type) {
                    case "string":
                        if (value != null)
                            // pstmt.setString(i + 1, ((String)
                            // value).startsWith("'") ? (String) value : "'" +
                            // (String) value + "'");
                            pstmt.setString(i + 1, (String) value);
                        else
                            pstmt.setString(i + 1, null);
                        break;
                    case "double":
                        pstmt.setDouble(i + 1, (double) value);
                        break;
                    case "date":
                        if (value != null)
                            pstmt.setDouble(i + 1, ((Date) value).getTime());
                        else
                            pstmt.setDouble(i + 1, 0);
                        break;
                    case "integer":
                        pstmt.setInt(i + 1, (int) value);
                        break;
                    default:
                        mLogger.error("Type non pris en charge ==> " + type);
                }
            }
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            mLogger.error("Erreur durant l'insert" + tableName + "... ==> " + sql, e);
        }
    }

    public final void updateData(final String tableName, final String sqlSet, String whereClause, List<String> types, List<Object> values) throws SQLException {
        String sql = "UPDATE " + tableName + " SET " + sqlSet + " WHERE " + whereClause;

        try {
            PreparedStatement pstmt = mConnection.prepareStatement(sql);

            // set the corresponding param
            for (int i = 0; i < values.size(); i++) {
                String type = types.get(i);
                Object value = values.get(i);
                switch (type) {
                    case "string":
                        if (value != null)
                            // pstmt.setString(i + 1, ((String)
                            // value).startsWith("'") ? (String) value : "'" +
                            // (String) value + "'");
                            pstmt.setString(i + 1, (String) value);
                        else
                            pstmt.setString(i + 1, null);
                        break;
                    case "double":
                        pstmt.setDouble(i + 1, (double) value);
                        break;
                    case "integer":
                        pstmt.setInt(i + 1, (int) value);
                        break;
                    default:
                        mLogger.error("Type non pris en charge ==> " + type);
                }
            }
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            mLogger.error("Erreur durant l'update" + tableName + "... ==> " + sql, e);
        }
    }

    public final List<Map<String, Object>> executeSelectFrom(final String tableName) throws SQLException {
        mLogger.debug("executeSelectFrom : " + "select * from " + tableName);
        Statement statement = mConnection.createStatement();
        List<Map<String, Object>> results = null;
        try {
            results = map(statement.executeQuery("select * from " + tableName));
        } catch (SQLException e) {
            throw e;
        } finally {
            statement.close();
        }
        return results;
    }

    public final List<Map<String, Object>> executeSelectFromWhere(final String tableName, final String whereClause) throws SQLException {
        mLogger.debug("executeSelectFromWhere : " + "select * from " + tableName + " where " + whereClause);
        Statement statement = mConnection.createStatement();
        List<Map<String, Object>> results = null;
        try {
            results = map(statement.executeQuery("select * from " + tableName + " where " + whereClause));
        } catch (SQLException e) {
            throw e;
        } finally {
            statement.close();
        }
        return results;
    }

    private static List<Map<String, Object>> map(ResultSet rs) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        try {
            if (rs != null) {
                ResultSetMetaData meta = rs.getMetaData();
                int numColumns = meta.getColumnCount();
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<String, Object>();
                    for (int i = 1; i <= numColumns; ++i) {
                        String name = meta.getColumnName(i);
                        Object value = rs.getObject(i);
                        row.put(name, value);
                    }
                    results.add(row);
                }
            }
        } finally {
            rs.close();
        }
        return results;
    }

    public final void deleteDataWhere(final String tableName, final String whereClause) throws SQLException {
        mLogger.debug("deleteDataWhere : " + "delete from " + tableName + " where " + whereClause);
        mConnection.createStatement().executeUpdate("delete from " + tableName + " where " + whereClause);
    }

    public final void endService() throws SQLException {
        if (mConnection != null) {
            mConnection.close();
        }
    }

    public final void startService(String databasePath) throws SQLException { // create
                                                                              // a
                                                                              // database
                                                                              // connection
        mConnection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
    }

    public void beginTransaction() throws SQLException {
        mConnection.setAutoCommit(false);

    }

    public void endTransaction() throws SQLException {
        mConnection.commit();
    }
}
