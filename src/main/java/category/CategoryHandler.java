package category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import category.Category;
import config.Configuration;

interface CategoryStandard {

	String update(Category c);

	String insert(Category c);

	boolean deleteById(short id);

	Category fetchCategory(short id);

	List<Category> fetchAllCategories();
}

public class CategoryHandler implements CategoryStandard {

	public String insert(Category c) {
		// TODO Auto-generated method stub
		try {
			String query = "insert into file" + "(name,description)value(?,?)";
			Connection conn = Configuration.conn;
			PreparedStatement p = conn.prepareStatement(query);
			p.setString(1, c.getName());
			p.setString(2, c.getDescription());
			p.executeUpdate();
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Category Inserted Successfully";
	}

	public List<Category> fetchAllCategories() {
		List<Category> list = new ArrayList<Category>();
		try {
			String query = "Select * from file";
			Connection conn = Configuration.conn;
			PreparedStatement p = conn.prepareStatement(query);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				list.add(new Category(rs.getShort("id"), rs.getString("name"), rs.getString("description")));

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return list;
	}

	public boolean deleteById(short id) {
		// TODO Auto-generated method stub
		try {
			String query = " delete from file where id = ?";
			Connection conn = Configuration.conn;
			PreparedStatement p = conn.prepareStatement(query);

			p.setShort(1, id);
			p.executeUpdate();

			return true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}

	public Category fetchCategory(short id) {
		// TODO Auto-generated method stub
		try {
			String query = "Select * from file where id = ?";

			Connection conn = Configuration.conn;

			PreparedStatement p = conn.prepareStatement(query);

			p.setShort(1, id);

			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				return new Category(rs.getShort("id"), rs.getString("name"), rs.getString("description"));

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return new Category();
	}

	public String update(category.Category c) {
		try {
			String query = "update file set name=?,description=? where id=?";

			Connection conn = Configuration.conn;

			PreparedStatement p = conn.prepareStatement(query);
			p.setString(1, c.getName());
			p.setString(2, c.getDescription());
			p.setShort(3, c.getId());

			p.executeUpdate();
		} catch (Exception e) {
			return e.getMessage();
		}
		{
			return "Category updated successfully";
		}
	}
}
