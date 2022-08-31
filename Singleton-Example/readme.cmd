
# Singleton Class

## About

- Only one object creation is possible.
- If we again try to create a new object it will give reference to previously created object.

## Use

- Restricts on number of object.
- Saves memory.
- Example, Suppose we have one license for jdbc connection, and it won't allow to make more than one call, so in this case we can use Singleton class that will allow only one call at a time.

## How to make the class Singleton

- Make constructor private
- Store the created object in static variable
- If someone calls for the object (getInstance OR className) return that object

## Forms of Singleton Class Pattern
- Early Instantiation: Object created at load time.
- Lazy Instantiation: Object created when required.

## Code Example

#### Code
```
class SingletonClass {

	private static SingletonClass single_object;
	// For Early Instantiation: private static SingletonClass single_object = new
	// SingletonClass();

	int x;

	private SingletonClass() {
		x = 10;
	}

	public static SingletonClass getInstance() {
		// Or by naming it by class name SingletonClass()
		// Lazy Instantiation
		if (single_object == null)
			single_object = new SingletonClass();
		return single_object;
	}

}

public class SingletonClassExample {
	public static void main(String[] args) {
		SingletonClass object1 = SingletonClass.getInstance();
		// new SingletonClass(); not visible
		System.out.println("Before changing in object1, x = " + object1.x);
		object1.x = 20;
		System.out.println("After changing in object1, x = " + object1.x);
		SingletonClass object2 = SingletonClass.getInstance();
		// The value in object2 is also changed, because they are referencing to same
		// variable
		System.out.println("Before changing in object2, x = " + object2.x);
	}
}
```

#### Output
```
Before changing in object1, x = 10
After changing in object1, x = 20
Before changing in object2, x = 20
```