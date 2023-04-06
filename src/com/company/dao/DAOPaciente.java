package com.company.dao;

import com.company.entidades.Paciente;
import java.util.ArrayList;
import java.sql.*;

public class DAOPaciente implements IDAO<Paciente>{

//    private String DB_JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://localhost:3306/turneramedica";
    private String DB_USER = "root";
    private String DB_PASSWORD = "";

    @Override
    public void create(Paciente object) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
//            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER
                    , DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO PACIENTES(DNI, NOMBRE, APELLIDO, NACIMIENTO, OBRASOCIAL, SEXO, PASSWORD) VALUES(?,?,?,?,?,?,?) ");
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, object.getDNI());
            preparedStatement.setString(2, object.getNombre());
            preparedStatement.setString(3, object.getApellido());
            preparedStatement.setDate(4, object.getNacimientoAsDate());
            preparedStatement.setString(5, object.getObrasocial());
            preparedStatement.setString(6, object.getSexo());
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
    public void update(Paciente object, int primaryKey) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
//            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER
                    , DB_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE PACIENTES SET DNI=?, NOMBRE=?, APELLIDO=?, NACIMIENTO=?, OBRASOCIAL=?, SEXO=?, PASSWORD=? WHERE DNI=? ");
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, object.getDNI());
            preparedStatement.setString(2, object.getNombre());
            preparedStatement.setString(3, object.getApellido());
            preparedStatement.setDate(4, object.getNacimientoAsDate());
            preparedStatement.setString(5, object.getObrasocial());
            preparedStatement.setString(6, object.getSexo());
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
            connection = DriverManager.getConnection(DB_URL, DB_USER
                    , DB_PASSWORD);
            preparedStatement = connection.prepareStatement("DELETE FROM PACIENTES WHERE DNI=? ");
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

    @Override
    public Paciente search(int primaryKey) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Paciente paciente = null;
        try{
//            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER
                    , DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM PACIENTES WHERE DNI=? ");
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, primaryKey);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                paciente = new Paciente();
                paciente.setDNI(resultSet.getInt("DNI"));
                paciente.setNombre(resultSet.getString("NOMBRE"));
                paciente.setApellido(resultSet.getString("APELLIDO"));
                paciente.setObrasocial(resultSet.getString("OBRASOCIAL"));
                paciente.setNacimiento(resultSet.getDate("NACIMIENTO").toLocalDate());
                paciente.setSexo(resultSet.getString("SEXO"));
                paciente.setPassword(resultSet.getString("PASSWORD"));
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
        return paciente;
    }

    public ArrayList<Paciente> searchAll() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Paciente paciente = null;
        ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER
                    , DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM PACIENTES ORDER BY APELLIDO");
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                paciente = new Paciente();
                paciente.setDNI(resultSet.getInt("DNI"));
                paciente.setNombre(resultSet.getString("NOMBRE"));
                paciente.setApellido(resultSet.getString("APELLIDO"));
                paciente.setObrasocial(resultSet.getString("OBRASOCIAL"));
                paciente.setNacimiento(resultSet.getDate("NACIMIENTO").toLocalDate());
                paciente.setSexo(resultSet.getString("SEXO"));
                paciente.setPassword(resultSet.getString("PASSWORD"));
                pacientes.add(paciente);
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
        return pacientes;
    }
    
    public Paciente login(int primaryKey, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Paciente paciente = null;
        try{
//            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER
                    , DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM PACIENTES WHERE DNI=? AND PASSWORD=?");
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, primaryKey);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                paciente = new Paciente();
                paciente.setDNI(resultSet.getInt("DNI"));
                paciente.setNombre(resultSet.getString("NOMBRE"));
                paciente.setApellido(resultSet.getString("APELLIDO"));
                paciente.setObrasocial(resultSet.getString("OBRASOCIAL"));
                paciente.setNacimiento(resultSet.getDate("NACIMIENTO").toLocalDate());
                paciente.setSexo(resultSet.getString("SEXO"));
                paciente.setPassword(resultSet.getString("PASSWORD"));
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
        return paciente;
    }
}
