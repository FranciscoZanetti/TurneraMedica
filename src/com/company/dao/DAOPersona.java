/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package com.company.dao;
//
//import com.company.entidades.Paciente;
//import com.company.entidades.Medico;
//import com.company.entidades.Persona;
//import java.util.ArrayList;
//import java.sql.*;
//
///**
// *
// * @author User
// */
//public class DAOPersona {
//    
//    private String DB_URL = "jdbc:mysql://localhost:3306/turneramedica";
//    private String DB_USER = "root";
//    private String DB_PASSWORD = "";
//    
//    public Persona login(int primaryKey, String password) throws DAOException {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        Paciente paciente = null;
//        Medico medico = null;
//        try{
////            Class.forName(DB_JDBC_DRIVER);
//            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//            preparedStatement = connection.prepareStatement("SELECT * FROM PACIENTES WHERE DNI=? AND PASSWORD=?");
//            System.out.println(preparedStatement);
//            preparedStatement.setInt(1, primaryKey);
//            preparedStatement.setString(2, password);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (!resultSet.next()){
//                try {
////                  if (preparedStatement != null){
//                    preparedStatement.close();
////                  }
//                }catch (SQLException exception){
//                    exception.printStackTrace();
//                    throw new DAOException(exception.getMessage());
//                }finally{
//                    connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//                    preparedStatement = connection.prepareStatement("SELECT * FROM MEDICOS WHERE DNI=? AND PASSWORD=?");
//                    System.out.println(preparedStatement);
//                    preparedStatement.setInt(1, primaryKey);
//                    preparedStatement.setString(2, password);
//                    resultSet = preparedStatement.executeQuery();
//                    while (resultSet.next()){
//                        paciente = new Paciente();
//                        paciente.setDNI(resultSet.getInt("DNI"));
//                        paciente.setNombre(resultSet.getString("NOMBRE"));
//                        paciente.setApellido(resultSet.getString("APELLIDO"));
//                        paciente.setObrasocial(resultSet.getString("OBRASOCIAL"));
//                        paciente.setNacimiento(resultSet.getDate("NACIMIENTO").toLocalDate());
//                        paciente.setSexo(resultSet.getString("SEXO"));
//                        paciente.setPassword(resultSet.getString("PASSWORD"));
//                    }
//                }
//            }
//            while (resultSet.next()){
//                paciente = new Paciente();
//                paciente.setDNI(resultSet.getInt("DNI"));
//                paciente.setNombre(resultSet.getString("NOMBRE"));
//                paciente.setApellido(resultSet.getString("APELLIDO"));
//                paciente.setObrasocial(resultSet.getString("OBRASOCIAL"));
//                paciente.setNacimiento(resultSet.getDate("NACIMIENTO").toLocalDate());
//                paciente.setSexo(resultSet.getString("SEXO"));
//                paciente.setPassword(resultSet.getString("PASSWORD"));
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
//        return paciente;
//    }
//}
