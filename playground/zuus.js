var roster = {
  "joe": {
    name: "joe",
    employmentPeriods: [{
      begin: (new Date(1995, 1, 1)).getTime(),
      end: (new Date(2100, 1, 1)).getTime()
    }, {
      begin: (new Date(1992, 1, 1)).getTime(),
      end: (new Date(1993, 1, 1)).getTime()
    }]
  },
  "schmoe": {
    name: "schmoe",
    employmentPeriods: [{
      begin: (new Date(2017, 1, 1)).getTime(),
      end: (new Date(3000, 1, 1)).getTime()
    }, {
      begin: (new Date(1992, 1, 1)).getTime(),
      end: (new Date(1993, 1, 1)).getTime()
    }]
  }
}

function getEmployedPersons(employees) {
  var today = (new Date()).getTime()
  var employedPersons = []

  for (var employee in employees) {
    var employmentPeriods = employees[employee]["employmentPeriods"]

    employmentPeriods.forEach(function(period) {

      if (period.begin <= today && period.end >= today) {

        employedPersons.push(employees[employee].name)

      }
    })
  }
  return employedPersons
}

var employedPersons = getEmployedPersons(roster)
