import Swal from "sweetalert2/dist/sweetalert2.js";

export const SucessToaster = Swal.mixin({
  toast: true,
  position: "top-end",
  icon: 'success',
  timer: 2500,
  timerProgressBar: true,
  showConfirmButton: false
});

export const WarningToaster = Swal.mixin({
  toast: true,
  icon: 'warning',
  position: "top-end",
  timer: 2500,
  timerProgressBar: true,
  showConfirmButton: false
});

export const ErrorToaster = Swal.mixin({
  toast: true,
  position: "top-end",
  icon: 'error',
  timer: 2500,
  timerProgressBar: true,
  showConfirmButton: false
});

export const ErrorAlert = Swal.mixin({
  icon: 'error',
  title: 'Oops...'
});

export const ConfirmAlert = Swal.mixin({
  icon: 'question',
  showCancelButton: true,
  confirmButtonColor: '#28a745',
  cancelButtonColor: '#dc3545',
  cancelButtonText: 'Não',
  confirmButtonText: 'Sim'
});

export default { SucessToaster, WarningToaster, ErrorToaster, ErrorAlert, ConfirmAlert }
