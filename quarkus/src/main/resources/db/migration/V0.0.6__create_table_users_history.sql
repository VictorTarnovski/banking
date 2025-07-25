create table users_history (
  id uuid, 
  document varchar(50), 
  email varchar(50), 
  full_name varchar(100), 
  password_salt varchar(255), 
  password_value varchar(255), 
  created_at timestamp with time zone, 
  updated_at timestamp with time zone
);
