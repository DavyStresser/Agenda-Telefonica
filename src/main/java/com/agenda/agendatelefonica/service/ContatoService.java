package com.agenda.agendatelefonica.service;

import com.agenda.agendatelefonica.model.Contato;
import com.agenda.agendatelefonica.model.Usuario;
import com.agenda.agendatelefonica.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {
    @Autowired
    private ContatoRepository contatoRepository;

    public boolean salvar(Contato contato, Usuario usuario) {
        if (contato.getNome() == null || contato.getNome().isEmpty() ||
            contato.getSobrenome() == null || contato.getSobrenome().isEmpty() ||
            contato.getDataNascimento() == null ||
            contato.getTelefones() == null || contato.getTelefones().isEmpty()) {
            return false; // Campos obrigatórios não preenchidos
        }
        contato.setUsuario(usuario);
        contatoRepository.save(contato);
        return true;
    }

    public List<Contato> listarTodos(Long usuarioId) {
        return contatoRepository.findByUsuarioId(usuarioId);
    }

    public Optional<Contato> buscarPorId(Long id) {
        return contatoRepository.findById(id);
    }

    public boolean deletar(Long id) {
        if (contatoRepository.existsById(id)) {
            contatoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}