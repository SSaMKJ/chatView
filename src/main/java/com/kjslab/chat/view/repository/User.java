package com.kjslab.chat.view.repository;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kimjinsam on 2016. 8. 13..
 */
@Document
public class User extends AbstractDocument {

    private String firstname, lastname;

    private String password;

    @Field("emailAddress")
    @Indexed(unique = true)
    private EmailAddress emailAddress;

    private Set<Address> addresses = new HashSet<Address>();

    /**
     * Creates a new {@link User} from the given firstname and lastname.
     *
     * @param firstname must not be {@literal null} or empty.
     * @param lastname  must not be {@literal null} or empty.
     */
    public User(String firstname, String lastname) {

        Assert.hasText(firstname);
        Assert.hasText(lastname);

        this.firstname = firstname;
        this.lastname = lastname;
    }

    protected User() {

    }

    /**
     * Adds the given {@link Address} to the {@link User}.
     *
     * @param address must not be {@literal null}.
     */
    public void add(Address address) {

        Assert.notNull(address);
        this.addresses.add(address);
    }

    /**
     * Returns the firstname of the {@link User}.
     *
     * @return
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Returns the lastname of the {@link User}.
     *
     * @return
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the lastname of the {@link User}.
     *
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    /**
     * Returns the {@link EmailAddress} of the {@link User}.
     *
     * @return
     */
    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the {@link User}'s {@link EmailAddress}.
     *
     * @param emailAddress must not be {@literal null}.
     */
    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Return the {@link User}'s addresses.
     *
     * @return
     */
    public Set<Address> getAddresses() {
        return Collections.unmodifiableSet(addresses);
    }
}
