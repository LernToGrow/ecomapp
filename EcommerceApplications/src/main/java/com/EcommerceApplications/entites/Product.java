package com.EcommerceApplications.entites;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.servlet.annotation.MultipartConfig;
import javax.validation.constraints.Size;



 
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int pid;
	private String ptitle;
	@Column(length=65555)
	private String pdesc;
//	private String pphoto;
	private String price;
	private int discount;
	@ManyToOne()
	private Category category;

	public Product() {

	}


	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPtitle() {
		return ptitle;
	}

	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

//	public String getPphoto() {
//		return pphoto;
//	}
//
//	public void setPphoto(String pphoto) {
//		this.pphoto = pphoto;
//	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	
	
	
/*    public int getPriceAfterApplyingDiscount()
    {
        int d=(int)((this.getDiscount()/100.0)*this.getPrice());
        return this.getPrice()-d;     
    }*/


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "Product [pid=" + pid + ", ptitle=" + ptitle + ", pdesc=" + pdesc + ", price=" + price + ", discount="
				+ discount + ", category=" + category + "]";
	}



/*	@Override
	public String toString() {
		return "Product [pid=" + pid + ", ptitle=" + ptitle + ", pdesc=" + pdesc + ", pphoto=" + pphoto + ", price="
				+ price + ", discount=" + discount + ", category=" + category + "]";
	}*/
	
	

}
