package com.sachin.finalproject.controller;


import com.jfoenix.controls.*;
import com.sachin.finalproject.dao.exception.ConstraintViolationException;
import com.sachin.finalproject.db.DBConnection;
import com.sachin.finalproject.dto.*;
import com.sachin.finalproject.regex.Validation;
import com.sachin.finalproject.regex.Validates;
import com.sachin.finalproject.service.ServiceFactory;
import com.sachin.finalproject.service.ServiceType;
import com.sachin.finalproject.service.custom.*;
import com.sachin.finalproject.service.exception.DuplicateException;
import com.sachin.finalproject.service.exception.InUseException;
import com.sachin.finalproject.service.exception.NotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;

public class AdminFormController {
    private final CustomerService cs = ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
    private final EmployeeService es = ServiceFactory.getInstance().getService(ServiceType.EMPLOYEE);
    private final ItemService is = ServiceFactory.getInstance().getService(ServiceType.ITEM);
    private final SalaryService salaryS = ServiceFactory.getInstance().getService(ServiceType.SALARY);
    private final StockService stockS = ServiceFactory.getInstance().getService(ServiceType.STOCK);
    private final UserService us = ServiceFactory.getInstance().getService(ServiceType.USER);

    private double x, y;

    public JFXTextField txtENameS;


    public JFXTextField txNicC;
    public JFXTextArea txtAddressC;
    public JFXButton btnUpdateC;
    public JFXTextField txtId;
    public JFXTextField txtPhoneNumberC;
    public TableView<CustomerTMDTO> customerTbl;
    public JFXComboBox<String> subCategoryS;
    public JFXComboBox<String> comboCategoryS;
    public JFXComboBox<String> comboNameS;
    public JFXTextField txtQtyS;

    public Label lblId;
    public JFXComboBox<String> comboSizeS;
    public JFXTextField txtIdS;
    public JFXTextField txtPrice;
    public JFXButton btnAddS;
    public JFXComboBox<String> comboCategorySNew;
    public JFXComboBox<String> subCategorySNew;
    public JFXTextField newItemName;
    public JFXButton btnNewFood;
    public Label lblStockWarning;
    public JFXButton btnUpdateS;
    public JFXDatePicker dateFromSalary;
    public JFXDatePicker dateToSalary;
    public TableView<SalaryDTO> salaryTbl;
    public TableColumn colMonthSalary;
    public TableColumn colNicSalary;
    public TableColumn colNameSalary;
    public TableColumn colSalarySalary;
    public TableColumn colStatus;
    public TableColumn colDateSalary;
    public TableColumn colPaysalary;

    public JFXTextField txtNICES;
    public JFXTextField txtSalaryeES;
    public JFXDatePicker dateSalaryES;
    public JFXTextField txtRoleSE;
    public JFXComboBox<String> comboNicSE;
    public JFXTextField txtNameES;
    public JFXButton btnPay;
    public Button settingsBtn;
    public Pane pnlSettings;
    public JFXComboBox<String> comboUserType;
    public JFXTextField txtUsername;
    public JFXPasswordField txtPasswordUser;
    public JFXComboBox<String> comboRoleUser;
    public JFXButton bnChooseimg;
    public JFXButton btnuserSave;
    public ImageView userimg;
    @FXML
    private Button employeeBtn;

    @FXML
    private Button customerBtn;

    @FXML
    private Button salaryBtn;

    @FXML
    private Button stockBtn;

    @FXML
    private Button salesBtn;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlEmployee;

    @FXML
    private JFXTextField txtNameE;

    @FXML
    private JFXTextField txtNicE;

    @FXML
    private JFXComboBox<String> comboRole;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    private JFXTextArea txtAddress;

    @FXML
    private JFXDatePicker dateDobE;

    @FXML
    private JFXTextField txtPhoneNumberE;

    @FXML
    private JFXComboBox<String> comboGenderE;

    @FXML
    private JFXButton registerBtn;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private Label warningE;

    @FXML
    private TableView<EmployeeTM> employeeTbl;

