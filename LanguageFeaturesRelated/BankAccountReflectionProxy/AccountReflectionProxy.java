package BankAccount_Proxy;

/*using reflection to invoke methods.
This is to demostrate the possibility of such a usage but
in fact it is discouraged as being like a "method pointer".
*/

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AccountReflectionProxy {
    public BankAccount realAccount;

    public AccountReflectionProxy(BankAccount realAccount) {
        this.realAccount = realAccount;
    }

    public Object callMethod(String methodName, Object param){
        Class cls = realAccount.getClass();

        try{
            if (param!=null){
                //here I made a workaround to avoid evaluating
                //the class type of any primitive types
                //as here it is known in advance that
                //the method parameter will be only double unless null
                Method method=cls.getMethod(methodName,double.class);
                Object returned= method.invoke(realAccount,param);
                return returned;
            }
            else {
                Method method=cls.getMethod(methodName);
                Object returned= method.invoke(realAccount);
                return returned;
            }
        }
        catch (NoSuchMethodException e){
            throw new IllegalArgumentException(cls.getName() + " does not support "+methodName);
        }
        catch (IllegalAccessException e){
            throw new IllegalArgumentException("Insufficient access permission to call" +methodName + " in class "+ cls.getName());
        }
        catch (InvocationTargetException e){
            throw new RuntimeException(e);
        }
    }
}
