
# Serialization and Deserialization

## About

- Converting an object to byte stream is Serialization.
- Converting a byte stream to object is Deserialization.

## Need
- Suppose, we have an object with some values in it, and we want to save it, or to send that object to anywhere over the network.
- So we can't store it directly, we can store it by converting it into the byte stream, then we are able to store.
- Byte stream is platform independent.

## How we achieve that
- The class which we want to Serialize or Deserialize should implement the java.io.Serializable.
- java.io.Serializable is a marker interface, means it is not having any method declaration.
- For Serialization: 
```
FileOutputStream file = new FileOutputStream(filename);
ObjectOutputStream out = new ObjectOutputStream(file);
// For Serialization
out.writeObject(object);
out.close();
file.close();
```
- For Deserialization:
```
FileInputStream file = new FileInputStream(filename);
ObjectInputStream in = new ObjectInputStream(file);
// For Deserialization
object1 = (Demo)in.readObject();
in.close();
file.close();
```

## Important Notes
- If parent class is implementing Serializable, no need to do in child class, vice versa is not true.
- Associated objects must implement Serializable.
- Only non-static data members can be persisted.
- Transient and static data members can't be persisted.
- We don't call constructor of object while Deserialization.
## SerialVersionUID: 
- Serialization associates the version with each Serializable class. 
- That can be used while Deserialization. 
- So that it will check Serialized Class are compatible with Deserialized class, otherwise it will throw InvalidClassException.
- Either, we can declare explicitly, otherwise serialization will generate by itself at runtime based on various aspect.
- For explicit declaration, it should follow this signature.
```
ANY-ACCESS-MODIFIER static final long serialVersionUID= ANY_LONG_VALUE; // 42L
```

## Example
### Code
```
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

```

### Output
- Here the static fields doesn't change because they depend on class level, not on serialized file.
```
Before Serialization: 
SerializeAndDeserializeClass [normalIntVar=1, normalStringVar=input1, transientIntVar=2, transientStringVar=input2, staticIntVar=3, staticStringVar=input3]
After Deserialization: 
SerializeAndDeserializeClass [normalIntVar=1, normalStringVar=input1, transientIntVar=0, transientStringVar=null, staticIntVar=3, staticStringVar=input3]
```

- File created inside that package

