export default function authHeader() {
  let user = JSON.parse(localStorage.getItem(process.env.VUE_APP_LOCAL_STORAGE_AUTH_KEY));
  if (user && user.token)
    return { Authorization: `Bearer ${user.token}` };

  return {};
}
