package com.az.authenticationservice.repository;

import com.az.authenticationservice.domain.RegistrationToken;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegistrationTokenRepo extends AbstractHibernateDao<RegistrationToken> {
    public RegistrationTokenRepo() {setClazz(RegistrationToken.class);}

    public void saveRegistrationToken(RegistrationToken registrationToken) {
        save(registrationToken);
    }

    public List<RegistrationToken> getAllRegistrationTokens() {
        return this.getAll();
    }

    public RegistrationToken getRegistrationTokenById(int id) {
        return findById(id);
    }

    public RegistrationToken getLastRegistrationToken() {
        List<RegistrationToken> registrationTokens = getAllRegistrationTokens();
        if (registrationTokens.size() > 0) {
            return registrationTokens.get(registrationTokens.size() - 1);
        }
        return null;
    }


}
