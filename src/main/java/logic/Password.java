package logic;

import org.mindrot.jbcrypt.BCrypt;

public class Password {

    public static String encryptPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public static String encryptPassword(String plainTextPassword, int salt) throws CustomException {
        if(salt > 20){
            throw new CustomException(CustomException.NUMBER_SALT_ERROR);
        }
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(salt));
    }

    public static Boolean validatePassword(String plainTextPassword, String hashedPassword){
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }


}
