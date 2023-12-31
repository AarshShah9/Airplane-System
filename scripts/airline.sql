-- Create database
CREATE DATABASE IF NOT EXISTS airline;
-- Use the 'airline' database
USE airline;
-- Drop 'tickets' table if it exists
DROP TABLE IF EXISTS tickets;
-- Drop 'bookings' table if it exists
DROP TABLE IF EXISTS bookings;
-- Drop 'crew' table if it exists
DROP TABLE IF EXISTS crew;
-- Drop 'flights' table if it exists
DROP TABLE IF EXISTS flights;
-- Drop 'seats' table if it exists
DROP TABLE IF EXISTS seats;
-- Drop 'aircrafts' table if it exists
DROP TABLE IF EXISTS aircrafts;
-- Drop 'airports' table if it exists
DROP TABLE IF EXISTS airports;
-- Drop 'locations' table if it exists
DROP TABLE IF EXISTS locations;
-- Drop 'users' table if it exists
DROP TABLE IF EXISTS users;
-- Drop 'aircraftTypes' table if it exists
DROP TABLE IF EXISTS aircraftTypes;
-- Drop 'promotions' table if it exists
DROP TABLE IF EXISTS promotion;
-- Now recreate the tables in the correct order
-- Create 'users' table
CREATE TABLE users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    address VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    role INT NOT NULL,
    member BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- Create 'locations' table
CREATE TABLE locations (
    code VARCHAR(100) PRIMARY KEY,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL
);
-- Create 'aircraftTypes' table
CREATE TABLE aircraftTypes (
    model VARCHAR(100) PRIMARY KEY,
    capacity INT DEFAULT 66 NOT NULL
);
-- Create 'aircrafts' table
CREATE TABLE aircrafts (
    aircraft_id INT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(100) NOT NULL,
    FOREIGN KEY (model) REFERENCES aircraftTypes(model) ON DELETE CASCADE
);
-- Create 'seats' table
CREATE TABLE seats (
    seat_id INT AUTO_INCREMENT PRIMARY KEY,
    aircraft_id INT NOT NULL,
    seat_number VARCHAR(10) NOT NULL,
    class VARCHAR(50) NOT NULL,
    is_available BOOLEAN DEFAULT TRUE NOT NULL,
    FOREIGN KEY (aircraft_id) REFERENCES aircrafts(aircraft_id) ON DELETE CASCADE
);
-- Create 'flights' table
CREATE TABLE flights (
    flight_id INT AUTO_INCREMENT PRIMARY KEY,
    aircraft_id INT NOT NULL,
    departure_time DATETIME NOT NULL,
    arrival_time DATETIME NOT NULL,
    departure_airport_id VARCHAR(100) NOT NULL,
    arrival_airport_id VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (aircraft_id) REFERENCES aircrafts(aircraft_id) ON DELETE CASCADE,
    FOREIGN KEY (departure_airport_id) REFERENCES locations(code) ON DELETE CASCADE,
    FOREIGN KEY (arrival_airport_id) REFERENCES locations(code) ON DELETE CASCADE
);
-- Create 'bookings' table
CREATE TABLE bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    flight_id INT NOT NULL,
    seat_id INT NOT NULL,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    insurance BOOLEAN DEFAULT FALSE,
    price DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username),
    FOREIGN KEY (flight_id) REFERENCES flights(flight_id) ON DELETE CASCADE,
    FOREIGN KEY (seat_id) REFERENCES seats(seat_id) ON DELETE CASCADE
);
-- Create 'crew' table
CREATE TABLE crew (
    crew_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    flight_id INT NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username),
    FOREIGN KEY (flight_id) REFERENCES flights(flight_id) ON DELETE CASCADE
);
-- Create 'promotion' table
CREATE TABLE promotion (
    promotion_id INT AUTO_INCREMENT PRIMARY KEY,
    discount DECIMAL(3, 2) NOT NULL, -- Stores values from 0.00 to 1.00, representing 0% to 100%
    price_for_discount DECIMAL(10, 2) NOT NULL
);