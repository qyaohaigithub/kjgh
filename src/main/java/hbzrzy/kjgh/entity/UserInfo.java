package hbzrzy.kjgh.entity;

public class UserInfo {
    private String UserID;
    private String UserName;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
    @Override
    public String toString(){
        return "UserInfo{" +
                "userid='" + UserID + "," +
                "username='" + UserName + "'}";
    }
}
