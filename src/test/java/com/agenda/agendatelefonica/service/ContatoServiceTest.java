package com.agenda.agendatelefonica.service;

import com.agenda.agendatelefonica.model.Contato;
import com.agenda.agendatelefonica.model.Usuario;
import com.agenda.agendatelefonica.repository.ContatoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContatoServiceTest {
    @Mock
    private ContatoRepository contatoRepository;

    @InjectMocks
    private ContatoService contatoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSalvarContatoComSucesso() {
        // Cenário
        Usuario usuario = new Usuario();
        Contato contato = new Contato();
        contato.setNome("João");
        contato.setSobrenome("Silva");
        contato.setDataNascimento(LocalDate.of(1990, 1, 1));
        contato.setTelefones(Collections.singletonList("123456789"));

        when(contatoRepository.save(contato)).thenReturn(contato);

        // Ação
        boolean resultado = contatoService.salvar(contato, usuario);

        // Verificação
        assertTrue(resultado);
        verify(contatoRepository, times(1)).save(contato);
    }

    @Test
    void testSalvarContatoComCamposObrigatoriosFaltando() {
        // Cenário
        Usuario usuario = new Usuario();
        Contato contato = new Contato(); // Nome, sobrenome, data e telefone vazios

        // Ação
        boolean resultado = contatoService.salvar(contato, usuario);

        // Verificação
        assertFalse(resultado);
        verify(contatoRepository, never()).save(any(Contato.class));
    }

    @Test
    void testListarContatosDoUsuario() {
        // Cenário
        Long usuarioId = 1L;
        List<Contato> contatosMock = Arrays.asList(new Contato(), new Contato());
        when(contatoRepository.findByUsuarioId(usuarioId)).thenReturn(contatosMock);

        // Ação
        List<Contato> contatos = contatoService.listarTodos(usuarioId);

        // Verificação
        assertEquals(2, contatos.size());
        verify(contatoRepository, times(1)).findByUsuarioId(usuarioId);
    }

    @Test
    void testDeletarContatoComSucesso() {
        // Cenário
        Long contatoId = 1L;
        when(contatoRepository.existsById(contatoId)).thenReturn(true);

        // Ação
        boolean resultado = contatoService.deletar(contatoId);

        // Verificação
        assertTrue(resultado);
        verify(contatoRepository, times(1)).deleteById(contatoId);
    }

    @Test
    void testDeletarContatoNaoExistente() {
        // Cenário
        Long contatoId = 1L;
        when(contatoRepository.existsById(contatoId)).thenReturn(false);

        // Ação
        boolean resultado = contatoService.deletar(contatoId);

        // Verificação
        assertFalse(resultado);
        verify(contatoRepository, never()).deleteById(anyLong());
    }
}