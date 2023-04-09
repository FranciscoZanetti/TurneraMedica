/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.dao;

import java.sql.*;
import com.company.entidades.ConsultorioMedico;
import com.company.entidades.Medico;
import com.company.dao.DAOException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class DAOConsultorioMedico{
    
    private String DB_URL = "jdbc:mysql://localhost:3306/turneramedica";
    private String DB_USER = "root";
    private String DB_PASSWORD = "";
    
    public void create(ConsultorioMedico object) throws DAOException{
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO CONSULTORIOS_MEDICOS(ID_CONSULTORIO, DNI_MEDICO, DIA, ENTRADA, SALIDA) VALUES(?,?,?,?,?) ");
            System.out.println(preparedStatement);
            preparedStatement.setString(1, object.getConsultorio());
            preparedStatement.setInt(2, object.getMedico().getDNI());
            preparedStatement.setString(3, object.getDia());
            preparedStatement.setTime(4, object.getEntradaAsTime());
            preparedStatement.setTime(5, object.getSalidaAsTime());
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
    
    public void update(ConsultorioMedico object) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE CONSULTORIOS_MEDICOS SET ID_CONSULTORIO=?, DNI_MEDICO=?, DIA=?, ENTRADA=?, SALIDA=? WHERE ID=? ");
            System.out.println(preparedStatement);
            preparedStatement.setString(1, object.getConsultorio());
            preparedStatement.setInt(2, object.getMedico().getDNI());
            preparedStatement.setString(3, object.getDia());
            preparedStatement.setTime(4, object.getEntradaAsTime());
            preparedStatement.setTime(5, object.getSalidaAsTime());
            preparedStatement.setInt(6, object.getId());
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
    
    public void delete(int primaryKey) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
//            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("DELETE FROM CONSULTORIOS_MEDICOS WHERE ID=? ");
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
    
    public void deleteByMedico(int dni) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
//            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("DELETE FROM CONSULTORIOS_MEDICOS WHERE DNI_MEDICO=? ");
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, dni);
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
    
    public ConsultorioMedico search(int primaryKey) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ConsultorioMedico consultorioMedico = null;
        Medico medico = null;
        try{
//            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM CONSULTORIOS_MEDICOS INNER JOIN MEDICOS ON CONSULTORIOS_MEDICOS.DNI_MEDICO = MEDICOS.DNI WHERE ID=?");
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
                consultorioMedico = new ConsultorioMedico();
                consultorioMedico.setId(resultSet.getInt("ID"));
                consultorioMedico.setConsultorio(resultSet.getString("ID_CONSULTORIO"));
                consultorioMedico.setMedico(medico);
                consultorioMedico.setDia(resultSet.getString("DIA"));
                consultorioMedico.setEntrada(resultSet.getTime("ENTRADA").toLocalTime());
                consultorioMedico.setSalida(resultSet.getTime("SALIDA").toLocalTime());
                
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
        return consultorioMedico;
    }
    
    public ArrayList<ConsultorioMedico> searchAll(int dni) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Medico medico = null;
        ConsultorioMedico consultorioMedico = null;
        ArrayList<ConsultorioMedico> consultoriosMedicos = new ArrayList<ConsultorioMedico>();
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM CONSULTORIOS_MEDICOS INNER JOIN MEDICOS ON CONSULTORIOS_MEDICOS.DNI_MEDICO = MEDICOS.DNI WHERE MEDICOS.DNI=?");
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, dni);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                medico = new Medico();
                medico.setDNI(resultSet.getInt("DNI"));
                medico.setNombre(resultSet.getString("NOMBRE"));
                medico.setApellido(resultSet.getString("APELLIDO"));
                medico.setObrasocial(resultSet.getString("OBRASOCIAL"));
                medico.setHonorarios(resultSet.getInt("HONORARIOS"));
                consultorioMedico = new ConsultorioMedico();
                consultorioMedico.setId(resultSet.getInt("ID"));
                consultorioMedico.setConsultorio(resultSet.getString("ID_CONSULTORIO"));
                consultorioMedico.setMedico(medico);
                consultorioMedico.setDia(resultSet.getString("DIA"));
                consultorioMedico.setEntrada(resultSet.getTime("ENTRADA").toLocalTime());
                consultorioMedico.setSalida(resultSet.getTime("SALIDA").toLocalTime());
                consultoriosMedicos.add(consultorioMedico);
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
        return consultoriosMedicos;
    }
    
}
