package com.agenda.agendatelefonica.controller;

import com.agenda.agendatelefonica.model.Usuario;
import com.agenda.agendatelefonica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String login(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/login")
    public String realizarLogin(@ModelAttribute Usuario usuario, HttpSession session, RedirectAttributes ra) {
        return usuarioService.login(usuario.getEmail(), usuario.getSenha())
                .map(user -> {
                    session.setAttribute("usuarioLogado", user);
                    return "redirect:/contatos";
                })
                .orElseGet(() -> {
                    ra.addFlashAttribute("mensagem", "Login falhou. Email ou senha incorretos.");
                    return "redirect:/";
                });
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(@ModelAttribute Usuario usuario, RedirectAttributes ra) {
        if (usuarioService.cadastrar(usuario)) {
            ra.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");
            return "redirect:/";
        }
        ra.addFlashAttribute("mensagem", "Email já cadastrado.");
        return "redirect:/cadastro";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}