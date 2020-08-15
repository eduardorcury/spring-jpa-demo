package com.br.springjpademo;

import com.br.springjpademo.domain.Cliente;
import com.br.springjpademo.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringJpaDemoApplication implements CommandLineRunner {

	@Autowired
	private ClienteService clienteService;

	public static void main(String[] args) {

		SpringApplication.run(SpringJpaDemoApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		// CRIANDO OBJETOS
	    Cliente cliente1 = new Cliente(null, "Eduardo", "eduardo@gmail.com", 22);
		Cliente cliente2 = new Cliente(null, "Maria", "maria@gmail.com", 30);
		Cliente cliente3 = new Cliente(null, "Jõao", "joao@gmail.com", 23);
		Cliente cliente4 = new Cliente(null, "Elizabeth", "elizabeth@gmail.com", 25);
		Cliente cliente5 = new Cliente(null, "Eduarda", "eduarda@gmail.com", 30);
		Cliente cliente6 = new Cliente(null, "Daniel", "daniel@gmail.com", 40);
		Cliente cliente7 = new Cliente(null, "Adriano", "adriano@gmail.com", 23);
		Cliente cliente8 = new Cliente(null, "Edgar", "edgar@gmail.com", 23);
		Cliente cliente9 = new Cliente(null, "Beatriz", "beatriz@gmail.com", 23);
		Cliente cliente10 = new Cliente(null, "Carlos", "carlos@gmail.com", 25);

		// INSERINDO OBJETOS NO BANCO DE DADOS
		clienteService.insertAll(Arrays.asList(
				cliente1,
				cliente2,
				cliente3,
				cliente4,
				cliente5,
				cliente6,
				cliente7,
				cliente8,
				cliente9,
				cliente10));

		// TESTANDO MÉTODOS COM PALAVRAS-CHAVE
		List<Cliente> clientesByNome = clienteService.findByNome("Ed");
		System.out.println("Clientes com nome começando com 'Ed': ");
		clientesByNome.forEach(cliente -> System.out.println(cliente.getNome()));

		List<Cliente> clientesByIdade = clienteService.findByIdade(23);
		System.out.println("\nClientes com idade igual a 23 ordenados por nome ascendente: ");
		clientesByIdade.forEach(cliente -> System.out.println(cliente.getNome()));

		System.out.println("\nDeletando cliente de nome Carlos ou cliente com idade igual a 30...");
		clienteService.deleteByNome("carlos", 30);

		// TESTANDO MÉTODOS COM @QUERY
        List<Cliente> query1 = clienteService.nomeComecandoCom("Ed");
        System.out.println("Clientes com nome começando com 'Ed': ");
        query1.forEach(cliente -> System.out.println(cliente.getNome()));

        List<Cliente> query2 = clienteService.idadeIgualOrdemNome(23);
        System.out.println("\nClientes com idade igual a 23 ordenados por nome ascendente: ");
        query2.forEach(cliente -> System.out.println(cliente.getNome()));

        System.out.println("\nDeletando cliente de nome Carlos ou cliente com idade igual a 30...");
        clienteService.deletarPorNomeOuIdade("carlos", 30);

	}

}
