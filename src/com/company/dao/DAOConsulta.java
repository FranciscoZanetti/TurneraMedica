/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.dao;

import com.company.entidades.Consulta;
import com.company.entidades.Paciente;
import com.company.entidades.Medico;
import com.company.entidades.Consultorio;
import java.util.ArrayList;
import java.sql.*;
import com.company.dao.DAOException;
import java.time.LocalDate;


/**
 *
 * @author User
 */
public class DAOConsulta {
    
    private String DB_URL = "jdbc:mysql://localhost:3306/turneramedica";
    private String DB_USER = "root";
    private String DB_PASSWORD = "";
    
    public void create(Consulta object) throws DAOException{
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("INSERT INTO CONSULTAS(FECHA, HORA, MEDICO, PACIENTE, STATUS, CONSULTORIO, PRECIO) VALUES(?,?,?,?,?,?,?) ");
            System.out.println(preparedStatement);
            preparedStatement.setDate(1, object.getFechaAsDate());
            preparedStatement.setTime(2, object.getHoraAsTime());
            preparedStatement.setInt(3, object.getMedico().getDNI());
            preparedStatement.setInt(4, object.getPaciente().getDNI());
            preparedStatement.setInt(5, 1);
            preparedStatement.setString(6, object.getConsultorio());
            preparedStatement.setInt(7, object.getPrecio());
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
    
    public void delete(int primaryKey) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
//            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("DELETE FROM CONSULTAS WHERE ID=? ");
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
    
    public void deleteByMedico(int medico) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
//            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("DELETE FROM CONSULTAS WHERE MEDICO=? AND STATUS=1");
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, medico);
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
    
