fetch("http://localhost:8080/news")
.then(res => res.json())
.then(data => {

const container = document.getElementById("news")

data.forEach(article => {

const card = document.createElement("div")
card.className = "card"

card.innerHTML = `
<div class="title">${article.title}</div>
<div class="summary">${article.summary}</div>
<div class="source">${article.source}</div>
<a href="${article.url}" target="_blank">Read more</a>
`

container.appendChild(card)

})

})