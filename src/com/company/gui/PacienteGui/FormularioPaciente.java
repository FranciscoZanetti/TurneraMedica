package com.company.gui.PacienteGui;

import com.company.entidades.Paciente;
import com.company.gui.PanelManager;
import com.company.service.ServiceException;
import com.company.service.ServicePaciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class FormularioPaciente extends JPanel {

    ServicePaciente servicePaciente;

    JPanel formularioPaciente;
    JPanel mostrarPacientes;

    JLabel jLabelNombre;
    JLabel jLabelApellido;
    JLabel jLabelDNI;
    JLabel jLabelObraSocial;
    JLabel jLabelNacimiento;
    JLabel jLabelSexo;


    JTextField jTextFieldNombre;
    JTextField jTextFieldApellido;
    JTextField jTextFieldDNI;
    JTextField jTextFieldObraSocial;
    JTextField jTextFieldNacimiento;
    ButtonGroup buttonGroupSexo;
    JRadioButton jRadioButtonHombre;
    JRadioButton jRadioButtonMujer;



    JButton jButtonCreate;
    JButton jButtonUpdate;
    JButton jButtonDelete;

    JLabel jLabelSearch;
    JTextField searchBar;
    JButton jButtonSearch;
    JPanel panelSearch;
    Paciente paciente;

    JButton jButtonRefreshPacientes;


    PanelManager panel;


    JPanel buttons;

    JTextArea jTextAreaPacientes;
    JScrollPane jScrollPanePacientes;

//    JOptionPane jOptionPane;


    public void armarPanel() throws ServiceException {

        formularioPaciente = new JPanel();
        formularioPaciente.setLayout(new GridLayout(8,1));

        jLabelNombre = new JLabel("Nombre");
        jTextFieldNombre = new JTextField(20);

        jLabelApellido = new JLabel("Apellido");
        jTextFieldApellido = new JTextField(20);

        jLabelDNI = new JLabel("DNI");
        jTextFieldDNI = new JTextField(20);

        jLabelObraSocial = new JLabel("Obra Social");
        jTextFieldObraSocial = new JTextField(20);

        jLabelNacimiento = new JLabel("Fecha de nacimiento  (yyyy-MM-dd)");
        jTextFieldNacimiento = new JTextField(20);

        jLabelSexo = new JLabel("Sexo");
        buttonGroupSexo = new ButtonGroup();
        jRadioButtonHombre = new JRadioButton("Hombre");
        jRadioButtonHombre.setActionCommand("hombre");
        jRadioButtonMujer = new JRadioButton("Mujer");
        jRadioButtonMujer.setActionCommand("mujer");
        buttonGroupSexo.add(jRadioButtonHombre);
        buttonGroupSexo.add(jRadioButtonMujer);


        buttons = new JPanel();
//        buttons.setLayout(new BorderLayout());
//        buttons.setLayout(new GridLayout(2, 3));

        jButtonCreate = new JButton("Crear Paciente");
        jButtonUpdate = new JButton("Editar Paciente");
        jButtonDelete = new JButton("Eliminar Paciente");
        searchBar = new JTextField(10);
//        jLabelSearch = new JLabel("Buscar");
        jButtonSearch = new JButton("Buscar");

//        buttons.add(searchBar, BorderLayout.PAGE_START);
//        buttons.add(jButtonSearch, BorderLayout.PAGE_START);
//        buttons.add(jButtonCreate, BorderLayout.LINE_START);
//        buttons.add(jButtonUpdate, BorderLayout.CENTER);
//        buttons.add(jButtonDelete, BorderLayout.LINE_END);

//        buttons.add(searchBar, 2,0);
//        buttons.add(jButtonSearch, 1,0);
//        buttons.add(jButtonCreate, 1,0);
//        buttons.add(jButtonUpdate, 1,1);
//        buttons.add(jButtonDelete, 1,2);

        buttons.add(searchBar);
        buttons.add(jButtonSearch);
        buttons.add(jButtonCreate);
        buttons.add(jButtonUpdate);
        buttons.add(jButtonDelete);

        add(buttons, BorderLayout.NORTH);

//        panelSearch = new JPanel();
////        panelSearch.setLayout(new GridLayout(1,2));
//
//        searchBar = new JTextField(20);
////        jLabelSearch = new JLabel("Buscar");
//        jButtonSearch = new JButton("Buscar");
//        add(panelSearch, BorderLayout.SOUTH);



        mostrarPacientes = new JPanel();
        mostrarPacientes.setLayout(new BorderLayout());
        jButtonRefreshPacientes = new JButton("Recargar Pacientes");

        jTextAreaPacientes = new JTextArea(5, 30);
        jTextAreaPacientes.setEditable(false);
        jScrollPanePacientes = new JScrollPane(jTextAreaPacientes);
        jScrollPanePacientes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPanePacientes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        mostrarPacientes.add(jTextAreaPacientes);
        mostrarPacientes.add(jScrollPanePacientes, BorderLayout.NORTH);
        mostrarPacientes.add(jButtonRefreshPacientes, BorderLayout.SOUTH);

        add(mostrarPacientes, BorderLayout.SOUTH);
        refreshPanel();


        jButtonRefreshPacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    refreshPanel();
                } catch (ServiceException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(exception);
                }
            }
        });

        jButtonCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                formularioPaciente.add(jLabelNombre);
                formularioPaciente.add(jTextFieldNombre);

                formularioPaciente.add(jLabelApellido);
                formularioPaciente.add(jTextFieldApellido);

                formularioPaciente.add(jLabelDNI);
                formularioPaciente.add(jTextFieldDNI);

                formularioPaciente.add(jLabelObraSocial);
                formularioPaciente.add(jTextFieldObraSocial);

                formularioPaciente.add(jLabelNacimiento);
                formularioPaciente.add(jTextFieldNacimiento);

                formularioPaciente.add(jLabelSexo);
                formularioPaciente.add(jRadioButtonHombre);
                formularioPaciente.add(jRadioButtonMujer);
