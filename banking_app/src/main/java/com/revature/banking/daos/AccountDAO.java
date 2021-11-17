package com.revature.banking.daos;

import com.revature.banking.models.Account;
import com.revature.banking.models.AppUser;
import com.revature.banking.util.ConnectionFactory;
import com.revature.banking.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AccountDAO implements CrudDAO<Account> {

    @Override
    public Account save(Account newAccount) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            newAccount.setAccountId(UUID.randomUUID().toString());

            String sql = "insert into accounts (account_id, balance, id) values (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newAccount.getAccountId());
            pstmt.setDouble(2, newAccount.getBalance());
            pstmt.setString(3, newAccount.getUserId());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                return newAccount;
            }

        } catch (SQLException e) {
            // TODO log this and throw our own custom exception to be caught in the service layer
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public boolean update(Account updatedObj) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            //sql here
            String sql = "update accounts " +
                    "set balance = ? " +
                    "where account_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, updatedObj.getBalance());
            pstmt.setString(2, updatedObj.getAccountId());

            pstmt.executeUpdate();
            return true;
        }catch (SQLException e) {
            // TODO log this and throw our own custom exception to be caught in the service layer
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Account findById(String accountId) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from accounts where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setAccountId(rs.getString("account_id"));
                account.setBalance(rs.getDouble("balance"));
                account.setUserId(rs.getString("id"));
                return account;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }
}
