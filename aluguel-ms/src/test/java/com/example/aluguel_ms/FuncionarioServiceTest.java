package com.example.aluguel_ms.service;

import com.example.aluguel_ms.model.Funcionario;
import com.example.aluguel_ms.repository.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository repository;

    @InjectMocks
    private FuncionarioService service;

    @Test
    void testCriarFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Raul");
        funcionario.setMatricula("2025");

        when(repository.save(funcionario)).thenReturn(funcionario);

        Funcionario resultado = service.criarFuncionario(funcionario);

        assertNotNull(resultado);
        assertEquals("Raul", resultado.getNome());
        verify(repository, times(1)).save(funcionario);
    }

    @Test
    void testListarTodos() {
        Funcionario func1 = new Funcionario(); func1.setNome("Edson");
        Funcionario func2 = new Funcionario(); func2.setNome("Pedro");

        when(repository.findAll()).thenReturn(Arrays.asList(func1, func2));

        List<Funcionario> resultado = service.listarTodos();

        assertEquals(2, resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testBuscarPorMatricula_Encontrado() {
        Funcionario funcionario = new Funcionario();
        funcionario.setMatricula("445");
        funcionario.setNome("Lucca");

        when(repository.findByMatricula("445")).thenReturn(Optional.of(funcionario));

        Optional<Funcionario> resultado = service.buscarPorMatricula("445");

        assertTrue(resultado.isPresent());
        assertEquals("Lucca", resultado.get().getNome());
        verify(repository, times(1)).findByMatricula("445");
    }
}
