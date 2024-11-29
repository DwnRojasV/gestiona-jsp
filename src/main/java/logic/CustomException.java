package logic;

public class CustomException extends Exception{
    public static final String NUMBER_SALT_ERROR = "Error [E2001]: The cost factor value entered is too high. " +
            "It is recommended to use values lower than 20 for optimal performance.\n";
    public static final String EMAIL_NULL_ERROR = "Error [E1003]: The email address cannot be null. " +
            "Please provide a valid email address.\n";
    public static final String PASSWORD_NULL_ERROR = "Error [E1003]: The password cannot be null. Please provide a valid password.\n";

    public CustomException(String message){
        super(message);
    }
}
