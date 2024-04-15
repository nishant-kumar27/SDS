/**
 * 
 */
var noOfRecords = 0;
// This method is used to filter the data based on the date ranges
function rangeFilter(dateId, tbodyId, dateRange) {
	var dateColoumn = $('#' + dateId).index();
	var tbodyId = "#" + tbodyId + " tr";
	var currentDateRange = dateRange;
	var currentDate, fromDate, toDate;
	if (currentDateRange == "week" || currentDateRange == "week1") {
		try {
			if (currentDateRange == "week1") {
				document.getElementById("Current_Date_Range1").innerHTML = "- Current Week";
			} else {
				document.getElementById("Current_Date_Range").innerHTML = "- Current Week";
			}
		} catch (err) {
			console.log(err.message);
		}
		currentDate = new Date();
		fromDate = moment().startOf('week').toDate().getTime();
		toDate = currentDate.getTime();
	} else if (currentDateRange == "month" || currentDateRange == "month1") {
		try {
			if (currentDateRange == "month1") {
				document.getElementById("Current_Date_Range1").innerHTML = "- Current Month";
			} else {
				document.getElementById("Current_Date_Range").innerHTML = "- Current Month";
			}
		} catch (err) {
			console.log(err.message);
		}
		currentDate = new Date();
		fromDate = moment().startOf('month').toDate().getTime();
		// var endDayOfWeek = moment().endOf('month').toDate();
		toDate = currentDate.getTime();
	} else if (currentDateRange == "quarter" || currentDateRange == "quarter1") {
		try {
			if (currentDateRange == "quarter1") {
				document.getElementById("Current_Date_Range1").innerHTML = "- Current Quarter";
			} else {
				document.getElementById("Current_Date_Range").innerHTML = "- Current Quarter";
			}
		} catch (err) {
			console.log(err.message);
		}
		currentDate = new Date();
		fromDate = moment().startOf('quarter').toDate().getTime();
		// var endDayOfWeek = moment().endOf('year').toDate();
		toDate = currentDate.getTime();
	} else if (currentDateRange == "year" || currentDateRange == "year1") {
		try {
			if (currentDateRange == "year1") {
				document.getElementById("Current_Date_Range1").innerHTML = "- Current Year";
			} else {
				document.getElementById("Current_Date_Range").innerHTML = "- Current Year";
			}
		} catch (err) {
			console.log(err.message);
		}
		currentDate = new Date();
		fromDate = moment().startOf('year').toDate().getTime();
		// var endDayOfWeek = moment().endOf('year').toDate();
		toDate = currentDate.getTime();
	} else if (currentDateRange == "none" || currentDateRange == "none1") {
		try {
			if (currentDateRange == "none1") {
				document.getElementById("Current_Date_Range1").innerHTML = "- None";
			} else {
				document.getElementById("Current_Date_Range").innerHTML = "- None";
			}
		} catch (err) {
			console.log(err.message);
		}
		currentDate = new Date();
		toDate = currentDate.getTime();
	}
	$(tbodyId).each(
			function() {
				var row = $(this);
				var date = row.find("td").eq(dateColoumn).html();
				//var claimDate = new Date(date).getTime();  //This date parsing is working for Chrome bunt not for Mozilla
				var months = {
					jan : 0,
					feb : 1,
					mar : 2,
					apr : 3,
					may : 4,
					jun : 5,
					jul : 6,
					aug : 7,
					sep : 8,
					oct : 9,
					nov : 10,
					dec : 11
				};
				date = date.split('-');
				var claimDate = new Date(date[2],
						months[date[1].toLowerCase()], date[0]).getTime();
				var show = true;
				if (currentDateRange == "none" || currentDateRange == "none1") {
					if (claimDate <= toDate) {
						show = true;
						noOfRecords++;
					} else {
						show = false;
					}
				} else {
					if (claimDate <= toDate && claimDate >= fromDate) {
						show = true;
						noOfRecords++;
					} else {
						show = false;
					}
				}
				if (show)
					row.show();
				else
					row.hide();
			})
	var inner = $('#noOfOrd').html(noOfRecords);
	noOfRecords = 0;
}
