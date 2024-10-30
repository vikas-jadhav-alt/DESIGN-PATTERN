package dp.singleton.breakdown.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


class Singleton2 {

	private static volatile Singleton2 instance;

	private Singleton2() {
		System.out.println("Singleton2 is Instantiated.");
	}

	public static Singleton2 getInstance() {
		// Double check locking
		if (instance == null) {
			synchronized (Singleton2.class) {
				if (instance == null) {
					instance = new Singleton2();
				}
			}
		}
		return instance;
	}

}

class Main {
	public static void main(String args[])
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		// At this postion we initialized the static member instance of Singleton2 Class
		Singleton2 instance0 = Singleton2.getInstance();

		// Using Reflection to create another instance
		Constructor<Singleton2> constructor;
		try {
			constructor = Singleton2.class.getDeclaredConstructor();

			// changing the accessibility to true
			constructor.setAccessible(true); // it is mandatory otherwise error will occur

			Singleton2 instance1 = constructor.newInstance();
			Singleton2 instance2 = constructor.newInstance();

			System.out.println(instance0.hashCode());
			System.out.println(instance1.hashCode());
			System.out.println(instance2.hashCode());

			// output
//			Singleton2 is Instantiated.
//			Singleton2 is Instantiated.
//			Singleton2 is Instantiated.
//			1586600255
//			474675244
//			932583850

			// Static Member is common for All.
			System.out.println(instance1.getInstance() == instance0);// true
			System.out.println(instance2.getInstance() == instance0);// true
			System.out.println(instance2.getInstance() == instance1.getInstance());// true

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

	}

}
