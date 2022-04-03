import Swal from "sweetalert2/dist/sweetalert2.js";

export const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  timer: 5000,
  timerProgressBar: true,
  showConfirmButton: false
});
