package com.example.jdbc;


import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        printUsers();
        deleteUser(1);
        printUsers();
        insertUser(1, "Mr", "HisHome");
//        printUser(1);
//        editUser(1, "Him", "someWhere");
//        editUserName(1, "Other");
//        printUser(1);
//        editUserAddress(1,  "anyWhere");
        printUsers();
        deleteUser(2);
        insertUser(2, "Ms", "HerHome");
        printUsers();
    }

    public static void deleteUser(int id) {
        try (Connection conn = DriverManager.getConnection(Props.getUrl(), Props.getUser(), Props.getPass());
             Statement stmt = conn.createStatement()) {
            System.out.println("deleting..." + id);

            String sql = "DELETE FROM users WHERE id = " + id + ";";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertUser(int id, String name, String address) {
        try (Connection conn = DriverManager.getConnection(Props.getUrl(), Props.getUser(), Props.getPass());
             Statement stmt = conn.createStatement()) {
            System.out.println("inserting..." + id);

            String sql = "INSERT INTO users VALUES (" + id + ", '" + name + "', '" + address + "')";

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //TODO FIx me
    public static void editUserName(int id, String name) {
        try (Connection conn = DriverManager.getConnection(Props.getUrl(), Props.getUser(), Props.getPass());
             Statement stmt = conn.createStatement()) {
            System.out.println("editing..." + id);

            String sql = "UPDATE users SET ( id=" + id + ", name=" + name + " WHERE id=" + id + ")";

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //TODO FIx me
    public static void editUser(int id, String name, String address) {
        try (Connection conn = DriverManager.getConnection(Props.getUrl(), Props.getUser(), Props.getPass());
             Statement stmt = conn.createStatement()) {
            System.out.println("editing..." + id);

            String sql = "UPDATE users SET ( id=" + id + ", name=" + name + ", address=" + address + " WHERE id=" + id + ")";

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //TODO FIx me
    public static void editUserAddress(int id, String address) {
        try (Connection conn = DriverManager.getConnection(Props.getUrl(), Props.getUser(), Props.getPass());
             Statement stmt = conn.createStatement()) {
            System.out.println("editing..." + id);

            String sql = "UPDATE users SET ( id=" + id + ", address=" + address + " WHERE id=" + id + ")";

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printUsers() {
        try (Connection conn = DriverManager.getConnection(Props.getUrl(), Props.getUser(), Props.getPass());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name, address FROM users")) {
            System.out.println("Printing all...");

            while (rs.next()) {
                print(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printUser(int id) {
        try (Connection conn = DriverManager.getConnection(Props.getUrl(), Props.getUser(), Props.getPass());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name, address FROM users where id = " + id + ";")) {
            System.out.println("Printing..." + id);

            while (rs.next()) {
                print(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void print(ResultSet rs) throws SQLException {
        System.out.print("ID: " + rs.getInt("id"));
        System.out.print(", Name: " + rs.getString("name"));
        System.out.println(", Address: " + rs.getString("address"));
    }

}