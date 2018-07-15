package com.learning.facturas.app.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ricard on 15/07/2018.
 */
@Entity
@Table(name = "authorities")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
