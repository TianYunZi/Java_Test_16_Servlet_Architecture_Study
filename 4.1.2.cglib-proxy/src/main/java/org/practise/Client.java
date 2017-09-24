package org.practise;

public class Client {

    public static void main(String[] args) {
        Greeting greeting = CglibDynamicProxy.getInstance().getProxy(GreetingImpl.class);
        greeting.sayHello("Dog!");
    }
}
