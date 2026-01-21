const content = document.getElementById("content");

// ============ DOCTORS ============
function loadDoctors() {
  fetch("/api/admin/doctors")
    .then(r => r.json())
    .then(data => {
      content.innerHTML = "<h3>Doctors</h3>";
      content.innerHTML += `<button onclick="addDoctor()">Add Doctor</button><br><br>`;
      data.forEach(d => {
        content.innerHTML += `
          <div>
            ${d.id} - ${d.name} (${d.department})
            <button onclick="editDoctor(${d.id})">Edit</button>
            <button onclick="deleteDoctor(${d.id})">Delete</button>
          </div>`;
      });
    });
}

function addDoctor() {
  const name = prompt("Doctor name");
  const department = prompt("Department");
  fetch("/api/admin/doctors", {
    method: "POST",
    headers: {"Content-Type":"application/json"},
    body: JSON.stringify({name, department})
  }).then(loadDoctors);
}

function editDoctor(id) {
  const name = prompt("New name");
  const department = prompt("New department");
  fetch(`/api/admin/doctors/${id}`, {
    method: "PUT",
    headers: {"Content-Type":"application/json"},
    body: JSON.stringify({name, department})
  }).then(loadDoctors);
}

function deleteDoctor(id) {
  fetch(`/api/admin/doctors/${id}`, { method: "DELETE" })
    .then(loadDoctors);
}

// ============ PATIENTS ============
function loadPatients() {
  fetch("/api/admin/patients")
    .then(r => r.json())
    .then(data => {
      content.innerHTML = "<h3>Patients</h3>";
      data.forEach(p => {
        content.innerHTML += `
          <div>
            ${p.id} - ${p.name} (${p.age}, ${p.gender}) ${p.phone}
            <button onclick="editPatient(${p.id})">Edit</button>
            <button onclick="deletePatient(${p.id})">Delete</button>
          </div>`;
      });
    });
}

function editPatient(id) {
  const name = prompt("Name");
  const age = prompt("Age");
  const gender = prompt("Gender");
  const phone = prompt("Phone");

  fetch(`/api/admin/patients/${id}`, {
    method: "PUT",
    headers: {"Content-Type":"application/json"},
    body: JSON.stringify({name, age, gender, phone})
  }).then(loadPatients);
}

function deletePatient(id) {
  fetch(`/api/admin/patients/${id}`, { method: "DELETE" })
    .then(loadPatients);
}

// ============ APPOINTMENTS ============
function loadAppointments() {
  fetch("/api/admin/appointments")
    .then(r => r.json())
    .then(data => {
      content.innerHTML = "<h3>Appointments</h3>";
      data.forEach(a => {
        content.innerHTML += `
          <div>
            ${a.id} | ${a.patient.name} | ${a.doctor.name} | ${a.appointmentDate} | ${a.status}
            <button onclick="editAppointment(${a.id})">Edit</button>
            <button onclick="deleteAppointment(${a.id})">Delete</button>
          </div>`;
      });
    });
}

function editAppointment(id) {
  const date = prompt("New date YYYY-MM-DD");
  const status = prompt("Status");
  fetch(`/api/admin/appointments/${id}`, {
    method: "PUT",
    headers: {"Content-Type":"application/json"},
    body: JSON.stringify({appointmentDate: date, status})
  }).then(loadAppointments);
}

function deleteAppointment(id) {
  fetch(`/api/admin/appointments/${id}`, { method: "DELETE" })
    .then(loadAppointments);
}

function logout() {
  window.location.href = "/login.html";
}
