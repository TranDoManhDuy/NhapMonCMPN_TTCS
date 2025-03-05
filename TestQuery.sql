USE TEST_VINHOMEPARKING

INSERT INTO buildings(building_name, address) VALUES 
	('S10.01', 'TP Thu Duc');
INSERT INTO customers (full_name, ssn, date_of_birth, gender, phone_number, address, building_id, nationality) VALUES
	('Tran Van Trong', '050505050503', '01/01/2004', 'M', '1234567893', 'TP Thu Duc', 1, 'VietNam');
INSERT INTO vehicles (identification_code, vehicle_type_id, vehicle_name, vehicle_color) VALUES 
	('0101010107', 1, 'R15', 'Black');
INSERT INTO shift_types (shift_type_name, start_time, end_time) VALUES 
	('Ca 2', '13:01', '19:00');
INSERT INTO resident_cards(customer_id, resident_card_id, is_active) VALUES
	(10, 2, 1);
INSERT INTO tasks(task_name) VALUES 
	('The ra');
INSERT INTO roles (role_name) VALUES
	('Nhan Vien');
INSERT INTO staff (role_id, full_name, ssn, date_of_birth, gender, phone_number, address, email, is_active) VALUES
	(1, 'Vu Dinh Khoa', '033333333331', '2004-11-21', 'M', '0888888887', 'TP Thu Duc', 'ksfhb@gmail.com', 1);
INSERT INTO shift_works (building_id, shift_type_id, shift_date, task_id, staff_id) VALUES 
	(1, 2, '05/03/2025', 1, 5);
INSERT INTO visitor_parking_cards (is_active) VALUES 
	(1);
INSERT INTO parking_sessions(card_id, is_service, check_in_time, check_in_shift_id, vehicle_id) VALUES
	(1, 0, '07:30', 4, 2);
INSERT INTO registration(customer_id, registration_date, vehicle_id, state) VALUES 
	(10, '2025-03-5', 5, '0');
DELETE FROM registration where registration_id = 2;

UPDATE resident_cards SET resident_card_id = 1, customer_id = 5, is_active = 0 WHERE pk_resident_card = 11;

	SELECT * FROM buildings
	SELECT * FROM customers
	SELECT * FROM resident_cards
	SELECT * FROM vehicle_types
	SELECT * FROM vehicles
	SELECT * FROM shift_types
	SELECT * FROM tasks
	SELECT * FROM roles
	SELECT * FROM staff
	SELECT * FROM shift_works	
	SELECT * FROM visitor_parking_cards
	SELECT * FROM parking_sessions
	SELECT * FROM lost_resident_cards
	SELECT * FROM registration;
	SELECT * FROM resident_cards_registration;

DELETE FROM customers WHERE customer_id = 3;

-- trigger
ALTER TRIGGER insert_residentcards_registration
ON registration
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    IF NOT EXISTS (
		SELECT 1 
		FROM INSERTED i
		JOIN resident_cards r
			ON i.customer_id = r.customer_id
		WHERE r.is_active = 1
	)
	BEGIN
		PRINT 'ROLLBACK: Không có resident_card nào đang active!';
        ROLLBACK;
	END

	INSERT INTO resident_cards_registration (pk_resident_card, registration_id)
	SELECT 
		r.pk_resident_card,
		i.registration_id
	FROM INSERTED i
	JOIN resident_cards r
		ON i.customer_id = r.customer_id
	WHERE r.is_active = 1;
END
GO

CREATE TRIGGER create_pk_resident_card 
ON resident_cards
AFTER INSERT
AS 
BEGIN
	SET NOCOUNT ON;
	
	UPDATE rc
	SET rc.pk_resident_card = i.resident_card_id + '-' + CAST(i.qr_index AS VARCHAR)
	FROM resident_cards rc
	INNER JOIN INSERTED i 
		ON rc.qr_index = i.qr_index AND rc.resident_card_id = i.resident_card_id;
END
GO