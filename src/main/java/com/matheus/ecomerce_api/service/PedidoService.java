package com.matheus.ecommerce_api.service;

import com.matheus.ecommerce_api.dto.ItemPedidoRequest;
import com.matheus.ecommerce_api.dto.PedidoRequest;
import com.matheus.ecommerce_api.exception.ResourceNotFoundException;
import com.matheus.ecommerce_api.model.ItemPedido;
import com.matheus.ecommerce_api.model.Pedido;
import com.matheus.ecommerce_api.model.Produto;
import com.matheus.ecommerce_api.model.StatusPedido;
import com.matheus.ecommerce_api.repository.PedidoRepository;
import com.matheus.ecommerce_api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Pedido não encontrado com id: " + id));
    }

    @Transactional
    public Pedido criar(PedidoRequest request) {
        Pedido pedido = new Pedido();
        BigDecimal total = BigDecimal.ZERO;

        for (ItemPedidoRequest itemRequest : request.getItens()) {
            Produto produto = produtoRepository.findById(itemRequest.getProdutoId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Produto não encontrado com id: " + itemRequest.getProdutoId()));

            if (produto.getQuantidadeEstoque() < itemRequest.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para: " + produto.getNome());
            }

            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemRequest.getQuantidade());
            item.setPrecoUnitario(produto.getPreco());

            produto.setQuantidadeEstoque(
                    produto.getQuantidadeEstoque() - itemRequest.getQuantidade());
            produtoRepository.save(produto);

            pedido.getItens().add(item);
            total = total.add(produto.getPreco()
                    .multiply(BigDecimal.valueOf(itemRequest.getQuantidade())));
        }

        pedido.setTotal(total);
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido cancelar(Long id) {
        Pedido pedido = buscarPorId(id);

        if (pedido.getStatusPedido() == StatusPedido.ENTREGUE) {
            throw new RuntimeException("Pedido já entregue não pode ser cancelado");
        }

        for (ItemPedido item : pedido.getItens()) {
            Produto produto = item.getProduto();
            produto.setQuantidadeEstoque(
                    produto.getQuantidadeEstoque() + item.getQuantidade());
            produtoRepository.save(produto);
        }

        pedido.setStatusPedido(StatusPedido.CANCELADO);
        return pedidoRepository.save(pedido);
    }
}