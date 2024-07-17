-- Insert data into the cars table
INSERT INTO cars (car_brand, car_model, car_license_plate, car_description, car_status, occupied_from, occupied_to)
VALUES
    ('Toyota', 'Camry', 'ABC123', 'Blue sedan', 'available', NULL, NULL),
    ('Honda', 'Civic', 'XYZ456', 'Red compact car', 'in_use', '2024-06-01', '2024-06-30'),
    ('Ford', 'Mustang', 'DEF789', 'Yellow sports car', 'maintenance', NULL, NULL);

-- Insert data into the drivers table
INSERT INTO drivers (first_name, last_name, driver_department)
VALUES
    ('John', 'Doe', 'Sales'),
    ('Jane', 'Smith', 'Marketing'),
    ('Michael', 'Johnson', 'Engineering');
