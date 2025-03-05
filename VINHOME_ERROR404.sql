CREATE DATABASE TEST_VINHOMEPARKING
USE TEST_VINHOMEPARKING

CREATE TABLE [supervisors] (
  [manager_id] int,
  [staff_id] int,
  PRIMARY KEY ([manager_id], [staff_id])
)
GO

CREATE TABLE [staff] (
  [staff_id] int PRIMARY KEY IDENTITY(1, 1),
  [role_id] int NOT NULL,
  [full_name] varchar(255) NOT NULL,
  [ssn] varchar(12) UNIQUE NOT NULL,
  [date_of_birth] date NOT NULL,
  [gender] varchar(1) NOT NULL,
  [phone_number] varchar(10) UNIQUE NOT NULL,
  [address] varchar(255) NOT NULL,
  [email] varchar(255) UNIQUE NOT NULL,
  [is_active] bit NOT NULL DEFAULT (1)
)
GO

CREATE TABLE [roles] (
  [role_id] int PRIMARY KEY IDENTITY(1, 1),
  [role_name] varchar(255) UNIQUE NOT NULL
)
GO

CREATE TABLE [shift_types] (
  [shift_type_id] int PRIMARY KEY IDENTITY(1, 1),
  [shift_type_name] varchar(255) UNIQUE NOT NULL,
  [start_time] time NOT NULL,
  [end_time] time NOT NULL
)
GO

CREATE TABLE [managers] (
  [staff_id] int PRIMARY KEY IDENTITY(1, 1)
)
GO

CREATE TABLE [rights] (
  [right_id] int PRIMARY KEY IDENTITY(1, 1),
  [right_name] varchar(255) UNIQUE NOT NULL,
  [right_desc] varchar(255)
)
GO

CREATE TABLE [roles_rights] (
  [role_id] int,
  [right_id] int,
  PRIMARY KEY ([role_id], [right_id])
)
GO

CREATE TABLE [customers] (
  [building_id] int NOT NULL,
  [customer_id] int PRIMARY KEY IDENTITY(1, 1),
  [full_name] varchar(255) NOT NULL,
  [ssn] varchar(12) UNIQUE NOT NULL,
  [date_of_birth] date NOT NULL,
  [gender] varchar(1) NOT NULL,
  [phone_number] varchar(10) UNIQUE NOT NULL,
  [address] varchar(255) NOT NULL,
  [nationality] varchar(255) NOT NULL
)
GO

CREATE TABLE [vehicles] (
  [vehicle_id] int PRIMARY KEY IDENTITY(1, 1),
  [identification_code] varchar(10) UNIQUE NOT NULL,
  [vehicle_type_id] int NOT NULL,
  [vehicle_name] varchar(255),
  [vehicle_color] varchar(255)
)
GO

CREATE TABLE [vehicle_types] (
  [vehicle_type_id] int PRIMARY KEY IDENTITY(1, 1),
  [vehicle_type_name] varchar(255) UNIQUE NOT NULL
)
GO

CREATE TABLE [registration] (
  [customer_id] int NOT NULL,
  [registration_id] int PRIMARY KEY IDENTITY(1, 1),
  [registration_date] date NOT NULL,
  [vehicle_id] int NOT NULL,
  [state] varchar(1) NOT NULL
)
GO

CREATE TABLE [resident_cards] (
  [customer_id] int,
  [resident_card_id] VARCHAR(10),
  [qr_index] int IDENTITY(1, 1),
  [is_active] bit NOT NULL DEFAULT (1),
  [pk_resident_card] VARCHAR(10) UNIQUE,
  PRIMARY KEY ([resident_card_id], [qr_index])
)
GO

CREATE TABLE [lost_resident_cards] (
  [pk_resident_card] VARCHAR(10),
  [parking_session_id] int,
  PRIMARY KEY ([pk_resident_card], [parking_session_id])
)
GO

CREATE TABLE [parking_sessions] (
  [card_id] int NOT NULL,
  [parking_session_id] int PRIMARY KEY IDENTITY(1, 1),
  [is_service] bit NOT NULL,
  [check_in_time] datetime NOT NULL,
  [check_out_time] datetime,
  [check_out_shift_id] int,
  [check_in_shift_id] int NOT NULL,
  [vehicle_id] int NOT NULL,
  [amount] INT
)
GO

