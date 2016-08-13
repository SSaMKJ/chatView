package com.kjslab.chat.view.repository;

import org.springframework.data.annotation.Id;

import java.math.BigInteger;

/**
 * Created by kimjinsam on 2016. 8. 13..
 */
public class AbstractDocument {

    @Id
    private BigInteger id;

    /**
     * Returns the identifier of the document.
     *
     * @return the id
     */
    public BigInteger getId() {
        return id;
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

        if (this.id == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
            return false;
        }

        AbstractDocument that = (AbstractDocument) obj;

        return this.id.equals(that.getId());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
