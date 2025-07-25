create or replace function insert_history()
returns trigger as $$
declare
    table_name text;
    history_table_name text;
    insert_history_statement text;
begin
    table_name := tg_table_name;
    history_table_name := table_name || '_history';
    insert_history_statement := format(
        'insert into %I.%I select * from %I.%I where id = $1;',
        tg_table_schema,
        history_table_name,
        tg_table_schema,
        table_name
    );
    execute insert_history_statement using new.id;
    return new;
end;
$$ language plpgsql;