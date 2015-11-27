package com.test.app.services.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.test.app.configuration.Webconfiguration;
import com.test.app.entities.User;
import com.test.app.services.UserService;

@Repository //it will create object that need autowired
//@Service("")
public class UserServiceImpl implements UserService {
	
	//inject by using @Repository, @Service, or @Component
//	@Autowired
//	@Qualifier("getmydata") //specify this multiple functions are return
//	@Resource() // it replace @Autowired and Qualifier
	@Resource(name = "getmydata")
	DataSource ds;
	
//	inject via constructor
//	public StudentServiceImpl(DataSource datasource){
//		
//		this.ds = datasource;
//	}
	
//	inject via setter
//	public void setDatasource(DataSource tds){
//		
//		ds = tds
//	}

	@Override
	public boolean insertUser(User stu) {
		
		String sql = "insert into tbuser(username,email,password,birthdate,"
				+ "registerdate,image)values(?,?,?,?,?,?)";
		try(Connection cnn = new Webconfiguration().getdatasource().getConnection();
				PreparedStatement stm = cnn.prepareStatement(sql);){
			
			stm.setString(1, stu.getUserName());
			stm.setString(2, stu.getEmail());
			stm.setString(3, stu.getPasswd());
			stm.setDate(4, stu.getDOB());
			stm.setDate(5, stu.getRegDate());
			stm.setString(6, stu.getImg());
			
			if(stm.executeUpdate()==0)
				return false;
			
			return true;			
			
		}catch(Exception ex){
		
			ex.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean updateUser(User stu) {
		
		
		String sql = "update tbuser set username=?, email=?, password=?,"
				+ "birthdate=?, image=? where id=?";
		try(Connection cnn = new Webconfiguration().getdatasource().getConnection();
				PreparedStatement stm = cnn.prepareStatement(sql);){
			
			System.out.println("update"+stu.getUserName());
			stm.setString(1, stu.getUserName());
			stm.setString(2, stu.getEmail());
			stm.setString(3, stu.getPasswd());
			stm.setDate(4, stu.getDOB());
			stm.setString(5, stu.getImg());
			stm.setInt(6, stu.getId());
			
			if(stm.executeUpdate()==0)
				return false;
			
			
			return true;			
			
		}catch(Exception ex){
		
			ex.printStackTrace();
			return false;
		}		
	}

	@Override
	public boolean deleteUser(int id) {
		
		String sql = "delete from tbuser where id=?";
		
		try(Connection cnn = ds.getConnection();
				PreparedStatement stm = cnn.prepareStatement(sql);){	
			
			stm.setInt(1, id);
			if(stm.executeUpdate()==0)
				return false;
			
			return true;			
			
		}catch(Exception ex){
		
			ex.printStackTrace();
			return false;
		}		
	}

	@Override
	public ArrayList<User> listUser() {
		
		String sql = "select * from tbuser order by id";
		if(ds!=null)
			System.out.println("not null");
		try(Connection cnn = ds.getConnection();
				Statement stm = cnn.createStatement()){
			
			ResultSet rs = stm.executeQuery(sql);		
				
			ArrayList<User> listStu = new ArrayList<User>();
			User s;
			
			while(rs.next()){
				
				s = new User();
				
				s.setId(rs.getInt("id"));
				s.setUserName(rs.getString("username"));
				s.setEmail(rs.getString("email"));
				s.setPasswd(rs.getString("password"));
				s.setDOB(rs.getDate("birthdate"));
				s.setRegDate(rs.getDate("registerdate"));
				s.setImg(rs.getString("image"));
				listStu.add(s);				
			}
			return listStu;			
			
		}catch(Exception ex){
		
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public int getMaxID(String tblName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User getUser(int id) {
		
		String sql = "select * from tbuser where id=?";
		if(ds==null)
			{
				System.out.println("false");
				ds = new Webconfiguration().getdatasource();
			}
		try(Connection cnn = ds.getConnection();
				PreparedStatement stm = cnn.prepareStatement(sql);){	
			
			stm.setInt(1, id);			
			ResultSet rs = stm.executeQuery();
			User stu = new User();
			stu.setId(id);
			
			if(rs.next()){				
				
				stu.setUserName(rs.getString("username"));
				stu.setEmail(rs.getString("email"));
				stu.setPasswd(rs.getString("password"));
				stu.setDOB(rs.getDate("birthdate"));
				stu.setRegDate(rs.getDate("registerdate"));
				stu.setImg(rs.getString("image"));								
			}
			
			return stu;			
			
		}catch(Exception ex){		
			ex.printStackTrace();
			return null;
		}		
	}
	
	

}
