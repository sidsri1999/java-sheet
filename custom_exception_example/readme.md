
# Custom Exceptions

## About
- Also known as, User Defined Exceptions.

## Need
- Some exceptions are specific to business logic, suppose we need to throw an exception if the age entered of person is less than 0.
- Or we provide subset of existing java exception.

## Types of Custom Exceptions
- Checked Custom Exceptions
- Unchecked Custom Exceptions

### Checked Custom Exception

#### Code

In this, we are defining the subset of FileNotFoundException
```
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class IncorrectFileNameException extends Exception {
	public IncorrectFileNameException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}

class CustomCheckedException {

	public void method() throws IncorrectFileNameException {
		String basePath = "C:\\Documents\\example\\springimpl3\\";
		String fileName = "demo1.txt";
		try {
			Scanner file = new Scanner(new File(basePath + fileName));
			if (file.hasNextLine())
				System.out.println(file.nextLine());
		} catch (FileNotFoundException err) {
			if (!isCorrectFileName(fileName)) {
				throw new IncorrectFileNameException("Incorrect filename : " + fileName, err);
			}
			System.out.println(err.toString());
		}
	}

	private boolean isCorrectFileName(String fileName) {
		return fileName.equalsIgnoreCase("demo.txt");
	}

}

public class Main {
	public static void main(String[] args) {
		CustomCheckedException object = new CustomCheckedException();
		try {
			object.method();
		} catch (IncorrectFileNameException e) {
			e.printStackTrace();
		}
	}
}
```

#### Outputs

- When the path is valid but file name is incorrect, actual file name: demo.txt
```
com.example.springimpl3.IncorrectFileNameException: Incorrect filename : demo1.txt
	at com.example.springimpl3.CustomCheckedException.method(Main.java:24)
	at com.example.springimpl3.Main.main(Main.java:40)
Caused by: java.io.FileNotFoundException: C:\.....
```
- When path is invalid, file name is valid
```
java.io.FileNotFoundException: C:\...\demo.txt (The system cannot find the path specified)
```

### Unchecked Custom Exception

#### Code
Here, if the age is less than 0, we are throwing runtime exception
```
import java.util.Scanner;

class InvalidAgeException extends RuntimeException {
	public InvalidAgeException(String errorMessage) {
		super(errorMessage);
	}
}

class CustomUnCheckedException {

	public void method(int age) {
		if (age < 0) {
			throw new InvalidAgeException("Invalid Age");
		}
		System.out.println("You entered a valid age: "+age);
	}

}

public class UnCheckedExceptionExample {
	public static void main(String[] args) {
		CustomUnCheckedException object = new CustomUnCheckedException();
		Scanner sc = new Scanner(System.in);
		object.method(sc.nextInt());
	}
}
```
#### Outputs
- When we enter age less than 0
```
Exception in thread "main" example.InvalidAgeException: Invalid Age
```
- When we enter age greater or equal to 0
```
You entered a valid age: 5
```