package com.orbirpinar.EventListenerProvider;

import com.orbirpinar.KafkaProducer.KafkaProducer;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RealmProvider;
import org.keycloak.models.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomEventListenerProvider implements EventListenerProvider {

    private static final Logger log = LoggerFactory.getLogger(CustomEventListenerProvider.class);

    private final KeycloakSession session;
    private final RealmProvider model;

    public CustomEventListenerProvider(KeycloakSession session) {
        this.session = session;
        this.model = session.realms();
    }

    @Override
    public void onEvent(Event event) {

        if (EventType.REGISTER.equals(event.getType())) {
            log.info("## NEW USER REGISTERED ##");
            RealmModel realm = this.model.getRealm(event.getRealmId());
            UserModel newRegisteredUser = this.session.users().getUserById(event.getUserId(), realm);

            KafkaProducer.publish(newRegisteredUser.getId());

            log.info("USERNAME ---> {}",newRegisteredUser.getUsername());
            log.info("USER ID ---> {}",newRegisteredUser.getId());

            log.info("EVENT PUBLISHED TO KAFKA");
        }

    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {

    }

    @Override
    public void close() {

    }
}
