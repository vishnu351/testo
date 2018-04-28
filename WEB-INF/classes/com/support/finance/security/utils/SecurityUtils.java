package com.support.finance.security.utils;

import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.web.util.HtmlUtils;

import com.support.finance.beans.CustomUser;
import com.support.finance.beans.Role;





public class SecurityUtils {
	
	private static final Md5PasswordEncoder INSTANCE = new Md5PasswordEncoder();

	public static String decrypt(String encryptedData, String keyStr) {
        final String salt = encryptedData.substring(0, 32);
        final String encryptedPart = encryptedData.substring(32);
        final TextEncryptor textEncryptor = Encryptors.text(keyStr, salt);
        return textEncryptor.decrypt(encryptedPart);
	}
	
	
    public static String passwordEncoder(String rawPassword) {
        return getPasswordEncoderInstance().encodePassword(rawPassword, null);
    }

    public static boolean checkPassword(String rawPassword, String encodedPassword) {
    	boolean result = false;
        if (passwordEncoder(rawPassword).equals(encodedPassword)) {
            result = true;
        }
        return result;
    }

    private static Md5PasswordEncoder getPasswordEncoderInstance() {
        return INSTANCE;
    }
    
	public static CustomUser convertUserMasterintoBean(List usersList){
		CustomUser customUser=null;
		@SuppressWarnings("rawtypes")
		Iterator iterator = usersList.iterator();
		while(iterator.hasNext()) {
			Object[] object = (Object[]) iterator.next();
			customUser = new CustomUser();
			customUser.setFirstName(object[0]+"");
			customUser.setLastName("");
			customUser.setUsername(object[1]+"");
			customUser.setPassword(object[2]+"");
			Role r = new Role();
	        r.setName(object[3]+"");
	        List<Role> roles = new ArrayList<Role>();
	        roles.add(r);
	        customUser.setAuthorities(roles);
		}
		return customUser;
	}
	
	public static boolean isHtml(String input) {
        boolean isHtml = false;
        if (input != null) {
            if (!input.equals(HtmlUtils.htmlEscape(input))) {
                isHtml = true;
            }
        }
        return isHtml;
    }
	
	
	
	
}
