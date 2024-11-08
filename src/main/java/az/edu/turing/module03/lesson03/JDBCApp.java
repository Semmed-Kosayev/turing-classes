package az.edu.turing.module03.lesson03;

import java.sql.*;
import java.util.Arrays;

public class JDBCApp {

    public static void main(String[] args) {
        emptyDatabase();
        initializeTables();
        insertDatas();
        displayTable(
                "users",
                "posts",
                "post_likes",
                "comments",
                "comment_likes",
                "comment_dislikes");
    }

    private static void insertDatas() {
        try (Connection connection = ConnectionHelper.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement("""
                    INSERT INTO users(name, surname, username, email, gender, age)
                    VALUES (?, ?, ?, ?, ?, ?);""")) {

                preparedStatement.setString(1, "Semmed");
                preparedStatement.setString(2, "Kosayev");
                preparedStatement.setString(3, "ForeverOyuncu2");
                preparedStatement.setString(4, "foreveroyuncu2@gmail.com");
                preparedStatement.setString(5, "MALE");
                preparedStatement.setInt(6, 19);
                preparedStatement.addBatch();

                preparedStatement.setString(1, "Nermine");
                preparedStatement.setString(2, "Kosayeva");
                preparedStatement.setString(3, "NaruKosa02");
                preparedStatement.setString(4, "nerminekosayeva@gmail.com");
                preparedStatement.setString(5, "FEMALE");
                preparedStatement.setInt(6, 21);
                preparedStatement.addBatch();

                int[] rowsAffectedArray = preparedStatement.executeBatch();
                int rowsAffected = Arrays.stream(rowsAffectedArray).sum();
                System.out.println(rowsAffected + " rows affected");
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement("""
                    INSERT INTO posts(created_by, description)
                    VALUES (?, ?);
                    """)) {
                preparedStatement.setInt(1, 1);
                preparedStatement.setString(2, "The first post of the account. Supporting is higly valued.");
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " rows affected.");
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement("""
                    INSERT INTO post_likes(liked_by, post_id)
                    VALUES(?, ?);
                    """)) {
                preparedStatement.setInt(1, 1);
                preparedStatement.setInt(2, 1);
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " rows affected.");
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement("""
                    INSERT INTO comments(created_by, post_id, context)
                    VALUES(?, ?, ?);
                    """)) {
                preparedStatement.setInt(1, 1);
                preparedStatement.setInt(2, 1);
                preparedStatement.setString(3, "postu beyenib, yorum atanlar sagolsun");
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " rows affected.");
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement("""
                    INSERT INTO comment_likes(liked_by, comment_id)
                    VALUES(?, ?);
                    """)) {
                preparedStatement.setInt(1, 1);
                preparedStatement.setInt(2, 1);
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " rows affected.");
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement("""
                    INSERT INTO comment_dislikes(disliked_by, comment_id)
                    VALUES(?, ?);
                    """)) {
                preparedStatement.setInt(1, 2);
                preparedStatement.setInt(2, 1);
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " rows affected.");
            }


            System.out.println();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();

            try (Connection connection = ConnectionHelper.getConnection()) {
                connection.rollback();
                System.out.println("Transaction has been rolled back due error");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void displayTable(final String... tableNames) {
        try (Connection connection = ConnectionHelper.getConnection()) {
            for (String tableName : tableNames) {
                String query = "SELECT * FROM %s".formatted(tableName);

                try (Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(query)) {

                    System.out.printf("%s TABLE:\n", tableName);

                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    for (int i = 1; i <= columnCount; i++)
                        System.out.print(metaData.getColumnLabel(i) + "\t");
                    System.out.println();

                    while (resultSet.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            System.out.print(resultSet.getString(i) + "\t");
                        }
                        System.out.println();
                    }
                    System.out.println();

                } catch (SQLException e) {
                    System.out.println("Error querying table: " + tableName);
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }
    }

    public static void initializeTables() {
        try (Connection connection = ConnectionHelper.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS users(
                    id                  bigserial,
                    created_at          timestamptz(3)                                                  DEFAULT CURRENT_TIMESTAMP,
                    updated_at          timestamptz(3)                                                  DEFAULT CURRENT_TIMESTAMP,
                    updated_by          bigint,
                    email               varchar(50) UNIQUE NOT NULL,
                    name                varchar(20) NOT NULL,
                    surname             varchar(30) NOT NULL,
                    username            varchar(40) NOT NULL UNIQUE,
                    gender              varchar(6) NOT NULL CHECK (gender IN ('MALE', 'FEMALE', 'OTHER')),
                    age                 integer CHECK(age > 0),
                    status              varchar(8) CHECK (status IN ('ACTIVE', 'INACTIVE'))    DEFAULT 'ACTIVE',
                    PRIMARY KEY(ID),
                    CONSTRAINT fk_user FOREIGN KEY(updated_by) REFERENCES users(id));
                    
                    CREATE TABLE IF NOT EXISTS posts(
                    id                  bigserial,
                    created_at          timestamptz(3)                          DEFAULT CURRENT_TIMESTAMP,
                    updated_at          timestamptz(3)                          DEFAULT CURRENT_TIMESTAMP,
                    created_by          bigint,
                    updated_by          bigint,
                    description         varchar(255),
                    PRIMARY KEY(id),
                    CONSTRAINT fk_user FOREIGN KEY(created_by) REFERENCES users(id),
                    CONSTRAINT fk_user2 FOREIGN KEY(updated_by) REFERENCES users(id));
                    
                    CREATE TABLE IF NOT EXISTS comments(
                    id                  bigserial,
                    created_at          timestamptz(3)                          DEFAULT CURRENT_TIMESTAMP,
                    updated_at          timestamptz(3)                          DEFAULT CURRENT_TIMESTAMP,
                    created_by          bigint,
                    updated_by          bigint,
                    post_id             bigint,
                    context             varchar(255),
                    PRIMARY KEY(id),
                    CONSTRAINT fk_user FOREIGN KEY(created_by) REFERENCES users(id),
                    CONSTRAINT fk_user2 FOREIGN KEY(updated_by) REFERENCES users(id),
                    CONSTRAINT fk_post FOREIGN KEY(post_id) REFERENCES posts(id));
                    
                    CREATE TABLE IF NOT EXISTS post_likes(
                    liked_by            bigint,
                    post_id             bigint,
                    PRIMARY KEY(liked_by, post_id));
                    
                    CREATE TABLE IF NOT EXISTS comment_likes(
                    liked_by            bigint,
                    comment_id          bigint,
                    PRIMARY KEY(liked_by, comment_id));
                    
                    CREATE TABLE IF NOT EXISTS comment_dislikes(
                    disliked_by            bigint,
                    comment_id          bigint,
                    PRIMARY KEY(disliked_by, comment_id));""");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean emptyDatabase() {
        try (Connection connection = ConnectionHelper.getConnection()) {
            Statement statement = connection.createStatement();
            return statement.execute("""
                    DROP SCHEMA public cascade;
                    CREATE SCHEMA public;
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