    @FXML
    private TableColumn<?, ?> colNicE;

    @FXML
    private TableColumn<?, ?> colNameE;

    @FXML
    private TableColumn<?, ?> colRoleE;

    @FXML
    private TableColumn<?, ?> colDelE;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private JFXTextField txtNameC;

    @FXML
    private JFXTextArea txtNicC;

    @FXML
    private JFXComboBox<String> comboGenderC;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableView<String> employeeTbl1;

    @FXML
    private TableColumn<?, ?> colPhoneNumberC;

    @FXML
    private TableColumn<?, ?> colNameC;

    @FXML
    private TableColumn<?, ?> colAddressC;

    @FXML
    private TableColumn<?, ?> colDobEcolDelC1;

    @FXML
    private Pane pnlSales;

    @FXML
    private PieChart pieChartCategory;

    @FXML
    private Label lblOrdersTotal;

    @FXML
    private Label lblTotalAmount;

    @FXML
    private Label lblMostVisitedCustomerName;

    @FXML
    private Label lblMostVisitedCustomerPN;

    @FXML
    private Label lblMosProfitCustomerName;

    @FXML
    private Label lblProfitVisitedCustomerPN;

    @FXML
    private Label lblProfitVisitedCustomerTotal;

    @FXML
    private Pane pnlSalary;

    @FXML
    private Pane pnlStock;

    public void initialize() {
        System.out.println("hellooooooooo");
        setSettingsCombo();
        setStockCombos();
        setEmployeeNics();
        setEmployeeTable();
        ArrayList<String> gender = new ArrayList<>();
        gender.add("Male");
        gender.add("Female");
        gender.add("Other");
        ObservableList<String> genderCombo = FXCollections.observableArrayList(gender);
        comboGenderE.setItems(genderCombo);
        comboGenderC.setItems(genderCombo);
        ArrayList<String> roles = new ArrayList<>();
        roles.add("Manager");
        roles.add("Cashier");
        roles.add("Head Chef");
        roles.add("Chef");
        roles.add("Head Waiter");
        roles.add("Waiter");
        ObservableList<String> setRoles = FXCollections.observableArrayList(roles);
        comboRole.setItems(setRoles);
    }

    private void setSettingsCombo() {
        ObservableList<String> userType = FXCollections.observableArrayList();
        userType.add("admin");
        userType.add("cashier");
        comboUserType.setItems(userType);
        ObservableList<String> roles = FXCollections.observableArrayList();
        roles.add("Manager");
        roles.add("Cashier");
        roles.add("Head Chef");
        roles.add("Chef");
        roles.add("Head Waiter");
        roles.add("Waiter");
        comboRoleUser.setItems(roles);

    }

    private void setEmployeeNics() {
        try {
            ArrayList<EmployeeDTO> allEmployee = (ArrayList<EmployeeDTO>) es.getAll();
            ArrayList<String> names = new ArrayList<>();
            for (EmployeeDTO e : allEmployee) {
                names.add(e.getNic());
            }
            ObservableList<String> employees = FXCollections.observableArrayList(names);
            comboNicSE.setItems(employees);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void setEmployeeTable() {
        setStockCombos();
        ArrayList<EmployeeTM> employeeTMS = new ArrayList<>();
        for (EmployeeDTO e : es.getAll()) {
            EmployeeTM eTm = new EmployeeTM();
            eTm.setNic(e.getNic());
            eTm.setName(e.getName());
            eTm.setRole(e.getRole());
            JFXButton btn = new JFXButton();
            btn.setText("Del");
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    EmployeeTM selectedE = employeeTbl.getSelectionModel().getSelectedItem();
                    try {
                        es.deleteEmployee(selectedE.getNic());
                        new Alert(Alert.AlertType.INFORMATION, "Employee Deleted").show();

                    } catch (ConstraintViolationException e) {
                        new Alert(Alert.AlertType.ERROR, "Employee in use").show();

                    }
                        /*boolean isDeleted = EmployeeModel.delete(selectedE);
                        if (isDeleted) {
                            setEmployeeTable();
                            employeeTbl.refresh();
                            new Alert(Alert.AlertType.INFORMATION, "Employee Deleted").show();
                        }*/
                }
            });
            eTm.setButton(btn);
            employeeTMS.add(eTm);
        }

