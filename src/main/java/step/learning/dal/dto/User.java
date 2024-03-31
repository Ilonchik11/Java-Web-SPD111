package step.learning.dal.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String email;
    private String avatar;
    private String salt;
    private String derivedKey;
    private Date registeredDate;
    private Date deletedDate;

    //factory
    public static User formResultSet(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(UUID.fromString(resultSet.getString("user_id")));
            user.setName(resultSet.getString("user_name"));
            user.setEmail(resultSet.getString("user_email"));
            user.setAvatar(resultSet.getString("user_avatar"));
            user.setSalt(resultSet.getString("user_salt"));
            user.setDerivedKey(resultSet.getString("user_dk"));
            Timestamp timestamp;
            timestamp = resultSet.getTimestamp("user_created");
            if(timestamp != null) {
                user.setRegisteredDate(new Date(timestamp.getTime()));
            }
            timestamp = resultSet.getTimestamp("user_deleted");
            if(timestamp != null) {
                user.setDeletedDate(new Date(timestamp.getTime()));
            }
            return user;
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getDerivedKey() {
        return derivedKey;
    }

    public void setDerivedKey(String derivedKey) {
        this.derivedKey = derivedKey;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }
}
