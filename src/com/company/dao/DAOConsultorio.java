/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.dao;

import java.util.ArrayList;
import java.sql.*;
import com.company.entidades.Consultorio;
import com.company.dao.DAOException;

/**
 *
 * @author User
 */
public class DAOConsultorio{
    
    private String DB_URL = "jdbc:mysql://localhost:3306/turneramedica";
    private String DB_USER = "root";
    private String DB_PASSWORD = "";
    
    public void create(Consultorio object) throws DAOException{
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO CONSULTORIOS(NOMBRE, ACTIVO) VALUES(?,?) ");
            System.out.println(preparedStatement);
            preparedStatement.setString(1, object.getNombre());
            preparedStatement.setInt(2, 1);
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
    
    public void update(Consultorio object, String primaryKey) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE CONSULTORIOS SET NOMBRE=?, ACTIVO=? WHERE NOMBRE=? ");
            System.out.println(preparedStatement);
            preparedStatement.setString(1, object.getNombre());
            preparedStatement.setInt(2, 1);
            preparedStatement.setString(3, primaryKey);
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
    
    public void delete(String primaryKey) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
//            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("DELETE FROM CONSULTORIOS WHERE NOMBRE=? ");
            System.out.println(preparedStatement);
            preparedStatement.setString(1, primaryKey);
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
    
//    public void deactivate(Consultorio object) throws DAOException{
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try{
//            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//            preparedStatement = connection.prepareStatement("UPDATE CONSULTORIOS SET NOMBRE=?, ACTIVO=? WHERE NOMBRE=? ");
//            System.out.println(preparedStatement);
//            preparedStatement.setString(1, object.getNombre());
//            preparedStatement.setInt(2, 0);
//            preparedStatement.setString(3, object.getNombre());
//            int result = preparedStatement.executeUpdate();
//            System.out.println("Registros actualizados " + result);
//        }catch (SQLException exception){
//            exception.printStackTrace();
//            throw new DAOException(exception.getMessage());
//        }finally {
//            try {
//                if (preparedStatement != null){
//                    preparedStatement.close();
//                }
//            }catch (SQLException exception){
//                exception.printStackTrace();
//                throw new DAOException(exception.getMessage());
//            }
//        }
//    }
    
    public void deactivate(String primaryKey) throws DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE CONSULTORIOS SET ACTIVO=0 WHERE NOMBRE=? ");
            System.out.println(preparedStatement);
            preparedStatement.setString(1, primaryKey);
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
    
//    public Consultorio search(int primaryKey) throws DAOException {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        Consultorio consultorio = null;
//        try{
////            Class.forName(DB_JDBC_DRIVER);
//            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//            preparedStatement = connection.prepareStatement("SELECT * FROM CONSULTORIOS WHERE ID=?");
//            System.out.println(preparedStatement);
//            preparedStatement.setInt(1, primaryKey);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()){
//                consultorio = new Consultorio();
//                consultorio.setId(resultSet.getInt("ID"));
//                consultorio.setNombre(resultSet.getString("NOMBRE"));
//            }
//        }catch (SQLException exception){
//            exception.printStackTrace();
//            throw new DAOException(exception.getMessage());
//        }finally {
//            try {
////                if (preparedStatement != null){
//                preparedStatement.close();
////                }
//            }catch (SQLException exception){
//                exception.printStackTrace();
//                throw new DAOException(exception.getMessage());
//            }
//        }
//        return consultorio;
//    }
//    
//    public ArrayList<Consultorio> searchAll(int active) throws DAOException {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        Consultorio consultorio = null;
//        ArrayList<Consultorio> consultorios = new ArrayList<Consultorio>();
//        try{
//            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//            preparedStatement = connection.prepareStatement("SELECT * FROM CONSULTORIOS WHERE ACTIVE=?");
//            System.out.println(preparedStatement);
//            preparedStatement.setInt(1, active);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()){
//                consultorio = new Consultorio();
//                consultorio.setId(resultSet.getInt("ID"));
//                consultorio.setNombre(resultSet.getString("NOMBRE"));
//                consultorios.add(consultorio);
//            }
//        }catch (SQLException exception){
//            exception.printStackTrace();
//            throw new DAOException(exception.getMessage());
//        }finally {
//            try {
//                if (preparedStatement != null){
//                    preparedStatement.close();
//                }
//            }catch (SQLException exception){
//                exception.printStackTrace();
//                throw new DAOException(exception.getMessage());
//            }
//        }
//        return consultorios;
//    }
}
