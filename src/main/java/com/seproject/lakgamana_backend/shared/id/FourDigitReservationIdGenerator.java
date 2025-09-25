package com.seproject.lakgamana_backend.shared.id;

import java.io.Serializable;
import java.security.SecureRandom;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * Generates a unique 4-digit ID for the reservations table in the range [6000, 9999].
 * Ensures uniqueness by checking for existing rows before returning.
 */
public class FourDigitReservationIdGenerator implements IdentifierGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int MIN_ID = 6000;
    private static final int MAX_ID = 9999;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        int attempts = (MAX_ID - MIN_ID + 1);
        while (attempts-- > 0) {
            long candidate = MIN_ID + RANDOM.nextInt(MAX_ID - MIN_ID + 1);
            if (!exists(session, candidate)) {
                return candidate;
            }
        }
        throw new HibernateException("Unable to generate unique 4-digit Reservation ID after many attempts");
    }

    private boolean exists(SharedSessionContractImplementor session, long id) {
        String sql = "select 1 from reservations where id = :id limit 1";
        Object result = session.createNativeQuery(sql)
                .setParameter("id", id)
                .uniqueResultOptional()
                .orElse(null);
        return result != null;
    }
}


