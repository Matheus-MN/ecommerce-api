package com.matheus.ecommerce_api.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class PedidoRequest {

    @NotEmpty(message = "Pedido deve ter pelo menos um item")
    private List<ItemPedidoRequest> itens;

    public List<ItemPedidoRequest> getItens() { return itens; }
    public void setItens(List<ItemPedidoRequest> itens) { this.itens = itens; }
}