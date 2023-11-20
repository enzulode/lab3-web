const canvas = new Canvas('area')

$(document).ready(() => {
	canvas.drawEmptyArea()
	canvas.onClicked(
		(success) => {
			clearErrors()

			addPoint([
				{ name: "x", value: success.x.toString() },
				{ name: "y", value: success.y.toString() },
				{ name: "r", value: success.r.toString() }
			])
		},
		(error) => {
			applyErrorMessage(error)
		}
	)
})