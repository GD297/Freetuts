package com.fsoft.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String paymentID;
	private String name;
	private String phone;
	private String email;
	private String facebook;
	private String paymentMethod;
}
