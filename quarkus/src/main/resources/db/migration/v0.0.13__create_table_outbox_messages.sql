create table outbox_messages(
  id uuid not null default uuid_generate_v7(), 
  type varchar(255) not null, 
  payload varchar(255) not null, 
  processed_at timestamp with time zone, 
  created_at timestamp with time zone not null default (current_timestamp at time zone 'UTC'),
  primary key (id)
);