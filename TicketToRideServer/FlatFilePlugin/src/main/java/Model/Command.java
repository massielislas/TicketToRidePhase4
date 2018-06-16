package Model;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Topper on 5/14/2018.
 */

public class Command {

    private long timeCreated;
    private String targetClass;
    private String instanceMethodName;
    private String methodName;
    private String[] instanceParamTypeNames;
    private Object[] instanceMethodArgs;
    private String[] methodParamTypeNames;
    private Object[] methodArguments;

    public Command(String targetClass, String instanceMethodName,
                   String methodName, String[] instanceParamTypeNames,
                   Object[] instanceMethodArgs, String[] methodParamTypeNames,
                   Object[] methodArguments) {
        this.timeCreated = System.currentTimeMillis();
        this.targetClass = targetClass;
        this.instanceMethodName = instanceMethodName;
        this.methodName = methodName;
        this.instanceParamTypeNames = instanceParamTypeNames;
        this.instanceMethodArgs = instanceMethodArgs;

        this.methodParamTypeNames = methodParamTypeNames;
        this.methodArguments = methodArguments;
    }

    public Object Execute() {

        //INSPECT decoded items

        System.out.println("COMMAND PARAMETERS-------------------------------------");
        System.out.println("");

        System.out.println("-----targetClass: " + targetClass);
        System.out.println("-----instanceMethodName: " + instanceMethodName);
        System.out.println("-----methodName: " + methodName);

        System.out.println("-----instanceParamTypeNames: " + instanceParamTypeNames.toString());
        for (int i = 0; i < instanceParamTypeNames.length; ++i) {
            System.out.println("--------" + i + ": " + instanceParamTypeNames[i]);
        }
        System.out.println("-----instanceMethodArgs: " + instanceMethodArgs.toString());
        for (int i = 0; i < instanceMethodArgs.length; ++i) {
            System.out.println("--------" + i + ": " + instanceMethodArgs[i]);
        }
        System.out.println("-----methodParamTypeNames: " + methodParamTypeNames.toString());
        for (int i = 0; i < methodParamTypeNames.length; ++i) {
            System.out.println("--------" + i + ": " + methodParamTypeNames[i]);
        }
        System.out.println("-----methodArguments: " + methodArguments.toString());
        for (int i = 0; i < methodArguments.length; ++i) {
            System.out.println("--------" + i + ": " + methodArguments[i]);
        }
        System.out.println("-----methodArguments classes: " + methodArguments.toString());
        for (int i = 0; i < methodArguments.length; ++i) {
            System.out.println("--------" + i + ": " + methodArguments[i].getClass());
        }


        try{
            Class C = Class.forName(targetClass);
            Class[] instanceParamTypes = new Class[instanceParamTypeNames.length];
            Class[] methodParamTypes = new Class[methodParamTypeNames.length];

            for (int i = 0; i < instanceParamTypeNames.length; i++)
            {
                instanceParamTypes[i] = Class.forName(instanceParamTypeNames[i]);
            }

            for (int i = 0; i< methodParamTypeNames.length; i++)
            {
                methodParamTypes[i] = Class.forName(methodParamTypeNames[i]);
            }

            Method instM = C.getMethod(instanceMethodName, instanceParamTypes);
            Object targetInst = instM.invoke(null, instanceMethodArgs);

            Method M = C.getMethod(methodName, methodParamTypes);
            Object obj = M.invoke(targetInst, methodArguments);
            return obj;
        }
        catch (Exception exc)
        {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Command command = (Command) o;

        if (!targetClass.equals(command.targetClass)) return false;
        if (!instanceMethodName.equals(command.instanceMethodName)) return false;
        if (!methodName.equals(command.methodName)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(instanceParamTypeNames, command.instanceParamTypeNames))
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(instanceMethodArgs, command.instanceMethodArgs)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(methodParamTypeNames, command.methodParamTypeNames)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!(this.timeCreated == command.timeCreated)) return false;
        return Arrays.equals(methodArguments, command.methodArguments);
    }

    @Override
    public int hashCode() {
        int result = targetClass.hashCode();
        result = 31 * result + instanceMethodName.hashCode();
        result = 31 * result + methodName.hashCode();
        result = 31 * result + Arrays.hashCode(instanceParamTypeNames);
        result = 31 * result + Arrays.hashCode(instanceMethodArgs);
        result = 31 * result + Arrays.hashCode(methodParamTypeNames);
        result = 31 * result + Arrays.hashCode(methodArguments);
        result += timeCreated;
        return result;
    }
//    @Override
//    public int hashCode() {
//        int hash = 1;
//        hash *= targetClass.hashCode();
//        hash -= instanceMethodName.hashCode();
//        hash += instanceMethodArgs.hashCode();
//        hash /= instanceParamTypeNames.hashCode();
//        hash *= methodName.hashCode();
//        hash -= methodParamTypeNames.hashCode();
//        return hash;
//    }
}
