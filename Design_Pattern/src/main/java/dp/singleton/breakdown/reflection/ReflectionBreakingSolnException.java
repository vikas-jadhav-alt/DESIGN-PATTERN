package dp.singleton.breakdown.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class Singleton4 {

	private static volatile Singleton4 instance;

	private Singleton4() {

		if (instance != null) {
			throw new RuntimeException("you are trying to break singleton pattern");
		}

		System.out.println("Singleton4 is Instantiated.");
	}

	public static Singleton4 getInstance() {
		// Double check locking
		if (instance == null) {
			synchronized (Singleton4.class) {
				if (instance == null) {
					instance = new Singleton4();
				}
			}
		}
		return instance;
	}

}

//Failed: Creating Very First Object Using Reflection API
/**
 * Here Even though we prevented the singleton breaking but, it is not working.
 * Because, prevention is based on Static Member is null or not, but in the
 * below example when we are calling the private constructor using the
 * Reflection API, ... we are creating the object of Singleton class but...
 * still Static Member is not initialised it is still, null
 */
class BreakingPreventionFailed {
	public static void main(String args[])
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Constructor<Singleton4> constructor;
		try {
			constructor = Singleton4.class.getDeclaredConstructor();

			// changing the accessibility to true
			constructor.setAccessible(true); // it is mandatory otherwise error will occur

			Singleton4 instance1 = constructor.newInstance();
			Singleton4 instance2 = constructor.newInstance();

			System.out.println(instance1.hashCode());
			System.out.println(instance2.hashCode());

			// output
//			Singleton4 is Instantiated.
//			Singleton4 is Instantiated.
//			1586600255
//			474675244

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

	}

}

//Passed: Creating First Object Using Static Factory Method
//Solution: 		/** Calling getInstance() to initialize the static member first */
class BreakingPreventionPass {
	public static void main(String args[])
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		/** Calling getInstance() to initialize the static member first */
		Singleton4 instance0 = Singleton4.getInstance();

		Constructor<Singleton4> constructor;
		try {
			constructor = Singleton4.class.getDeclaredConstructor();

			// changing the accessibility to true
			constructor.setAccessible(true); // it is mandatory otherwise error will occur

			Singleton4 instance1 = constructor.newInstance();
			Singleton4 instance2 = constructor.newInstance();

			System.out.println(instance1.hashCode());
			System.out.println(instance2.hashCode());

			// output
//			Singleton4 is Instantiated.
//			Exception in thread "main" java.lang.reflect.InvocationTargetException
//				at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

	}

}
