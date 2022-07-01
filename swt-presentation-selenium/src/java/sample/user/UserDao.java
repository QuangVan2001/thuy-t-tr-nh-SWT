/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.user.UserDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Xqy
 */
public class UserDao {

    private static final String LOGIN = "SELECT userID, fullname, roleID, status FROM tblUsers WHERE userID=? AND password =?";
    private static final String SEARCH = "SELECT userID,fullName,roleID FROM tblUsers WHERE fullName like ? ";

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, userID);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String fullname = rs.getString("fullname");
                    String roleID = rs.getString("roleID");
                    Boolean status = rs.getBoolean("status");
                    user = new UserDTO(userID, fullname, roleID, "", status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return user;
    }

//    public List<UserDTO> getListUser(String search) throws SQLException, ClassNotFoundException {
//        List<UserDTO> list = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement ptm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                ptm = conn.prepareStatement(SEARCH);
//                ptm.setString(1, "%" + search + "%");
//                rs = ptm.executeQuery();
//                while (rs.next()) {
//                    String userID = rs.getString("userID");
//                    String fullName = rs.getString("fullName");
//                    String roleID = rs.getString("roleID");
//                    String password = "***";
//                    list.add(new UserDTO(userID, fullName, roleID, password));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ptm != null) {
//                ptm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return list;
//    }
}
