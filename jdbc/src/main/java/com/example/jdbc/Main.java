package com.example.jdbc;


import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        printUsers();
        deleteUser(1);
        printUsers();
        insertUser(1, "Mr", "HisHome");
        printUser(1);
        editUser(1, "Him", "someWhere");
        printUser(1);
        editUserName(1, "Other");
        printUser(1);
        editUserAddress(1,  "anyWhere");
        printUsers();
        deleteUser(2);
        printUsers();
        insertUser(2, "Ms", "HerHome");
        printUsers();
    }

    public static void deleteUser(int id) {
        try (Connection conn = DriverManager.getConnection(Props.getUrl(), Props.getUser(), Props.getPass());
             Statement stmt = conn.createStatement()) {
            System.out.println("\ndeleting..." + id);

            String sql = "DELETE FROM users WHERE id = " + id + ";";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertUser(int id, String name, String address) {
        try (Connection conn = DriverManager.getConnection(Props.getUrl(), Props.getUser(), Props.getPass());
             Statement stmt = conn.createStatement()) {
            System.out.println("\ninserting..." + id);

            String sql = "INSERT INTO users VALUES (" + id + ", '" + name + "', '" + address + "')";

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editUserName(int id, String name) {
        String sql = "UPDATE users SET  name=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(Props.getUrl(), Props.getUser(), Props.getPass());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.println("\nediting..." + id);

            pstmt.setString(1, name);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editUser(int id, String name, String address) {
        String sql = "UPDATE users SET  name=?, address=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(Props.getUrl(), Props.getUser(), Props.getPass());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.println("\nediting..." + id);

            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //TODO FIx me
    public static void editUserAddress(int id, String address) {
        String sql = "UPDATE users SET  address=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(Props.getUrl(), Props.getUser(), Props.getPass());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.println("\nediting..." + id);

            pstmt.setString(1, address);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printUsers() {
        try (Connection conn = DriverManager.getConnection(Props.getUrl(), Props.getUser(), Props.getPass());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name, address FROM users")) {
            System.out.println("\nPrinting all...");

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
            System.out.println("\nPrinting..." + id);

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