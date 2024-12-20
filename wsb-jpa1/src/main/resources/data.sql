INSERT INTO address (id, city, address_line1, address_line2, postal_code)
VALUES
(1, 'city', 'xx', 'yy', '62-030'),
(2, 'Leszno', 'Kwiatowa 1', 'Malarska 2', '64-140');

INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number,  specialization,  address_id)
VALUES
(1, 'John', 'Doe', '123456789', 'johndoe@outlook.com', 'D1',  'DERMATOLOGIST' , 1),
(2, 'Jane', 'Smith', '666666666', 'janesmith@gmail.com', 'D2',  'OCULIST' , 2);

INSERT INTO patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, is_active, address_id)
VALUES 
(1, 'Tomas', 'Smith', '987654321', 'thomas@gmail.com', 'P1', '1990-05-15', true, 1),
(2, 'Jane', 'Kowalski', '878563412', 'jane@gmail.com', 'P2', '2000-01-01', true, 2);

INSERT INTO visit (id, description, time, doctor_id, patient_id)
VALUES
(1, 'Routine Checkup', '2024-11-24 10:00:00', 1, 1),
(2, 'Routine Checkup', '2024-11-25 10:00:00', 2, 2);

INSERT INTO medical_treatment(id, description, type, visit_id)
VALUES
(1, 'Ultrasound examination', 'USG', 1),
(2, 'Electrocardiogram test', 'EKG', 2);

