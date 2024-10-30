package dp.singleton.breakdown.reflection;

import java.lang.reflect.Constructor;

////Normal Enum also Will Work
//enum Singleton5 {
//	INSTANCE;
//}

//No Problem Enum Having Constructor

enum Singleton5 {

	INSTANCE("something");

	String j;

	Singleton5(String t) {
		j = t;
	}

}

class BreakingPreventionEnumPass {
	public static void main(String args[]) throws NoSuchMethodException, SecurityException {

		Constructor<Singleton5> constructor;

//		constructor = Singleton5.class.getDeclaredConstructor();

//			Error: java.lang.NoSuchMethodException: dp.singleton.breakdown.reflection.Singleton5.<init>()
//			at java.base/java.lang.Class.getConstructor0(Class.java:3585)
//			at java.base/java.lang.Class.getDeclaredConstructor(Class.java:2754)
//			at dp.singleton.breakdown.reflection.BreakingPreventionFailed1.main(ReflectionBreakingSolnEnum.java:26)

		Singleton5 instance1 = Singleton5.INSTANCE;
		Singleton5 instance2 = Singleton5.INSTANCE;

		System.out.println(instance1.hashCode()); // 1651191114
		System.out.println(instance2.hashCode()); // 1651191114

	}

}
