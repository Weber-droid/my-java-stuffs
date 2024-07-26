package com.example.mainhub;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static javafx.geometry.Pos.CENTER;

import java.util.ArrayList;
import java.util.List;

public class MainApp extends Application {

    private VBox loginPane;
    private VBox signUpPane;
    private BorderPane dashboardPane;
    private Stage primaryStage;
    private Dashboard dashboard;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("TICKIFY");

        dashboard = new Dashboard(this);

        initializeLoginPane();
        initializeSignUpPane();
        initializeDashboardPane();

        Scene scene = new Scene(loginPane, 1200, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeLoginPane() {
        loginPane = new VBox(20);
        loginPane.setPadding(new Insets(40));
        loginPane.setAlignment(Pos.CENTER);
        loginPane.setStyle("-fx-background-color: #f0f0f0;");

        Label titleLabel = new Label("Welcome to TICKIFY");
        titleLabel.setFont(Font.font("Arial", 32));
        titleLabel.setStyle("-fx-text-fill: #410000;");

        VBox signInPanel = new VBox(10);
        signInPanel.setAlignment(Pos.CENTER);
        signInPanel.setPadding(new Insets(20));
        signInPanel.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        signInPanel.setEffect(new DropShadow(10, Color.GRAY));

        Label signInLabel = new Label("Sign In");
        signInLabel.setFont(Font.font("Arial", 24));
        signInLabel.setStyle("-fx-text-fill: #410000;");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(300);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(300);

        Button signInButton = new Button("SIGN IN");
        signInButton.setStyle("-fx-background-color: #410000; -fx-text-fill: White;");
        signInButton.setMaxWidth(150);
        signInButton.setOnAction(e -> {
            System.out.println("Sign In button clicked");
            authenticateUser();
        });

        Hyperlink forgotPasswordLink = new Hyperlink("Forgot your password?");
        forgotPasswordLink.setStyle("-fx-text-fill: #410000;");

        HBox signUpLinkBox = new HBox(10);
        signUpLinkBox.setAlignment(Pos.CENTER_RIGHT);
        Label signUpLabel = new Label("Don't have an account?");
        signUpLabel.setStyle("-fx-text-fill: #410000;");
        Hyperlink signUpLink = new Hyperlink("Sign Up");
        signUpLink.setStyle("-fx-text-fill: #410000;");
        signUpLink.setOnAction(e -> showSignUp());
        signUpLinkBox.getChildren().addAll(signUpLabel, signUpLink);

        signInPanel.getChildren().addAll(signInLabel, emailField, passwordField, signInButton, forgotPasswordLink, signUpLinkBox);

        loginPane.getChildren().addAll(titleLabel, signInPanel);
    }

    private void initializeSignUpPane() {
        signUpPane = new VBox(20);
        signUpPane.setPadding(new Insets(40));
        signUpPane.setAlignment(Pos.CENTER);
        signUpPane.setStyle("-fx-background-color: #f0f0f0;");

        Label titleLabel = new Label("Create Account");
        titleLabel.setFont(Font.font("Arial", 32));
        titleLabel.setStyle("-fx-text-fill: #410000;");

        VBox signUpPanel = new VBox(10);
        signUpPanel.setAlignment(Pos.CENTER);
        signUpPanel.setPadding(new Insets(20));
        signUpPanel.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        signUpPanel.setEffect(new DropShadow(10, Color.GRAY));

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        nameField.setMaxWidth(300);

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(300);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(300);

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");
        confirmPasswordField.setMaxWidth(300);

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");
        phoneField.setMaxWidth(300);

        Button signUpButton = new Button("SIGN UP");
        signUpButton.setStyle("-fx-background-color: #410000; -fx-text-fill: white;");
        signUpButton.setMaxWidth(150);
        signUpButton.setOnAction(e -> {
            System.out.println("Sign Up button clicked");
            authenticateUser();
        });

        HBox signInLinkBox = new HBox(10);
        signInLinkBox.setAlignment(Pos.CENTER_RIGHT);
        Label signInLabel = new Label("Already have an account?");
        signInLabel.setStyle("-fx-text-fill: #410000;");
        Hyperlink signInLink = new Hyperlink("Sign In");
        signInLink.setStyle("-fx-text-fill: #410000;");
        signInLink.setOnAction(e -> showLogin());
        signInLinkBox.getChildren().addAll(signInLabel, signInLink);

        signUpPanel.getChildren().addAll(nameField, emailField, passwordField, confirmPasswordField, phoneField, signUpButton, signInLinkBox);

        signUpPane.getChildren().addAll(titleLabel, signUpPanel);
    }

    private void initializeDashboardPane() {
        dashboardPane = new BorderPane();
        dashboardPane.setLeft(dashboard.getSidebar());
        dashboardPane.setCenter(dashboard.getDashboardPane());
    }

    public void showLogin() {
        primaryStage.getScene().setRoot(loginPane);
    }

    public void showSignUp() {
        primaryStage.getScene().setRoot(signUpPane);
    }

    public void showDashboard() {
        primaryStage.getScene().setRoot(dashboardPane);
    }

    public void authenticateUser() {
        // Implement your authentication logic here
        System.out.println("Authentication logic here");
        showDashboard();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Dashboard class as an inner class for simplicity
    public static class Dashboard {

        private BorderPane root;
        private MainApp mainApp;
        private List<String> bookedMovies;

        public Dashboard(MainApp mainApp) {
            this.mainApp = mainApp;
            this.bookedMovies = new ArrayList<>();
            this.root = new BorderPane();
        }

        public VBox getSidebar() {
            // Sidebar
            VBox sidebar = new VBox(10);
            sidebar.setPadding(new Insets(50, 15, 15, 15)); // Add padding at the top
            sidebar.setStyle("-fx-background-color: #410000; -fx-pref-width: 200px; -fx-border-color: #410000; -fx-border-width: 2px; -fx-border-radius: 10px;");
            sidebar.setAlignment(Pos.TOP_CENTER); // Align items to the top
            Label welcomeLabel = new Label("Welcome\n To TICTIFY");
            welcomeLabel.setStyle("-fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 20px 0; -fx-alignment: center;");

            // Sidebar buttons
            Button dashboardBtn = createSidebarButton("Dashboard");
            Button addMoviesBtn = createSidebarButton("Add Movies");
            Button availableMoviesBtn = createSidebarButton("Available Movies");
            Button bookedMoviesBtn = createSidebarButton("Booked Movies");

            VBox.setVgrow(dashboardBtn, Priority.NEVER);
            VBox.setVgrow(addMoviesBtn, Priority.NEVER);
            VBox.setVgrow(availableMoviesBtn, Priority.NEVER);
            VBox.setVgrow(bookedMoviesBtn, Priority.NEVER);

            VBox menuButtons = new VBox(10, dashboardBtn, addMoviesBtn, availableMoviesBtn, bookedMoviesBtn);
            VBox.setVgrow(menuButtons, Priority.ALWAYS);

            // Center the buttons
            menuButtons.setPadding(new Insets(50,15, 15,15));


            // Sign Out button
            Button signOutBtn = createSidebarButton("Sign Out");
            VBox.setVgrow(signOutBtn, Priority.NEVER);
            HBox signOutBox = new HBox(signOutBtn);
            signOutBox.setAlignment(Pos.BOTTOM_CENTER);
            VBox.setVgrow(signOutBox, Priority.NEVER);

            sidebar.getChildren().addAll(welcomeLabel, menuButtons, signOutBox);

            // Button actions
            dashboardBtn.setOnAction(e -> mainApp.dashboardPane.setCenter(getDashboardPane()));
            addMoviesBtn.setOnAction(e -> mainApp.dashboardPane.setCenter(getAddMoviesPane()));
            availableMoviesBtn.setOnAction(e -> mainApp.dashboardPane.setCenter(getAvailableMoviesPane()));
            bookedMoviesBtn.setOnAction(e -> mainApp.dashboardPane.setCenter(getEventListPane()));
            signOutBtn.setOnAction(e -> mainApp.showLogin());

            return sidebar;
        }

        private Button createSidebarButton(String text) {
            Button button = new Button(text);
            button.setStyle("-fx-background-color: #800000; -fx-text-fill: white; -fx-pref-width: 160px; -fx-pref-height: 40px;");
            return button;
        }

        // Replace the existing getDashboardPane method with the new one
        public Node getDashboardPane() {
            HBox topStats = new HBox(20);
            topStats.setPadding(new Insets(10));
            topStats.setPadding(new Insets(10, 10, 10, 240));
            topStats.getChildren().addAll(createStatBox("Total Sold Ticket", "9"));
            topStats.getChildren().addAll(createStatBox("Total Income", "$990.0"));
            topStats.getChildren().addAll(createStatBox("Available Movies", "15"));


            String imagePath = "file:src/main/java/com/example/mainhub/cinema.jpg";
            ImageView imageView = new ImageView(new Image(imagePath));
            imageView.setFitHeight(500);
            imageView.setFitWidth(2000);
            imageView.setPreserveRatio(true);

            HBox imageContainer = new HBox(imageView);
            imageContainer.setAlignment(CENTER);
            imageContainer.setPadding(new Insets(15));

            VBox mainContent = new VBox(20);
            mainContent.getChildren().addAll(topStats, imageContainer);

            return mainContent;
        }


        // Helper method to create stat boxes
        private VBox createStatBox(String title, String value) {
            VBox statBox = new VBox(10);
            statBox.setAlignment(Pos.CENTER);
            statBox.setStyle("-fx-background-color: #410000; -fx-padding: 20px; -fx-border-radius: 10px;");

            Label titleLabel = new Label(title);
            titleLabel.setFont(Font.font("Arial", 18));
            titleLabel.setStyle("-fx-text-fill: White;");

            Label valueLabel = new Label(value);
            valueLabel.setFont(Font.font("Arial", 24));
            valueLabel.setStyle("-fx-text-fill: White;");

            statBox.getChildren().addAll(titleLabel, valueLabel);
            return statBox;
        }


        public Pane getAddMoviesPane() {
            VBox pane = new VBox();
            pane.setAlignment(Pos.CENTER);
            showAddMovies(pane); // Call the showAddMovies method to display the Add Movies pane
            return pane;
        }

        public Pane getAvailableMoviesPane() {
            VBox pane = new VBox();
            pane.setAlignment(Pos.CENTER);
            showAvailableMovies(pane);
            return pane;
        }

        public Pane getEventListPane() {
            VBox pane = new VBox(20);
            pane.setAlignment(Pos.TOP_CENTER);
            pane.setPadding(new Insets(20));

            // Header
            Label header = new Label("Booked Movies");
            header.setFont(Font.font("Arial", 24));
            header.setStyle("-fx-text-fill: #410000;");

            // Search Bar
            HBox searchBar = new HBox(10);
            searchBar.setAlignment(Pos.CENTER);
            TextField searchField = new TextField();
            searchField.setPromptText("Search movies...");
            searchField.setMaxWidth(300);
            Button searchButton = new Button("Search");
            searchButton.setStyle("-fx-background-color: #410000; -fx-text-fill: white;");
            searchButton.setOnAction(e -> {
                // Implement search functionality
                String query = searchField.getText().toLowerCase();
                List<String> filteredMovies = new ArrayList<>();
                for (String movie : bookedMovies) {
                    if (movie.toLowerCase().contains(query)) {
                        filteredMovies.add(movie);
                    }
                }
                updateMovieList(pane, filteredMovies);
            });
            searchBar.getChildren().addAll(searchField, searchButton);

            // Movie List
            ListView<String> movieListView = new ListView<>();
            movieListView.getItems().addAll(bookedMovies);
            movieListView.setPrefWidth(600);
            movieListView.setPrefHeight(400);

            // Add Components to Pane
            pane.getChildren().addAll(header, searchBar, movieListView);

            return pane;
        }

        // Helper method to update the movie list
        private void updateMovieList(VBox pane, List<String> movies) {
            ListView<String> movieListView = new ListView<>();
            movieListView.getItems().addAll(movies);
            movieListView.setPrefWidth(600);
            movieListView.setPrefHeight(400);
            pane.getChildren().set(2, movieListView); // Update the movie list view
        }


        private void showAvailableMovies(Pane root) {
            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10, 10, 10, 10));
            grid.setAlignment(CENTER);
            grid.setVgap(30);
            grid.setHgap(30);

            // Creating a container for movie images
            FlowPane imageContainer = new FlowPane();
            imageContainer.setPadding(new Insets(10));
            imageContainer.setVgap(20); // Vertical gap between rows
            imageContainer.setHgap(20); // Horizontal gap between images
            imageContainer.setPrefWrapLength(800); // Wraps content when width is exceeded

            // Adding sample images to the container
            for (int i = 0; i < 15; i++) { // Adjust the number of images as needed
                ImageView imageView = new ImageView(new Image("file:src/main/resources/com/example/mainapp/demo1/sample-movie" + i + ".jpg")); // Adjust the path to your image resources
                imageView.setFitHeight(150);
                imageView.setFitWidth(100);
                imageView.setPreserveRatio(true);

                VBox imageBox = new VBox();
                imageBox.setPadding(new Insets(10)); // Padding inside each image box
                imageBox.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc; -fx-border-width: 1px; -fx-border-radius: 5px;");
                imageBox.getChildren().add(imageView);

                // Add click event handler to the imageBox
                imageBox.setOnMouseClicked(e -> showBookingPopup("Sample Movie Title"));

                imageContainer.getChildren().add(imageBox);
            }

            // Add the imageContainer to the GridPane
            GridPane.setConstraints(imageContainer, 0, 0); // Position the FlowPane in the grid
            grid.getChildren().add(imageContainer);

            // Add other UI components to the GridPane as needed

            root.getChildren().add(grid);
        }

        private void showBookingPopup(String sampleMovieTitle) {
            // Create a new stage for the popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Book Tickets");

            // Create the GridPane for the booking form
            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10, 10, 10, 10));
            grid.setVgap(8);
            grid.setHgap(10);

