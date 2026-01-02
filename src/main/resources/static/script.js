const baseUrl = "";

// ---------------- PATIENTS ----------------
function addPatient() {
    const patient = {
        name: document.getElementById("pName").value,
        age: document.getElementById("pAge").value,
        gender: document.getElementById("pGender").value,
        phone: document.getElementById("pPhone").value
    };

    fetch(`${baseUrl}/api/patients`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(patient)
    })
    .then(res => res.json())
    .then(() => loadPatients());
}

function loadPatients() {
    fetch(`${baseUrl}/api/patients`)
        .then(res => res.json())
        .then(data => {
            const list = document.getElementById("patientList");
            list.innerHTML = "";
            data.forEach(p => {
                const li = document.createElement("li");
                li.textContent = `ID: ${p.id} | ${p.name} (${p.age})`;
                list.appendChild(li);
            });
        });
}

// ---------------- DOCTORS ----------------
function addDoctor() {
    const doctor = {
        name: document.getElementById("dName").value,
        specialization: document.getElementById("dSpec").value
    };

    fetch(`${baseUrl}/api/doctors`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(doctor)
    })
    .then(res => res.json())
    .then(() => loadDoctors());
}

function loadDoctors() {
    fetch(`${baseUrl}/api/doctors`)
        .then(res => res.json())
        .then(data => {
            const list = document.getElementById("doctorList");
            list.innerHTML = "";
            data.forEach(d => {
                const li = document.createElement("li");
                li.textContent = `ID: ${d.id} | ${d.name} (${d.specialization})`;
                list.appendChild(li);
            });
        });
}

// ---------------- APPOINTMENTS ----------------
function bookAppointment() {
    const appointment = {
        patientId: document.getElementById("aPatientId").value,
        doctorId: document.getElementById("aDoctorId").value,
        appointmentDate: document.getElementById("aDate").value
    };

    fetch(`${baseUrl}/api/appointments`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(appointment)
    })
    .then(res => res.json())
    .then(() => loadAppointments());
}

function loadAppointments() {
    fetch(`${baseUrl}/api/appointments`)
        .then(res => res.json())
        .then(data => {
            const list = document.getElementById("appointmentList");
            list.innerHTML = "";
            data.forEach(a => {
                const li = document.createElement("li");
                li.textContent =
                    `Appointment ${a.appointmentId}: ${a.patientName} → ${a.doctorName} on ${a.appointmentDate}`;
                list.appendChild(li);
            });
        });
}

// Load everything on page load
loadPatients();
loadDoctors();
loadAppointments();
