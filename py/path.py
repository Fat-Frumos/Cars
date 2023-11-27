import re

file_path = "gift.sql"

with open(file_path, "r") as f:
    text = f.read()

lines = text.split("\n")

modified_lines = []
number = 1

for line in lines:

    match = re.search(r"photo_1", line)
    if match:
        if number > 648:
            number =1

        modified_line = re.sub(rf"photo_1", f"photo_{number}", line)
        number = number + 1

        modified_lines.append(modified_line)
    else:

        modified_lines.append(line)

modified_text = "\n".join(modified_lines)

with open(file_path, "w") as f:
    f.write(modified_text)
