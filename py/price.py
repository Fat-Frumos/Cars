# Specify the paths to the SQL and text files
sql_file_path = 'gift.sql'

with open(sql_file_path, 'r') as f:
    sql_lines = f.readlines()

# Create a new list of modified SQL lines
modified_sql_lines = []
for sql_line in sql_lines:
    parts = sql_line.strip().split(',')
    if len(parts) >= 7:
        name = parts[-3].strip()
        price = 20 + (300 - 20) * hash(name) % 281  # Generate price between 20 and 300 based on hash of name
        modified_sql_line = f"{', '.join(parts[:-2])}, {price}, {parts[-2]}, {parts[-1]}\n"
        modified_sql_lines.append(modified_sql_line)
    else:
        modified_sql_lines.append(sql_line)

# Write the modified SQL lines back to the SQL file
with open(sql_file_path, 'w') as f:
    f.writelines(modified_sql_lines)
