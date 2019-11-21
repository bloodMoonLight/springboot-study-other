package com.lambda.study;

public class LambdaTargetType {

    @FunctionalInterface
    interface  A{
        void a();
    }
    @FunctionalInterface
    interface  B{
        void b();
    }


    class UserAB{
        void use(A a){
            System.out.println("USE A");
        }

        void use(B b){
            System.out.println("USE B");
        }
    }

    void targetType(){
        UserAB userAB = new UserAB();
       // userAB.use(() -> System.out.println("Use")); 这样写会造成编译器无法识别
        //解决办法，可以先将一个lambda表达式赋值给一个接口，然后将接口传入就行了
        A a = () -> System.out.println("User");
        userAB.use(a);
    }

    public static void main(String[] args) {
        LambdaTargetType lambdaTargetType = new LambdaTargetType();
        lambdaTargetType.targetType();
    }

}
