package com.learning.facturas.app.view;

import com.learning.facturas.app.models.Cliente;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Ricard on 16/07/2018.
 */

@XmlRootElement(name = "clientes")
public class ClienteList {

    public ClienteList() {
    }

    public ClienteList(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @XmlElement(name = "cliente")
    private List<Cliente> clientes;


    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
