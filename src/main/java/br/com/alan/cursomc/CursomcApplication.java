package br.com.alan.cursomc;

import java.text.SimpleDateFormat;
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
import br.com.alan.cursomc.domain.ItemPedido;
import br.com.alan.cursomc.domain.Pagamento;
import br.com.alan.cursomc.domain.PagamentoComBoleto;
import br.com.alan.cursomc.domain.PagamentoComCartao;
import br.com.alan.cursomc.domain.Pedido;
import br.com.alan.cursomc.domain.Produto;
import br.com.alan.cursomc.domain.enums.EstadoPagamento;
import br.com.alan.cursomc.domain.enums.TipoCliente;
import br.com.alan.cursomc.repositories.CategoriaRepository;
import br.com.alan.cursomc.repositories.CidadeRepository;
import br.com.alan.cursomc.repositories.ClienteRepository;
import br.com.alan.cursomc.repositories.EnderecoRepository;
import br.com.alan.cursomc.repositories.EstadoRepository;
import br.com.alan.cursomc.repositories.ItemPedidoRepository;
import br.com.alan.cursomc.repositories.PagamentoRepository;
import br.com.alan.cursomc.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
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
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
		
		Pedido ped1 = new Pedido(null, dateFormat.parse("03/08/2020 10:20:22"), cli1, end1);
		Pedido ped2 = new Pedido(null, dateFormat.parse("07/08/2020 16:07:01"), cli1, end2);
		
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, dateFormat.parse("07/09/2020 00:00:00"), null);
		ped2.setPagamento(pgto2);
		
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
		
		
		ItemPedido ip1 = new ItemPedido(0.00, 1, 2000.00, ped1, p1);
		ItemPedido ip2 = new ItemPedido(0.00, 2, 80.00, ped1, p3);
		ItemPedido ip3 = new ItemPedido(100.00, 1, 800.00, ped2, p2);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
