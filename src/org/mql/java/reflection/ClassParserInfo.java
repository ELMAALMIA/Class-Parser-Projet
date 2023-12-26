package org.mql.java.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassParserInfo {

	public ClassParserInfo() {
		
	}
	
	public static String getInformation(String source) {
		
		StringBuffer stringBuffer = new StringBuffer("Informations pour la class :"+source+"\n");
		int nombreM = 0;
		int nombrep=0;
		StringBuffer stringHeritage=new StringBuffer();

		try {
			Class<?> c = Class.forName(source);
			if(c!=null) {
				Field []fields = c.getDeclaredFields();
				for (Field field : fields) {
					nombreM++;
				}
				stringBuffer.append("Nombre de propriétés : "+nombrep+"\n");
				Method []method = c.getDeclaredMethods();
				
				for (Method m : method) {
					nombrep++;
				}
				stringBuffer.append("nombre de méthodes :"+nombreM+"\n");
				Class<?> classcopy =c;
				stringHeritage.append(classcopy.getSimpleName());
				while(classcopy.getSuperclass()!=null) {
					classcopy = classcopy.getSuperclass();
					stringHeritage.insert(0, " <---- ").insert(0, classcopy.getSimpleName());
				}
				stringBuffer.append("Chaine d'héritage : "+stringHeritage+"\n");
	
				
				
				
				
				return stringBuffer.toString();		
				
			}
		
		} catch (Exception e) {

		return "";
		}
		
		
		
		
		return "";
	}

}
