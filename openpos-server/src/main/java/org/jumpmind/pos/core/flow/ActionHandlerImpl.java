package org.jumpmind.pos.core.flow;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

// TODO should be called just ActionHandler, need to repackage annotation of the same name.
@Component
@org.springframework.context.annotation.Scope("prototype")
public class ActionHandlerImpl {

    // private Logger logger = LoggerFactory.getLogger(getClass());
    private static final String METHOD_ON_ANY = "onAnyAction";

    private ObjectMapper jsonMapper = new ObjectMapper();

    public boolean handleAction(Object state, Action action, Object deserializedPayload) {
        Class<?> clazz = state.getClass();

        while (clazz != null) {

            List<Method> methods = MethodUtils.getMethodsListWithAnnotation(clazz, ActionHandler.class, true, false);

            Method anyMethod = null;

            String actionName = action.getName();
            for (Method method : methods) {
                String matchingMethodName = "on" + actionName;
                method.setAccessible(true);
                if (matchingMethodName.equals(method.getName())) {
                    invokeHandleAction(state, action, method, deserializedPayload);
                    return true;
                } else if (METHOD_ON_ANY.equals(method.getName())) {
                    anyMethod = method;
                }
            }

            if (anyMethod != null) {
                invokeHandleAction(state, action, anyMethod, deserializedPayload);
                return true;
            }

            clazz = clazz.getSuperclass();

        }

        return false;

    }

    protected void invokeHandleAction(Object state, Action action, Method method, Object deserializedPayload) {
        List<Object> arguments = new ArrayList<Object>();
        try {
            for (Class<?> type : method.getParameterTypes()) {
                if (type.isAssignableFrom(Action.class)) {
                    arguments.add(action);
                } else if (deserializedPayload != null && type.isAssignableFrom(deserializedPayload.getClass())) {
                    arguments.add(deserializedPayload);
                } else if (action.getData() != null && (action.getData() instanceof String || action.getData() instanceof byte[])) {
                    Object deserializedTarget = jsonMapper.convertValue(action.getData(), type);
                    if (deserializedTarget != null) {
                        arguments.add(deserializedTarget);
                    } else {
                        throw new FlowException("Failed to satisfy action method argument " + type);
                    }
                } else {
                    arguments.add(null);
                }
            }

            method.invoke(state, arguments.toArray(new Object[arguments.size()]));
        } catch (Exception ex) {
            throw new FlowException("Failed to invoke method " + method, ex);
        }
    }

}
