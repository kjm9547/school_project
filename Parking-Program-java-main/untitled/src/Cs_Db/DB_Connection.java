package Cs_Db;

import java.sql.*;

public class DB_Connection {
    private static int cnt = 0;
    private Connection connection;
    private ResultSet rs;
    private Statement st;
    private String url = "jdbc:mysql://localhost:3306/car_surprise?serverTimezone=UTC";
    private String user = "root";
    private String password = "0714";
    private String driverName = "com.mysql.cj.jdbc.Driver";

    /** DB 연결 */
    public void connect() {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, user, password);
            st = connection.createStatement();
            if (cnt == 0) {
                System.out.println("[Connection successful!]");
                cnt++;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("[로드 오류]\n" + e.getStackTrace());
        } catch (SQLException e) {
            System.out.println("[연결 오류]\n" + e.getStackTrace());
        }
    }

    /** sign_Dialog의 회원가입 버튼 */

    /** 데이터베이스에 정보 저장 */

    public boolean login(String combo_item, String pw) {
        boolean id_c = false;
        boolean pw_c = false;
        String table_pw="";
        try {
            String SQL = "SELECT myPw FROM login WHERE location = '"+combo_item+"';";
            System.out.println(SQL);
            rs = st.executeQuery(SQL);
            if(rs.next()) {
                System.out.println(rs.getString("myPw"));
                table_pw = rs.getString("myPw");
                if(table_pw.equals(pw)){ ;return true;}
                else { return false;}
            }

            else{return false;}
        }
        catch(Exception e) {
            System.out.println("[데이터베이스 검색 오류] : " + e.getMessage());
            System.out.println("로그인 실패");
            return false;
        }
    }

    /** Login_GUI의 로그인 버튼 */

    /**
     * 1. 가져온 id가 일치하는지, 2. 가져온 pw가 일치하는지 boolean 체크하고 둘다 true일 시 로그인(return true)
     */

}