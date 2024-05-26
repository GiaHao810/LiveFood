package app.manager.client.dto;

import app.manager.client.model.Customer;

import java.util.List;

public record OrderRequest(Customer customer, List<OrderDTO> orderDTO){}
