CREATE TABLE cars (
    car_id INT AUTO_INCREMENT PRIMARY KEY,
    car_brand VARCHAR(80) NOT NULL,
    car_model VARCHAR(80) NOT NULL,
    car_license_plate VARCHAR(20) UNIQUE NOT NULL,
    car_description VARCHAR(1024),
    status ENUM('available', 'in_use', 'maintenance') NOT NULL,
    occupied_from DATE,
    occupied_to DATE
);

CREATE TABLE drivers (
    driver_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    driver_department VARCHAR(80)
);

CREATE TABLE car_drivers (
    car_id INT NOT NULL,
    driver_id INT NOT NULL,
    PRIMARY KEY (car_id, driver_id),
    FOREIGN KEY (car_id) REFERENCES cars(car_id),
    FOREIGN KEY (driver_id) REFERENCES drivers(driver_id)
);