            Label movieTitleLabel = new Label("Movie Title:");
            GridPane.setConstraints(movieTitleLabel, 0, 0);

            TextField movieTitleInput = new TextField();
            GridPane.setConstraints(movieTitleInput, 1, 0);

            Label quantityLabel = new Label("Quantity:");
            GridPane.setConstraints(quantityLabel, 0, 4);

            Spinner<Integer> quantitySpinner = new Spinner<>(0, 100, 0);
            GridPane.setConstraints(quantitySpinner, 1, 4);

            Button bookTicketButton = new Button("Book Ticket");
            GridPane.setConstraints(bookTicketButton, 1, 5);

            // Add all components to the grid
            grid.getChildren().addAll(movieTitleLabel, movieTitleInput, quantityLabel, quantitySpinner, bookTicketButton);

            // Set the scene and show the popup stage
            Scene scene = new Scene(grid, 300, 200);
            popupStage.setScene(scene);
            popupStage.show();

            // Event handler for the Book Ticket button
            bookTicketButton.setOnAction(e -> {
                String movieTitle = movieTitleInput.getText();
                if (movieTitle.isEmpty()) {
                    // Display an error message if the movie title is empty
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Movie title cannot be empty!");

                    alert.showAndWait();
                } else {
                    // Display confirmation message
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Booking Confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("You have booked the movie successfully!");

                    alert.showAndWait();

                    // Add the booked movie to the list
                    bookedMovies.add(movieTitle + " (Quantity: " + quantitySpinner.getValue() + ")");



                    // Close the popup window
                    popupStage.close();
                }
            });
        }


        private void showAddMovies(Pane root) {
            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10, 10, 10, 10));
            grid.setAlignment(CENTER);
            grid.setVgap(30);
            grid.setHgap(30);

            Label movieTitleLabel = new Label("Movie Title:");
            GridPane.setConstraints(movieTitleLabel, 0, 0);

            TextField movieTitleInput = new TextField();
            GridPane.setConstraints(movieTitleInput, 1, 0);

            Label genreLabel = new Label("Genre:");
            GridPane.setConstraints(genreLabel, 0, 1);

            TextField genreInput = new TextField();
            GridPane.setConstraints(genreInput, 1, 1);

            Label durationLabel = new Label("Duration:");
            GridPane.setConstraints(durationLabel, 0, 2);

            TextField durationInput = new TextField();
            GridPane.setConstraints(durationInput, 1, 2);

            Label showingDateLabel = new Label("Showing Date:");
            GridPane.setConstraints(showingDateLabel, 0, 3);

            DatePicker showingDateInput = new DatePicker();
            GridPane.setConstraints(showingDateInput, 1, 3);

            Button insertButton = new Button("Insert");
            GridPane.setConstraints(insertButton, 1, 4);
            insertButton.setStyle("-fx-background-color: #3a81f2; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");

            Button deleteButton = new Button("Delete");
            GridPane.setMargin(deleteButton, new Insets(0, 0, 0, -110)); // Adjust margin to move closer
            GridPane.setConstraints(deleteButton, 2, 4);
            deleteButton.setStyle("-fx-background-color: #e24652; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");

            ListView<String> movieListView = new ListView<>();
            movieListView.setPrefWidth(600);
            movieListView.setPrefHeight(400);
            GridPane.setConstraints(movieListView, 4, 0, 4, 9); // Position it in the fourth column, spanning 5 rows

            insertButton.setOnAction(e -> {
                String movieTitle = movieTitleInput.getText();
                String genre = genreInput.getText();
                String duration = durationInput.getText();
                String showingDate = (showingDateInput.getValue() != null) ? showingDateInput.getValue().toString() : "";

                if (!movieTitle.isEmpty() && !genre.isEmpty() && !duration.isEmpty() && !showingDate.isEmpty()) {
                    String movieDetails = "Title: " + movieTitle + ", Genre: " + genre + ", Duration: " + duration + ", Date: " + showingDate;
                    movieListView.getItems().add(movieDetails);
                    movieTitleInput.clear();
                    genreInput.clear();
                    durationInput.clear();
                    showingDateInput.setValue(null);
                }
            });

            deleteButton.setOnAction(e -> {
                String selectedItem = movieListView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    movieListView.getItems().remove(selectedItem);
                }
            });



            grid.getChildren().addAll(movieTitleLabel, movieTitleInput, genreLabel, genreInput, durationLabel, durationInput, showingDateLabel, showingDateInput, insertButton, deleteButton, movieListView);

            root.getChildren().add(grid);
        }
    }
}
