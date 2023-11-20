function printTime() {
	let date = new Date();
	$('#clock').text(date.toLocaleString())
}

$(document).ready(() => {
	printTime()
	setInterval(printTime, 7000)
})