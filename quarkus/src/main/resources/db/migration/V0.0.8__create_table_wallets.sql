create table wallets (
  id uuid not null default uuid_generate_v7(), 
  initial_balance_amount bigint not null, 
  initial_balance_currency varchar(3) not null, 
  balance_amount bigint not null, 
  balance_currency varchar(3) not null, 
  user_id uuid not null, 
  created_at timestamp with time zone not null default (current_timestamp at time zone 'UTC'),
  updated_at timestamp with time zone not null default (current_timestamp at time zone 'UTC'),
  primary key (id),
  foreign key (user_id) references users(id)
);