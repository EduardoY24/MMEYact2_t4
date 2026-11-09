package com.eymm.spring.app1.mmeyact1_t4.controllers;

import java.util.HashMap;
import java.util.Map;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eymm.spring.app1.mmeyact1_t4.models.Empleados;
import com.eymm.spring.app1.mmeyact1_t4.models.dto.ClaseDTO;





@RestController
@RequestMapping("/api")
public class EjemploRestController {

    @GetMapping(path = "/detalles_info2")
    
    public ClaseDTO detalles_info() { 
ClaseDTO usuario1 = new ClaseDTO();
        usuario1.setTitulo("Administrador");
        usuario1.setUsuario("Informaticonfig");

        /*Empleados empleado1 = new Empleados("Juan", "Rodriguez", "Calle 1 No 2", "Gerente", 35, 123456789, 001);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("Empleado","Datos empleado");
        respuesta.put("Información", empleado1);*/
        return usuario1;
    }

}
