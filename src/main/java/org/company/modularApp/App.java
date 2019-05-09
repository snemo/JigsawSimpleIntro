/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.company.modularApp;

import org.company.utils.NaiveRandomGenerator;
import org.company.utils.ProcessUtils;
import org.company.utils.StringUtils;

import java.util.ServiceLoader;
import java.util.function.Consumer;

public class App {

    public static void main(String[] args) {
        var stringUtils = new StringUtils();
        var processUtils = new ProcessUtils();

        System.out.println(stringUtils.greet(String.valueOf(processUtils.getPid())));

        processUtils.printArguments();

        ReflectionDemo reflectionDemo = new ReflectionDemo();
        reflectionDemo.demoReflectionWithExportedPackage();
        reflectionDemo.demoReflectionWithNonExportedPackage();
//        reflectionDemo.demoReflectionWith3rdParty();

        printThreeRandomNumbers();
        printHowLongRunning(reflectionDemo);

    }
    
    private static void printThreeRandomNumbers() {
        ServiceLoader<NaiveRandomGenerator> loader = ServiceLoader.load(NaiveRandomGenerator.class);
        Consumer<? super ServiceLoader.Provider<NaiveRandomGenerator>> consumer;
        loader.stream().forEach((var provider) -> {
            var generator = provider.get();
            var type = provider.type();
            System.out.println("Random number generated by "+ type.getCanonicalName()+ " is "+ generator.getNextInt());
        });
    }

    private static void printHowLongRunning(ReflectionDemo reflectionDemo){

        var mxBean = reflectionDemo.getRuntimeMxBean();

        System.out.println("Running for: " + mxBean.getUptime() + "ms");
    }

}
