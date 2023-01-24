package com.sachin.finalproject.controller;

import com.jfoenix.controls.*;
import com.sachin.finalproject.db.DBConnection;
//import com.sachin.finalproject.model.*;
import com.sachin.finalproject.dto.*;
import com.sachin.finalproject.regex.Validation;
import com.sachin.finalproject.regex.Validates;
import com.sachin.finalproject.service.ServiceFactory;
import com.sachin.finalproject.service.ServiceType;
import com.sachin.finalproject.service.custom.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


import java.io.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CashierFormController {
    private final CustomerService cs = ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
    private final EmployeeService es = ServiceFactory.getInstance().getService(ServiceType.EMPLOYEE);
    private final ItemService is = ServiceFactory.getInstance().getService(ServiceType.ITEM);
    private final SalaryService salaryS = ServiceFactory.getInstance().getService(ServiceType.SALARY);
    private final StockService stockS = ServiceFactory.getInstance().getService(ServiceType.STOCK);
    private final UserService us = ServiceFactory.getInstance().getService(ServiceType.USER);
    private final OrderService os = ServiceFactory.getInstance().getService(ServiceType.ORDER);

    private double x, y;
    private UserDTO user;
    private Image image;
    public Label lblWarning;
    public Label lblOrderId;
    public JFXComboBox<String> comboGenderCashier;
    public Label lblCustomerId;
    public Label lblCashierName;
    public JFXTextField txtCustomerCash;
    public ImageView cashierImg;
    public ImageView testImg;
    private boolean nameValidated;
    private boolean phoneNumberValidated;
    private boolean addressValidated;
    public JFXButton placeOrderBtn;
    private Pattern phoneNumber;
    private Pattern name;
    private Pattern address;
    private Pattern phoneNumberP;
    private final ArrayList<OrderTm> orders = new ArrayList<OrderTm>();
    public TableColumn colActionOrder;
    public TableView<OrderTm> orderTable;
    public TableColumn colCodeOrder;
    public Label lblTotal;
    public Label lblDate;
    public TableColumn colPlus;
    public TableColumn colSizeOrder;
    public JFXComboBox<Integer> comboQty;
    ObservableList<OrderTm> orderTms;
    private OrderTm selectedItem;
    @FXML
    private Button foodBtn;

    @FXML
    private Button drinkBtn;

    @FXML
    private Button dessertBtn;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane foodPnl;

    @FXML
    private TableView<ItemDTO> foodTbl;

    @FXML
    private TableColumn<?, ?> colCodeFood;

    @FXML
    private TableColumn<?, ?> colNameFood;

    @FXML
    private TableColumn<?, ?> colSizeFood;

    @FXML
    private TableColumn<?, ?> colQtyOnHandFood;

    @FXML
    private TableColumn<?, ?> colPriceFood;

    @FXML
    private JFXTextField txtSearchFood;

    @FXML
    private JFXRadioButton radioAllFood;

    @FXML
    private ToggleGroup FoodSelection;

    @FXML
    private JFXRadioButton radioRice;

    @FXML
    private JFXRadioButton radioPizza;

    @FXML
    private Pane drinkPnl;

    @FXML
    private TableView<ItemDTO> drinkTbl;

    @FXML
    private TableColumn<?, ?> colCodeDrink;

    @FXML
    private TableColumn<?, ?> colNameDrink;

    @FXML
    private TableColumn<?, ?> colSizeDrink;

    @FXML
    private TableColumn<?, ?> colQtyOnHandDrink;

    @FXML
    private TableColumn<?, ?> colpriceDrink;

    @FXML
    private JFXTextField textSearchDrink;

    @FXML
    private JFXRadioButton radioAllDrink;

    @FXML
    private ToggleGroup FoodSelection1;

    @FXML
    private JFXRadioButton radiosoftDrink;

    @FXML
    private JFXRadioButton radioHotDrink;

    @FXML
    private Pane dessertPnl;

    @FXML
    private TableView<ItemDTO> dessertTbl;

    @FXML
    private TableColumn<?, ?> colCodeDessert;

    @FXML
    private TableColumn<?, ?> colNameDessert;

    @FXML
    private TableColumn<?, ?> colSizeDessert;

    @FXML
    private TableColumn<?, ?> colQtyOnHandDessert;

    @FXML
    private TableColumn<?, ?> colPricedessert;

    @FXML
    private JFXTextField txtSearchDessert;

    @FXML
    private JFXRadioButton radioAllDessert;

    @FXML
    private ToggleGroup FoodSelection2;

    @FXML
    private JFXRadioButton radioIceCream;

    @FXML
    private JFXRadioButton radioPudding;

    @FXML
    private JFXTextField txtPhoeNumber;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTimePicker pickUpTime;

    @FXML
    private JFXTextArea textAddress;

    @FXML
    private JFXComboBox<String> comboOrdertype;

    @FXML
    private TableColumn<?, ?> colNameOrder;

    @FXML
    private TableColumn<?, ?> colQtyOrder;

    @FXML
    private TableColumn<?, ?> colPriceOrder;

    @FXML
    private TableColumn<?, ?> colTotalOrder;
    private double total;

    public void initialize() {
        //setUser();


        ObservableList<String> gender = FXCollections.observableArrayList();
        gender.add("Female");
        gender.add("Other");
        gender.add("Male");
        comboGenderCashier.setItems(gender);
        String orderId = os.getOrderId().get();
        if (orderId.isEmpty()) {
            orderId = "D00001";
            lblOrderId.setText(orderId);
        } else {
            String s = os.generateId(orderId);
            lblOrderId.setText(s);
        }


        name = Pattern.compile("^[a-zA-Z]{1,50}$");
        address = Pattern.compile("([a-zA-Z|0-9|\\s+]{3,})");
//        phoneNumberP = Pattern.compile("^[0|+94][77|76|71|70|78|74|75|][0-9]{7}$");
        lblDate.setText(String.valueOf(LocalDate.now()));
        ObservableList<Integer> qty = FXCollections.observableArrayList();
        qty.add(1);
        qty.add(2);
        qty.add(3);
        qty.add(4);
        qty.add(5);
        qty.add(6);
        qty.add(7);
        qty.add(8);
        qty.add(9);
        qty.add(10);
        comboQty.setItems(qty);
        comboQty.getSelectionModel().selectFirst();

        ArrayList<String> orderTypes = new ArrayList<>();
        orderTypes.add("Dine In");
        orderTypes.add("Take Away");
        orderTypes.add("Delivery");
        ObservableList<String> ot = FXCollections.observableArrayList(orderTypes);

        comboOrdertype.setItems(ot);
    }

    public void setFoodAll() throws SQLException, ClassNotFoundException {
        foodTbl.getItems().clear();

        foodtbSet();

        ArrayList<ItemDTO> food = (ArrayList<ItemDTO>) is.getAllItem("food");
        ObservableList<ItemDTO> items = FXCollections.observableArrayList(food);
        foodTbl.setItems(items);

    }

    private void foodtbSet() {
        colCodeFood.setCellValueFactory(new PropertyValueFactory<>("id"));//id = code
        colNameFood.setCellValueFactory(new PropertyValueFactory<>("des"));
        colSizeFood.setCellValueFactory(new PropertyValueFactory<>("size"));
        colQtyOnHandFood.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colPriceFood.setCellValueFactory(new PropertyValueFactory<>("price"));
        colSizeFood.setCellValueFactory(new PropertyValueFactory<>("size"));
    }

    public void setFoodRice() throws SQLException, ClassNotFoundException {
        foodTbl.getItems().clear();

        foodtbSet();

        ArrayList<ItemDTO> food = (ArrayList<ItemDTO>) is.getAllItem("food","rice");
        ObservableList<ItemDTO> items = FXCollections.observableArrayList(food);
        foodTbl.setItems(items);

    }

    public void setFoodPizza() throws SQLException, ClassNotFoundException {
        foodTbl.getItems().clear();
        foodtbSet();
        ArrayList<ItemDTO> food = (ArrayList<ItemDTO>) is.getAllItem("food","pizza");
        ObservableList<ItemDTO> items = FXCollections.observableArrayList(food);
        foodTbl.setItems(items);

    }

    @FXML
    void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == foodBtn) {
            disapperPnls();
            foodPnl.toFront();
            foodPnl.setVisible(true);
        }
        if (event.getSource() == drinkBtn) {
            disapperPnls();
            System.out.println("drink");
            drinkPnl.toFront();
            drinkPnl.setVisible(true);
        }
        if (event.getSource() == dessertBtn) {
            disapperPnls();
            dessertPnl.toFront();

            dessertPnl.setVisible(true);
        }
        if (event.getSource() == btnSignout) {
            disapperPnls();
            close(event);
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sachin/finalproject/view/LoginForm.fxml"));
            Parent root = loader.load();

            stage.setScene(new Scene(root));
            show(stage, root);
        }
    }

    private void close(ActionEvent actionEvent) {
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

    private void disapperPnls() {
        dessertPnl.setVisible(false);
        drinkPnl.setVisible(false);
        foodPnl.setVisible(false);
    }


    public void radioAllFoodOnAction(ActionEvent actionEvent) {
        try {
            setFoodAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void radioRiceOnAction(ActionEvent actionEvent) {
        try {
            setFoodRice();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void radioPizzaOnAction(ActionEvent actionEvent) {

        try {
            setFoodPizza();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void setDrinkAll() throws SQLException, ClassNotFoundException {
        drinkTbl.getItems().clear();

        drinkTbSet();
        ArrayList<ItemDTO> drinks = (ArrayList<ItemDTO>) is.getAllItem("drink");
        ObservableList<ItemDTO> items = FXCollections.observableArrayList(drinks);
        drinkTbl.setItems(items);

    }

    private void drinkTbSet() {
        colCodeDrink.setCellValueFactory(new PropertyValueFactory<>("id"));//id = code
        colNameDrink.setCellValueFactory(new PropertyValueFactory<>("des"));
        colSizeDrink.setCellValueFactory(new PropertyValueFactory<>("size"));
        colQtyOnHandDrink.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colpriceDrink.setCellValueFactory(new PropertyValueFactory<>("price"));
        colSizeDrink.setCellValueFactory(new PropertyValueFactory<>("size"));
    }

    public void setSoftDrink() throws SQLException, ClassNotFoundException {
        drinkTbl.getItems().clear();
        drinkTbSet();
        ArrayList<ItemDTO> drinks = (ArrayList<ItemDTO>) is.getAllItem("drink", "soft drink");
        ObservableList<ItemDTO> items = FXCollections.observableArrayList(drinks);
        drinkTbl.setItems(items);

    }

    public void setHotDrink() throws SQLException, ClassNotFoundException {
        drinkTbl.getItems().clear();
        drinkTbSet();
        ArrayList<ItemDTO> drinks = (ArrayList<ItemDTO>) is.getAllItem("drink", "hot drink");
        ObservableList<ItemDTO> items = FXCollections.observableArrayList(drinks);
        drinkTbl.setItems(items);

    }

    public void radioAllDrinkOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        try {
            setDrinkAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void radiosoftDrinkOnAction(ActionEvent actionEvent) {
        try {
            setSoftDrink();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void radioHotDrinkOnAction(ActionEvent actionEvent) {
        try {
            setHotDrink();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void radioAllDessert(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        setDessertAll();
    }

    private void setDessertAll() throws SQLException, ClassNotFoundException {
        dessertTbl.getItems().clear();
        dessertTbSet();
        ArrayList<ItemDTO> dessertAll = (ArrayList<ItemDTO>) is.getAllItem("dessert");
        ObservableList<ItemDTO> items = FXCollections.observableArrayList(dessertAll);
        dessertTbl.setItems(items);
    }

    private void dessertTbSet() {
        colCodeDessert.setCellValueFactory(new PropertyValueFactory<>("id"));//id = code
        colNameDessert.setCellValueFactory(new PropertyValueFactory<>("des"));
        colSizeDessert.setCellValueFactory(new PropertyValueFactory<>("size"));
        colQtyOnHandDessert.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colPricedessert.setCellValueFactory(new PropertyValueFactory<>("price"));
        colSizeDessert.setCellValueFactory(new PropertyValueFactory<>("size"));
    }

    public void setIceCream() throws SQLException, ClassNotFoundException {
        dessertTbl.getItems().clear();
        dessertTbSet();
        ArrayList<ItemDTO> dessert = (ArrayList<ItemDTO>) is.getAllItem("dessert","ice cream");
        ObservableList<ItemDTO> items = FXCollections.observableArrayList(dessert);
        dessertTbl.setItems(items);

    }

    public void setPudding() throws SQLException, ClassNotFoundException {
        dessertTbl.getItems().clear();
        dessertTbSet();
        ArrayList<ItemDTO> dessert = (ArrayList<ItemDTO>) is.getAllItem("dessert","pudding");
        ObservableList<ItemDTO> items = FXCollections.observableArrayList(dessert);
        dessertTbl.setItems(items);

    }

    public void radioIceCreamOnAction(ActionEvent actionEvent) {
        try {
            setIceCream();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void radioPuddingOnAction(ActionEvent actionEvent) {
        try {
            setPudding();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void radioAllDessertOnAction(ActionEvent actionEvent) {
        try {
            setDessertAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtSearchFood(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> items = (ArrayList<ItemDTO>) is.searchItem(txtSearchFood.getText().toLowerCase(),"food");
        foodTbl.getItems().clear();
        foodtbSet();
        ObservableList<ItemDTO> observableList = FXCollections.observableArrayList(items);
        foodTbl.setItems(observableList);
    }

    public void textSearchDrinkOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> items = (ArrayList<ItemDTO>) is.searchItem(txtSearchFood.getText().toLowerCase(),"drink");
        drinkTbl.getItems().clear();
//            textSearchDrink
        drinkTbSet();

        ObservableList<ItemDTO> observableList = FXCollections.observableArrayList(items);
        drinkTbl.setItems(observableList);
    }

    public void txtSearchDessertOnaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> items = (ArrayList<ItemDTO>) is.searchItem(txtSearchFood.getText().toLowerCase(),"dessert");

        dessertTbl.getItems().clear();
//            textSearchDrink
        dessertTbSet();

        ObservableList<ItemDTO> observableList = FXCollections.observableArrayList(items);
        dessertTbl.setItems(observableList);
    }

    public void searchKeyPressed(KeyEvent keyEvent) {
    }

    public void searchFoodKeyPressed(KeyEvent keyEvent) {
    }

    public void searchDessertKeyPressed(KeyEvent keyEvent) {
    }

    public void searchDrinkKeyPressed(KeyEvent keyEvent) {
    }


    public void tableFoodOnClicked(MouseEvent mouseEvent) {
        clickOnTable(foodTbl);
    }

    private void clickOnTable(TableView t) {
        TableView.TableViewSelectionModel<ItemDTO> food = t.getSelectionModel();
        total = Double.parseDouble(lblTotal.getText());
        int focusedIndex = food.getFocusedIndex();
        ObservableList<ItemDTO> items = t.getItems();
        for (OrderTm o : orders) {
            if (items.get(focusedIndex).getId().equals(o.getCode())) {
                int qty = o.getQty();
                if (qty < items.get(focusedIndex).getQtyOnHand()) {
                    o.setQty(qty + 1);
                } else {
                    return;
                }

                double price = o.getPrice();
                o.setTotal(o.getQty() * price);
                total += price;
                lblTotal.setText(String.valueOf(total));
                setOrdertable();
                orderTable.refresh();
                return;
            }
        }
        OrderTm i = new OrderTm();


        i.setName(items.get(focusedIndex).getDes());
        i.setSize(items.get(focusedIndex).getSize());
        int qty = items.get(focusedIndex).getQty() + 1;
        i.setQty(qty);
        double price = items.get(focusedIndex).getPrice();
        i.setPrice(price);
        i.setQtyOnHand(items.get(focusedIndex).getQtyOnHand());
        double currentTotal = price * qty;
        i.setTotal(currentTotal);
        double total1 = i.getTotal();
        total += total1;
        lblTotal.setText(String.valueOf(total));
        i.setCode(items.get(focusedIndex).getId());
        JFXButton del = new JFXButton();
        JFXButton add = new JFXButton();

        del.setText("-");
        del.setTextFill(Paint.valueOf("red"));
        del.setStyle("-fx-background-color: red");

        add.setText("+");
        add.setTextFill(Paint.valueOf("red"));
        add.setStyle("-fx-background-color: green");
        del.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String id = items.get(focusedIndex).getId();
                for (int j = 0; j < orders.size(); j++) {
                    if (id.equals(orders.get(j).getCode())) {
                        int qty1 = orders.get(j).getQty();
                        if (qty1 == 1) {
                            double price1 = orders.get(j).getPrice();
                            total -= price1;
                            orders.remove(j);
                            setOrdertable();
                            break;
                        }
                        orders.get(j).setQty(qty1 - 1);
                        double price1 = orders.get(j).getPrice();
                        orders.get(j).setTotal(orders.get(j).getQty() * price1);
                        total -= price1;
                        setOrdertable();
                        orderTable.refresh();
                        break;
                    }
                }
                orderTable.refresh();
                lblTotal.setText(String.valueOf(total));
            }
        });
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String id = items.get(focusedIndex).getId();
                for (OrderTm order : orders) {
                    if (id.equals(order.getCode())) {
                        int qty1 = order.getQty();//order current qty
                        double tot = order.getPrice();//oder means the table reference
                        int comboSelected = comboQty.getSelectionModel().getSelectedItem();
                        int capacity = comboSelected + qty1;
                        if (capacity <= order.getQtyOnHand()) {
                            order.setQty(qty1 + comboSelected);
                        } else {
                            comboQty.getSelectionModel().select(0);
                            return;
                        }

                        order.setTotal(order.getQty() * order.getPrice());

                        total += tot;
                        orderTable.refresh();
                        comboQty.getSelectionModel().select(0);
                        lblTotal.setText(String.valueOf(total));
                        return;
                    }
                }

            }
        });
        i.setButton(del);
        i.setPlusBtn(add);
        selectedItem = i;
        orders.add(selectedItem);
        setOrdertable();
    }

    private void setOrdertable() {
        colNameOrder.setCellValueFactory(new PropertyValueFactory<>("name"));//name = des
        colCodeOrder.setCellValueFactory(new PropertyValueFactory<>("code"));//code
        colQtyOrder.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPriceOrder.setCellValueFactory(new PropertyValueFactory<>("price"));
        colTotalOrder.setCellValueFactory(new PropertyValueFactory<>("total"));
        colActionOrder.setCellValueFactory(new PropertyValueFactory<>("button"));
        colSizeOrder.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPlus.setCellValueFactory(new PropertyValueFactory<>("plusBtn"));
        orderTms = FXCollections.observableArrayList(orders);

        orderTable.setItems(orderTms);

    }

    public void tableOnClicked(MouseEvent mouseEvent) {
    }

    public void drinkTblOnClicked(MouseEvent mouseEvent) {
        clickOnTable(drinkTbl);
    }

    public void dessertTblOnClicked(MouseEvent mouseEvent) {
        clickOnTable(dessertTbl);
    }

    public void txtNameKeyPressed(KeyEvent keyEvent) {
        try {
            String txtNameText = txtName.getText();
            boolean matcher = name.matcher(txtNameText).matches();
            if (matcher) {
                txtName.setStyle("-jfx-focus-color : green");
                lblWarning.setText("");
                return;
            }
            nameValidated = false;
            lblWarning.setText("Check details again!");
            txtName.setStyle("-jfx-focus-color : red");
            ArrayList<CustomerDTO> allC = null;

            allC = (ArrayList<CustomerDTO>) cs.getAllCustomer();

            boolean existsC = false;
            for (CustomerDTO c : allC) {
                if (c.getPhoneNumber().equals(txtPhoeNumber.getText())) {
                    existsC = true;
                    break;
                }
            }
            if (!existsC) {
                String currentId = cs.getLastCustomer();//lastCustomerId
                String s = cs.generateCustomerId("C",currentId);
                lblCustomerId.setText(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void textAddressOnkeyPressed(KeyEvent keyEvent) {
        String txtNameText = textAddress.getText();
        boolean matcher = address.matcher(txtNameText).matches();
        if (matcher) {
            textAddress.setStyle("-jfx-focus-color : green");
            lblWarning.setText("");
            return;
        }
        if (comboOrdertype.getSelectionModel().getSelectedItem().equals("Delivery")) {
            lblWarning.setText("Check details again!");
        }


        textAddress.setStyle("-jfx-focus-color : red");
    }

    public void txtPhoeNumberOnKeyReleased(KeyEvent keyEvent) {
        try {
            String ph = txtPhoeNumber.getText();

            CustomerDTO c = setCustomerExists(ph);
            if (c != null) {
                txtName.setText(c.getName());
                comboGenderCashier.getSelectionModel().selectFirst();
                comboGenderCashier.setValue(c.getGender());
                lblCustomerId.setText(c.getId());
                System.out.println(c.getGender());
                textAddress.setText(c.getAddress());
                lblWarning.setText("");
                txtPhoeNumber.setStyle("-jfx-focus-color : green");
                return;
            } else {
                txtName.setText("");
                comboGenderCashier.getSelectionModel().select("");
                comboGenderCashier.setValue("");
                lblCustomerId.setText("");
                textAddress.setText("");
            }

            boolean matcher = Validation.match(ph, Validates.PHONE_NUMBER);
            if (matcher) {
                txtPhoeNumber.setStyle("-jfx-focus-color : green");
                lblWarning.setText("");
                return;
            }
            txtPhoeNumber.setStyle("-jfx-focus-color : red");
            lblWarning.setText("Check details again!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private CustomerDTO setCustomerExists(String ph) throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers = (ArrayList<CustomerDTO>) cs.getAllCustomer();
        for (CustomerDTO c : allCustomers) {
            if (c.getPhoneNumber().equals(ph)) {
                return c;
            }
        }
        return null;
    }

    public void placeOrderBtnOnAction(ActionEvent actionEvent) {
        try {
            boolean check = checkfields();
            if (!check) {
                new Alert(Alert.AlertType.INFORMATION, "Please fill the details First").show();
                return;
            }
            if (orderTable.getItems().size() == 0) {
                new Alert(Alert.AlertType.INFORMATION, "Please Add Some Items to Order").show();
                return;
            }
            String orderId = lblOrderId.getText();
            String ph = txtPhoeNumber.getText();
            if (comboOrdertype.getSelectionModel().getSelectedItem().equals("Delivery")) {
                if (textAddress.getText().equals("")) {
                    new Alert(Alert.AlertType.INFORMATION, "Please Enter the Delivery Address").show();
                    return;
                }
            }
            ArrayList<CustomerDTO> allC = (ArrayList<CustomerDTO>) cs.getAllCustomer();
            boolean cExists = false;
            for (CustomerDTO c : allC) {
                if (lblCustomerId.getText().equals(c.getId())) {
                    cExists = true;
                    break;
                }
            }

            if (!cExists) {
                String id = lblCustomerId.getText();
                String name = txtName.getText();
                String phoneN = txtPhoeNumber.getText();
                String gender = comboGenderCashier.getSelectionModel().getSelectedItem();
                String address = textAddress.getText();
                cs.saveCustomer(new CustomerDTO(name,gender,address,id,phoneN));
            }
            ObservableList<OrderTm> items = orderTable.getItems();
            List<OrderDetailDTO> orderDetails = items.stream().map(orderTm -> new OrderDetailDTO(
                    orderId,
                    orderTm.getCode(),
                    orderTm.getQty(),
                    orderTm.getPrice()
            )).collect(Collectors.toList());
            String orderType = comboOrdertype.getSelectionModel().getSelectedItem();
            System.out.println(lblCustomerId.getText());
            OrdersDTO ordersDTO = new OrdersDTO(orderId, LocalDate.now(), lblCustomerId.getText(), orderType);
            boolean ordersAdded = os.placeOrder(ordersDTO, (ArrayList<OrderDetailDTO>) orderDetails);
            if (ordersAdded) {
                double customerCash = Double.parseDouble(txtCustomerCash.getText());
                double total = Double.parseDouble(lblTotal.getText());
                double balance = customerCash - total;
                generateReport(balance, customerCash, total);
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed Successfully").show();
                orderTable.getItems().clear();
                orderTable.refresh();
                comboOrdertype.getSelectionModel().clearSelection();
                comboGenderCashier.getSelectionModel().clearSelection();
                txtPhoeNumber.setText("");
                txtName.setText("");
                textAddress.setText("");
                String orderID = lblOrderId.getText();

                String d = os.generateId(orderID);
                lblOrderId.setText(d);
                orderTms.clear();
                orders.clear();
                lblCustomerId.setText("");

            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                DBConnection.getInstance().getConnection().setAutoCommit(true);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void generateReport(double balance, double customerCash, double total) {
        try {
            HashMap<String, Object> hm = new HashMap<>();
            ObservableList<OrderTm> items = orderTable.getItems();
            List<OrderTm> i = new ArrayList<OrderTm>();
            for (OrderTm o : items) {
                i.add(o);

            }
            for (OrderTm o : i) {
                System.out.println(o.getName());
            }

            JRBeanCollectionDataSource jr = new JRBeanCollectionDataSource(i);
            hm.put("total", total);
            hm.put("balance", balance);
            hm.put("cPaidAmount", customerCash);
            hm.put("cashier", lblCashierName.getText());
            hm.put("CollectionBeanParameter", jr);


            InputStream inputStream = this.getClass().getResourceAsStream("/com.sachin.finalproject/report/OrderBill.jrxml");
            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
            JasperReport compileReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, hm, new JREmptyDataSource());
            //JasperPrintManager.printReport(JasperPrint, true); // true = ask to print or not, false = direct print
            JasperViewer.viewReport(jasperPrint);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkfields() {
        String id = lblCustomerId.getText();
        String name = txtName.getText();
        String phoneN = txtPhoeNumber.getText();
        String gender = comboGenderCashier.getSelectionModel().getSelectedItem();
        String address = textAddress.getText();
        if (id.isEmpty() || name.isEmpty() || phoneN.isEmpty() || gender.isEmpty()) {
            return false;
        }
        return true;
    }

    private void loadNextOrderId() {
        try {
            //String orderId = PlaceOrderModel.generateNextOrderId(lblOrderId.getText());
            // lblOrderId.setText(orderId);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setUser(String name, Image img, UserDTO user) throws SQLException, ClassNotFoundException, FileNotFoundException {
        System.out.println(image);
        lblCashierName.setText(name);

        //OutputStream outputStream = new FileOutputStream();
    }


}



