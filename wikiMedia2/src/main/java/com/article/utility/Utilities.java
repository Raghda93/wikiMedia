package main.java.com.article.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;

/**
 * Contains a utilities methods that can be used from all the project.
 *
 */
public class Utilities {

	/**
	 * Returns true if the passed string is null or empty.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmptyString(String str) {

		if (str != null && !str.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns true if the passed collection is null or empty.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmptyCollection(Collection c) {

		if (c != null && !c.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns true if the passed map is null or empty.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmptyMap(Map map) {

		if (map != null && !map.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Escape some special characters like ( \ , ' , " )
	 * @param inputString
	 * @return
	 */
	public static String escapeMetaCharacters(String inputString){
	    final String[] metaCharacters = {"\\","\"", "\'"};

	    for (int i = 0 ; i < metaCharacters.length ; i++){
	        if(inputString.contains(metaCharacters[i])){
	            inputString = inputString.replace(metaCharacters[i],"'"+metaCharacters[i]);
	        }
	    }
	    return inputString;
	}
}
