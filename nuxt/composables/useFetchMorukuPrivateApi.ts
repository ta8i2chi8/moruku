import { UseFetchOptions } from "nuxt/app"
import { KeysOf } from "nuxt/dist/app/composables/asyncData"

export const useFetchMorukuPrivateApi = async (request: any, opts?: UseFetchOptions<any, any, KeysOf<any>, null, any, "get" | "post" | "put" | "delete"> | undefined) => {
  const config = useRuntimeConfig();

  const { user } = useAuth();
  if (!user.value) {
    await navigateTo('/signin');
    return;
  }
  const token = await user.value.getIdToken();

  return await useFetch(request, { 
    baseURL: config.public.BASE_URL, 
    headers: {
      authorization: `Bearer ${token}`,
    },
    ...opts 
  });
}