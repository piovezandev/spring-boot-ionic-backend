package br.com.alan.cursomc;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alan.cursomc.domain.Categoria;
import br.com.alan.cursomc.domain.Cidade;
import br.com.alan.cursomc.domain.Cliente;
import br.com.alan.cursomc.domain.Endereco;
import br.com.alan.cursomc.domain.Estado;
import br.com.alan.cursomc.domain.Produto;
import br.com.alan.cursomc.domain.enums.TipoCliente;
import br.com.alan.cursomc.repositories.CategoriaRepository;
import br.com.alan.cursomc.repositories.CidadeRepository;
import br.com.alan.cursomc.repositories.ClienteRepository;
import br.com.alan.cursomc.repositories.EnderecoRepository;
import br.com.alan.cursomc.repositories.EstadoRepository;
import br.com.alan.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Compuador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 900.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "Jundiai", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		
		
		Cliente cli1 = new Cliente(null, "Maria silva", "maria@gmail.com", "11111111111", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("1101010000","11900000000"));
		
		Endereco end1 = new Endereco(null, "Rua das flores", "100", "", "centro", "11111111", cli1, c1);
		Endereco end2 = new Endereco(null, "Avenida nações", "10", "", "vila aurora", "33333333", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
	}

}
