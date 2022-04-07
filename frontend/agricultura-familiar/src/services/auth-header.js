export default function authHeader() {
  let user = JSON.parse(localStorage.getItem(process.env.LOCAL_STORAGE_AUTH_KEY));
  if (user && user.accessToken)
    return { Authorization: 'Bearer ' + user.accessToken };
  
  return {};
}
