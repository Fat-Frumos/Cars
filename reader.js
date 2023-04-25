window.onload = function() {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', 'pg236.txt', true);
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4 && xhr.status === 200) {
        const text = xhr.responseText;
        const paragraphs = text.split('\n');
        const contentDiv = document.getElementById('book-content');
        for (const paragraph of paragraphs) {
          const words = paragraph.split(' ');
          for (const word of words) {
            const span = document.createElement('span');
            span.textContent = word + ' ';
            contentDiv.appendChild(span);
          }
          const br = document.createElement('br');
          contentDiv.appendChild(br);
        }
      }
    };
    xhr.send();
};
