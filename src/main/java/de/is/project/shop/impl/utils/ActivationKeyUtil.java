package de.is.project.shop.impl.utils;

import java.util.UUID;

public class ActivationKeyUtil {

public static String getUniqueActivationKey() {
		
		UUID uuid = UUID.randomUUID();
		
		return MD5.makeMD5(uuid.toString());
	}
	
}
