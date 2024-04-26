window.addEventListener("scroll", function() {
    const navContainer = document.querySelector(".nav-container");
    const sticky = navContainer.offsetTop;

    if (window.pageYOffset >= sticky) {
        navContainer.classList.add("sticky");
    } else {
        navContainer.classList.remove("sticky");
    }
});

document.getElementById("new-race-form").addEventListener("submit", function(event) {
    const raceName = document.getElementById("raceName").value.trim();
    const raceLength = document.getElementById("raceLength").value;

    if (raceName === "" || !/^[A-Za-z\s]+$/.test(raceName)) {
        alert("Race name can't be empty and must only contain letters and spaces");
        event.preventDefault();
        return false;
    }

    if (raceLength <= 0) {
        alert("Race length must be a positive number");
        event.preventDefault();
        return false;
    }
});