        ObservableList<EmployeeTM> employeesTm = FXCollections.observableArrayList(employeeTMS);
        colNicE.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colNameE.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRoleE.setCellValueFactory(new PropertyValueFactory<>("role"));
        colDelE.setCellValueFactory(new PropertyValueFactory<>("button"));
        employeeTbl.setItems(employeesTm);

    }

    @FXML
    void tableFoodOnClicked(MouseEvent event) {

    }

    public void handleClicks(MouseEvent mouseEvent) throws IOException {

        if (mouseEvent.getSource() == customerBtn) {
            hidePamels();
            setCustomerTbl();
            pnlCustomer.setVisible(true);
        }
        if (mouseEvent.getSource() == settingsBtn) {
            hidePamels();
            pnlSettings.setVisible(true);
        }
        if (mouseEvent.getSource() == employeeBtn) {
            hidePamels();
            pnlEmployee.setVisible(true);
        }
        if (mouseEvent.getSource() == salaryBtn) {
            hidePamels();
            pnlSalary.setVisible(true);
        }
        if (mouseEvent.getSource() == stockBtn) {
            hidePamels();
            pnlStock.setVisible(true);
        }
        if (mouseEvent.getSource() == salesBtn) {
            hidePamels();
            pnlSales.setVisible(true);
        }
        if (mouseEvent.getSource() == btnSignout) {
            hidePamels();
            close(mouseEvent);
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sachin/finalproject/view/LoginForm.fxml"));
            Parent root = loader.load();

            stage.setScene(new Scene(root));
            show(stage, root);
        }
    }

    private void close(MouseEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stagee = (Stage) source.getScene().getWindow();
        stagee.close();
    }

    private void show(Stage stage, Parent root) {
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {

            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

        });

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.show();
    }

    private void hidePamels() {
        pnlSettings.setVisible(false);
        pnlCustomer.setVisible(false);
        pnlEmployee.setVisible(false);
        pnlSalary.setVisible(false);
        pnlSales.setVisible(false);
        pnlStock.setVisible(false);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            es.updateEmployee(getEmployee());

            new Alert(Alert.AlertType.CONFIRMATION, "Employee Updated Successfully!").show();
            setEmployeeTable();
            employeeTbl.refresh();
            warningE.setText("");

        } catch (InputMismatchException | NullPointerException |
                 NumberFormatException e) {
            warningE.setText("Check Details again");
            System.out.println(e);
        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        try {
            EmployeeDTO e = getEmployee();

            es.saveEmployee(e);
            setEmployeeTable();
            employeeTbl.refresh();
            new Alert(Alert.AlertType.CONFIRMATION, "Employee Added").show();
            warningE.setText("");

        } catch (NullPointerException | InputMismatchException |
             NumberFormatException ex) {
            System.out.println(ex);
            warningE.setText("Check Details again");
        }catch (DuplicateException e){
            new Alert(Alert.AlertType.ERROR, "Not Added").show();
        }

    }

    private EmployeeDTO getEmployee() {
        String nic = txtNicE.getText();
        String name = txtNameE.getText();
        String address = txtAddress.getText();
        String role = comboRole.getSelectionModel().getSelectedItem();
        LocalDate date = dateDobE.getValue();
        String phoneNumber = txtPhoneNumberE.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        validateEmployee(nic, name, address, phoneNumber);
        String gender = comboGenderE.getSelectionModel().getSelectedItem();
        EmployeeDTO e = new EmployeeDTO(phoneNumber, nic, name, role, salary, date, address, gender);
        return e;
    }

    private void validateEmployee(String nic, String name, String address, String phoneNumber) {

        if (Validation.match(txtNameE.getText(), Validates.NAME)) {
            txtNameE.setStyle("-jfx-focus-color : green");
            return;
        }
        txtNameE.setStyle("-jfx-unfocus-color : red");
        if (Validation.match(txtNicE.getText(), Validates.NIC)) {
            txtNicE.setStyle("-jfx-unfocus-color : green");
            return;
        }
        txtNicE.setStyle("-jfx-unfocus-color : red");

        if (Validation.match(txtSalary.getText(), Validates.SALARY)) {
            txtSalary.setStyle("-jfx-focus-color : green");
            return;
        }
        txtSalary.setStyle("-jfx-unfocus-color : red");
        if (Validation.match(txtAddress.getText(), Validates.ADDRESS)) {
            txtAddress.setStyle("-jfx-focus-color : green");
            return;
        }
        txtAddress.setStyle("-jfx-focus-color : red");
        if (Validation.match(txtPhoneNumberE.getText(), Validates.PHONE_NUMBER)) {
            txtPhoneNumberE.setStyle("-jfx-focus-color : green");
            return;
        }
        txtPhoneNumberE.setStyle("-jfx-unfocus-color : red");
    }

    public void txtNameEOnKeyPressed(KeyEvent keyEvent) {
        if (Validation.match(txtNameE.getText(), Validates.NAME)) {
            txtNameE.setStyle("-jfx-focus-color : green");
            return;
        }
        txtNameE.setStyle("-jfx-focus-color : red");
    }

    public void txtNicEOnKeyPressed(KeyEvent keyEvent) {
        if (Validation.match(txtNicE.getText(), Validates.NIC)) {
            txtNicE.setStyle("-jfx-focus-color : green");
            return;
        }
        txtNicE.setStyle("-jfx-focus-color : red");
    }

    public void txtSalaryOnPressed(KeyEvent keyEvent) {
        if (Validation.match(txtSalary.getText(), Validates.SALARY)) {
            txtSalary.setStyle("-jfx-focus-color : green");
            return;
        }
        txtSalary.setStyle("-jfx-focus-color : red");
    }

    public void txtAddressOnPressed(KeyEvent keyEvent) {
        if (Validation.match(txtAddress.getText(), Validates.ADDRESS)) {
            txtAddress.setStyle("-jfx-focus-color : green");
            return;
        }
        txtAddress.setStyle("-jfx-focus-color : red");
    }

    public void txtPhoneNumberEOnPressed(KeyEvent keyEvent) {
        if (Validation.match(txtPhoneNumberE.getText(), Validates.PHONE_NUMBER)) {
            txtPhoneNumberE.setStyle("-jfx-focus-color : green");
            return;
        }
        txtPhoneNumberE.setStyle("-jfx-focus-color : red");
    }

    public void tableEmployeeOnClicked(MouseEvent mouseEvent) {
        EmployeeTM si = employeeTbl.getSelectionModel().getSelectedItem();
        EmployeeDTO selectedEmployee = new EmployeeDTO();
        ArrayList<EmployeeDTO> allEmployee = (ArrayList<EmployeeDTO>) es.getAll();
        for (EmployeeDTO employee : allEmployee) {
            if (employee.getNic().equals(si.getNic())) {
                selectedEmployee = employee;
                System.out.println(selectedEmployee.getRole());
                break;
            }
        }

        txtNicE.setText(selectedEmployee.getNic());
        txtNameE.setText(selectedEmployee.getName());
        txtSalary.setText(String.valueOf(selectedEmployee.getSalary()));
        txtPhoneNumberE.setText(selectedEmployee.getPhoneNumber());
        dateDobE.setValue(selectedEmployee.getDob());

        comboRole.getSelectionModel().selectFirst();
        comboRole.setValue(selectedEmployee.getRole());
        txtAddress.setText(selectedEmployee.getAddress());
        comboGenderE.setValue(selectedEmployee.getGender());

    }

    public void btnUpdateCOnAction(ActionEvent actionEvent) {


        String name = txtNameC.getText();
        String address = txtAddressC.getText();
        String gender = comboGenderC.getSelectionModel().getSelectedItem();
        String phonenumber = txtPhoneNumberC.getText();
        String id = txtId.getText();

//            boolean isUpdated = CustomerModel.updateCustomer(new Customer(name, gender, address, id, phonenumber));
        try {
            cs.updateCustomer(new CustomerDTO(name, gender, address, id, phonenumber));
            new Alert(Alert.AlertType.INFORMATION, "Customer Updated Successfully").show();
            txtNameC.setText("");
            txtAddressC.setText("");
            comboGenderC.setValue("");
            txtPhoneNumberC.setText("");
            txtId.setText("");
            setCustomerTbl();
            customerTbl.refresh();
        } catch (ConstraintViolationException e) {
            new Alert(Alert.AlertType.ERROR, "Customer Updated failed").show();
        }


    }

    private void setCustomerTbl() {
        ArrayList<CustomerDTO> customers = (ArrayList<CustomerDTO>) cs.getAllCustomer();
        ObservableList<CustomerTMDTO> customerTMS = FXCollections.observableArrayList();
        for (CustomerDTO c : customers) {
            CustomerTMDTO ct = new CustomerTMDTO();
            ct.setId(c.getId());
            ct.setAddress(c.getAddress());
            ct.setName(c.getName());
            ct.setGender(c.getGender());
            ct.setPhoneNumber(c.getPhoneNumber());
            JFXButton button = new JFXButton("Del");
            button.setStyle("-fx-background-color: #EA3939");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                    ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete?", yes, no);
                    alert.showAndWait().ifPresent(buttonType -> {
                        if (buttonType == yes) {
                            try {
                                cs.deleteCustomer(ct.getId());
                                setCustomerTbl();
                                customerTbl.refresh();
                                new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully");

                                /*boolean isDeleted = CustomerModel.delete(ct.getId());
                                if (isDeleted) {
                                    new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully");
                                    setCustomerTbl();
                                    customerTbl.refresh();
                                    return;
                                }*/
                            } catch (InUseException | NotFoundException e) {
                                System.out.println(e);
                            }
                        }
                        if (buttonType == no) {
                            return;
                        }
                    });
                }
            });
            ct.setButton(button);
            customerTMS.add(ct);
        }
        colNameC.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddressC.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhoneNumberC.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colDobEcolDelC1.setCellValueFactory(new PropertyValueFactory<>("button"));
        customerTbl.setItems(customerTMS);
    }

    public void customerTblOnAction(MouseEvent mouseEvent) {
        CustomerTMDTO si = customerTbl.getSelectionModel().getSelectedItem();
        CustomerDTO customer = new CustomerDTO();
        ArrayList<CustomerDTO> customers = (ArrayList<CustomerDTO>) cs.getAllCustomer();
        for (CustomerDTO c : customers) {
            if (si.getId().equals(c.getId())) {
                customer = c;
            }
        }
        txtNameC.setText(customer.getName());
        txtId.setText(customer.getId());
        txtPhoneNumberC.setText(customer.getPhoneNumber());
        txtAddressC.setText(customer.getAddress());
        comboGenderC.getSelectionModel().selectFirst();
        comboGenderC.setValue(customer.getGender());

    }

    public void btnAddSOnAction(ActionEvent actionEvent) {
        try {
            ItemDTO item = getItem();
            ItemDTO found = is.getItem(item.getId());
            if (found != null) {
                lblStockWarning.setText("Id already exists");
            }
            stockS.saveItem(item);
            new Alert(Alert.AlertType.INFORMATION, "Added Successfully").show();
            lblStockWarning.setText("");
            clearItem();

        } catch (NotFoundException e) {
            System.out.println(e);
            lblStockWarning.setText("Check details Again");
        }


    }

    private void clearItem() {
        comboCategoryS.getSelectionModel().clearSelection();
        subCategoryS.getSelectionModel().clearSelection();
        comboNameS.getSelectionModel().clearSelection();
        txtIdS.setText("");
        comboSizeS.getSelectionModel().clearSelection();
        txtQtyS.setText("");
        txtPrice.setText("");
    }

    private ItemDTO getItem() {
        ItemDTO item = null;
        try {


            String category = comboCategoryS.getSelectionModel().getSelectedItem();
            String subCategory = subCategoryS.getSelectionModel().getSelectedItem();
            String name = comboNameS.getSelectionModel().getSelectedItem();
            String id = txtIdS.getText().toUpperCase();
            String size = comboSizeS.getSelectionModel().getSelectedItem();
            int qty = Integer.parseInt(txtQtyS.getText());
            double price = Double.parseDouble(txtPrice.getText());
            item = new ItemDTO(0, id, qty, name, price, category, subCategory, size);
        } catch (Exception e) {
            System.out.println(e);
        }
        return item;


    }

    public void setStockCombos() {
        ObservableList<String> category = FXCollections.observableArrayList();
        category.add("food");
        category.add("dessert");
        category.add("drink");
        comboCategorySNew.setItems(category);
        comboCategoryS.setItems(category);
    }

    public void comboCategorySOnAction(ActionEvent actionEvent) {

        categorySet(comboCategoryS, subCategoryS);
    }

    private void categorySet(ComboBox<String> comboCategoryS, ComboBox<String> subCategoryS) {
        try {
            String i = comboCategoryS.getSelectionModel().getSelectedItem();
            if (i.equals("food")) {
                ObservableList<String> subCategory = FXCollections.observableArrayList();
                subCategory.add("rice");
                subCategory.add("pizza");
                subCategory.add("soup");
                subCategoryS.setItems(subCategory);
                ObservableList<String> items = FXCollections.observableArrayList();
                items.add("regular");
                items.add("large");
                comboSizeS.setItems(items);
            }
            if (i.equals("drink")) {
                ObservableList<String> subCategory = FXCollections.observableArrayList();
                subCategory.add("soft drink");
                subCategory.add("hot drink");
                subCategoryS.setItems(subCategory);
                ObservableList<String> items = FXCollections.observableArrayList();
                items.add("100ML");
                items.add("300ML");
                comboSizeS.setItems(items);
            }
            if (i.equals("dessert")) {
                ObservableList<String> subCategory = FXCollections.observableArrayList();
                subCategory.add("ice cream");
                subCategory.add("pudding");
                subCategoryS.setItems(subCategory);
                ObservableList<String> items = FXCollections.observableArrayList();
                items.add("small");
                items.add("large");
                comboSizeS.setItems(items);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void subCategorySOnAction(ActionEvent actionEvent) {
        try {
            String sub = subCategoryS.getSelectionModel().getSelectedItem();


            String category = comboCategoryS.getSelectionModel().getSelectedItem();
            String subCategory = subCategoryS.getSelectionModel().getSelectedItem();


            ArrayList<String> names = stockS.getAllFoodNames(category, subCategory);
            for (String n : names) {
                System.out.println(n);
            }
            ObservableList<String> namess = FXCollections.observableArrayList();
            namess.addAll(names);
            comboNameS.setItems(namess);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void comboNameS(ActionEvent actionEvent) {


    }

    public void comboSizeSOnAction(ActionEvent actionEvent) {

    }


    public void txtIdSOnKeyReleased(KeyEvent keyEvent) {


        String id = txtIdS.getText().toLowerCase();
        System.out.println(id);
        Optional<ItemDTO> itemO = null;
        try {
            ArrayList<ItemDTO> all = (ArrayList<ItemDTO>) stockS.getAllItems();
            ArrayList<String> ids = new ArrayList<>();

            for (ItemDTO i : all) {
                ids.add(i.getId());
            }
            String[] idsAr = ids.toArray(new String[ids.size()]);

            //TextFields.bindAutoCompletion(txtIdS, idsAr);

            itemO = stockS.getItem(id);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
        if (itemO.isPresent()) {
            ItemDTO itemDTO = itemO.get();

            comboCategoryS.getSelectionModel().select("");
            comboCategoryS.setValue(itemDTO.getCategory());
            subCategoryS.getSelectionModel().select("");
            subCategoryS.setValue(itemDTO.getSubCategory());
            comboNameS.getSelectionModel().select("");
            comboNameS.setValue(itemDTO.getDes());
            comboSizeS.getSelectionModel().select("");
            comboSizeS.setValue(itemDTO.getSize());
            txtQtyS.setText(String.valueOf(itemDTO.getQtyOnHand()));

            txtPrice.setText(String.valueOf(itemDTO.getPrice()));
            btnAddS.setDisable(true);
            return;
        }
        comboCategoryS.getSelectionModel().clearSelection();

        subCategoryS.getSelectionModel().clearSelection();

        comboNameS.getSelectionModel().clearSelection();

        comboSizeS.getSelectionModel().clearSelection();
        comboSizeS.setItems(null);

        txtPrice.setText("");
        btnAddS.setDisable(false);
    }

    public void btnNewFoodOnAction(ActionEvent actionEvent) {
        try {
            String category = comboCategorySNew.getSelectionModel().getSelectedItem();
            String subCategory = subCategorySNew.getSelectionModel().getSelectedItem();
            System.out.println(subCategory);
            String name = newItemName.getText();
            if (category == null || name == null || subCategory == null) {
                new Alert(Alert.AlertType.INFORMATION, name + " Please Fill first").show();
                return;
            }
            is.addNewFood(category,subCategory,name);
            new Alert(Alert.AlertType.INFORMATION, name + " Added Successfully").show();
            comboCategorySNew.getSelectionModel().clearSelection();
            subCategorySNew.getSelectionModel().clearSelection();
            newItemName.setText("");

        } catch (Exception e) {
            new Alert(Alert.AlertType.INFORMATION, "This Item Already added").show();
            System.out.println(e);
        }
    }

    public void btnUpdateSOnAction(ActionEvent actionEvent) {
        ItemDTO item = getItem();
        try {
            stockS.updateItem(item);
            new Alert(Alert.AlertType.INFORMATION, "Updated Successfully").show();
        } catch (NotFoundException e) {
            System.out.println(e);
        }
    }

    public void comboCategorySNewOnAction(ActionEvent actionEvent) {
        categorySet(comboCategorySNew, subCategorySNew);
    }

    public void newItemNameOnKeyReleased(KeyEvent keyEvent) {
        boolean match = Validation.match(newItemName.getText(), Validates.NEW_ITEM_NAME);
        if (match) {
            newItemName.setStyle("-jfx-focus-color: green");
            System.out.println("matc");
            return;
        }
        newItemName.setStyle("-jfx-focus-color: red");
    }

    public void comboENamesOnAction(ActionEvent actionEvent) {

    }

    public void comboNicSEOnAction(ActionEvent actionEvent) {
        String nic = comboNicSE.getSelectionModel().getSelectedItem();
        try {
            ArrayList<EmployeeDTO> allEmployee = (ArrayList<EmployeeDTO>) es.getAll();
            for (EmployeeDTO e : allEmployee) {
                if (e.getNic().equals(nic)) {
                    txtNameES.setText(e.getName());
                    txtRoleSE.setText(e.getRole());
                    txtSalaryeES.setText(String.valueOf(e.getSalary()));
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void btnPayOnAction(ActionEvent actionEvent) {
        try {
            String nic = comboNicSE.getSelectionModel().getSelectedItem();
            String name = txtNameES.getText();
            String role = txtRoleSE.getText();
            double salary = Double.parseDouble(txtSalaryeES.getText());
            LocalDate date = dateSalaryES.getValue();
            int year = date.getYear();
            Month month = date.getMonth();
            ArrayList<SalaryDTO> salaries = (ArrayList<SalaryDTO>) salaryS.getAll();
            for (SalaryDTO s : salaries) {
                System.out.println(month.toString());
                System.out.println(s.getMonth().toString());


                if (s.getMonth().getMonth() == date.getMonth() && s.getMonth().getYear() == date.getYear() & s.getEnic().equals(nic)) {

                    new Alert(Alert.AlertType.INFORMATION, "Already Paid for " + month.toString() + " " + date.getYear()).show();
                    return;
                }
            }
//            boolean isAdded = SalaryModel.addSalary(nic, name, role, salary, date, "paid");
            salaryS.saveSalary(new SalaryDTO(nic, name, salary, date, date, "paid"));
            new Alert(Alert.AlertType.INFORMATION, "Salary Paid").show();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void btnLoadDetailsSalary(ActionEvent actionEvent) {
        colNicSalary.setCellValueFactory(new PropertyValueFactory<>("enic"));
        colNameSalary.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSalarySalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colMonthSalary.setCellValueFactory(new PropertyValueFactory<>("month"));
        colDateSalary.setCellValueFactory(new PropertyValueFactory<>("date"));
        LocalDate date = dateFromSalary.getValue();
        try {

            ArrayList<SalaryDTO> sl = salaryS.getAllByDate( date.getMonthValue(), date.getYear());

            ObservableList<SalaryDTO> tms = FXCollections.observableArrayList(sl);
            salaryTbl.setItems(tms);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void bnChooseimgOnAction(ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);
            InputStream fileInputStream = new FileInputStream(file);
            Image image = new Image(fileInputStream);
            userimg.setImage(image);
            String userType = comboUserType.getSelectionModel().getSelectedItem();
            String name = txtENameS.getText();
            String username = txtUsername.getText();
            String password = txtPasswordUser.getText();
            String role = comboRoleUser.getSelectionModel().getSelectedItem();
            PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO user Values(?,?,?,?,?,?)");

            ps.setObject(1, username);
            ps.setObject(2, password);
            ps.setBlob(3, fileInputStream);
            ps.setObject(4, role);
            ps.setObject(5, userType);
            ps.setObject(6, name);
            int userAdded = ps.executeUpdate();
            boolean isAdded = userAdded > 0;
            System.out.println(userAdded);
            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION, "User Added Successfully").show();
                txtUsername.setText("");
                txtPasswordUser.setText("");
                userimg.setImage(null);
                comboRoleUser.getSelectionModel().clearSelection();
                comboUserType.getSelectionModel().clearSelection();
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public void btnuserSaveOnAction(ActionEvent actionEvent) {
        try {


        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void txtQtySOnReleased(KeyEvent keyEvent) {
        String qty = txtQtyS.getText();
        boolean match = Validation.match(qty, Validates.QTY);
        if (match) {
            txtQtyS.setStyle("-jfx-focus-color : green");
            return;
        }
        txtQtyS.setStyle("-jfx-focus-color : red");
    }

    public void txtPriceOnReleased(KeyEvent keyEvent) {
        String price = txtPrice.getText();
        boolean match = Validation.match(price, Validates.QTY);
        if (match) {
            txtPrice.setStyle("-jfx-focus-color : green");
            return;
        }
        txtPrice.setStyle("-jfx-focus-color : red");
    }

    public void txtNameCOnKeyReleased(KeyEvent keyEvent) {
        String name = txtNameC.getText();
        boolean match = Validation.match(name, Validates.NAME);
        if (match) {
            txtNameC.setStyle("-jfx-focus-color : green");
            return;
        }
        txtNameC.setStyle("-jfx-focus-color : red");
    }

    public void txtPhoneNumberCOnKeyReleased(KeyEvent keyEvent) {
        String phoneN = txtPhoneNumberC.getText();
        boolean match = Validation.match(phoneN, Validates.PHONE_NUMBER);
        if (match) {
            txtPhoneNumberC.setStyle("-jfx-focus-color : green");
            return;
        }
        txtPhoneNumberC.setStyle("-jfx-focus-color : red");
    }

    public void txtAddressCOnKeyReleased(KeyEvent keyEvent) {
        String address = txtAddressC.getText();
        boolean match = Validation.match(address, Validates.ADDRESS);
        if (match) {
            txtAddressC.setStyle("-jfx-focus-color : green");
            return;
        }
        txtAddressC.setStyle("-jfx-focus-color : red");
    }
}
