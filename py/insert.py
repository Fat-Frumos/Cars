import re

file_path = "new.sql"
h2_file_path = "h2.txt"

with open(h2_file_path, "r") as f:
    replacement = f.read()
with open(file_path, "r") as f:
    text = f.read()
lines = text.split("\n")
replacements= replacement.split("\n")
pattern = r"\(([^,]+),\s*NOW\(\),\s*([^,]+),\s*([^,]+),\s*NOW\(\),\s'(.+?)',(\s\d\d\.\d\d+.)"

modified_lines = []
count = 0
for line in lines:
    new_line = re.sub(pattern, rf"(\1, NOW(), \2, \3, NOW(), '{replacements[count]}', \5", line)
    modified_lines.append(new_line)
    count += 1

modified_text = "\n".join(modified_lines)
with open("gift.sql", "w") as f:
    f.write(modified_text)
