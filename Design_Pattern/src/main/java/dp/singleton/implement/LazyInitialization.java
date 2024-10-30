package dp.singleton.implement;

class Singleton2 {

	// Note: Member is volatile
	private static volatile Singleton2 instance;

	private Singleton2() {
		System.out.println("Singleton2 is Instantiated.");
	}

	// OBVIOUSLY we can use empty constructor as well
//	private Singleton2() {

//	// Only one thread can execute this at a time
//	public static synchronized Singleton2 getInstance() {
//		if (instance == null)
//			instance = new Singleton2();
//		return instance;
//	}

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

	public static void doSomething() {
		System.out.println("Somethong is Done.");
	}
}

class Main {
	public static void main(String args[]) {
		Singleton2 instance1 = Singleton2.getInstance();
		Singleton2 instance2 = Singleton2.getInstance();

		System.out.println(instance1.hashCode());
		System.out.println(instance2.hashCode());
	}

}
