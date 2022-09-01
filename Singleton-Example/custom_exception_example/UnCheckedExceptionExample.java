package com.example.springimpl3;

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