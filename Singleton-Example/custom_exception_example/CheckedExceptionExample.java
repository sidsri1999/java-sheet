package custom_exception_example;

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
		String basePath = "C:\\Documents\\custom_exception_example\\";
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

public class CheckedExceptionExample {
	public static void main(String[] args) {
		CustomCheckedException object = new CustomCheckedException();
		try {
			object.method();
		} catch (IncorrectFileNameException e) {
			e.printStackTrace();
		}
	}
}