package json_conversion;

import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

class Student {
	private int id;
	private String name;
	private boolean isEnrolled;
	private int[] marks;

	public Student() {
		super();
	}

	public Student(int id, String name, boolean isEnrolled, int[] marks) {
		super();
		this.id = id;
		this.name = name;
		this.isEnrolled = isEnrolled;
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", isEnrolled=" + isEnrolled + ", marks="
				+ Arrays.toString(marks) + "]";
	}
}

public class JsonConversionExample {

	private Student getObject() {
		return new Student(1, "Student1", true, new int[] { 1, 2, 3, 4 });
	}

	public static void main(String[] args) {
		JsonConversionExample jsonConversionExample = new JsonConversionExample();
		Student object = jsonConversionExample.getObject();
		viaGson(object);
		viaJackson(object);
	}

	private static void viaGson(Student object) {
		System.out.println("** Using GSON: ");
		Gson gson = new Gson();
		String jsonString = gson.toJson(object, Student.class);
		System.out.println(jsonString);
		Student objectFromString = gson.fromJson(jsonString, Student.class);
		System.out.println(objectFromString);
	}

	private static void viaJackson(Student object) {
		System.out.println("** Using Jackson: ");
		ObjectMapper objectMapper = new ObjectMapper();
		// either the field should be public or getter/setter should be public,
		// otherwise we need to use below line
		objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		String jsonString = null;
		try {
			jsonString = objectMapper.writeValueAsString(object);
			System.out.println(jsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		try {
			Student objectFromString = objectMapper.readValue(jsonString, Student.class);
			System.out.println(objectFromString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