ALTER TABLE parking_sessions
ALTER COLUMN check_in_time TIME NOT NULL;

ALTER TABLE parking_sessions
ALTER COLUMN check_out_time TIME NULL;


CREATE TABLE [service_fee] (
  [service_fee_id] int PRIMARY KEY IDENTITY(1, 1),
  [decision_date] date,
  [vehicle_type_id] int NOT NULL,
  [amount] int NOT NULL,
  [is_active] bit NOT NULL DEFAULT (1)
)
GO

CREATE TABLE [accounts] (
  [account_number] int PRIMARY KEY IDENTITY(1, 1),
  [password] varchar(255) NOT NULL,
  [is_active] bit NOT NULL DEFAULT (1),
  [staff_id] int UNIQUE NOT NULL
)
GO

CREATE TABLE [tasks] (
  [task_id] int PRIMARY KEY IDENTITY(1, 1),
  [task_name] varchar(255) UNIQUE NOT NULL,
  [task_desc] varchar(255)
)
GO

CREATE TABLE [shift_works] (
  [building_id] int NOT NULL,
  [shift_work_id] int PRIMARY KEY IDENTITY(1, 1),
  [shift_type_id] int NOT NULL,
  [shift_date] date NOT NULL,
  [staff_id] int NOT NULL,
  [task_id] int NOT NULL
)
GO

CREATE TABLE [buildings] (
  [building_id] int PRIMARY KEY IDENTITY(1, 1),
  [building_name] varchar(255) UNIQUE NOT NULL,
  [address] varchar(255) UNIQUE NOT NULL
)
GO

CREATE TABLE [visitor_parking_cards] (
  [visitor_parking_card_id] int PRIMARY KEY IDENTITY(1, 1),
  [is_active] bit NOT NULL DEFAULT (1)
)
GO

CREATE TABLE [lost_visitor_parking_cards] (
  [lost_visitor_parking_card_id] int PRIMARY KEY IDENTITY(1, 1),
  [parking_session_id] int UNIQUE NOT NULL
)
GO

CREATE TABLE [payments] (
  [payment_id] int PRIMARY KEY IDENTITY(1, 1),
  [registration_id] int NOT NULL,
  [extension_time] date NOT NULL,
  [payment_state] bit NOT NULL DEFAULT (0),
  [service_type_id] int NOT NULL
)
GO

DROP TABLE [type_service]
GO
CREATE TABLE [type_service] (
  [type_service_id] int PRIMARY KEY IDENTITY(1, 1),
  [service_fee_id] int NOT NULL,
  [month_unit] int NOT NULL,
  [service_name] varchar(255) UNIQUE NOT NULL,
  [decision_date] date not null,
  [payment_coefficient] int NOT NULL
)
GO

CREATE TABLE [time_frames] (
  [time_frame_id] int PRIMARY KEY IDENTITY(1, 1),
  [decision_date] date NOT NULL,
  [time_start] time NOT NULL,
  [time_end] time NOT NULL,
  [is_active] bit NOT NULL DEFAULT (1)
)
GO

CREATE TABLE [session_fees] (
  [time_frame_id] int NOT NULL,
  [vehicle_type_id] int NOT NULL,
  [decision_date] date NOT NULL,
  [session_fee_id] int PRIMARY KEY IDENTITY(1, 1),
  [amount] int NOT NULL,
  [is_active] bit NOT NULL DEFAULT (1)
)
GO

CREATE TABLE [resident_cards_registration] (
  [pk_resident_card] VARCHAR(10),
  [registration_id] int,
  PRIMARY KEY ([pk_resident_card], [registration_id])
)
GO

ALTER TABLE [resident_cards_registration] ADD FOREIGN KEY ([pk_resident_card]) REFERENCES [resident_cards] ([pk_resident_card])
GO

ALTER TABLE [resident_cards_registration] ADD FOREIGN KEY ([registration_id]) REFERENCES [registration] ([registration_id])
GO

ALTER TABLE [lost_resident_cards] ADD FOREIGN KEY ([pk_resident_card]) REFERENCES [resident_cards] ([pk_resident_card])
GO

ALTER TABLE [payments] ADD FOREIGN KEY ([service_type_id]) REFERENCES [type_service] ([type_service_id])
GO

