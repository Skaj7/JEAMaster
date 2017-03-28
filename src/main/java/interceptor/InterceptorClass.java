package interceptor;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Created by Kaj Suiker on 13-3-2017.
 */
public class InterceptorClass {

        @AroundInvoke
        public Object aroundInvoke(InvocationContext context) throws Exception {
            Object[] parameters = context.getParameters();
            String tweetText = (String)parameters[1];
            tweetText = tweetText.replaceAll(" nee ", " ja ");
            tweetText = tweetText.replaceAll(" cool ", " hard ");
            parameters[1] = tweetText;
            context.setParameters(parameters);
            return context.proceed();
        }
    }

