package com.revature.banking.util;

/**
 * Interfaces
 *
 *      - acts as a "contract" for implementing classes (layman's terms: any concrete class that implements
 *        this interface will be required to provide logic for the declared methods of this interface)
 *
*       - do not have constructors (and therefore cannot be instantiated)
 *
 *       - the interface declaration is implicitly abstract
 *
 *       - all method stubs declared within an interface are implicitly abstract and public
 *
 *       - any fields included in an interface are implicitly: public, static, and final
 *
 *       - can contain concrete (non-abstract) methods:
 *          + static methods
 *          + default methods (introduced in Java 8)
 */
public interface List<T> extends Collection<T> {

    // the line below is an example of a method stub (a method without an implementation; can only be declared
    // within an abstract construct (abstract class or interface)
    T get(int index);

    // Default methods are interface methods that have a default implementation
    // Introduced in Java v8 (added to help to maintain backward compatibility)
    // Can be overridden
    default void defaultMethodExample() {
        System.out.println("This is a default method in an interface, it CAN be overridden by implementing classes");
    }

    // Static methods
    // Since Java 1
    // Cannot be overridden
    // Accessed like this: List.staticMethodExample()
    static void staticMethodExample() {
        System.out.println("This is a static method in an interface, it CANNOT be overridden - but it can be shadowed.");
    }



}
