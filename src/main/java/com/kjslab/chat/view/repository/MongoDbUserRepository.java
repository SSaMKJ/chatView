package com.kjslab.chat.view.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by kimjinsam on 2016. 8. 13..
 */
@Repository
//@Profile("mongodb")
public class MongoDbUserRepository implements UserRepository {

    private final MongoOperations operations;


//    public List<User> findAll(){
//        return operations.findAll(User.class);
//    }

    /**
     * Creates a new {@link MongoDbUserRepository} using the given {@link MongoOperations}.
     *
     * @param operations must not be {@literal null}.
     */
    @Autowired
    public MongoDbUserRepository(MongoOperations operations) {

        Assert.notNull(operations);
        this.operations = operations;
    }

    /* 
     * (non-Javadoc)
     * @see com.oreilly.springdata.mongodb.core.UserRepository#findOne(java.lang.Long)
     */
    @Override
    public User findOne(Long id) {

        Query query = query(where("id").is(id));
        return operations.findOne(query, User.class);
    }

    /* 
     * (non-Javadoc)
     * @see com.oreilly.springdata.mongodb.core.UserRepository#save(com.oreilly.springdata.mongodb.core.User)
     */
    @Override
    public User save(User User) {

        operations.save(User);
        return User;
    }

    /* 
     * (non-Javadoc)
     * @see com.oreilly.springdata.mongodb.core.UserRepository#findByEmailAddress(com.oreilly.springdata.mongodb.core.EmailAddress)
     */
    @Override
    public User findByEmailAddress(String emailAddress) {
        return findByEmailAddress(new EmailAddress(emailAddress));
    }

    @Override
    public User findByEmailAddress(EmailAddress emailAddress) {

        Query query = query(where("emailAddress").is(emailAddress));
        return operations.findOne(query, User.class);
    }
}
