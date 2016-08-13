package com.kjslab.chat.view.repository;

import org.springframework.data.repository.Repository;

/**
 * Created by kimjinsam on 2016. 8. 13..
 */
interface  UserRepository extends Repository<User, Long> {

    /**
     * Returns the User with the given identifier.
     *
     * @param id
     * @return
     */
    User findOne(Long id);

    /**
     * Saves the given {@link User}. #
     *
     * @param User
     * @return
     */
    User save(User User);

    /**
     *
     * @param emailAddress
     * @return
     */
    User findByEmailAddress(EmailAddress emailAddress);

    /**
     *
     * @param emailAddress
     * @return
     */
    public User findByEmailAddress(String emailAddress);
}
