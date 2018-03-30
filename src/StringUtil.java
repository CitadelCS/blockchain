import java.security.MessageDigest;


public class StringUtil {

	public static String encryptSha256( String data) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(data.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			
			for (int i = 0; i < hash.length; i++) {
				String hexidecimal = Integer.toHexString(0xff & hash[i]);
				if(hexidecimal.length() == 1)
					hexString.append('0');
				hexString.append(hexidecimal);
			}
			
			return hexString.toString();
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	
	
}
