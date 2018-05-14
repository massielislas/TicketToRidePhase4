package clientModel;

import java.lang.reflect.Method;

/**
 * Created by Topper on 5/14/2018.
 */

public class Command implements iCommand {

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
        this.targetClass = targetClass;
        this.instanceMethodName = instanceMethodName;
        this.methodName = methodName;
        this.instanceParamTypeNames = instanceParamTypeNames;
        this.instanceMethodArgs = instanceMethodArgs;
        this.methodParamTypeNames = methodParamTypeNames;
        this.methodArguments = methodArguments;
    }

    public Object Execute() {
        try{
            Class C = Class.forName(targetClass);
            Class[] pTypes1 = new Class[instanceParamTypeNames.length];
            Class[] pTypes2 = new Class[methodParamTypeNames.length];

            for (int i = 0; i < instanceParamTypeNames.length; i++)
            {
                pTypes1[i] = Class.forName(instanceParamTypeNames[i]);
            }

            for (int i = 0; i< methodParamTypeNames.length; i++)
            {
                pTypes2[i] = Class.forName(methodParamTypeNames[i]);
            }

            Method instM = C.getMethod(instanceMethodName, pTypes1);
            Object targetInst = instM.invoke(null, instanceMethodArgs);

            Method M = C.getMethod(methodName, pTypes2);
            Object obj = M.invoke(targetInst, methodArguments);
            return obj;
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
            return null;
        }
    }
}