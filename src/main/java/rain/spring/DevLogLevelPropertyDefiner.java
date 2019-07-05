package rain.spring;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.PropertyDefiner;
import ch.qos.logback.core.status.Status;


public class DevLogLevelPropertyDefiner implements PropertyDefiner {

    @Override public String getPropertyValue() {
        return "INFO";
    }

    @Override public void setContext(Context context) {

    }

    @Override public Context getContext() {
        return null;
    }

    @Override public void addStatus(Status status) {

    }

    @Override public void addInfo(String s) {

    }

    @Override public void addInfo(String s, Throwable throwable) {

    }

    @Override public void addWarn(String s) {

    }

    @Override public void addWarn(String s, Throwable throwable) {

    }

    @Override public void addError(String s) {

    }

    @Override public void addError(String s, Throwable throwable) {

    }
}
