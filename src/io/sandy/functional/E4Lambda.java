package io.sandy.functional;

public class E4Lambda {

//    The method using a lambad function or accepts an implementation needs to be an interface and should have only
//    one abstract method. If there are many abstract methods, then the compiler gets confused about which
//    abstract method the implementation belongs to. Hence there should be only one abstract function in the interface
//

//   FunctionInterface annotation here makes sure that interface has only one abstract method and complaints
//    if there are more than 2. Its not mandatory to annotate lanbda interfaces with this annotation but its
//    used as caution.

//    All the existing APIs or functions which have only one abstract function in Java 7 work just fine
//    with java 8 and can be implemented as lambda functions.

@FunctionalInterface
    interface Socialise {
        public void sayHello();
    }

    public static void main(String[] args) {
//        below definition by defaut assigns the function implementation on RHS to sayHello method
//        as there is only one method in the interface.
            Socialise newSocialise = () -> System.out.println("Hello All");
            newSocialise.sayHello();
    }

}
