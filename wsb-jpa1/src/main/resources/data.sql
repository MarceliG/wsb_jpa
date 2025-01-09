INSERT INTO address (id, city, address_line1, address_line2, postal_code)
VALUES
(1, 'city', 'test_adres_1', 'test_adres_2', '62-030'),
(2, 'Leszno', 'Kwiatowa 1', 'Malarska 2', '64-140'),
(3, 'Poznań', 'Grunwaldzka 12', '2C', '61-123'),
(4, 'Wrocław', 'Rynek 22', '2D', '50-438'),
(5, 'Kraków', 'Floriańska 10', '2E', '31-019'),
(6, 'Gdańsk', 'Długa 5', '2F', '80-831');

INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
VALUES
(1, 'John', 'Doe', '123456789', 'johndoe@outlook.com', 'D1',  'DERMATOLOGIST' , 1),
(2, 'Jane', 'Smith', '666666666', 'janesmith@gmail.com', 'D2',  'OCULIST' , 2),
(3, 'Michael', 'Johnson', '777777777', 'michaelj@clinic.com', 'D3', 'OCULIST', 3),
(4, 'Emily', 'Davis', '888888888', 'emilyd@clinic.com', 'D4', 'SURGEON', 4),
(5, 'Robert', 'Kowalski', '999999999', 'robertk@clinic.com', 'D5', 'GP', 5),
(6, 'Laura', 'Martinez', '111222333', 'lauram@clinic.com', 'D6', 'DERMATOLOGIST', 6);

INSERT INTO patient (id, `version`, first_name, last_name, telephone_number, email, patient_number, date_of_birth, is_active, address_id)
VALUES 
(1, 0, 'Tomas', 'Smith', '987654321', 'thomas@gmail.com', 'P1', '1990-05-15', true, 1),
(2, 0, 'Jane', 'Kowalski', '878563412', 'jane@gmail.com', 'P2', '2000-01-01', true, 2),
(3, 0, 'Anna', 'Nowak', '556677889', 'anna.nowak@mail.com', 'P3', '1985-07-20', true, 3),
(4, 0, 'Marcin', 'Zielinski', '334455667', 'marcin.zielinski@mail.com', 'P4', '1978-12-10', true, 4),
(5, 0, 'Piotr', 'Węgrzyn', '123123123', 'piotr.wegrzyn@mail.com', 'P5', '1995-03-05', true, 5),
(6, 0, 'Monika', 'Lewandowska', '321321321', 'monika.lewandowska@mail.com', 'P6', '1992-11-22', true, 6);

INSERT INTO visit (id, description, time, doctor_id, patient_id)
VALUES
(1, 'Routine Checkup', '2024-11-24 10:00:00', 1, 1),
(2, 'Routine Checkup', '2024-11-25 10:00:00', 2, 2),
(3, 'Follow-up', '2024-11-26 09:00:00', 1, 3),
(4, 'Consultation', '2024-11-27 14:00:00', 3, 4),
(5, 'Routine Checkup', '2024-11-28 11:00:00', 4, 3),
(6, 'Consultation', '2024-11-29 15:30:00', 2, 4),
(7, 'Neurology Consultation', '2024-12-01 10:30:00', 5, 5),
(8, 'Pediatric Checkup', '2024-12-02 09:00:00', 6, 6),
(9, 'Routine Checkup', '2024-12-03 13:00:00', 4, 5),
(10, 'Follow-up', '2024-12-04 11:30:00', 5, 6);

INSERT INTO medical_treatment(id, description, type, visit_id)
VALUES
(1, 'Ultrasound examination', 'USG', 1),
(2, 'Electrocardiogram test', 'EKG', 2),
(3, 'Ultrasound examination', 'USG', 3),
(4, 'Electrocardiogram test', 'EKG', 4),
(5, 'X-ray examination', 'RTG', 5),
(6, 'Ultrasound examination', 'USG', 6),
(7, 'X-ray examination', 'RTG', 7),
(8, 'Electrocardiogram test', 'EKG', 8),
(9, 'X-ray examination', 'RTG', 9),
(10, 'Ultrasound examination', 'USG', 10);
