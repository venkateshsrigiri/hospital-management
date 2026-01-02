const baseUrl = window.location.origin;

// PATIENTS
function addPatient() {
    fetch(`${baseUrl}/api/patients`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            name: pName.value,
            age: pAge.value,
            gender: pGender.value,
            phone: pPhone.value
        })
    }).then(() => loadPatients());
}

function loadPatients() {
    fetch(`${baseUrl}/api/patients`)
        .then(r => r.json())
        .then(d => patientList.innerHTML =
            d.map(p => `<li>${p.id} - ${p.name}</li>`).join(""));
}

// DOCTORS
function addDoctor() {
    fetch(`${baseUrl}/api/doctors`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            name: dName.value,
            specialization: dSpec.value
        })
    }).then(() => loadDoctors());
}

function loadDoctors() {
    fetch(`${baseUrl}/api/doctors`)
        .then(r => r.json())
        .then(d => doctorList.innerHTML =
            d.map(doc => `<li>${doc.id} - ${doc.name}</li>`).join(""));
}

// APPOINTMENTS
function bookAppointment() {
    fetch(`${baseUrl}/api/appointments`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            patientId: aPatientId.value,
            doctorId: aDoctorId.value,
            appointmentDate: aDate.value
        })
    }).then(() => loadAppointments());
}

function loadAppointments() {
    fetch(`${baseUrl}/api/appointments`)
        .then(r => r.json())
        .then(d => appointmentList.innerHTML =
            d.map(a => `<li>${a.patientName} → ${a.doctorName}</li>`).join(""));
}

loadPatients();
loadDoctors();
loadAppointments();
