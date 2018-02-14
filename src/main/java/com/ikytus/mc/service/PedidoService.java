package com.ikytus.mc.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ikytus.mc.domain.Cliente;
import com.ikytus.mc.domain.ItemPedido;
import com.ikytus.mc.domain.PagamentoComBoleto;
import com.ikytus.mc.domain.Pedido;
import com.ikytus.mc.domain.enums.EstadoPagamento;
import com.ikytus.mc.repository.ClienteRepository;
import com.ikytus.mc.repository.ItemPedidoRepository;
import com.ikytus.mc.repository.PagamentoRepository;
import com.ikytus.mc.repository.PedidoRepository;
import com.ikytus.mc.repository.ProdutoRepository;
import com.ikytus.mc.service.exceptions.AutorizationException;
import com.ikytus.mc.service.exceptions.ObjectNotFoundException;
import com.ikytus.mc.util.email.EmailService;
import com.ikytus.mc.util.security.UserSS;
import com.ikytus.mc.util.security.UserService;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private EmailService emailService;
		
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	
	public Pedido find(Long id) {
		
		Pedido pedido = pedidoRepository.findOne(id); 
		
		if(pedido == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Pedido.class.getName());
		}
		return pedido; 
	}
	
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.setCliente(clienteRepository.findOne(pedido.getCliente().getId()));
		pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
		}
		
		pedido = pedidoRepository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		for(ItemPedido ip : pedido.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoRepository.findOne(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(pedido);
		}
		
		itemPedidoRepository.save(pedido.getItens());
		emailService.sendOrderConfirmationHtmlEmail(pedido);
		return pedido;
	}
	
	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		UserSS user = UserService.authenticated();
		if(user==null) {
			throw new AutorizationException("Acesso negado");
		}
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction),orderBy);
		Cliente cliente = clienteRepository.findOne(user.getId());
		
		return pedidoRepository.findByCliente(cliente, pageRequest);
	}
}
