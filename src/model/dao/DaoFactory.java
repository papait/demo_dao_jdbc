package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
// essa class opera��e static para instanciar os daos
// Macete a minha class um metodo de retorna o tipo da interface e internamente  retornar uma implementa��o
	// macete pra n�o expor a implementa��o
	//Meu programa s� vai conhecer a interface e n a implementa��o
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
	
}
