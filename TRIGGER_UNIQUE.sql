-- CREATE UQ
ALTER TABLE shift_works
ADD CONSTRAINT uq_shift_works UNIQUE (shift_type_id, shift_date, staff_id)
GO

ALTER TABLE registration
ADD CONSTRAINT uq_registration UNIQUE (customer_id, vehicle_id)
GO

ALTER TABLE service_fee
ADD CONSTRAINT uq_service_fee UNIQUE (decision_date, vehicle_type_id)
GO	

ALTER TABLE payments
ADD CONSTRAINT uq_payments UNIQUE (registration_id, extension_time)
GO	

ALTER TABLE type_service
ADD CONSTRAINT uq_type_service UNIQUE (service_fee_id, service_name, decision_date)
GO	

ALTER TABLE session_fees
ADD CONSTRAINT uq_session_fees UNIQUE (time_frame_id, vehicle_type_id, decision_date)
GO	

CREATE TRIGGER prevent_multiple_shifts 
ON [dbo].[shift_works]
FOR INSERT
AS
BEGIN
	IF EXISTS (
		SELECT 1 
		FROM INSERTED i
		JOIN shift_works s
			ON i.staff_id = s.staff_id AND i.shift_date = s.shift_date
	)
	BEGIN 
		RAISERROR('Vượt số ca quy định!', 16, 1);
		ROLLBACK TRAN 
		RETURN
	END
END
GO