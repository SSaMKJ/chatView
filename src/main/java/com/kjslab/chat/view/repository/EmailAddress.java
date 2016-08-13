package com.kjslab.chat.view.repository;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by kimjinsam on 2016. 8. 13..
 */

public final class EmailAddress {

    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

    @Field("emailAddress")
    private String emailAddress;

    /**
     * Creates a new {@link EmailAddress} from the given {@link String} representation.
     *
     * @param emailAddress must not be {@literal null} or empty.
     */
    public EmailAddress(String emailAddress) {
        Assert.isTrue(isValid(emailAddress), "Invalid email address!");
        this.emailAddress = emailAddress.toLowerCase();
    }

    /**
     * Returns whether the given {@link String} is a valid {@link EmailAddress} which means you can safely instantiate the
     * class.
     *
     * @param candidate
     * @return
     */
    public static boolean isValid(String candidate) {
        return candidate == null ? false : PATTERN.matcher(candidate).matches();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return emailAddress;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof EmailAddress)) {
            return false;
        }

        EmailAddress that = (EmailAddress) obj;
        return this.emailAddress.equals(that.emailAddress);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return emailAddress.hashCode();
    }


    public void setEmailAddress(String email ) {
        this.emailAddress = email;
    }

    @Component
    static class EmailAddressToStringConverter implements Converter<EmailAddress, String> {

        /*
         * (non-Javadoc)
         * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
         */
        @Override
        public String convert(EmailAddress source) {
            return source == null ? null : source.emailAddress;
        }
    }

    @Component
    static class StringToEmailAddressConverter implements Converter<String, EmailAddress> {

        /*
         * (non-Javadoc)
         * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
         */
        public EmailAddress convert(String source) {
            return StringUtils.hasText(source) ? new EmailAddress(source) : null;
        }
    }
}
