const baseUrl = "";

// ===== GLOBAL STATE =====
let selectedPatientId = null;
let selectedDoctorId = null;

// ---------------- PATIENTS ----------------

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
    }).then(loadPatients);
}

function editPatient(p) {
    selectedPatientId = p.id;
    pName.value = p.name;
    pAge.value = p.age;
    pGender.value = p.gender;
    pPhone.value = p.phone;
}

function updatePatient() {
    fetch(`${baseUrl}/api/patients/${selectedPatientId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            name: pName.value,
            age: pAge.value,
            gender: pGender.value,
            phone: pPhone.value
        })
    }).then(() => {
        selectedPatientId = null;
        loadPatients();
    });
}

function deletePatient(id) {
    fetch(`${baseUrl}/api/patients/${id}`, { method: "DELETE" })
        .then(loadPatients);
}

function loadPatients() {
    fetch(`${baseUrl}/api/patients`)
        .then(res => res.json())
        .then(data => {
            patientList.innerHTML = "";
            data.forEach(p => {
                patientList.innerHTML += `
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.name}</td>
                        <td>${p.age}</td>
                        <td>${p.gender}</td>
                        <td>${p.phone}</td>
                        <td>
                            <button onclick='editPatient(${JSON.stringify(p)})'>Edit</button>
                            <button onclick='deletePatient(${p.id})'>Delete</button>
                        </td>
                    </tr>`;
            });
        });
}

// ---------------- DOCTORS ----------------

function addDoctor() {
    fetch(`${baseUrl}/api/doctors`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            name: dName.value,
            department: dSpec.value
        })
    }).then(loadDoctors);
}

function editDoctor(d) {
    selectedDoctorId = d.id;
    dName.value = d.name;
    dSpec.value = d.department;
}

function updateDoctor() {
    fetch(`${baseUrl}/api/doctors/${selectedDoctorId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            name: dName.value,
            department: dSpec.value
        })
    }).then(() => {
        selectedDoctorId = null;
        loadDoctors();
    });
}

function deleteDoctor(id) {
    fetch(`${baseUrl}/api/doctors/${id}`, { method: "DELETE" })
        .then(loadDoctors);
}

function loadDoctors() {
    fetch(`${baseUrl}/api/doctors`)
        .then(res => res.json())
        .then(data => {
            doctorList.innerHTML = "";
            data.forEach(d => {
                doctorList.innerHTML += `
                    <tr>
                        <td>${d.id}</td>
                        <td>${d.name}</td>
                        <td>${d.department}</td>
                        <td>
                            <button onclick='editDoctor(${JSON.stringify(d)})'>Edit</button>
                            <button onclick='deleteDoctor(${d.id})'>Delete</button>
                        </td>
                    </tr>`;
            });
        });
}

// ---------------- APPOINTMENTS ----------------

function bookAppointment() {
    fetch(`${baseUrl}/api/appointments`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            patientId: aPatientId.value,
            doctorId: aDoctorId.value,
            appointmentDate: aDate.value
        })
    })
    .then(res => res.json())
    .then(loadAppointments)
    .catch(err => alert(err.message));
}

function loadAppointments() {
    fetch(`${baseUrl}/api/appointments`)
        .then(res => res.json())
        .then(data => {
            appointmentList.innerHTML = "";
            data.forEach(a => {
                appointmentList.innerHTML += `
                    <tr>
                        <td>${a.appointmentId}</td>
                        <td>${a.patientName}</td>
                        <td>${a.doctorName}</td>
                        <td>${a.appointmentDate}</td>
                        <td>${a.status}</td>
                    </tr>`;
            });
        });
}

// INITIAL LOAD
loadPatients();
loadDoctors();
loadAppointments();
