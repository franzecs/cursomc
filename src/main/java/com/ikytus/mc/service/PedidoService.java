package com.ikytus.mc.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikytus.mc.domain.ItemPedido;
import com.ikytus.mc.domain.PagamentoComBoleto;
import com.ikytus.mc.domain.Pedido;
import com.ikytus.mc.domain.enums.EstadoPagamento;
import com.ikytus.mc.repository.ItemPedidoRepository;
import com.ikytus.mc.repository.PagamentoRepository;
import com.ikytus.mc.repository.PedidoRepository;
import com.ikytus.mc.repository.ProdutoRepository;
import com.ikytus.mc.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
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
			ip.setPreco(produtoRepository.findOne(ip.getProduto().getId()).getPreco());
			ip.setPedido(pedido);
		}
		
		itemPedidoRepository.save(pedido.getItens());
		
		return pedido;
	}
}
