package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department dep = new Department(1,"cook");
		//Injeção de dependcia sem explicitar a implementação
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST NUMBER 1: Seller findbyid=== ");
		Seller seller = sellerDao.findById(3);

		System.out.println(seller);
	}

}
