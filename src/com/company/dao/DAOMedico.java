/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.dao;

import java.util.ArrayList;
import java.sql.*;
import com.company.entidades.Medico;

/**
 *
 * @author User
 */
public class DAOMedico implements IDAO<Medico>{
    
    private String DB_URL = "jdbc:mysql://localhost:3306/turneramedica";
    private String DB_USER = "root";
    private String DB_PASSWORD = "";
    
    @Override
    public void create(Medico object) throws DAOException{
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO MEDICOS(DNI, NOMBRE, APELLIDO, HONORARIOS, OBRASOCIAL, ACTIVO, PASSWORD) VALUES(?,?,?,?,?,?,?) ");
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, object.getDNI());
            preparedStatement.setString(2, object.getNombre());
            preparedStatement.setString(3, object.getApellido());
            preparedStatement.setInt(4, object.getHonorarios());
            preparedStatement.setString(5, object.getObrasocial());
            preparedStatement.setInt(6, 1);
            preparedStatement.setString(7, object.getPassword());
            int result = preparedStatement.executeUpdate();
            System.out.println("Registros ingresados " + result);
        }catch (SQLException exception){
            exception.printStackTrace();
            throw new DAOException(exception.getMessage());
        }finally {
            try {
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch (SQLException exception){
                exception.printStackTrace();
                throw new DAOException(exception.getMessage());
            }
        }
    }
    
    @Override
    public void update(Medico object, int primaryKey) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE MEDICOS SET DNI=?, NOMBRE=?, APELLIDO=?, HONORARIOS=?, OBRASOCIAL=?, ACTIVO=?, PASSWORD=? WHERE DNI=? ");
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, object.getDNI());
            preparedStatement.setString(2, object.getNombre());
            preparedStatement.setString(3, object.getApellido());
            preparedStatement.setInt(4, object.getHonorarios());
            preparedStatement.setString(5, object.getObrasocial());
            preparedStatement.setInt(6, 1);
            preparedStatement.setString(7, object.getPassword());
            preparedStatement.setInt(8, primaryKey);
            int result = preparedStatement.executeUpdate();
            System.out.println("Registros actualizados " + result);
        }catch (SQLException exception){
            exception.printStackTrace();
            throw new DAOException(exception.getMessage());
        }finally {
            try {
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch (SQLException exception){
                exception.printStackTrace();
                throw new DAOException(exception.getMessage());
            }
        }
    }
    
    @Override
    public void delete(int primaryKey) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
