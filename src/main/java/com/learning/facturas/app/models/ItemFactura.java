package com.learning.facturas.app.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Ricard on 09/07/2018.
 */
@Entity
@Table(name = "facturas_items")
public class ItemFactura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long calcularImporte() {
        return null;
    }
}
