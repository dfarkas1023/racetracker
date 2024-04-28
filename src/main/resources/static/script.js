document.addEventListener('DOMContentLoaded', function() {
    const threshold = 15;
    window.onscroll = function() {
        const navContainer = document.querySelector(".nav-container");
        const sticky = navContainer.offsetTop;

        // console.log("Scroll position:", window.pageYOffset);
        // console.log("Has sticky class:", navContainer.classList.contains("sticky"));


        if (window.pageYOffset >= sticky + threshold) {
            navContainer.classList.add("sticky");
        } else {
            navContainer.classList.remove("sticky");
        }
    };


    const newRunnerForm = document.getElementById('new-runner-form');
    if (newRunnerForm) {
        const nameInput = document.getElementById('runnerName');
        const ageInput = document.getElementById('runnerAge');
        const genderSelect = document.getElementById('gender');
        function validateForm() {
            let isValid = true;

            if (nameInput.value.trim() === '') {
                alert('Name cannot be empty!');
                isValid = false;
            } else if (nameInput.value.length < 2 || nameInput.value.length > 28) {
                alert('Name must be between 2 and 28 characters!');
                isValid = false;
            }

            if (ageInput.value.trim() === '' || isNaN(ageInput.value) || ageInput.value < 12 || ageInput.value > 120) {
                alert('Age must be a number between 12 and 120!');
                isValid = false;
            }

            if (genderSelect.value === '') {
                alert('Please select a gender!');
                isValid = false;
            }

            return isValid;
        }


        newRunnerForm.addEventListener('submit', (event) => {
            console.log(validateForm())
            if (!validateForm()) {
                event.preventDefault();
                return;
            }
            console.log("submitted")
            newRunnerForm.submit();
            const runnerName = nameInput.value.toString().trim();
            window.alert(`Runner "${runnerName}" successfully added!`)
        })
    }

    const newRaceForm = document.getElementById("new-race-form");
    if (newRaceForm) {
        const raceNameInput = document.getElementById('raceName');
        const raceLengthInput = document.getElementById('raceLength');

        function validateRaceFormRaces() {
            let isValid = true;

            if (raceNameInput.value.trim() === '') {
                alert('Race name cannot be empty!');
                isValid = false;
            } else if (raceNameInput.value.length < 6 || raceNameInput.value.length > 100) {
                alert('Race name must be between 6 and 100 characters!');
                isValid = false;
            }

            if (raceLengthInput.value.trim() === '' || isNaN(raceLengthInput.value) || raceLengthInput.value <= 0 || raceLengthInput.value > 100) {
                alert('Race length must be a positive number between 1 and 100 km!');
                isValid = false;
            }

            return isValid;
        }

        newRaceForm.addEventListener('submit', (event) => {
            console.log(validateRaceFormRaces());
            if (!validateRaceFormRaces()) {
                event.preventDefault();
                return;
            }
            console.log('submitted');
            const raceName = raceNameInput.value.toString().trim();
            window.alert(`Race "${raceName}" successfully added!`);
        });
    }


    const addRunnerFormList = document.getElementById('add-runner-form-list');
    if (addRunnerFormList) {
        const runnerSelectEl = document.getElementById('runnerSelection');
        const runnerNameList = document.getElementById('runnerNameList')
        function validateFormSelectRunner() {
            let isValid = true;
            if (runnerSelectEl.value === '') {
                isValid = false;
            }
            return isValid;
        }

        addRunnerFormList.addEventListener('submit', (event) => {
            console.log(validateFormSelectRunner());
            if (!validateFormSelectRunner()) {
                event.preventDefault();
                return;
            }

            const selectedRunnerId = runnerSelectEl.value;
            console.log('submitted');
            const runnerName = runnerNameList.innerText.toString().trim();
            window.alert(`Runner "${runnerName}" successfully added!`);
        })
    }


});


