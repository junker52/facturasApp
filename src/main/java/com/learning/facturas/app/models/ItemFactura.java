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

    //Al ser unidireccional el campo foraneo en ItemFactura se crea automatico
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id")
    //@JsonIgnoreProperties -- Para ignorar algunas propiedades del objeto
    private Producto producto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double calcularImporte() {
        return cantidad.doubleValue() * producto.getPrecio();
    }
}
