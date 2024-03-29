package com.geeksforless.station.persistence.entity.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("OPERATOR")
public class Operator extends User {

    public Operator() {
        super();

    }
}
