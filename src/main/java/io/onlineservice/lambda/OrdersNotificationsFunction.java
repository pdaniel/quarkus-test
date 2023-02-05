package io.onlineservice.lambda;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import io.quarkus.funqy.Funq;

import org.jboss.logging.Logger;

public class OrdersNotificationsFunction {
    private static final Logger LOG = Logger.getLogger(OrdersNotificationsFunction.class);

    @Funq
    public String notification(SQSEvent payload) {
        LOG.info(payload);
        return "DONE";
    }
}
