package org.mql.java.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class ClassParser {
	private String source;

	public ClassParser() {

	}

	public ClassParser(String source) {
		this.source = source;
	}

	public static String toString(String source) {
		StringBuffer data = new StringBuffer();
		if ("".equals(source))
			return "invalid source " + source;

		try {

			Class<?> c = Class.forName(source);

			if (c == null)
				return "Class not found " + source;
			int modifier = c.getModifiers();
			data.append("Compiled from \"").append(c.getSimpleName() + ".java").append("\"\n");
			data.append(Modifier.toString(modifier));
			String type;
			if (c.isInterface()) {
				type = "";
			} else {
				type = " class ";
			}
			data.append(type + " " + c.getTypeName() + " ");

			Class<?> superClass = c.getSuperclass();
			if (superClass != null) {
				data.append(" extends ").append(superClass.getName());
			}

			Class<?> interfaces[] = c.getInterfaces();
			if (interfaces.length > 0) {
				data.append(" implements ");
				for (int i = 0; i < interfaces.length; i++) {
					data.append(interfaces[i].getName());
					if (i < interfaces.length - 1) {
						data.append(", ");
					}
				}
			}
			data.append("{\n");

			Method[] methods = c.getDeclaredMethods();
			for (Method method : methods) {
				data.append("  ").append(Modifier.toString(method.getModifiers())).append(" ")
						.append(method.getReturnType().getSimpleName()).append(" ").append(method.getName())
						.append("(");

				Parameter[] parameters = method.getParameters();
				for (int i = 0; i < parameters.length; i++) {
					data.append(parameters[i].getType().getSimpleName()).append(" ").append(parameters[i].getName());
					if (i < parameters.length - 1) {
						data.append(", ");
					}
				}
				Class<?>[] exceptions = method.getExceptionTypes();
				if (exceptions.length > 0) {
					data.append(" throws ");
					for (int i = 0; i < exceptions.length; i++) {
						data.append(exceptions[i].getSimpleName());
						if (i < exceptions.length - 1) {
							data.append(", ");
						}
					}
				}

				data.append(") ;\n");
			}

			Field[] fields = c.getDeclaredFields();
			for (Field field : fields) {
				data.append("  ").append(Modifier.toString(field.getModifiers())).append(" ")
						.append(field.getType().getSimpleName()).append(" ").append(field.getName()).append(";\n");
			}

			Class<?>[] innerClasses = c.getDeclaredClasses();
			for (Class<?> innerClass : innerClasses) {
				data.append("\n").append(new ClassParser(innerClass.getName())).append("\n");
			}

			Constructor<?>[] constructors = c.getDeclaredConstructors();
			for (Constructor<?> constructor : constructors) {
				data.append("  ").append(Modifier.toString(constructor.getModifiers())).append(" ")
						.append(c.getSimpleName()).append("(");

				Parameter[] parameters = constructor.getParameters();
				for (int i = 0; i < parameters.length; i++) {
					data.append(parameters[i].getType().getSimpleName()).append(" ").append(parameters[i].getName());
					if (i < parameters.length - 1) {
						data.append(", ");
					}
				}

				data.append(") ;\n");
			}

			data.append("}\n");
		} catch (Exception e) {

			return "";
		}

		return data.toString();
	}

}
