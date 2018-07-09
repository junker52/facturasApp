package com.learning.facturas.app.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ricard on 09/07/2018.
 */

@Entity
@Table(name = "facturas")
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private String observacion;

    @Temporal(TemporalType.DATE)
    private Date createAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Cliente cliente;

    //Al ser unidireccional, hay que indicar la columna de foreignkey de la otra tabla
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id")
    private List<ItemFactura> items;

    public Factura() {
        this.items = new ArrayList<ItemFactura>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemFactura> getItems() {
        return items;
    }

    public void setItems(List<ItemFactura> items) {
        this.items = items;
    }

    public void addItemFactura(ItemFactura itemFactura) {
        this.items.add(itemFactura);
    }

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }
}
