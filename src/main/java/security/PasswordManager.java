package security;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Fare on 20.07.2016.
 */
public class PasswordManager {

    public String getHexString (String password){
        return DigestUtils.sha256Hex(password);
    }

    public boolean comparePasswordHex (String existingPassword, String inputPassword){
        return existingPassword.equals(getHexString(inputPassword));
    }
}