//            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("DELETE FROM MEDICOS WHERE DNI=? ");
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, primaryKey);
            int result = preparedStatement.executeUpdate();
            System.out.println("Registros eliminados " + result);
        }catch (SQLException exception){
            exception.printStackTrace();
            throw new DAOException(exception.getMessage());
        }finally {
            try {
//                if (preparedStatement != null){
                    preparedStatement.close();
//                }
            }catch (SQLException exception){
                exception.printStackTrace();
                throw new DAOException(exception.getMessage());
            }
        }
    }
    
    public void deactivate(Medico object, int primaryKey) throws DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE MEDICOS SET DNI=?, NOMBRE=?, APELLIDO=?, HONORARIOS=?, OBRASOCIAL=?, ACTIVO=?, PASSWORD=? WHERE DNI=? ");
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, object.getDNI());
            preparedStatement.setString(2, object.getNombre());
            preparedStatement.setString(3, object.getApellido());
            preparedStatement.setInt(4, object.getHonorarios());
            preparedStatement.setString(5, object.getObrasocial());
            preparedStatement.setInt(6, 0);
            preparedStatement.setString(7, object.getPassword());
            preparedStatement.setInt(8, primaryKey);
            int result = preparedStatement.executeUpdate();
            System.out.println("Registros actualizados " + result);
        }catch (SQLException exception){
            exception.printStackTrace();
            throw new DAOException(exception.getMessage());
        }finally {
            try {
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch (SQLException exception){
                exception.printStackTrace();
                throw new DAOException(exception.getMessage());
            }
        }
    }
    
    @Override
    public Medico search(int primaryKey) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Medico medico = null;
        try{
//            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM MEDICOS WHERE DNI=?");
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, primaryKey);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                medico = new Medico();
                medico.setDNI(resultSet.getInt("DNI"));
                medico.setNombre(resultSet.getString("NOMBRE"));
                medico.setApellido(resultSet.getString("APELLIDO"));
                medico.setObrasocial(resultSet.getString("OBRASOCIAL"));
                medico.setHonorarios(resultSet.getInt("HONORARIOS"));
                medico.setPassword(resultSet.getString("PASSWORD"));
            }
        }catch (SQLException exception){
            exception.printStackTrace();
            throw new DAOException(exception.getMessage());
        }finally {
            try {
//                if (preparedStatement != null){
                preparedStatement.close();
//                }
            }catch (SQLException exception){
                exception.printStackTrace();
                throw new DAOException(exception.getMessage());
            }
        }
        return medico;
    }
    
    public ArrayList<Medico> searchAll(int active) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Medico medico = null;
        ArrayList<Medico> medicos = new ArrayList<Medico>();
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM MEDICOS WHERE ACTIVO=? ORDER BY APELLIDO");
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, active);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                medico = new Medico();
                medico.setDNI(resultSet.getInt("DNI"));
                medico.setNombre(resultSet.getString("NOMBRE"));
                medico.setApellido(resultSet.getString("APELLIDO"));
                medico.setObrasocial(resultSet.getString("OBRASOCIAL"));
                medico.setHonorarios(resultSet.getInt("HONORARIOS"));
                medico.setPassword(resultSet.getString("PASSWORD"));
                medicos.add(medico);
            }
        }catch (SQLException exception){
            exception.printStackTrace();
            throw new DAOException(exception.getMessage());
        }finally {
            try {
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch (SQLException exception){
                exception.printStackTrace();
                throw new DAOException(exception.getMessage());
            }
        }
        return medicos;
    }
    
    public Medico login(int primaryKey, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Medico medico = null;
        try{
//            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM MEDICOS WHERE DNI=? AND PASSWORD=? AND ACTIVO=1");
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, primaryKey);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                medico = new Medico();
                medico.setDNI(resultSet.getInt("DNI"));
                medico.setNombre(resultSet.getString("NOMBRE"));
                medico.setApellido(resultSet.getString("APELLIDO"));
                medico.setObrasocial(resultSet.getString("OBRASOCIAL"));
                medico.setHonorarios(resultSet.getInt("HONORARIOS"));
                medico.setPassword(resultSet.getString("PASSWORD"));
            }
        }catch (SQLException exception){
            exception.printStackTrace();
            throw new DAOException(exception.getMessage());
        }finally {
            try {
//                if (preparedStatement != null){
                preparedStatement.close();
//                }
            }catch (SQLException exception){
                exception.printStackTrace();
                throw new DAOException(exception.getMessage());
            }
        }
        return medico;
    }
    
    public ArrayList<Medico> searchAllByApellido(int active, String apellido) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Medico medico = null;
        ArrayList<Medico> medicos = new ArrayList<Medico>();
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM MEDICOS WHERE ACTIVO=? AND APELLIDO LIKE ? ORDER BY APELLIDO");
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, active);
            preparedStatement.setString(2, apellido+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                medico = new Medico();
                medico.setDNI(resultSet.getInt("DNI"));
                medico.setNombre(resultSet.getString("NOMBRE"));
                medico.setApellido(resultSet.getString("APELLIDO"));
                medico.setObrasocial(resultSet.getString("OBRASOCIAL"));
                medico.setHonorarios(resultSet.getInt("HONORARIOS"));
                medico.setPassword(resultSet.getString("PASSWORD"));
                medicos.add(medico);
            }
        }catch (SQLException exception){
            exception.printStackTrace();
            throw new DAOException(exception.getMessage());
        }finally {
            try {
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch (SQLException exception){
                exception.printStackTrace();
                throw new DAOException(exception.getMessage());
            }
        }
        return medicos;
    }
    
}
