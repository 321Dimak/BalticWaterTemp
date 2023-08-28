const fetchDataButton = document.getElementById('fetchDataButton');
const apiDataContainer = document.getElementById('apiDataContainer');
const fromDateInput = document.getElementById('fromDate');
const toDateInput = document.getElementById('toDate');
const stationButtons = document.querySelectorAll('.station-li');
const showTableButton = document.getElementById('showTableButton');
const stationsDropdown = document.getElementById('stationsDropdown');
const stationsList = document.querySelector('.stations-list');

stationsDropdown.addEventListener('click', () => {
    if (stationsList.style.display === 'block') {
        stationsList.style.display = 'none';
    }
    else {
        stationsList.style.display = 'block';
    }

});


stationsList.addEventListener('mouseleave', () => {
    stationsList.style.display = 'none';
});

stationButtons.forEach(button => {
    button.addEventListener('click', () => {
        const selectedStation = button.getAttribute('data-station');
        const selectedStationName = button.textContent;
        fetchApiData(selectedStation, selectedStationName);
        stationsList.style.display = 'none'; // Hide dropdown after selecting a station
    });
});

showTableButton.addEventListener('click', () => {
    apiDataContainer.style.display = 'block';
    showTableButton.style.display = 'none';
});




window.addEventListener('load', () => {
    const today = new Date();
    const year = today.getFullYear();
    const month = ('0' + (today.getMonth() + 1)).slice(-2);
    const day = ('0' + today.getDate()).slice(-2);
    const formattedToday = `${year}-${month}-${day}`;
    const formattedYesterday = `${year}-${month}-${(day-1)}`;


    fromDateInput.value = formattedYesterday;
    toDateInput.value = formattedToday;

});

function fetchApiData(selectedStation, selectedStationName) {

    const fromDate = fromDateInput.value;
    const toDate = toDateInput.value;
    const formattedFromDate = formatDate(fromDate, 'api');
    const formattedToDate = formatDate(toDate, 'api');

    fetch(`/fetch-api-data?station=${selectedStation}&fromDate=${formattedFromDate}&toDate=${formattedToDate}`)
        .then(response => response.json())
        .then(data => {
            data.result.records.sort((a, b) => new Date(a.DATETIME) - new Date(b.DATETIME));
            const dates = [];
            const temperatures = [];

            data.result.records.forEach(record => {
                dates.push(formatDate(record.DATETIME, 'display'));
                temperatures.push(parseFloat(record.VALUE));
            });
            const lastTemperature = temperatures[temperatures.length - 1];
            console.log(lastTemperature);
            displayApiData(data, selectedStationName);
            createTemperatureChart(dates, temperatures);
            const selectedStationElement = document.getElementById('selectedStation');
            selectedStationElement.textContent = `Selected Station: ${selectedStationName} (${lastTemperature}°C)` ;
            const dataContainer = document.getElementById('dataContainer');
            dataContainer.style.display = 'block';
        })
        .catch(error => {
            console.error('Error fetching API data:', error);
        });
}

let temperatureChart;
function createTemperatureChart(dates, temperatures) {
    if (temperatureChart) {
        temperatureChart.destroy();
    }
    const temperatureChartContext = document.getElementById('temperatureChart').getContext('2d');

    temperatureChart = new Chart(temperatureChartContext, {
        type: 'line',
        data: {
            labels: dates,
            datasets: [{
                label: 'Temperature (°C)',
                data: temperatures,
                borderColor: '#6a1b9a',
                fill: false
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                y: {
                    beginAtZero: false
                }
            }
        }
    });
    return temperatureChart;
}


function formatDate(dateString, format) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const day = ('0' + date.getDate()).slice(-2);
    const hours = ('0' + date.getHours()).slice(-2);
    const minutes = ('0' + date.getMinutes()).slice(-2);
    const seconds = ('0' + date.getSeconds()).slice(-2);

    if (format === 'api') {
        return `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;
    } else if (format === 'display') {
        return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    } else {
        return ''; // Invalid format
    }
}

function displayApiData(data, selectedStationName) {
    apiDataContainer.innerHTML = ''; // Clear previous content
    const table = document.createElement('table');
    table.classList.add('data-table');

    // Create the header row with all the column headers
    const headerRow = document.createElement('tr');
    const headers = ['Date and Time', 'Station ID', 'Station Name', 'Temperature (C)'];
    headers.forEach(headerText => {
        const headerCell = document.createElement('th');
        headerCell.textContent = headerText;
        headerRow.appendChild(headerCell);
    });
    table.appendChild(headerRow);

    // Iterate through the records and create rows
    data.result.records.forEach(record => {
        const row = document.createElement('tr');

        // Date and Time cell
        const datetimeCell = document.createElement('td');
        datetimeCell.textContent = formatDate(record.DATETIME, 'display');
        row.appendChild(datetimeCell);

        // Station ID cell
        const stationIdCell = document.createElement('td');
        stationIdCell.textContent = record.STATION_ID;
        row.appendChild(stationIdCell);

        // Station Name cell
        const stationNameCell = document.createElement('td');
        stationNameCell.textContent = selectedStationName;
        row.appendChild(stationNameCell);

        // Value cell
        const valueCell = document.createElement('td');
        valueCell.textContent = record.VALUE;
        row.appendChild(valueCell);

        table.appendChild(row);
        if (data.result.records.length === 0) {
            // Hide the data container when no data is available
            const dataContainer = document.getElementById('dataContainer');
            dataContainer.style.display = 'none';
        }
    });

    apiDataContainer.appendChild(table);
    apiDataContainer.style.display = 'none';
    showTableButton.style.display = 'block';
}
document.addEventListener("DOMContentLoaded", function () {
    const fav = document.querySelectorAll(".favorite-location");
    fav.forEach(item => {
        const nameButton = item.querySelector(".favspan");
        const idButton = item.querySelector(".favspana");

        const locationName = nameButton.textContent;
        const locationId = idButton.textContent;


        item.addEventListener("click", function () {

            fetchApiData(locationId, locationName);
        });
    });

});

function saveFavoriteLocation(locationId) {

    fetch(`/save-favorite-location?locationId=${locationId}`, {
        method: "POST"
    })
        .then(function(response) {
            if (response.ok) {
                console.log("Favorite location saved successfully.");
                console.log(response);
            } else {
                console.error("Failed to save favorite location.");
            }
        })
        .catch(function(error) {
            console.error("Error:", error);
        });

}



