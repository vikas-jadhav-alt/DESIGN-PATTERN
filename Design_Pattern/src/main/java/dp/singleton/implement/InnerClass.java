package dp.singleton.implement;

//using class loading concept
//Singleton1 design pattern

class Singleton1 {

	private Singleton1() {
		System.out.println("Instance created");
	}

	private static class Singleton1Inner {

		private static final Singleton1 INSTANCE = new Singleton1();
	}

	public static Singleton1 getInstance() {
		return Singleton1Inner.INSTANCE;
	}
}

class Test1 {
	public static void main(String[] args) {
		Singleton1 instance1 = Singleton1.getInstance();
		Singleton1 instance2 = Singleton1.getInstance();

		System.out.println(instance1.hashCode());
		System.out.println(instance2.hashCode());
	}
}

//output
//Singleton2 is Instantiated.
//1651191114
//1651191114
