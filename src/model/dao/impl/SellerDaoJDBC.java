package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbExecption;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

// Classe de implementa os Dao especificos para o JDBC
public class SellerDaoJDBC implements SellerDao {

// dao dependendia com a conex�o disponivel em qualquer metodo da minha classe

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement( 
					"SELECT seller.*,department.Name as DepName "+
					" FROM seller INNER JOIN department" +
					" ON seller.DepartmentId = department.Id "+
					" WHERE seller.Id = ?");
					
			st.setInt(1, id);
			// comando sql do st vai ser executado e o reusltado vai ser armazenado no resultset
			rs = st.executeQuery();
			//rs.next teste de venho um resultado pq rs esta na prosi��o 0 (que n tem objetop) e quando vem o reusltado vai pra posi��o 1 (que tem objeto)
			if (rs.next()) { // true entra no if
				// preciso ter os objetos instaciandos em memoria
				// Reutiliza��o de iinstancia��o com metodos auxiliadres
				Department dep = indtsnDepartment(rs);
				Seller sel = instSeller (rs,dep);
				return sel;
			}
			return null;
			
		} catch (SQLException e) {
			throw new DbExecption(e.getMessage());
		}
		 finally {
			// n�O PRECISA CONECTAR A CONEX�O JA QUE O OBJETO DAO PODE FAZER MAIS opera��es
			 DB.closeStatemant(st);
			DB.closeResultSet(rs);
		}
		
		
	}
private Seller instSeller(ResultSet rs, Department dep) throws SQLException {
		Seller sel = new Seller (rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"),rs.getDate("BirthDate"),rs.getDouble("BaseSalary"), dep);
		return sel;
}

	// propagar a exess�o
	private Department indtsnDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
