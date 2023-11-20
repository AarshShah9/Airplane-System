-- Use the 'airline' database
USE airline;

-- Drop 'tickets' table if it exists
DROP TABLE IF EXISTS tickets;

-- Drop 'bookings' table if it exists
DROP TABLE IF EXISTS bookings;

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

-- Now recreate the tables in the correct order

-- Create 'users' table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100),
    role INT NOT NULL,
    member BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create 'locations' table
CREATE TABLE locations (
    location_id INT AUTO_INCREMENT PRIMARY KEY,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL
);

-- Create 'airports' table
CREATE TABLE airports (
    airport_id INT AUTO_INCREMENT PRIMARY KEY,
    location_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    code VARCHAR(10) NOT NULL,
    FOREIGN KEY (location_id) REFERENCES locations(location_id)
);

-- Create 'aircrafts' table
CREATE TABLE aircrafts (
    aircraft_id INT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(100) NOT NULL,
    capacity INT NOT NULL
);

-- Create 'seats' table
CREATE TABLE seats (
    seat_id INT AUTO_INCREMENT PRIMARY KEY,
    aircraft_id INT NOT NULL,
    seat_number VARCHAR(10) NOT NULL,
    class VARCHAR(50) NOT NULL,
    FOREIGN KEY (aircraft_id) REFERENCES aircrafts(aircraft_id)
);

-- Create 'flights' table
CREATE TABLE flights (
    flight_id INT AUTO_INCREMENT PRIMARY KEY,
    aircraft_id INT NOT NULL,
    departure_time DATETIME NOT NULL,
    arrival_time DATETIME NOT NULL,
    departure_airport_id INT NOT NULL,
    arrival_airport_id INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (aircraft_id) REFERENCES aircrafts(aircraft_id),
    FOREIGN KEY (departure_airport_id) REFERENCES airports(airport_id),
    FOREIGN KEY (arrival_airport_id) REFERENCES airports(airport_id)
);

-- Create 'bookings' table
CREATE TABLE bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    flight_id INT NOT NULL,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (flight_id) REFERENCES flights(flight_id)
);

-- Create 'tickets' table
CREATE TABLE tickets (
    ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    booking_id INT NOT NULL,
    seat_id INT NOT NULL,
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id),
    FOREIGN KEY (seat_id) REFERENCES seats(seat_id)
);
