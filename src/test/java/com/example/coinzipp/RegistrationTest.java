package com.example.coinzipp;
import org.junit.jupiter.api.*;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationTest {

        private static final String DB_URL = "jdbc:mariadb://localhost:3306/testdb";
        private static final String USER = "root";
        private static final String PASS = "Daisy2004";

        private Connection conn;

        @BeforeEach
        public void setUp() throws SQLException {
            // Establish a connection to the test database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TEMPORARY TABLE IF NOT EXISTS users (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "username VARCHAR(255) NOT NULL," +
                        "password VARCHAR(255) NOT NULL," +
                        "name VARCHAR(255) NOT NULL)");
            }
        }

        @Test
        public void testUserRegistration() throws SQLException {
            // Simulate user registration
            String username = "testuser";
            String password = "hashedpassword"; // In real scenario, use a proper hashing method
            String name = "Test User";

            // Insert the user
            String sql = "INSERT INTO users (username, password, name) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                pstmt.setString(3, name);
                pstmt.executeUpdate();
            }

            // Check if the user was added to the database
            sql = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                try (ResultSet rs = pstmt.executeQuery()) {
                    assertTrue(rs.next(), "User should be found in the database");
                    assertEquals(username, rs.getString("username"));
                    assertEquals(name, rs.getString("name"));
                    assertEquals(password, rs.getString("password"));
                }
            }
        }

        @AfterEach
        public void tearDown() throws SQLException {
            // Clean up: drop the temporary table
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("DROP TEMPORARY TABLE IF EXISTS users");
            }
            // Close the connection
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }


