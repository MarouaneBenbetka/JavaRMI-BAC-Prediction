# Baccalauréat Score Prediction RMI Application

This repository contains a Java RMI application designed to predict Baccalauréat (BAC) scores. It utilizes a client-server architecture to facilitate seamless communication and computation for BAC score predictions.

## Structure

The project is divided into two main components:

-   `client`: The client module is responsible for the user interface and interaction. It sends data to the server for processing and displays prediction results.

-   `server`: The server module handles the core computation and prediction logic. It receives data from the client, processes it, and sends back the prediction results.

## Prerequisites

-   Java JDK 17 or higher
-   Apache Maven (for dependency management and building the project)

## Setup

1. Clone the repository to your local machine.
2. Navigate to the project's root directory.
3. Build the project using Maven:

## Database Setup

1. Clone the repository to your local machine.
2. Install MySQL and ensure it's running on your system.
3. Create a new MySQL database for the application.
4. Navigate to the `server` directory and open the `DatabaseUtil` class.
5. Configure your database connection details (username, password, database URL) in the `DatabaseUtil` class.
6. Execute the following SQL command to create the `StudentRecords` table within your MySQL database:

    ```sql
    CREATE TABLE StudentRecords (
        id INT AUTO_INCREMENT PRIMARY KEY,
        grade1 FLOAT,
        grade2 FLOAT,
        grade3 FLOAT,
        bacGrade FLOAT,
        schoolName varchar(255),
        createdAt datetime DEFAULT CURRENT_TIMESTAMP
    );
    ```

## Running the Application

To run the server component:

1. Navigate to the `server` directory.
2. Execute the server application:

To run the client component:

1. Navigate to the `client` directory.
2. Execute the client application:

## Features

-   **Data Upload:** Users can upload student records for analysis.
-   **Score Prediction:** The application predicts BAC scores based on the provided data.
-   **Model Training:** New prediction models can be trained with updated data sets.

## Architecture

The application follows a Remote Method Invocation (RMI) pattern, allowing the client and server to communicate remotely with the following interfaces:

-   `DataUploadService`
-   `PredictionService`
-   `TrainingService`
