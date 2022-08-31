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

public class Main {
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