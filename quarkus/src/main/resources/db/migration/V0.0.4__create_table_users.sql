create table users (
  id uuid not null default uuid_generate_v7(), 
  document varchar(50) not null, 
  email varchar(50) not null, 
  full_name varchar(100) not null, 
  password_salt varchar(255) not null, 
  password_value varchar(255) not null, 
  created_at timestamp with time zone not null default (current_timestamp at time zone 'UTC'),
  updated_at timestamp with time zone not null default (current_timestamp at time zone 'UTC'),
  primary key (id)
);
