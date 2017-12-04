package com.weblogin.api.repositories;

import com.weblogin.beans.CredentialBean;
import com.weblogin.database.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AuthorizationRepository {

    public CredentialBean getUserCredentials(String userName) {
        CredentialBean credentialBean = new CredentialBean();
        PreparedStatement ps = null;
        try (Connection connection = DatabaseUtil.getInstance().getConnection()) {
            ps = connection.prepareStatement
                    ("SELECT hashed_pwd, email FROM user WHERE fname = ?;");
            ps.setString(1, userName);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                credentialBean.setHash(rs.getString("hashed_pwd"));
                credentialBean.setSalt(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return credentialBean;
    }
}
