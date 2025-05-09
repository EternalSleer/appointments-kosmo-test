INSERT INTO doctor (id,first_name, last_name, specialty) VALUES (1,'Samuel', 'Parra', 'Nuerocirujano');
INSERT INTO doctor (id,first_name, last_name, specialty) VALUES (2,'Daniel', 'Jimenez', 'Cirjano Cardiologo');
INSERT INTO doctor (id,first_name, last_name, specialty) VALUES (3,'Edgar', 'Sanchez', 'Pediatra');
INSERT INTO doctor (id,first_name, last_name, specialty) VALUES (4,'David', 'Evans', 'Ginecologo');
INSERT INTO doctor (id,first_name, last_name, specialty) VALUES (5, 'Angel', 'Diaz', 'Urologo');

INSERT INTO room (id,number, floor) VALUES (1,102, 1);
INSERT INTO room (id,number, floor) VALUES (2,204, 2);
INSERT INTO room (id, number, floor) VALUES (3, 306, 3);


INSERT INTO appointment (patient_name, time, doctor_id, room_id) VALUES ('EDGAR Doe', '2025-05-09 11:00:00', 1, 1);
INSERT INTO appointment (patient_name, time, doctor_id, room_id) VALUES ('SAMUEL Doe', '2025-05-09 12:00:00', 1, 1);
INSERT INTO appointment (patient_name, time, doctor_id, room_id) VALUES ('DANIUEL Doe', '2025-05-09 13:00:00', 1, 1);
INSERT INTO appointment (patient_name, time, doctor_id, room_id) VALUES ('EVANS Doe', '2025-05-09 14:00:00', 1, 1);
INSERT INTO appointment (patient_name, time, doctor_id, room_id) VALUES ('ANGEL Doe', '2025-05-09 15:00:00', 1, 1);
INSERT INTO appointment (patient_name, time, doctor_id, room_id) VALUES ('RAMON Doe', '2025-05-09 16:00:00', 1, 1);
INSERT INTO appointment (patient_name, time, doctor_id, room_id) VALUES ('John Doe', '2025-05-09 17:00:00', 1, 1);