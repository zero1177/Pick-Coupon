package hackathon.pickCoupon;

public class Test {

    @org.junit.jupiter.api.Test
    void jdbcSmoke() throws Exception {
        var url = "jdbc:mysql://127.0.0.1:3306/pickcoupon?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8&allowPublicKeyRetrieval=true";
        try (var c = java.sql.DriverManager.getConnection(url, "young", "test1234")) {
            System.out.println("JDBC OK = " + (c != null && !c.isClosed()));
        }
    }
}
