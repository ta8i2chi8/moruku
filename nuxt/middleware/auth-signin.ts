export default defineNuxtRouteMiddleware(async () => {
  if (!process.server) {
    const { isAuthed, checkAuthState } = useAuth();
    await checkAuthState();
    if (isAuthed.value) {
      return await navigateTo("/", { replace: true });
    }
  }
});