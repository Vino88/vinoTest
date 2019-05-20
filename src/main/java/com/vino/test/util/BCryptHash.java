package com.vino.test.util;

import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptHash implements PasswordEncoder {

    private static final UpdatableBCrypt bcrypt = new UpdatableBCrypt(11);

    @Override
    public String encode(CharSequence rawPassword) {
        return bcrypt.hash(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return bcrypt.verifyHash(rawPassword.toString(), encodedPassword);
    }
}