//                jRadioButtonHombre.setSelected(false);
//                jRadioButtonMujer.setSelected(false);

                openWindow(formularioPaciente, "save");

                formularioPaciente.remove(jLabelNombre);
                formularioPaciente.remove(jTextFieldNombre);

                formularioPaciente.remove(jLabelApellido);
                formularioPaciente.remove(jTextFieldApellido);

                formularioPaciente.remove(jLabelDNI);
                formularioPaciente.remove(jTextFieldDNI);

                formularioPaciente.remove(jLabelObraSocial);
                formularioPaciente.remove(jTextFieldObraSocial);

                formularioPaciente.remove(jLabelNacimiento);
                formularioPaciente.remove(jTextFieldNacimiento);

                formularioPaciente.remove(jLabelSexo);
                formularioPaciente.remove(jRadioButtonHombre);
                formularioPaciente.remove(jRadioButtonMujer);

            }
        });

        jButtonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (paciente != null){
                    formularioPaciente.add(jLabelNombre);
                    formularioPaciente.add(jTextFieldNombre);
                    jTextFieldNombre.setText(paciente.getNombre());

                    formularioPaciente.add(jLabelApellido);
                    formularioPaciente.add(jTextFieldApellido);
                    jTextFieldApellido.setText(paciente.getApellido());

                    formularioPaciente.add(jLabelDNI);
                    formularioPaciente.add(jTextFieldDNI);
                    jTextFieldDNI.setText(String.valueOf(paciente.getDNI()));

                    formularioPaciente.add(jLabelObraSocial);
                    formularioPaciente.add(jTextFieldObraSocial);
                    jTextFieldObraSocial.setText(paciente.getObrasocial());

                    formularioPaciente.add(jLabelNacimiento);
                    formularioPaciente.add(jTextFieldNacimiento);
                    jTextFieldNacimiento.setText(String.valueOf(paciente.getNacimiento()));

                    formularioPaciente.add(jLabelSexo);
                    formularioPaciente.add(jRadioButtonHombre);
                    formularioPaciente.add(jRadioButtonMujer);
                    if (paciente.esHombre()){
                        jRadioButtonHombre.setSelected(true);
                        jRadioButtonMujer.setSelected(false);
                    }
                    if (paciente.esMujer()){
                        jRadioButtonMujer.setSelected(true);
                        jRadioButtonHombre.setSelected(false);
                    }

                    openWindow(formularioPaciente, "update");

                    formularioPaciente.remove(jLabelNombre);
                    formularioPaciente.remove(jTextFieldNombre);

                    formularioPaciente.remove(jLabelApellido);
                    formularioPaciente.remove(jTextFieldApellido);

                    formularioPaciente.remove(jLabelDNI);
                    formularioPaciente.remove(jTextFieldDNI);

                    formularioPaciente.remove(jLabelObraSocial);
                    formularioPaciente.remove(jTextFieldObraSocial);

                    formularioPaciente.remove(jLabelNacimiento);
                    formularioPaciente.remove(jTextFieldNacimiento);

                    formularioPaciente.remove(jLabelSexo);
                    formularioPaciente.remove(jRadioButtonHombre);
                    formularioPaciente.remove(jRadioButtonMujer);
                }

            }
        });

        jButtonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formularioPaciente.add(jLabelDNI);
                formularioPaciente.add(jTextFieldDNI);
                if (paciente != null){
                    jTextFieldDNI.setText(String.valueOf(paciente.getDNI()));
                }

                openWindow(formularioPaciente, "delete");

                formularioPaciente.remove(jLabelDNI);
                formularioPaciente.remove(jTextFieldDNI);

            }
        });

        jButtonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paciente = null;
                try{
                    paciente = searchOne(Integer.parseInt(searchBar.getText()));
                }catch (ServiceException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(exception);
                }
            }
        });


        formularioPaciente.setVisible(true);
        formularioPaciente.validate();
    }



    public FormularioPaciente(PanelManager panelPrincipal) throws ServiceException {
        panel = panelPrincipal;
        this.paciente = null;
        armarPanel();
    }



    public void openWindow(JPanel panel, String action){
        String title = "Formulario";
        switch (action){
            case "save":
                title = "Registrar paciente";
                break;
            case "update":
                title = "Editar paciente";
                break;
            case "delete":
                title = "Eliminar paciente";
        }
        int result = JOptionPane.showConfirmDialog(null, panel, title, JOptionPane.PLAIN_MESSAGE);
//        jRadioButtonHombre.setSelected(false);
//        jRadioButtonMujer.setSelected(false);


        if (result == JOptionPane.OK_OPTION) {
//            System.out.println("You entered " +
//                    jTextFieldNombre.getText() + ", " +
//                    jTextFieldApellido.getText() + ", " +
//                    jTextFieldDNI.getText()) + ", " +
//                    jTextFieldObraSocial.getText() + ", " +
//                    jTextFieldNacimiento.getText() + ", " +
//                    jTextFieldEdad.getText() + ", " +
//                    buttonGroupSexo.getSelection().getActionCommand();


            Paciente pacienteSave = new Paciente();


            if(action.equals("save")){

                pacienteSave.setNombre(jTextFieldNombre.getText());
                pacienteSave.setApellido(jTextFieldApellido.getText());
                pacienteSave.setDNI(Integer.parseInt(jTextFieldDNI.getText()));
                pacienteSave.setObrasocial(jTextFieldObraSocial.getText());
                pacienteSave.setNacimiento(LocalDate.parse(jTextFieldNacimiento.getText()));
                pacienteSave.setSexo(buttonGroupSexo.getSelection().getActionCommand());

                Boolean success = true;

                try {
                    servicePaciente = new ServicePaciente();
                    servicePaciente.create(pacienteSave);
                } catch (ServiceException exception) {
                    success = false;
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(exception);
                }finally {
                    if (success){
                        clearTextFields();
                        try{
                            refreshPanel();
                        }catch (ServiceException exception){
                            JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            throw new RuntimeException(exception);
                        }
                    }
                }
            }

            if(action.equals("update")){


                pacienteSave.setNombre(jTextFieldNombre.getText());
                pacienteSave.setApellido(jTextFieldApellido.getText());
                pacienteSave.setDNI(Integer.parseInt(jTextFieldDNI.getText()));
                pacienteSave.setObrasocial(jTextFieldObraSocial.getText());
                pacienteSave.setNacimiento(LocalDate.parse(jTextFieldNacimiento.getText()));
                pacienteSave.setSexo(buttonGroupSexo.getSelection().getActionCommand());

                Boolean success = true;

                try {
                    servicePaciente = new ServicePaciente();
                    servicePaciente.update(pacienteSave, paciente.getDNI());
                } catch (ServiceException exception) {
                    success = false;
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(exception);
                }finally {
                    if (success){
                        clearTextFields();
                        paciente = pacienteSave;
                        try{
                            searchOne(paciente.getDNI());
                        }catch (ServiceException exception){
                            JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            throw new RuntimeException(exception);
                        }
                    }
                }

            }

            if(action.equals("delete")){

                int DNI = (Integer.parseInt(jTextFieldDNI.getText()));
                Boolean success = true;

                try {
                    servicePaciente = new ServicePaciente();
                    servicePaciente.delete(DNI);
                } catch (ServiceException exception) {
                    success = false;
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(exception);
                }finally {
                    if (success){
                        jTextFieldNombre.setText("");
                        jTextFieldApellido.setText("");
                        jTextFieldDNI.setText("");
//                        if (paciente.getDNI() == DNI){
                            try{
                                refreshPanel();
                            }catch (ServiceException exception){
                                JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                throw new RuntimeException(exception);
                            }
//                        }
                    }
                }

            }




        } else {
            System.out.println("canceled / closed the dialog, result = " + result);
        }

    }



    private void refreshPanel() throws ServiceException {
        paciente = null;
        searchBar.setText("");
        servicePaciente = new ServicePaciente();
        ArrayList<Paciente> pacientes = servicePaciente.searchAll();
        jTextAreaPacientes.setText("");
        for (int i = 0; i < pacientes.toArray().length; i++) {
            jTextAreaPacientes.append(pacientes.get(i).toString());
            jTextAreaPacientes.append("\n");

        }

    }

    private Paciente searchOne(int DNI) throws ServiceException{
        paciente = servicePaciente.search(DNI);
        jTextAreaPacientes.setText("");
        jTextAreaPacientes.append(paciente.toString());
        return paciente;
    }

    private void clearTextFields(){
        jTextFieldNombre.setText("");
        jTextFieldApellido.setText("");
        jTextFieldDNI.setText("");
        jTextFieldObraSocial.setText("");
        jTextFieldNacimiento.setText("");
        jRadioButtonHombre.setSelected(false);
        jRadioButtonMujer.setSelected(false);
    }


}
