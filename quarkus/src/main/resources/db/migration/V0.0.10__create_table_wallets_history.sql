create table wallets_history (
  id uuid, 
  initial_balance_amount bigint, 
  initial_balance_currency varchar(3), 
  balance_amount bigint, 
  balance_currency varchar(3), 
  user_id uuid, 
  created_at timestamp with time zone, 
  updated_at timestamp with time zone 
);