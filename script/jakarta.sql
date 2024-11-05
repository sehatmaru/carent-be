-- -------------------------------------------------------------
-- TablePlus 6.1.6(570)
--
-- https://tableplus.com/
--
-- Database: jakarta
-- Generation Time: 2024-11-04 11:08:16.2860 AM
-- -------------------------------------------------------------


DROP TABLE IF EXISTS "public"."t_bill";
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS t_bill_id_seq;
DROP TYPE IF EXISTS "public"."payment_type";
CREATE TYPE "public"."payment_type" AS ENUM ('BANK_TRANSFER', 'VIRTUAL_ACCOUNT', 'E_WALLET');
DROP TYPE IF EXISTS "public"."payment_status";
CREATE TYPE "public"."payment_status" AS ENUM ('WAITING', 'PAID', 'FAILED', 'CANCELED', 'REFUNDED', 'PARTIALLY_PAID');

-- Table Definition
CREATE TABLE "public"."t_bill" (
    "id" int4 NOT NULL DEFAULT nextval('t_bill_id_seq'::regclass),
    "customer_id" int4,
    "order_id" int4,
    "total_payment" int4,
    "total_paid" int4,
    "application_fee" float8,
    "payment_type" "public"."payment_type",
    "payment_status" "public"."payment_status",
    "invoice_number" varchar(255) NOT NULL DEFAULT ''::character varying,
    "created_date" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_date" timestamp DEFAULT CURRENT_TIMESTAMP,
    "verified_date" timestamp,
    "deleted_date" timestamp,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."t_booking";
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS t_booking_id_seq;
DROP TYPE IF EXISTS "public"."pickup_type";
CREATE TYPE "public"."pickup_type" AS ENUM ('SELF_PICKUP', 'DELIVERY');

-- Table Definition
CREATE TABLE "public"."t_booking" (
    "id" int4 NOT NULL DEFAULT nextval('t_booking_id_seq'::regclass),
    "order_id" int4,
    "bill_id" int4,
    "product_id" int4,
    "pickup_type" "public"."pickup_type",
    "delivery_address" varchar(255) NOT NULL DEFAULT ''::character varying,
    "return_address" varchar(255) NOT NULL DEFAULT ''::character varying,
    "customer_notes" text DEFAULT ''::text,
    "admin_notes" text DEFAULT ''::text,
    "rating" int4,
    "start_date" timestamp,
    "end_date" timestamp,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."t_company";
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS t_company_id_seq;

-- Table Definition
CREATE TABLE "public"."t_company" (
    "id" int4 NOT NULL DEFAULT nextval('t_company_id_seq'::regclass),
    "firm_name" varchar(255) NOT NULL DEFAULT ''::character varying,
    "alias_name" varchar(255) NOT NULL DEFAULT ''::character varying,
    "address" text DEFAULT ''::text,
    "mobile" varchar(50) DEFAULT ''::character varying,
    "rating" int4,
    "founding_date" timestamp,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."t_fee";
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS t_fee_id_seq;
DROP TYPE IF EXISTS "public"."fee_type";
CREATE TYPE "public"."fee_type" AS ENUM ('APPLICATION_FEE', 'SERVICE_FEE', 'LATE_FEE', 'DAMAGE_FEE');

-- Table Definition
CREATE TABLE "public"."t_fee" (
    "id" int4 NOT NULL DEFAULT nextval('t_fee_id_seq'::regclass),
    "percentage" float8,
    "price" int4,
    "type" "public"."fee_type",
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."t_order";
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS t_order_id_seq;
DROP TYPE IF EXISTS "public"."order_status";
CREATE TYPE "public"."order_status" AS ENUM ('WAITING_PAYMENT', 'WAITING_APPROVAL', 'IN_PROGRESS', 'COMPLETED', 'CANCELED');

-- Table Definition
CREATE TABLE "public"."t_order" (
    "id" int4 NOT NULL DEFAULT nextval('t_order_id_seq'::regclass),
    "customer_id" int4,
    "product_id" int4,
    "vehicle_id" int4,
    "status" "public"."order_status",
    "rating" int4,
    "created_date" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_date" timestamp DEFAULT CURRENT_TIMESTAMP,
    "deleted_date" timestamp,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."t_product";
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS t_product_id_seq;
DROP TYPE IF EXISTS "public"."transmission";
CREATE TYPE "public"."transmission" AS ENUM ('MATIC', 'MANUAL');
DROP TYPE IF EXISTS "public"."engine_type";
CREATE TYPE "public"."engine_type" AS ENUM ('GASOLINE', 'HYBRID', 'ELECTRIC', 'DIESEL');
DROP TYPE IF EXISTS "public"."vehicle_brand";
CREATE TYPE "public"."vehicle_brand" AS ENUM ('TOYOTA', 'HONDA', 'MAZDA', 'SUZUKI', 'YAMAHA', 'HYUNDAI', 'BYD', 'MITSUBISHI', 'DAIHATSU', 'NISSAN', 'OTHER');
DROP TYPE IF EXISTS "public"."product_status";
CREATE TYPE "public"."product_status" AS ENUM ('AVAILABLE', 'NOT_AVAILABLE');

-- Table Definition
CREATE TABLE "public"."t_product" (
    "id" int4 NOT NULL DEFAULT nextval('t_product_id_seq'::regclass),
    "company_id" int4,
    "name" varchar(255) NOT NULL DEFAULT ''::character varying,
    "price" int4,
    "quantity" int4,
    "available" int4,
    "province_id" int4,
    "province_name" varchar(255) NOT NULL DEFAULT ''::character varying,
    "regency_id" int4,
    "regency_name" varchar(255) NOT NULL DEFAULT ''::character varying,
    "district_id" int4,
    "district_name" varchar(255) NOT NULL DEFAULT ''::character varying,
    "deliverable" bool NOT NULL DEFAULT false,
    "transmission" "public"."transmission",
    "seat" int4,
    "engine_type" "public"."engine_type",
    "brand" "public"."vehicle_brand",
    "status" "public"."product_status",
    "created_date" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_date" timestamp DEFAULT CURRENT_TIMESTAMP,
    "deleted_date" timestamp,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."t_token";
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS t_token_id_seq;
DROP TYPE IF EXISTS "public"."token_type";
CREATE TYPE "public"."token_type" AS ENUM ('OTP', 'NON_OTP');

-- Table Definition
CREATE TABLE "public"."t_token" (
    "id" int4 NOT NULL DEFAULT nextval('t_token_id_seq'::regclass),
    "user_id" int4 NOT NULL,
    "code" varchar(255) NOT NULL DEFAULT ''::character varying,
    "created_date" timestamp DEFAULT CURRENT_TIMESTAMP,
    "expire_date" timestamp,
    "is_active" bool NOT NULL DEFAULT false,
    "type" "public"."token_type" NOT NULL DEFAULT 'NON_OTP'::token_type,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."t_user";
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS t_user_id_seq;
DROP TYPE IF EXISTS "public"."credential_type";
CREATE TYPE "public"."credential_type" AS ENUM ('KTP', 'DRIVER_LICENSE');
DROP TYPE IF EXISTS "public"."user_role";
CREATE TYPE "public"."user_role" AS ENUM ('ADMIN', 'TENANT_MANAGER', 'TENANT_ADMIN', 'CUSTOMER');

-- Table Definition
CREATE TABLE "public"."t_user" (
    "id" int4 NOT NULL DEFAULT nextval('t_user_id_seq'::regclass),
    "company_id" int4,
    "created_by" int4,
    "full_name" varchar(255) NOT NULL DEFAULT ''::character varying,
    "mobile" varchar(50) NOT NULL DEFAULT ''::character varying,
    "username" varchar(255) NOT NULL DEFAULT ''::character varying,
    "email" varchar(255) NOT NULL DEFAULT ''::character varying,
    "credential_no" varchar(255) NOT NULL DEFAULT ''::character varying,
    "credential_type" "public"."credential_type",
    "password" varchar(255) NOT NULL DEFAULT ''::character varying,
    "role" "public"."user_role" NOT NULL DEFAULT 'CUSTOMER'::user_role,
    "rating" int4,
    "created_date" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_date" timestamp DEFAULT CURRENT_TIMESTAMP,
    "verified_date" timestamp,
    "deleted_date" timestamp,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."t_vehicle";
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS t_vehicle_id_seq;

-- Table Definition
CREATE TABLE "public"."t_vehicle" (
    "id" int4 NOT NULL DEFAULT nextval('t_vehicle_id_seq'::regclass),
    "product_id" int4,
    "name" varchar(255) NOT NULL DEFAULT ''::character varying,
    "year" varchar(10) NOT NULL DEFAULT ''::character varying,
    "license_number" varchar(50) NOT NULL DEFAULT ''::character varying,
    "rating" int4,
    "created_by" int4,
    "created_date" timestamp DEFAULT CURRENT_TIMESTAMP,
    "updated_date" timestamp DEFAULT CURRENT_TIMESTAMP,
    "deleted_date" timestamp,
    PRIMARY KEY ("id")
);

