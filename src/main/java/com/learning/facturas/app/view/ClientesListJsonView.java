package com.learning.facturas.app.view;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Map;

/**
 * Created by Ricard on 16/07/2018.
 */
@Component("listar.json")
public class ClientesListJsonView extends MappingJackson2JsonView {
    @Override
    protected Object filterModel(Map<String, Object> model) {
        model.remove("titulo");
        model.remove("paginator");
        return super.filterModel(model);
    }


}
