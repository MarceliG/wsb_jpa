insert into address (id, address_line1, address_line2, city, postal_code)
            values (1, 'xx', 'yy', 'city', '62-030'),
            (2, 'Kwiatowa 1', 'Malarska 2', 'Leszno', '64-140');

insert into doctor (id, doctor_number, email, first_name, last_name, specialization, telephone_number, address_id)
            values (1, 'D1', 'johndoe@outlook.com', 'John', 'Doe', 'Cardiology', '123456789', 1),
            (2, 'D2', 'janesmith@gmail.com', 'Jane', 'Smith', 'Neurology', '666666666', 2);

insert into patient (id, date_of_birth, email, first_name, last_name, patient_number, telephone_number, address_id)
            values (1, '1990-05-15', 'thomas@gmail.com', 'Tomas', 'Smith', 'P1', '987654321', 1),
            (2, '2000-01-01', 'jane@gmail.com', 'Jane', 'Kowalski', 'P2', '878563412', 2);

insert into visit (id, description, time, doctor_id, patient_id)
            values (1, 'Routine Checkup', '2024-11-24 10:00:00', 1, 1),
            (2, 'Routine Checkup', '2024-11-25 10:00:00', 2, 2);

insert into medical_treatment(id, description, type, visit_id)
            values (1, 'Blood Pressure Monitoring', 'Routine', 1),
            (2, 'Eye controll', 'Routing', 2);