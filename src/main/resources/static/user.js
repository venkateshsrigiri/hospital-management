// Load doctors
fetch("/api/doctors")
  .then(res => res.json())
  .then(data => {
    doctorSelect.innerHTML = `<option value="">Select Doctor</option>`;
    data.forEach(d => {
      doctorSelect.innerHTML += `
        <option value="${d.id}">
          ${d.name} (${d.department})
        </option>`;
    });
  });

// Book appointment
function bookAppointment() {
  if (!pName.value || !doctorSelect.value || !apptDate.value) {
    alert("Please fill all required fields");
    return;
  }

  fetch("/api/appointments", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      name: pName.value,
      age: pAge.value,
      gender: pGender.value,
      phone: pPhone.value,
      doctorId: doctorSelect.value,
      appointmentDate: apptDate.value
    })
  })
  .then(res => res.json())
  .then(() => {
    alert("Appointment booked successfully");
    loadAppointments();
  });
}

// Load appointments
function loadAppointments() {
  fetch("/api/appointments/my")
    .then(res => res.json())
    .then(data => {
      appointmentList.innerHTML = "";
      data.forEach(a => {
        appointmentList.innerHTML += `
          <tr>
            <td>${a.doctorName}</td>
            <td>${a.department}</td>
            <td>${a.appointmentDate}</td>
            <td>${a.status}</td>
          </tr>`;
      });
    });
}

loadAppointments();
