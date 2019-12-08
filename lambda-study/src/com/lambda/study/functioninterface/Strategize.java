package com.lambda.study.functioninterface;

/**
 * @ClassName Strategize
 * @Description TODO
 * @Author 张鸿志
 * @Date 2019年12月8日20:52:24 20:52
 * Version 1.0
 **/
public class Strategize {
    Strategy strategy;
    String msg;

    /**
     * 构造方法
     * @Description TODO
     * @params
     * @Author 张鸿志
     * @Date 2019/12/8 20:53
     * @Return
     **/
    Strategize(String msg){
        strategy = new Soft();
        this.msg = msg;
    }

    void communicate(){
        System.out.println(strategy.approach(msg));
    }

    void changeStrategy(Strategy strategy){
        this.strategy = strategy;
    }

    public static void main(String[] args) {
        //创建一个Strategy接口数组，通过三种方式实现内部对象的创建，分别给出不同的方法
        Strategy[] strategies = {
          new Strategy() {   // 匿名内部类
              @Override
              public String approach(String msg) {
                  return msg.toUpperCase() + "!";
              }
          },msg -> msg.substring(0,5),  //Lambada表达式创建
                Unrelated::twice   // Java8的方法引用 :: 左边是类或对象的名称，右边是方法的名称，但是没有参数列表
        };
        //创建一个Strategize
        Strategize strategize = new Strategize("HelloWorld");
        //输出默认实现该接口类的输出方式 带 ?形式的。也就是Soft类，他是默认策略
        strategize.communicate();
        for (Strategy newStrategy : strategies) {
            // 将另外三个通过不同方式创建的对象赋值给strategize
            strategize.changeStrategy(newStrategy);
            //分别调用不同对象的打印形式
            strategize.communicate();
        }

    }



}
