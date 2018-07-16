package com.learning.facturas.app.view;

import com.learning.facturas.app.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Ricard on 16/07/2018.
 */
@Component("listar.xml")
public class ClienteListXmlView extends MarshallingView {

    @Autowired
    public ClienteListXmlView(Jaxb2Marshaller marshaller) {
        super(marshaller);
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        model.remove("paginator");
        model.remove("titulo");
        List<Cliente> clientes = (List<Cliente>) model.get("clientesList");
        model.remove("clientesList");
        model.put("clienteList", new ClienteList(clientes));
        super.renderMergedOutputModel(model, request, response);
    }
}
