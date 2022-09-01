package serialization_deserialization_example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class SerializeAndDeserializeClass implements Serializable {
	private static final long serialVersionUID = 1L;
	int normalIntVar;
	String normalStringVar;
	transient int transientIntVar;
	transient String transientStringVar;
	static int staticIntVar;
	static String staticStringVar;

	public SerializeAndDeserializeClass(int normalIntVar, String normalStringVar, int transientIntVar,
			String transientStringVar) {
		super();
		this.normalIntVar = normalIntVar;
		this.normalStringVar = normalStringVar;
		this.transientIntVar = transientIntVar;
		this.transientStringVar = transientStringVar;
	}

	@Override
	public String toString() {
		return "SerializeAndDeserializeClass [normalIntVar=" + normalIntVar + ", normalStringVar=" + normalStringVar
				+ ", transientIntVar=" + transientIntVar + ", transientStringVar=" + transientStringVar
				+ ", staticIntVar=" + staticIntVar + ", staticStringVar=" + staticStringVar + "]";
	}

}

public class SerializationDeserializationExample {
	public static void main(String[] args) {
		SerializationDeserializationExample example = new SerializationDeserializationExample();
		String currentDirectory = System.getProperty("user.dir");
		String packagePath = "src" + File.separator + "serialization_deserialization_example";
		String fileName = "serialize_deserialize.ser";
		String fullPath = currentDirectory + File.separator + packagePath + File.separator + fileName;
		example.serialize(fullPath);
		example.deserialize(fullPath);

	}

	private void serialize(String filePath) {
		SerializeAndDeserializeClass objectToSerialize = new SerializeAndDeserializeClass(1, "input1", 2, "input2");
		SerializeAndDeserializeClass.staticIntVar = 3;
		SerializeAndDeserializeClass.staticStringVar = "input3";
		System.out.println("Before Serialization: ");
		System.out.println(objectToSerialize.toString());
		try {
			FileOutputStream file = new FileOutputStream(filePath);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(objectToSerialize);
			out.close();
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void deserialize(String fullPath) {
		try {
			FileInputStream fis = new FileInputStream(fullPath);
			ObjectInputStream ois = new ObjectInputStream(fis);
			SerializeAndDeserializeClass deserializedObject = (SerializeAndDeserializeClass) ois.readObject();
			System.out.println("After Deserialization: ");
			System.out.println(deserializedObject.toString());
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
