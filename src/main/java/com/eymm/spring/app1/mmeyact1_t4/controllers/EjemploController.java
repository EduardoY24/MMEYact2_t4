package com.eymm.spring.app1.mmeyact1_t4.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eymm.spring.app1.mmeyact1_t4.models.dto.EmpleadoDTO;

@Controller
public class EjemploController {

    @Value("${config.usuario}")
    private String usuario;

    @GetMapping("/detalles_info")
    public String info(Model model) {
        EmpleadoDTO empleado1 = new EmpleadoDTO("Juan", "Rodriguez", "Calle 1 No 1", "Gerente", 35, 123456789, 1);
        model.addAttribute("Empleado", empleado1);
        model.addAttribute("usuario", usuario);
        return "detalles_info";
    }

    @GetMapping("/empleados")
    public String buscar(@RequestParam(required = false, defaultValue = "") String buscar,
                         @RequestParam(required = false, defaultValue = "") String puesto,
                         Model model) {
        List<EmpleadoDTO> resultado = new ArrayList<>();
        for (EmpleadoDTO e : ListaEmpleados()) {
            boolean coincideNombre = e.getNombre().toLowerCase().contains(buscar.toLowerCase())
                    || e.getApellido().toLowerCase().contains(buscar.toLowerCase());
            boolean coincidePuesto = puesto.isEmpty() || e.getPuesto().equals(puesto);  //==null
            if (coincideNombre && coincidePuesto) { //Metodos de busqueda, aqui se agregan
                resultado.add(e);
            }
        }
        model.addAttribute("Empleados", resultado);
        model.addAttribute("buscar", buscar);
        model.addAttribute("puesto", puesto);
        model.addAttribute("usuario", usuario); //para que se queden en la caja de busqueda los valores que se ingresaron
        return "empleados";
    }

    @GetMapping("/empleado/{id}")
    public String detalle(@PathVariable int id, Model model) {
        for (EmpleadoDTO e : ListaEmpleados()) {
            if (e.getId() == id) {
                model.addAttribute("Empleado", e);
            }
        }
        model.addAttribute("usuario", usuario);
        return "detalle";
    }

    @GetMapping("/formulario")
    public String formulario(Model model) {
        model.addAttribute("empleado", new EmpleadoDTO());
        model.addAttribute("usuario", usuario);
        return "formulario";
    }

    @PostMapping("/formulario")
    public String recibirFormulario(@ModelAttribute EmpleadoDTO empleado, Model model) {
        model.addAttribute("empleado", empleado);
        return "resultado";
    }

    @ModelAttribute("Empleados")
    public List<EmpleadoDTO> ListaEmpleados() {
        return Arrays.asList(
            new EmpleadoDTO("María", "Perez", "Calle segunda no.2", "Secretaria", 20, 123654987, 2),
            new EmpleadoDTO("Juan", "Rodriguez", "Calle primera no.1", "Gerente", 35, 123456789, 1),
            new EmpleadoDTO("Luis", "Martínez", "Calle tercera no.3", "Contador", 28, 987654321, 3),
            new EmpleadoDTO("Ana", "González", "Avenida central no.4", "Diseñadora", 30, 456789123, 4),
            new EmpleadoDTO("Pedro", "Sánchez", "Boulevard no.5", "Analista", 25, 789123456, 5)
        );
    }
}
