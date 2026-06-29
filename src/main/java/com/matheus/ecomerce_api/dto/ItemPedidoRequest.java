package com.matheus.ecommerce_api.dto;

import jakarta.validation.constraints.Positive;

public class ItemPedidoRequest {

    @Positive(message = "ID do produto deve ser maior que zero")
    private Long produtoId;

    @Positive(message = "Quantidade deve ser maior que zero")
    private Integer quantidade;

    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
}