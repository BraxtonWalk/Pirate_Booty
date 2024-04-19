public class User {
    private static String userName;
    private static String currency;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        User.userName = userName;
    }

    public static String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        User.currency = currency;
    }
}