ALTER TABLE [session_fees] ADD FOREIGN KEY ([time_frame_id]) REFERENCES [time_frames] ([time_frame_id])
GO

ALTER TABLE [supervisors] ADD FOREIGN KEY ([staff_id]) REFERENCES [staff] ([staff_id])
GO

ALTER TABLE [service_fee] ADD FOREIGN KEY ([vehicle_type_id]) REFERENCES [vehicle_types] ([vehicle_type_id])
GO

ALTER TABLE [session_fees] ADD FOREIGN KEY ([vehicle_type_id]) REFERENCES [vehicle_types] ([vehicle_type_id])
GO

ALTER TABLE [type_service] ADD FOREIGN KEY ([service_fee_id]) REFERENCES [service_fee] ([service_fee_id])
GO

ALTER TABLE [payments] ADD FOREIGN KEY ([registration_id]) REFERENCES [registration] ([registration_id])
GO

ALTER TABLE [accounts] ADD FOREIGN KEY ([staff_id]) REFERENCES [staff] ([staff_id])
GO

ALTER TABLE [shift_works] ADD FOREIGN KEY ([task_id]) REFERENCES [tasks] ([task_id])
GO

ALTER TABLE [shift_works] ADD FOREIGN KEY ([shift_type_id]) REFERENCES [shift_types] ([shift_type_id])
GO

ALTER TABLE [shift_works] ADD FOREIGN KEY ([building_id]) REFERENCES [buildings] ([building_id])
GO

ALTER TABLE [parking_sessions] ADD FOREIGN KEY ([check_out_shift_id]) REFERENCES [shift_works] ([shift_work_id])
GO

ALTER TABLE [parking_sessions] ADD FOREIGN KEY ([check_in_shift_id]) REFERENCES [shift_works] ([shift_work_id])
GO

ALTER TABLE [shift_works] ADD FOREIGN KEY ([staff_id]) REFERENCES [staff] ([staff_id])
GO

ALTER TABLE [customers] ADD FOREIGN KEY ([building_id]) REFERENCES [buildings] ([building_id])
GO

-- ALTER TABLE [parking_sessions] ADD FOREIGN KEY ([card_id]) REFERENCES [visitor_parking_cards] ([visitor_parking_card_id])
-- GO

ALTER TABLE [lost_visitor_parking_cards] ADD FOREIGN KEY ([parking_session_id]) REFERENCES [parking_sessions] ([parking_session_id])
GO

-- ALTER TABLE [parking_sessions] ADD FOREIGN KEY ([card_id]) REFERENCES [resident_cards] ([pk_resident_card])
-- GO

ALTER TABLE [resident_cards] ADD FOREIGN KEY ([customer_id]) REFERENCES [customers] ([customer_id])
GO

ALTER TABLE [lost_resident_cards] ADD FOREIGN KEY ([parking_session_id]) REFERENCES [parking_sessions] ([parking_session_id])
GO

ALTER TABLE [parking_sessions] ADD FOREIGN KEY ([vehicle_id]) REFERENCES [vehicles] ([vehicle_id])
GO

ALTER TABLE [registration] ADD FOREIGN KEY ([vehicle_id]) REFERENCES [vehicles] ([vehicle_id])
GO

ALTER TABLE [vehicles] ADD FOREIGN KEY ([vehicle_type_id]) REFERENCES [vehicle_types] ([vehicle_type_id])
GO

ALTER TABLE [supervisors] ADD FOREIGN KEY ([manager_id]) REFERENCES [managers] ([staff_id])
GO

ALTER TABLE [managers] ADD FOREIGN KEY ([staff_id]) REFERENCES [staff] ([staff_id])
GO

ALTER TABLE [staff] ADD FOREIGN KEY ([role_id]) REFERENCES [roles] ([role_id])
GO

ALTER TABLE [roles_rights] ADD FOREIGN KEY ([role_id]) REFERENCES [roles] ([role_id])
GO

ALTER TABLE [roles_rights] ADD FOREIGN KEY ([right_id]) REFERENCES [rights] ([right_id])
GO

ALTER TABLE [registration] ADD FOREIGN KEY ([customer_id]) REFERENCES [customers] ([customer_id])
GO

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