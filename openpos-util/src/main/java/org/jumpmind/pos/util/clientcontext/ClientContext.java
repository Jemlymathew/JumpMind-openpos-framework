package org.jumpmind.pos.util.clientcontext;

import org.jumpmind.pos.util.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class ClientContext {

    private ThreadLocal<Map<String, String>> propertiesMap = new ThreadLocal<>();
    final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${openpos.installationId:'not set'}")
    String installationId;

    @Value("${openpos.businessunitId:'not set'}")
    String businessUnitId;

    @Value("${openpos.deviceMode:'not set'}")
    String deviceMode;

    public void put(String name, String value) {
        if (propertiesMap.get() == null) {
            propertiesMap.set(new HashMap<>());
        }

        if ("deviceMode".equals(name))  {
            value = ((value == null) || value.equals("'not set'") ? "default" : value);
        }

        propertiesMap.get().put(name, value);
    }

    public String get(String name) {
        Map<String, String> props = propertiesMap.get();

        if (props == null || !props.containsKey(name)) {
            if ("deviceId".equals(name)) {
                return installationId;
            } else if ("appId".equals(name)) {
                return "server";
            } else if ("deviceMode".equals(name)) {
                return deviceMode;
            } else if ("timezoneOffset".equals(name)) {
                return AppUtils.getTimezoneOffset();
            }
            log.warn("ClientContext property '" + name + "' not found in ClientContext map.");
            return null;
        }

        return props.get(name);
    }

    /**
     * Removes the given property from the ClientContext
     * @param name The name of the property to remove.
     */
    public void remove(String name) {
        Map<String, String> props = propertiesMap.get();
        if (props != null && name != null) {
            props.remove(name);
        }
    }

    public Set<String> getPropertyNames() {
        Map<String, String> props = propertiesMap.get();

        if (props == null) {
            return new HashSet<>();
        }

        return props.keySet();
    }

    public void clear() {
        propertiesMap.set(null);
    }
}
