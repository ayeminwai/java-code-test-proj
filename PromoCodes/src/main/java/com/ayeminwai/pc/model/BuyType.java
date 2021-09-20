package com.ayeminwai.pc.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "buy_type")
@Getter
@Setter
@ToString
public class BuyType {

	@Id
	@Column(name = "bt_id")
	private Long btId;
	
	@Column(name = "bt_desc")
	private String btDesc;
	
    @OneToMany(mappedBy = "buyType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EVoucher> evoucher;
    
    @OneToMany(mappedBy = "buyType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EVoucherDetails> evoucherDetails;
}
