create table transactions (
  id uuid not null default uuid_generate_v7(), 
  amount bigint not null, 
  currency varchar(3) not null, 
  from_wallet_id uuid, 
  to_wallet_id uuid, 
  type smallint check (type between 0 and 2), 
  occurred_at  timestamp with time zone not null default (current_timestamp at time zone 'UTC'),
  primary key (id),
  foreign key (from_wallet_id) references wallets(id),
  foreign key (to_wallet_id) references wallets(id)
);
