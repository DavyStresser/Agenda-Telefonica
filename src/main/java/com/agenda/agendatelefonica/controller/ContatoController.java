package com.agenda.agendatelefonica.controller;

import com.agenda.agendatelefonica.model.Contato;
import com.agenda.agendatelefonica.model.Usuario;
import com.agenda.agendatelefonica.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/contatos")
public class ContatoController {
    @Autowired
    private ContatoService contatoService;

    @GetMapping
    public String listar(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            return "redirect:/";
        }
        List<Contato> contatos = contatoService.listarTodos(usuario.getId());
        model.addAttribute("contatos", contatos);
        return "lista-contatos";
    }

    @GetMapping("/novo")
    public String novoContato(Model model) {
        model.addAttribute("contato", new Contato());
        return "form-contato";
    }

    @PostMapping("/salvar")
    public String salvarContato(@ModelAttribute Contato contato, HttpSession session, RedirectAttributes ra) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (contatoService.salvar(contato, usuario)) {
            ra.addFlashAttribute("mensagem", "Operação realizada com sucesso!");
        } else {
            ra.addFlashAttribute("mensagem", "Falha na operação: verifique os campos obrigatórios.");
        }
        return "redirect:/contatos";
    }

    @GetMapping("/editar/{id}")
    public String editarContato(@PathVariable("id") Long id, Model model) {
        return contatoService.buscarPorId(id).map(contato -> {
            model.addAttribute("contato", contato);
            return "form-contato";
        }).orElse("redirect:/contatos");
    }

    @GetMapping("/deletar/{id}")
    public String deletarContato(@PathVariable("id") Long id, RedirectAttributes ra) {
        if (contatoService.deletar(id)) {
            ra.addFlashAttribute("mensagem", "Operação realizada com sucesso!");
        } else {
            ra.addFlashAttribute("mensagem", "Falha na operação.");
        }
        return "redirect:/contatos";
    }
}