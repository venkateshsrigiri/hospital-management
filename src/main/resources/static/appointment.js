// ✅ get appointment id from URL
const params = new URLSearchParams(window.location.search);
const appointmentId = params.get("id");

if (!appointmentId) {
  alert("Appointment ID missing from URL");
  throw new Error("No appointment ID");
}

// ✅ load appointment details
fetch(`/api/appointments/${appointmentId}`)
  .then(res => {
    if (!res.ok) throw new Error("Failed to load appointment");
    return res.json();
  })
  .then(data => {
    document.getElementById("vName").value = data.patientName;
    document.getElementById("vAge").value = data.age;
    document.getElementById("vGender").value = data.gender;
    document.getElementById("vPhone").value = data.phone;

    document.getElementById("doctorInfo").innerText =
      `Dr ${data.doctorName} (${data.department})`;

    document.getElementById("apptDate").innerText =
      data.appointmentDate;
  })
  .catch(err => {
    alert("Could not load appointment");
    console.error(err);
  });

// ✅ update patient info
function updatePatient() {
  const payload = {
    name: document.getElementById("vName").value,
    age: document.getElementById("vAge").value,
    gender: document.getElementById("vGender").value,
    phone: document.getElementById("vPhone").value
  };

  fetch(`/api/appointments/${appointmentId}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload)
  })
    .then(res => {
      if (!res.ok) throw new Error("Update failed");
      alert("Update successful");
    })
    .catch(err => {
      alert("Update failed");
      console.error(err);
    });
}

function logout() {
  window.location.href = "index.html";
}
