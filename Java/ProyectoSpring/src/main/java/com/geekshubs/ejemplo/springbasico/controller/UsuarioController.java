package com.geekshubs.ejemplo.springbasico.controller;

import com.geekshubs.ejemplo.springbasico.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Value("${springbasico.texto.titulo.listaUsuarios}")
    private String tituloListaUsuarios;

    @Value("${springbasico.texto.titulo.perfil}")
    private String tituloPerfilUsuario;

    public static int usuariosCount;

    @GetMapping(value = "/perfil/{id}")
    public String perfil(@PathVariable String id, Model model) {
        model.addAttribute("titulo", tituloPerfilUsuario);
        Integer idUsuario = null;
        try {
            idUsuario = Integer.parseInt(id);
        } catch(NumberFormatException e) {
            idUsuario = -1;
        } finally {
            if (idUsuario != -1) {
                model.addAttribute("id", Integer.parseInt(id));
                List<Usuario> listaUsuarios = (ArrayList<Usuario>) model.getAttribute("listaUsuarios");
                Usuario usuario = getUserById(Integer.parseInt(id), listaUsuarios);
                model.addAttribute("usuario", usuario);
            }
        }

        return "springbasico/usuario/perfil";
    }

    /* @GetMapping(value = "/perfil")
    public String perfil(@RequestParam(defaultValue = "-1") Integer id, Model model) {
        model.addAttribute("id", id.intValue());
        if(id.intValue() != -1) {
            List<Usuario> listaUsuarios = (ArrayList<Usuario>) model.getAttribute("listaUsuarios");
            Usuario usuario = getUserById(id, listaUsuarios);
            model.addAttribute("usuario", usuario);
        }
        return "usuario/perfil";
    } */

    @GetMapping(value = "/listarUsuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("titulo", tituloListaUsuarios);
        return "springbasico/usuario/lista";
    }

    @ModelAttribute("listaUsuarios")
    public static List<Usuario> listaUsuarios() {
        usuariosCount = 0;
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario(++usuariosCount, "Paco", "García", 23, "pgarcia@gmail.com"));
        listaUsuarios.add(new Usuario(++usuariosCount, "Marcos", "Gómez", 45, "mgomez@gmail.com"));
        listaUsuarios.add(new Usuario(++usuariosCount, "Sergio", "Martínez", 36, null));
        listaUsuarios.add(new Usuario(++usuariosCount, "Jesús", "Sánchez", 28, "jsanchez@gmail.com"));
        return listaUsuarios;
    }

    public static Usuario getUserById(int id, List<Usuario> listaUsuarios) {
        for (Usuario u : listaUsuarios) {
            if (u.getId() == id)
                return u;
        }
        return null;
    }
}