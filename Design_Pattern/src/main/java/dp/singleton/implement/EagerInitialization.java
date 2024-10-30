package dp.singleton.implement;

class Singleton3 {

	// Static initializer
	private static Singleton3 obj = new Singleton3();

	private Singleton3() {
	}

	public static Singleton3 getInstance() {
		return obj;
	}

}

class Main2 {
	public static void main(String args[]) {
		Singleton3 instance1 = Singleton3.getInstance();
		Singleton3 instance2 = Singleton3.getInstance();

		System.out.println(instance1.hashCode());// 1651191114
		System.out.println(instance2.hashCode());// 1651191114
	}

}