    public void deactivate(Consulta object) throws DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("UPDATE CONSULTAS SET STATUS=0 WHERE ID=? ");
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, object.getId());
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
    
    public ArrayList<Consulta> searchAllBetweenByMedico(int dni_medico, LocalDate start, LocalDate end) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Medico medico = null;
        Paciente paciente = null;
        Consulta consulta = null;
        ArrayList<Consulta> consultas = new ArrayList<Consulta>();
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM (CONSULTAS INNER JOIN MEDICOS ON CONSULTAS.MEDICO = MEDICOS.DNI) LEFT JOIN PACIENTES ON CONSULTAS.PACIENTE = PACIENTES.DNI WHERE CONSULTAS.FECHA BETWEEN ? AND ? AND MEDICOS.DNI = ? AND STATUS = 0 ");
            System.out.println(preparedStatement);
            preparedStatement.setDate(1, Date.valueOf(start));
            preparedStatement.setDate(2, Date.valueOf(end));
            preparedStatement.setInt(3, dni_medico);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                medico = new Medico();
                medico.setDNI(resultSet.getInt("MEDICOS.DNI"));
                medico.setNombre(resultSet.getString("MEDICOS.NOMBRE"));
                medico.setApellido(resultSet.getString("MEDICOS.APELLIDO"));
                medico.setObrasocial(resultSet.getString("MEDICOS.OBRASOCIAL"));
                medico.setHonorarios(resultSet.getInt("MEDICOS.HONORARIOS"));
                paciente = new Paciente();
                paciente.setDNI(resultSet.getInt("PACIENTES.DNI"));
                paciente.setNombre(resultSet.getString("PACIENTES.NOMBRE"));
                paciente.setApellido(resultSet.getString("PACIENTES.APELLIDO"));
                paciente.setObrasocial(resultSet.getString("PACIENTES.OBRASOCIAL"));
                paciente.setNacimiento(resultSet.getDate("PACIENTES.NACIMIENTO").toLocalDate());
                paciente.setSexo(resultSet.getString("PACIENTES.SEXO"));
                consulta = new Consulta();
                consulta.setId(resultSet.getInt("ID"));
                consulta.setFecha(resultSet.getDate("FECHA").toLocalDate());
                consulta.setHora(resultSet.getTime("HORA").toLocalTime());
                consulta.setConsultorio(resultSet.getString("CONSULTORIO"));
                consulta.setPrecio(resultSet.getInt("PRECIO"));
                consulta.setMedico(medico);
                consulta.setPaciente(paciente);
                consultas.add(consulta);
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
        return consultas;
    }
    
    public ArrayList<Consulta> searchAllBetween(int activo, LocalDate start, LocalDate end) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Medico medico = null;
        Paciente paciente = null;
        Consulta consulta = null;
        ArrayList<Consulta> consultas = new ArrayList<Consulta>();
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM (CONSULTAS INNER JOIN MEDICOS ON CONSULTAS.MEDICO = MEDICOS.DNI) LEFT JOIN PACIENTES ON CONSULTAS.PACIENTE = PACIENTES.DNI WHERE CONSULTAS.FECHA BETWEEN ? AND ? AND MEDICOS.ACTIVO = ? AND STATUS = 0 ");
            System.out.println(preparedStatement);
            preparedStatement.setDate(1, Date.valueOf(start));
            preparedStatement.setDate(2, Date.valueOf(end));
            preparedStatement.setInt(3, activo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                medico = new Medico();
                medico.setDNI(resultSet.getInt("MEDICOS.DNI"));
                medico.setNombre(resultSet.getString("MEDICOS.NOMBRE"));
                medico.setApellido(resultSet.getString("MEDICOS.APELLIDO"));
                medico.setObrasocial(resultSet.getString("MEDICOS.OBRASOCIAL"));
                medico.setHonorarios(resultSet.getInt("MEDICOS.HONORARIOS"));
                paciente = new Paciente();
                paciente.setDNI(resultSet.getInt("PACIENTES.DNI"));
                paciente.setNombre(resultSet.getString("PACIENTES.NOMBRE"));
                paciente.setApellido(resultSet.getString("PACIENTES.APELLIDO"));
                paciente.setObrasocial(resultSet.getString("PACIENTES.OBRASOCIAL"));
                paciente.setNacimiento(resultSet.getDate("PACIENTES.NACIMIENTO").toLocalDate());
                paciente.setSexo(resultSet.getString("PACIENTES.SEXO"));
                consulta = new Consulta();
                consulta.setId(resultSet.getInt("ID"));
                consulta.setFecha(resultSet.getDate("FECHA").toLocalDate());
                consulta.setHora(resultSet.getTime("HORA").toLocalTime());
                consulta.setConsultorio(resultSet.getString("CONSULTORIO"));
                consulta.setPrecio(resultSet.getInt("PRECIO"));
                consulta.setMedico(medico);
                consulta.setPaciente(paciente);
                consultas.add(consulta);
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
        return consultas;
    }
    
    public ArrayList<Consulta> searchAllByMedico(int dni_medico, int status) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Medico medico = null;
        Paciente paciente = null;
        Consulta consulta = null;
        ArrayList<Consulta> consultas = new ArrayList<Consulta>();
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM (CONSULTAS INNER JOIN MEDICOS ON CONSULTAS.MEDICO = MEDICOS.DNI) LEFT JOIN PACIENTES ON CONSULTAS.PACIENTE = PACIENTES.DNI WHERE MEDICOS.DNI = ? AND STATUS = ? ");
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, dni_medico);
            preparedStatement.setInt(2, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                medico = new Medico();
                medico.setDNI(resultSet.getInt("MEDICOS.DNI"));
                medico.setNombre(resultSet.getString("MEDICOS.NOMBRE"));
                medico.setApellido(resultSet.getString("MEDICOS.APELLIDO"));
                medico.setObrasocial(resultSet.getString("MEDICOS.OBRASOCIAL"));
                medico.setHonorarios(resultSet.getInt("MEDICOS.HONORARIOS"));
                paciente = new Paciente();
                paciente.setDNI(resultSet.getInt("PACIENTES.DNI"));
                paciente.setNombre(resultSet.getString("PACIENTES.NOMBRE"));
                paciente.setApellido(resultSet.getString("PACIENTES.APELLIDO"));
                paciente.setObrasocial(resultSet.getString("PACIENTES.OBRASOCIAL"));
                paciente.setNacimiento(resultSet.getDate("PACIENTES.NACIMIENTO").toLocalDate());
                paciente.setSexo(resultSet.getString("PACIENTES.SEXO"));
                consulta = new Consulta();
                consulta.setId(resultSet.getInt("ID"));
                consulta.setFecha(resultSet.getDate("FECHA").toLocalDate());
                consulta.setHora(resultSet.getTime("HORA").toLocalTime());
                consulta.setConsultorio(resultSet.getString("CONSULTORIO"));
                consulta.setPrecio(resultSet.getInt("PRECIO"));
                consulta.setMedico(medico);
                consulta.setPaciente(paciente);
                consultas.add(consulta);
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
        return consultas;
    }
    
    public ArrayList<Consulta> searchAll(int activo, int status) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Medico medico = null;
        Paciente paciente = null;
        Consulta consulta = null;
        ArrayList<Consulta> consultas = new ArrayList<Consulta>();
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM (CONSULTAS INNER JOIN MEDICOS ON CONSULTAS.MEDICO = MEDICOS.DNI) LEFT JOIN PACIENTES ON CONSULTAS.PACIENTE = PACIENTES.DNI WHERE MEDICOS.ACTIVO = ? AND STATUS = ? ");
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, activo);
            preparedStatement.setInt(2, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                medico = new Medico();
                medico.setDNI(resultSet.getInt("MEDICOS.DNI"));
                medico.setNombre(resultSet.getString("MEDICOS.NOMBRE"));
                medico.setApellido(resultSet.getString("MEDICOS.APELLIDO"));
                medico.setObrasocial(resultSet.getString("MEDICOS.OBRASOCIAL"));
                medico.setHonorarios(resultSet.getInt("MEDICOS.HONORARIOS"));
                paciente = new Paciente();
                paciente.setDNI(resultSet.getInt("PACIENTES.DNI"));
                paciente.setNombre(resultSet.getString("PACIENTES.NOMBRE"));
                paciente.setApellido(resultSet.getString("PACIENTES.APELLIDO"));
                paciente.setObrasocial(resultSet.getString("PACIENTES.OBRASOCIAL"));
                paciente.setNacimiento(resultSet.getDate("PACIENTES.NACIMIENTO").toLocalDate());
                paciente.setSexo(resultSet.getString("PACIENTES.SEXO"));
                consulta = new Consulta();
                consulta.setId(resultSet.getInt("ID"));
                consulta.setFecha(resultSet.getDate("FECHA").toLocalDate());
                consulta.setHora(resultSet.getTime("HORA").toLocalTime());
                consulta.setConsultorio(resultSet.getString("CONSULTORIO"));
                consulta.setPrecio(resultSet.getInt("PRECIO"));
                consulta.setMedico(medico);
                consulta.setPaciente(paciente);
                consultas.add(consulta);
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
        return consultas;
    }
    
    public ArrayList<Consulta> searchAllByPaciente(int dni_paciente, int status) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Medico medico = null;
        Paciente paciente = null;
        Consulta consulta = null;
        ArrayList<Consulta> consultas = new ArrayList<Consulta>();
        try{
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM (CONSULTAS INNER JOIN MEDICOS ON CONSULTAS.MEDICO = MEDICOS.DNI) LEFT JOIN PACIENTES ON CONSULTAS.PACIENTE = PACIENTES.DNI WHERE PACIENTES.DNI = ? AND STATUS = ? ");
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, dni_paciente);
            preparedStatement.setInt(2, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                medico = new Medico();
                medico.setDNI(resultSet.getInt("MEDICOS.DNI"));
                medico.setNombre(resultSet.getString("MEDICOS.NOMBRE"));
                medico.setApellido(resultSet.getString("MEDICOS.APELLIDO"));
                medico.setObrasocial(resultSet.getString("MEDICOS.OBRASOCIAL"));
                medico.setHonorarios(resultSet.getInt("MEDICOS.HONORARIOS"));
                paciente = new Paciente();
                paciente.setDNI(resultSet.getInt("PACIENTES.DNI"));
                paciente.setNombre(resultSet.getString("PACIENTES.NOMBRE"));
                paciente.setApellido(resultSet.getString("PACIENTES.APELLIDO"));
                paciente.setObrasocial(resultSet.getString("PACIENTES.OBRASOCIAL"));
                paciente.setNacimiento(resultSet.getDate("PACIENTES.NACIMIENTO").toLocalDate());
                paciente.setSexo(resultSet.getString("PACIENTES.SEXO"));
                consulta = new Consulta();
                consulta.setId(resultSet.getInt("ID"));
                consulta.setFecha(resultSet.getDate("FECHA").toLocalDate());
                consulta.setHora(resultSet.getTime("HORA").toLocalTime());
                consulta.setConsultorio(resultSet.getString("CONSULTORIO"));
                consulta.setPrecio(resultSet.getInt("PRECIO"));
                consulta.setMedico(medico);
                consulta.setPaciente(paciente);
                consultas.add(consulta);
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
        return consultas;
    }
    
}
